package com.ecommerce.domain.wallet.application;

import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import com.ecommerce.domain.wallet.domain.Wallet;
import com.ecommerce.domain.wallet.domain.WalletRepository;
import com.ecommerce.domain.wallet.dto.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalListAccounts;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;

import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class WalletService {

    private static final String OX = "0x%s";
    private static final int HEX = 16;
    private String adminEthAddress = "0x2bd661bad97160c81eb0704ae29cb97bcbec6f8a";



    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final Web3j web3j;
    private final Admin admin;
    List<String> addressList;

    @Transactional
    public Wallet createWallet(final User request) throws CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        final ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        final String address = address(ecKeyPair);
        final String privateKey = privateKey(ecKeyPair);
        final Credentials credentials = Credentials.create(privateKey);
        final User user = userRepository.findByEmail(request.getEmail()).orElseThrow(IllegalArgumentException::new);
        final EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        final Wallet wallet = wallet(address, credentials, user, ethGetBalance.getBalance());
        return walletRepository.save(wallet);
    }

    public Wallet getWalletAddressByUser(final String email){
        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        return walletRepository.findByUser(user).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Wallet getBalance(final String address) throws IOException {
        final Web3j web3j = Web3j.build(new HttpService());
        final EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        final Wallet wallet = walletRepository.findByCredentialsAddress(address).orElseThrow(IllegalArgumentException::new);
        return wallet.changeBalance(ethGetBalance.getBalance());
    }

    private String address(final ECKeyPair ecKeyPair) throws CipherException {
        final String seed = UUID.randomUUID().toString();
        final WalletFile wallet = org.web3j.crypto.Wallet.createLight(seed, ecKeyPair);
        return String.format(OX, wallet.getAddress());
    }

    private String privateKey(final ECKeyPair ecKeyPair) {
        final BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
        return privateKeyInDec.toString(HEX);
    }

    private Wallet wallet(final String address, final Credentials credentials, final User user, final BigInteger balance) {
        return Wallet.builder()
                .credentials_address(credentials.getAddress())
                .wallet_address(address)
                .user(user)
                .balance(balance)
                .build();
    }

    //ToDo 트랜잭션 처리방법, 계정을 만들고 1회 한해서 이더리움을 받을 수 있도록 하기
    // user 테이블에 1회 충전이 되었는지 속성 필요
    // 1회 충전을 눌렀을 때 -> user테이블에서 검사하고 user.charge -> boolean 값으로 만들고 만약 fasle이면
    // 충전 가능
    public Transaction transactionFunction(String functionName, List<Type> inputParameters, List<TypeReference<?>> outputParameters, String amount) throws IOException{
        final Web3j web3j = Web3j.build(new HttpService());
        final Admin admin = Admin.build(new HttpService());
        PersonalListAccounts personalListAccounts = admin.personalListAccounts().send();
        addressList = personalListAccounts.getAccountIds();

        PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount(adminEthAddress,"1234").send();
        Boolean isUnlocked = personalUnlockAccount.accountUnlocked();

        //contract function 만들기
        Function function = new Function(functionName, inputParameters, outputParameters);

        // nonce 체크
        EthGetTransactionCount ethGetTransactionCount = null;
        try{
            ethGetTransactionCount = web3j.ethGetTransactionCount(addressList.get(0), DefaultBlockParameterName.LATEST)
                    .sendAsync().get();
        } catch(InterruptedException | ExecutionException e1){
            e1.printStackTrace();
        }

        BigInteger value = new BigInteger(amount);
        BigInteger nonce =  ethGetTransactionCount.getTransactionCount();

        String to = String.valueOf(inputParameters.get(1));
        Transaction transaction = Transaction.createFunctionCallTransaction(addressList.get(0), nonce,Transaction.DEFAULT_GAS,null,to,value,FunctionEncoder.encode(function));


        EthSendTransaction test = web3j.ethSendTransaction(transaction).send();
        return transaction;

    }




}

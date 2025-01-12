package com.ecommerce.domain.wallet.application;

import com.ecommerce.domain.payment.dto.PaymentSaveRequest;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import com.ecommerce.domain.wallet.domain.Wallet;
import com.ecommerce.domain.wallet.domain.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
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
import org.web3j.tx.Transfer;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.String.valueOf;
import static java.util.Collections.emptyList;
import static org.web3j.abi.FunctionEncoder.encode;
import static org.web3j.protocol.core.methods.request.Transaction.DEFAULT_GAS;
import static org.web3j.protocol.core.methods.request.Transaction.createFunctionCallTransaction;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class WalletService {

    private static final String temp = "0x2bd661bad97160c81eb0704ae29cb97bcbec6f8a";
    private static final String ADMIN_ETH_ADDRESS = "0x22510CbFA46e5dC4b625BD16f854024f2E6114b5";
    private static final String FUNCTION_NAME = "reqEth";
    private static final int COIN_BASE = 0;
    private static final int TO_INDEX = 1;

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final Web3j web3j;
    private final Admin admin;

    @Transactional
    public Wallet createWallet(final String email) throws CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        if (user.getWallet() != null) {
            return user.getWallet();
        }
        final String walletFile = WalletUtils.generateNewWalletFile(user.getPassword(), new File(Wallet.WALLET_DIRECTORY));
        final Credentials credentials = WalletUtils.loadCredentials(user.getPassword(), new File(Wallet.WALLET_DIRECTORY + walletFile));
        final EthGetBalance ethGetBalance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        final Wallet wallet = wallet(credentials, walletFile, user, ethGetBalance.getBalance());
        return walletRepository.save(wallet);
    }

    @Transactional
    public void getBalance(final String fromAddress, final String toAddress, final String amount) throws IOException {
        EthGetBalance fromAddressGetBalance = web3j.ethGetBalance(fromAddress, DefaultBlockParameterName.LATEST).send();
        EthGetBalance toAddressGetBalance = web3j.ethGetBalance(toAddress, DefaultBlockParameterName.LATEST).send();
        BigInteger fromBalance = fromAddressGetBalance.getBalance();
        BigInteger toBalance = toAddressGetBalance.getBalance();

        final Wallet fromWallet = walletRepository.findByAddress(fromAddress).orElseThrow(IllegalArgumentException::new);
        final Wallet toWallet = walletRepository.findByAddress(toAddress).orElseThrow(IllegalArgumentException::new);
        final BigInteger amountValue = new BigInteger(amount);

        fromWallet.subtractBalance(amountValue);
        toWallet.addBalance(amountValue);

        while (fromBalance.equals(fromWallet.getBalance()) && toBalance.equals(toWallet.getBalance())) {
            fromAddressGetBalance = web3j.ethGetBalance(fromAddress, DefaultBlockParameterName.LATEST).send();
            toAddressGetBalance = web3j.ethGetBalance(toAddress, DefaultBlockParameterName.LATEST).send();
            fromBalance = fromAddressGetBalance.getBalance();
            toBalance = toAddressGetBalance.getBalance();
        }
    }

    @Transactional
    public Wallet getBalance(final String address) throws IOException {
        final EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        final Wallet wallet = walletRepository.findByAddress(address).orElseThrow(IllegalArgumentException::new);
        return wallet.changeBalance(ethGetBalance.getBalance());
    }

    @Transactional
    public Wallet getBalance(final PaymentSaveRequest paymentSaveRequest) throws IOException {
        final String address = paymentSaveRequest.getReceiver();
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        final Wallet wallet = walletRepository.findByAddress(address).orElseThrow(IllegalArgumentException::new);
        final BigInteger walletBalance = wallet.getBalance();
        final BigInteger amount = new BigInteger(paymentSaveRequest.getAmount());
        final BigInteger expectedValue = walletBalance.add(amount);
        while (!ethGetBalance.getBalance().equals(expectedValue)) {
            ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        }
        return wallet.changeBalance(ethGetBalance.getBalance());
    }

    public Wallet getWalletAddressByUser(final String email) {
        final User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        return walletRepository.findByUser(user).orElseThrow(IllegalArgumentException::new);
    }

    private Wallet wallet(final Credentials credentials, final String walletFile, final User user, final BigInteger balance) {
        return Wallet.builder()
                .address(credentials.getAddress())
                .fileName(walletFile)
                .user(user)
                .balance(balance)
                .build();
    }

    //ToDo 트랜잭션 처리방법, 계정을 만들고 1회 한해서 이더리움을 받을 수 있도록 하기
    // user 테이블에 1회 충전이 되었는지 속성 필요
    // 1회 충전을 눌렀을 때 -> user테이블에서 검사하고 user.charge -> boolean 값으로 만들고 만약 fasle이면
    // 충전 가능
    public void transactionFunction(final PaymentSaveRequest paymentSaveRequest) throws IOException, ExecutionException, InterruptedException {
        unLockAdminAccount();
        final List<Type> inputParameters = inputParameters(ADMIN_ETH_ADDRESS, paymentSaveRequest.getReceiver(), paymentSaveRequest.getAmount());
        final List<String> addresses = addressList();
        final Transaction transaction = createFunctionCallTransaction(
                addresses.get(COIN_BASE),
                transactionCount(addresses),
                DEFAULT_GAS,
                null,
                valueOf(inputParameters.get(TO_INDEX)),
                new BigInteger(paymentSaveRequest.getAmount()),
                encode(new Function(FUNCTION_NAME, inputParameters, emptyList()))
        );
        web3j.ethSendTransaction(transaction).send();
    }

    public void transactionFunction(final String from, final String to, final String amount) throws IOException, ExecutionException, InterruptedException {
        unLockAdminAccount();
        final List<Type> inputParameters = inputParameters(from, to, amount);
        final List<String> addresses = addressList();
        final Transaction transaction = createFunctionCallTransaction(
                from,
                transactionCount(addresses),
                DEFAULT_GAS,
                null,
                valueOf(to),
                new BigInteger(amount),
                encode(new Function(FUNCTION_NAME, inputParameters, emptyList()))
        );
        web3j.ethSendTransaction(transaction).send();
    }

    private void unLockAdminAccount() throws IOException {
        PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount(ADMIN_ETH_ADDRESS, "1234").send();
        personalUnlockAccount.accountUnlocked();
    }

    private List<String> addressList() throws IOException {
        final PersonalListAccounts personalListAccounts = admin.personalListAccounts().send();
        return personalListAccounts.getAccountIds();
    }

    private BigInteger transactionCount(final List<String> addresses) throws ExecutionException, InterruptedException {
        final EthGetTransactionCount ethGetTransactionCount =
                web3j.ethGetTransactionCount(
                        addresses.get(COIN_BASE),
                        DefaultBlockParameterName.LATEST).sendAsync().get();
        return ethGetTransactionCount.getTransactionCount();
    }

    private List<Type> inputParameters(final String from, final String to, final String amount) {
        return List.of(
                new Address(from),
                new Address(to),
                new Address(amount)
        );
    }

}

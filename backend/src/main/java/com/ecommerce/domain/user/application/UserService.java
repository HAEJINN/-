package com.ecommerce.domain.user.application;

import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import com.ecommerce.domain.user.dto.UserFindListResponse;
import com.ecommerce.domain.wallet.domain.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerce.domain.wallet.domain.Wallet.WALLET_DIRECTORY;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Web3j web3j;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User save(final User user) throws IOException, CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        final File file = new File(WALLET_DIRECTORY);
        file.mkdirs();
        final String walletFile = WalletUtils.generateNewWalletFile(user.getPassword(), file);
        final Credentials credentials = WalletUtils.loadCredentials(user.getPassword(), new File(WALLET_DIRECTORY + walletFile));
        final EthGetBalance ethGetBalance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        final Wallet wallet = wallet(credentials, walletFile, user, ethGetBalance.getBalance());
        user.changeProfileImage("profile.jpg")
                .changeWallet(wallet)
                .passwordEncode(passwordEncoder);
        return userRepository.save(user);
    }

    @Transactional
    public void update(Long id, String photo) {
        final User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        user.changeEmail(photo);
    }

    @Transactional
    public void delete(Long id) {
        final User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<UserFindListResponse> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserFindListResponse(user))
                .collect(Collectors.toList());
    }

    public List<UserFindListResponse> findAllLatest(final Pageable pageable) {
        return userRepository.findAll(pageable).stream()
                .map(user -> new UserFindListResponse(user))
                .collect(Collectors.toList());
    }

    public User login(final User loginUser) {
        final User user = userRepository.findByEmail(loginUser.getEmail()).orElseThrow();
        user.matchPassword(passwordEncoder, loginUser.getPassword());
        return user;
    }

    private Wallet wallet(final Credentials credentials, final String walletFile, final User user, final BigInteger balance) {
        return Wallet.builder()
                .address(credentials.getAddress())
                .fileName(walletFile)
                .user(user)
                .balance(balance)
                .build();
    }

    public List<UserFindListResponse> findAllRandom() {
        final List<User> all = userRepository.findAll();
        Collections.shuffle(all);
        int lastIndex = 12;
        if (all.size() < 12) {
            lastIndex = all.size();
        }
        return all.subList(0, lastIndex).stream()
                .map(user -> new UserFindListResponse(user))
                .collect(Collectors.toList());
    }

}

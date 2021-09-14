package com.ecommerce.domain.wallet.application;

import com.ecommerce.domain.etehereum.application.EthereumService;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import com.ecommerce.domain.wallet.domain.Wallet;
import com.ecommerce.domain.wallet.domain.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * TODO Sub PJT Ⅱ 과제 1, 과제 3
 * 과제 1: 지갑 관련 기능 구현
 * 1) 지갑 등록, 2) 지갑 조회, 3) 충전
 * 과제 3: 지갑 관련 기능 확장 구현
 * 1) 지갑 토큰 잔액 조회 추가
 * <p>
 * IWalletService를 implements 하여 구현합니다.
 */
@Service
public class WalletService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final EthereumService ethereumService;
    private final CashContractService cashContractService;

    public WalletService(final WalletRepository walletRepository,
                         final UserRepository userRepository,
                         final EthereumService ethereumService,
                         final CashContractService cashContractService) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.ethereumService = ethereumService;
        this.cashContractService = cashContractService;
    }

    public Wallet findByUserId(final Long userId) {
        final User user = findUserById(userId);
        return findWalletByUser(user);
    }

    /**
     * 지갑을 DB에 등록한다.
     *
     * @param wallet
     * @return
     */
    public Wallet save(final Wallet wallet) {
        return walletRepository.save(wallet);
    }

    /**
     * DB에 저장된 지갑주소의 정보와 이더리움의 잔액 정보를 동기화한다.
     *
     * @param walletAddress
     * @return Wallet
     */
    public Wallet syncBalance(final String walletAddress, final BigDecimal balance, final int cash) {
        return null;
    }

    /**
     * [지갑주소]로 이더를 송금하는 충전 기능을 구현한다.
     * 무한정 충전을 요청할 수 없도록 조건을 두어도 좋다.
     *
     * @param walletAddress
     * @return Wallet
     */
    public Wallet requestEth(final String walletAddress) {
        return null;
    }


    private Wallet findWalletByUser(final User user) {
        return walletRepository.findByUser(user).orElseThrow(IllegalArgumentException::new);
    }

    private User findUserById(final Long userId) {
        return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
    }

}

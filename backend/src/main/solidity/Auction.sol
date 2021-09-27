pragma solidity ^0.5.0;

contract SimpleAuction {
    // 경매 매개변수.
    // 절대 유닉스 타임스탬프(1970-01-01 이후 초) 또는 기간(초)
    address payable public beneficiary;
    uint public auctionEndTime;

    // 경매의 현재 상태입니다.
    address public highestBidder;
    uint public highestBid;

    // 이전 입찰 철회 허용
    mapping(address => uint) pendingReturns;

    // 마지막에 true로 설정하면 변경이 허용되지 않습니다.
    // 기본적으로 'false'로 초기화됩니다.
    bool ended;

    // 변경 시 발생하는 이벤트입니다.
    event HighestBidIncreased(address bidder, uint amount);
    event AuctionEnded(address winner, uint amount);

    // 다음은 소위 natspec 주석입니다.
    // 세 개의 슬래시로 식별할 수 있습니다.
    // 사용자에게 거래 확인을 요청할 때 표시됩니다.

    /// `_biddingTime`으로 간단한 경매 만들기
    /// 수혜자 주소 `_beeficiary`를 대신한 입찰 시간(초)입니다.
    constructor(
        uint _biddingTime,
        address payable _beneficiary
    ) public {
        beneficiary = _beneficiary;
        auctionEndTime = now + _biddingTime;
    }

    /// 보낸 값으로 경매에 입찰
    /// 트랜잭션과 함께
    /// 경매가 낙찰되지 않은 경우에만 가치가 환불됩니다.
    function bid() public payable {
        // 인수가 필요하지 않습니다.
        // 정보는 이미 거래의 일부입니다.
        // 기능이 Ether를 수신할 수 있으려면 payable 키워드가 필요합니다.

        // 입찰 기간이 끝나면 통화를 되돌립니다.
        require(
            now <= auctionEndTime,
            "Auction already ended."
        );

        // 입찰가가 더 높지 않으면 돈을 다시 보내십시오.
        require(
            msg.value > highestBid,
            "There already is a higher bid."
        );

        if (highestBid != 0) {
            //단순히 highBidder를 사용하여 돈을 되돌려줍니다.
            // send(highestBid)는 신뢰할 수 없는 계약을 실행할 수 있으므로 보안 위험입니다.
            // 수령인이 직접 돈을 인출하도록 하는 것이 항상 더 안전합니다.
            pendingReturns[highestBidder] += highestBid;
        }
        highestBidder = msg.sender;
        highestBid = msg.value;
        emit HighestBidIncreased(msg.sender, msg.value);
    }

    // 초과 입찰된 입찰을 철회합니다.
    function withdraw() public returns (bool) {
        uint amount = pendingReturns[msg.sender];
        if (amount > 0) {
            // 수신자가 `send`가 반환되기 전에 수신 호출의 일부로 이 함수를 다시 호출할 수 있으므로 이 값을 0으로 설정하는 것이 중요합니다.
            pendingReturns[msg.sender] = 0;
            if (!msg.sender.send(amount)) {
                // 여기에서 throw를 호출할 필요가 없습니다. 미결제 금액을 재설정하면 됩니다.
                pendingReturns[msg.sender] = amount;
                return false;
            }
        }
        return true;
    }

    ///
    //경매를 종료하고 가장 높은 입찰가를 수혜자에게 보냅니다.
    function auctionEnd() public {
        // 다른 계약과 상호 작용하는 기능을 구성하는 데 좋은 지침입니다(예: 기능을 호출하거나 Ether를 전송).
        // into three phases:
        // 1. 조건 확인
        // 2. 작업 수행(잠재적으로 변경되는 조건)
        // 3. 다른 계약과 상호 작용
        // 이러한 단계가 혼합되면 다른 계약이 현재 계약을
        // 다시 호출하고 상태를 수정하거나 여러 번 수행되는 효과(이더 지불)를 유발할 수 있습니다.
        // 내부적으로 호출되는 기능이 외부 계약과의 상호 작용을 포함하는 경우 외부 계약과의 상호 작용도 고려해야 합니다.

        // 1. Conditions
        require(now >= auctionEndTime, "Auction not yet ended.");
        require(!ended, "auctionEnd has already been called.");

        // 2. Effects
        ended = true;
        emit AuctionEnded(highestBidder, highestBid);

        // 3. Interaction
        beneficiary.transfer(highestBid);
    }
}
pragma solidity ^0.5.0;


contract BlindAuction {
    struct Bid {
        bytes32 blindedBid;
        uint deposit;
    }

    address payable public beneficiary;
    uint public biddingEnd;
    uint public revealEnd;
    bool public ended;

    mapping(address => Bid[]) public bids;

    address public highestBidder;
    uint public highestBid;

    // 이전 입찰 철회 허용
    mapping(address => uint) pendingReturns;

    event AuctionEnded(address winner, uint highestBid);

    // 수정자는 함수에 대한 입력의 유효성을 검사하는 편리한 방법입니다.
    // 'onlyBefore'는 아래의 'bid'에 적용됩니다. 새 함수 본문은 수정자의 본문이며 `_'는 이전 함수 본문으로 대체됩니다.
    modifier onlyBefore(uint _time) { require(now < _time); _; }
    modifier onlyAfter(uint _time) { require(now > _time); _; }

    constructor(
        uint _biddingTime,
        uint _revealTime,
        address payable _beneficiary
    ) public {
        beneficiary = _beneficiary;
        biddingEnd = now + _biddingTime;
        revealEnd = biddingEnd + _revealTime;
    }

    // `_blindedBid` = keccak256(abi.encodePacked(value, fake, secret))으로 블라인드 입찰을 합니다.
    // 보낸 이더는 공개 단계에서 입찰가가 올바르게 공개된 경우에만 환불됩니다.
    // 입찰과 함께 보낸 이더가 "값" 이상이고 "가짜"가 사실이 아닌 경우 입찰이 유효합니다.
    // "fake"를 true로 설정하고 정확한 금액을 보내지 않는 것은 실제 입찰가를 숨기지만 여전히 필요한 보증금을 만드는 방법입니다.
    // 동일한 주소로 여러 입찰을 할 수 있습니다.
    function bid(bytes32 _blindedBid)
    public
    payable
    onlyBefore(biddingEnd)
    {
        bids[msg.sender].push(Bid({
        blindedBid: _blindedBid,
        deposit: msg.value
        }));
    }

    // 블라인드 입찰을 공개합니다.
    // 당신은 모두에 대한 환불을받을 것입니다
    // 올바르지 않은 무효 입찰 및 완전히 높은 입찰가를 제외한 모든 입찰가에 대해.
    function reveal(
        uint[] memory _values,
        bool[] memory _fake,
        bytes32[] memory _secret
    )
    public
    onlyAfter(biddingEnd)
    onlyBefore(revealEnd)
    {
        uint length = bids[msg.sender].length;
        require(_values.length == length);
        require(_fake.length == length);
        require(_secret.length == length);

        uint refund;
        for (uint i = 0; i < length; i++) {
            Bid storage bidToCheck = bids[msg.sender][i];
            (uint value, bool fake, bytes32 secret) =
            (_values[i], _fake[i], _secret[i]);
            if (bidToCheck.blindedBid != keccak256(abi.encodePacked(value, fake, secret))) {
                // 입찰가는 실제로 공개되지 않았습니다.
                // 보증금을 환불하지 마십시오.
                continue;
            }
            refund += bidToCheck.deposit;
            if (!fake && bidToCheck.deposit >= value) {
                if (placeBid(msg.sender, value))
                    refund -= value;
            }
            // 발신인이 다시 청구할 수 없도록 설정
            // 같은 보증금.
            bidToCheck.blindedBid = bytes32(0);
        }
        msg.sender.transfer(refund);
    }

    // 이것은 계약 자체(또는 파생된 계약)에서만 호출할 수 있는 "내부" 기능입니다.
    function placeBid(address bidder, uint value) internal
    returns (bool success)
    {
        if (value <= highestBid) {
            return false;
        }
        if (highestBidder != address(0)) {
            // Refund the previously highest bidder.
            pendingReturns[highestBidder] += highestBid;
        }
        highestBid = value;
        highestBidder = bidder;
        return true;
    }

    // 초과 입찰된 입찰을 철회합니다.
    function withdraw() public {
        uint amount = pendingReturns[msg.sender];
        if (amount > 0) {
            // 수신자가 `transfer`가 반환되기 전에 수신 호출의 일부로 이 함수를 다시 호출할 수 있기 때문에
            // 이것을 0으로 설정하는 것이 중요합니다(조건 -> 효과 -> 상호 작용에 대한 위의 설명 참조).
            pendingReturns[msg.sender] = 0;
            msg.sender.transfer(amount);
        }
    }

    // 경매를 종료하고 가장 높은 입찰가를 수혜자에게 보냅니다.
    function auctionEnd()
    public
    onlyAfter(revealEnd)
    {
        require(!ended);
        emit AuctionEnded(highestBidder, highestBid);
        ended = true;
        beneficiary.transfer(highestBid);
    }
}
pragma solidity ^0.5.0;

import './ERC721.sol';

contract ERC721Impl is ERC721 {
    function _mint(address to, uint256 tokenId) internal {
        require(to != address(0), "ERC721: mint to the zero address");
        require(!_exists(tokenId), "ERC721: token already minted");

        _tokenOwner[tokenId] = to;
        _ownedTokensCount[to].increment();

        emit Transfer(address(0), to, tokenId);
    }
}

contract BusinessLogin is ERC721Impl{
    struct Charactor {
        string  tokenURI;  // 캐릭터 이름
        uint256 cardId; // 캐릭터 레벨
        address buyer;
    }
    mapping(uint => string) tokenURIs;
    uint public count;

    function getCount() public view returns (uint) {
        return count;
    }

    function tokenURI(uint256 tokenId) public view returns (string memory) {
        return tokenURIs[tokenId];
    }

    Charactor[] public charactors; // default: []
    address public owner;          // 컨트랙트 소유자

    constructor () public {
        owner = msg.sender;
    }

    modifier isOwner() {
        require(owner == msg.sender);
        _;
    }

    // 캐릭터 생성
    function mint(string memory tokenURI, address account) public returns (uint256){
        uint256 cardId = charactors.length; // 유일한 캐릭터 ID
        charactors.push(Charactor(tokenURI, cardId, account));
        _mint(account, cardId); // ERC721 소유권 등록
        count = count + 1;
        tokenURIs[cardId] = tokenURI;

        return cardId;
    }
}

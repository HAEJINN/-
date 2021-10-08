# 포팅 메뉴얼 

## 배포 환경(클론 이후 빌드 및 배포할 수 있는 작업 문서) 
### 서버
* [메인 서버](http://3.34.135.3)  
* [추가 서버](http://3.36.50.29)

### 버전
* JDK11 
* geth 1.10.8-stable   

### DB 정보 
* `http://3.34.135.3:3036`
* ID : root  
* PWD: ssafy    
* DB명: bcssafy    

### geth 접속
```
geth --networkid 15 --datadir ~/dev/eth_localdata --nodiscover --port 30303 --rpc --rpcport "8545" --maxpeers 2 --rpcaddr "0.0.0.0" --rpccorsdomain "*" --rpcapi "eth, net, web3, miner, debug, personal, rpc" console --allow-insecure-unlock --rpc.allow-unprotected-txs
```

# 프로젝트에서 사용하는 외부 서비스 문서 
**AWS**
* AWS S3 주소: https://najakwha.s3.ap-northeast-2.amazonaws.com/
* bucket name : najakwha
* accessKey: `AKIAVISGU4KSDZTO6LHK`
* secretKey: `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`
    
**IPFS**
* IPFS 주소: https://api.pinata.cloud/pinning/pinFileToIPFS
* pinata_api_key: `44358c443a728b145d65`
* pinata_secret_api_key: `aaf68fd21d73a8fe39ef53749ec9dc24ecd976f73a2d3c934297a6c6bd6f8b4d`
    
# 시연 시나리오 
[시나리오 파일 이동](https://lab.ssafy.com/s05-blockchain/S05P21C201/-/blob/develop/exec/4.%EC%8B%9C%EC%97%B0%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4.pptx) 

# DB 덤프 파일  
[DB 폴더 이동](https://lab.ssafy.com/s05-blockchain/S05P21C201/-/tree/develop/exec/database)

# 특이사항 
프로젝트 배포 및 geth 네트워크 처음 접속 후 아래와 같은 값을 바꿔줘야함 
    
## ADMIN 및 스마트 컨트랙트 ADDRESS 설정   
1. 프로젝트 빌드 및 실행, `java -jar backend-0.0.1-SNAPSHOT.jar`       
2. `http://3.34.135.3:8080/api/v1/deploy`를 통해 NFT 컨트랙트 배포 및 address 값 얻기     
3. `프로젝트 > backend > src > main > resources > application-prod.yml` 에서 아래와 같이 설정   

```  
eth:
  erc721:
    contract: {{`http://3.34.135.3:8080/api/v1/deploy` 반환 값}}
  admin:
    address: {{ GETH 설정하면서 처음 설정한 계정 정보(코인베이스)}}
```

## Wallet 설정 

* `프로젝트 > backend > src > main > java > com.ecommererec > domain > wallet > api > walletController` 에서   
변수 이름 `adminEthAddress`를 `GETH 설정하면서 처음 설정한 계정 정보(코인베이스)`로 변경
* `프로젝트 > backend > src > main > java > com.ecommererec > domain > wallet > api > walletService`에서   
ADMIN_ETH_ADDRESS 변수를 `GETH 설정하면서 처음 설정한 계정 정보(코인베이스)`로 변경,     
personalUnlockAccount(계정에 맞게 비밀번호 변경)    











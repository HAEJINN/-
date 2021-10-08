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

## 프로젝트에서 사용하는 외부 서비스 문서 
* AWS S3 : https://najakwha.s3.ap-northeast-2.amazonaws.com/
* bucket name : najakwha
* accessKey: AKIAVISGU4KSDZTO6LHK
* secretKey: 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

## 시연 시나리오 
[시나리오 파일 이동](https://lab.ssafy.com/s05-blockchain/S05P21C201/-/blob/develop/exec/4.%EC%8B%9C%EC%97%B0%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4.pptx) 

## DB 덤프 파일  
[DB 폴더 이동](https://lab.ssafy.com/s05-blockchain/S05P21C201/-/tree/develop/exec/database)












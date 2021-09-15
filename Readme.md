# 블록체인 네트워크 구축 및 활용

* Req. 0. Reference
* Req. 1. 프라이빗 이더리움 네트워크 구축
    * Req. 1-1 가상머신구성
    * Req. 1-2 이더리움 eth0 노드구성
    * Req. 1-3 이더리움 eth1 노드구성
* Req. 2. 이더리움 계정생성
    * Req. 2-1 계정생성
    * Req. 2-2 코인베이스(coinbase) 설정
    * Req. 2-3 마이닝(mining) 시작
    * Req. 2-4 마이닝(mining) 결과 확인
* Req. 3. 이더리움 트랜잭션 생성
    * Req. 3-1 트랜잭션(Transaction) 생성
    * Req. 3-2 트랜잭션(Transaction) 결과 확인
* Req. 4. 스마트 컨트랙트 배포
    * Req. 4-1 eth0 노드 확인
    * Req. 4-2 Metamask 설정
    * Req. 4-3 스마트 컨트랙트 배포(Remix)
    * Req. 4-4 블록 정보 조회
---

# Req. 0. Reference
* ★ 주요 정보는 [다음](https://gongple.tistory.com/9)을 참고했습니다 <br>

* ★ ```sudo apt-get install tree``` :: 디렉토리 구조 보기 편함
    * ![noname01](/uploads/740a925c518a529685433a1e33d5ba14/noname01.png) <br>
* 채굴이 되지 않는 상황에서, genesis.json으로 초기화부터 다시 시작함을 기본으로한다. <br>
* ★ --datadir 삭제 : geth 서버경로가 된다. (필자 본인은 ~/dev/eth_localdata 가 geth 서버경로) <br>
    * ex) rm –r dev <br>

---

# Req. 1. 프라이빗 이더리움 네트워크 구축

### Req. 1-1 가상머신구성

* ★ VritualBox 설치
    * 공식 웹사이트에서 OS 버전에 맞는 설치파일 다운로드
* ★ Vagrant 설치
    * 공식 웹사이트에서 OS 버전에 맞는 설치파일 다운로드
    * 디렉토리 생성 (원하는 곳)
        * ```mkdir bc-ethereum```
    * 설치여부 및 버전확인
        * ```vagrant version```
    * 호스트와 가상머신 간 파일전송 플러그인 설치
        * ```vagrant plugin install vagrant-scp```
* ★ 가상머신 생성 및 구동
    * Vagrant 초기화 (VagrantFile 생성)
        * ```vagrant init```
    * VagrantFile 수정        
        ```
            VAGRANT_API_VERSION = "2"
            vms = {
                'eth0' => '10',
                'eth1' => '11'
            }

            Vagrant.configure(VAGRANT_API_VERSION) do |config|
                config.vm.box = "ubuntu/bionic64"
                vms.each do |key, value|
                    config.vm.define "#{key}" do |node|
                        node.vm.network "private_network", ip: "192.168.50.#{value}"
                        if "#{key}" == "eth0"
                            node.vm.network "forwarded_port", guest: 8545, host: 8545
                        end
                        node.vm.hostname = "#{key}"
                        node.vm.provider "virtualbox" do |nodev|
                        nodev.memory = 2048
                        end
                    end
                end
            end
        ```
* ★ 가상머신 구동
    * ```vagrant up```
* ★ 가상머신 구동 상태확인
    * ```vagrant status```
* ★ 가상머신 접속
    * 가상머신 접속 전, 가상머신(VirtualBox)이 실행중이어야 정상적으로 생성됨
    * vagrant ssh eth0

### Req. 1-2 이더리움 eth0 노드구성
* ★ geth 실행 
    * eth0 RPC API를 사용하는 경우 <br>
    * ```geth --networkid 921 --datadir ~/dev/eth_localdata --nodiscover --port 30303 --rpc --rpcport "8545" --maxpeers 2 --rpcaddr "0.0.0.0" --rpccorsdomain "*" --rpcapi "eth, net, web3, miner, debug, personal, rpc" console```

### Req. 1-3 이더리움 eth1 노드구성
* ★ geth 실행 
    * eth1 RPC API를 사용하지않고, Addr가 localhost인경우 <br>
    * ```geth --networkid 921 --datadir ~/dev/eth_localdata --nodiscover --port 30303 --rpc --rpcport "8545" --maxpeers 2 --rpccorsdomain "*" console```
---

# Req. 2. 이더리움 계정생성

### Req. 2-1 계정생성
* ★ --datadir 에 계정 생성 : geth 서버경로에 새 계정을 생성한다
    * ```geth –-datadir ~/dev/eth_localdata account new```
    * 생성된 계정 public address key 기억할것
    * 잊어버린경우 해당 서버경로(~/dev/eth_localdata)의 keystore 디렉토리 확인
---

### Req. 2-2 코인베이스(coinbase) 설정
* ★ 코인베이스 설정 및 geth 초기 설정을위해 genesis.json 생성 후 수정
    * genesis.json 생성
        * ```touch genesis.json```
    * genesis.json 수정
        * vi genesis.json
            *
            ```
            {
                "config": {
                    "chainId": 15,
                    "homesteadBlock": 0,
                    "eip150Block": 0,
                    "eip155Block": 0,
                    "eip158Block": 0,
                    "byzantiumBlock": 0,
                    "constantinopleBlock": 0,
                    "petersburgBlock": 0,
                    "istanbulBlock": 0,
                    "berlinBlock": 0
                },
                "alloc": {
                    "0xD0AF273B57ff971845D9aE02ad1a63EAa6E7Cc49": {
                        "balance": "1000000000000000000"
                    },
                    "0x997b395Bb9014458dFa0dFAEA91A9B46e9A89746": {
                        "balance": "2000000000000000000"
                    }
                },
                "coinbase": "0xD0AF273B57ff971845D9aE02ad1a63EAa6E7Cc49",
                "difficulty": "0x10",
                "extraData": "",
                "gasLimit": "9999999",
                "nonce": "0xdeadbeefdeadbeef",
                "timestamp": "0x00"
            }
            ```
    * ★ geth 서버경로에 genesis.json로 geth 초기화
        * ```geth --datadir ~/dev/eth_localdata init ~/genesis.json```

---

### Req. 2-3 마이닝(mining) 시작
* ★ 채굴시작 전 설정
    * miner.setEtherbase(eth.accounts[0])
* ★ 채굴시작 
    * miner.start()
* ★ 채굴 시작 후 
    * epoch가 0인 곳의 percentage가 100이 완료되면 채굴이 시작된다.
    * 만약 epoch 1인 곳의 percentage가 100이 될 때까지 채굴이 되지않았으면, <br> genesis.json 첫 블록 설정의 문제이다
    * ![noname02](/uploads/44566c559cbbd2fa388988d8f96a8f03/noname02.png)
* ★ 채굴종료
    * miner.stop()
---

### Req. 2-4 마이닝(mining) 결과 확인
* ★ 결과 확인
    * ```web3.fromWei(eth.getBalance(eth.accounts[{accountNumber}]), "ehter")```
    * ![noname03](/uploads/83c894b6197a8e3a912da873dea22dfd/noname03.png)
* ★ 생성된 블록 조회
    * ```eth.blockNumber```
    * ![noname04](/uploads/0c4f3a624d057b4d266931d0a673444f/noname04.png)
    * 생성된 블록의 상세정보조회
        * ```eth.getBlock(0)```
        * ![noname05](/uploads/9fd54c2ac65fff9cefb91d6abd8c4372/noname05.png)

---

# Req. 3. 이더리움 트랜잭션 생성
* ★ 계정간 트랜잭션을 이동하기 위해선 서버연결부터 다시 해야한다. <br> 트랜잭션을 서로 전송하기 위해서 계정 Lock을 풀어야함 <br> geth 연결시 ```--allow-insecure-unlock``` 옵션 추가 필요
* ★ eth0
    * ```geth --networkid 921 --datadir ~/dev/eth_localdata --nodiscover --port 30303 --rpc --rpcport "8545" --maxpeers 2 --rpcaddr "0.0.0.0" --rpccorsdomain "*" --rpcapi "eth, net, web3, miner, debug, personal, rpc" --allow-insecure-unlock console```
* ★ eth1
    * ```geth --networkid 921 --datadir ~/dev/eth_localdata --nodiscover --port 30303 --rpc --rpcport "8545" --maxpeers 2 --rpccorsdomain "*" --allow-insecure-unlock console```
* ★ 계정 Lock해제
    * locked 및 unlcoked 확인
        * ```personal.listWallet[0].status```
    * 계정해제
        * ```web3.personal.unlockAccount(eth.accounts[0])```
        * write your password, to create account
        * ![noname06](/uploads/097d00cb249b3afd0d77b3b72f08e340/noname06.png)

### Req. 3-1 트랜잭션(Transaction) 생성
* ★ 트랜잭션 생성
    *
    ```
    tx = {
        from: "0xe656bb3ef94b68efffcd5881c524ea5e36609a90",
        to: "0x39578a6420decd61137ca8fd54d263fcbec9b04f",
        value: web3.toWei(100, "ether"),
        data: web3.toHex("sendTransaction Sample")
    }
    ```
* ★ 트랜잭션 전송
    * 트랜잭션은 곧바로 전송되지않고, mining 시작시 트랜잭션 리스트로써 전송된다.
    * ```eth.sendTransaction(tx)```
    * ![noname07](/uploads/27a987b4014d27cf12f9130d45964a46/noname07.png)
* ★ 트랜잭션 정보 확인방법
    * 트랜잭션 리스트 검색
        * ```eth.pendingTransactions```
    * 트랜잭션 검색
        * ```eth.getTransaction(tx_send)```

### Req. 3-2 트랜잭션(Transaction) 결과 확인
* ★ 트랜잭션 전송 전
    * ![noname8](/uploads/29b374e6549aa7ca7f33eff9e57fbc2e/noname8.png)
* ★ 트랜잭션 전송 후
    * ![noname09](/uploads/5d60e7cbe90e919f28b931c89547baf8/noname09.png)

---

# Req. 4. 스마트 컨트랙트 배포

### Req. 4-1 eth0 노드 확인
* ★ 포트포워딩 확인
    * VritualBox
        * eth0 node`s setting (가상머신 eth0 노드)
            * network (네트워크)
                * advanced (고급)
                    * port fowarding (포트포워딩)
                        * new rules or check rules (규칙 생성 or 확인)
                            * Host : 8545, Guest : 8545
    * ![noname10](/uploads/7b999a0456fa542be8848bfeadf34b07/noname10.png)
    * ![noname11](/uploads/7a747e3582665817eb65bf10afe6d6dd/noname11.png)

### Req. 4-2 Metamask 설정
* ★ 단 버그있었음
    * eth0의 계정을 가져옴
    * localhost:8545 의 chainId는 15인데, 1337로 설정하여도 가져와짐
    * eth1노드 또한 localhost:8545인데 이쪽의 계정은 가져올 수 없었음
    * ![noname012](/uploads/f2663ac4b28af37c68494e8f2a2c2cc2/noname012.png)
    * ![noname013](/uploads/5307ea01cf07255c353abf3107eb720a/noname013.png)

### Req. 4-3 스마트 컨트랙트 배포(Remix)
* ★ Remix와 Localhost Directory 연결
    * ```remixd -s <path-to-the-shared-folder> -u <remix-ide-instance-URL>```
* ★ Deploy & RunTransaction 항목으로 이동
* ★ Environment 지정
    * ![noname014](/uploads/bc733da6b12fae034cb2bd2180649342/noname014.png)

* ★ 예제 선택 후 코드확인
* ★ Complie
* ★ Deploy
* ★ 결과확인

### Req. 4-4 블록 정보 조회
* ★ 이더리움 네트워크 정보 수정
* ★ 프로젝트 구동
* ★ 블록 및 트랜잭션 정보 확인

---

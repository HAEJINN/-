# Block Chain Sub1

## 1. 환경 설정

- **VirtaulBox 설치**

- **Vagrant 설치**
    - vagrant init : vagrant 설정 파일 생성
    - vagrant up : 가상 머신 구동
    - vagrant status : 가상 머신 상태 확인
    - vagrant ssh [name] : 해당 가상 머신에 접속

- **가상 머신에서 Geth 설치**

    ```jsx
    sudo apt-get update
    sudo apt-get install software-properties-common
    sudo add-apt-repository -y ppa:ethereum/ethereum
    sudo apt-get install ethereum
    geth version
    ```

- **가상 머신에서 geth 실행**

    ```jsx
    //eth0
    geth --networkid 921 --datadir ~/dev/eth_localdata --nodiscover --port 30303 --rpc --rpcport "8545" --maxpeers 2 --rpcaddr "0.0.0.0" --rpccorsdomain "*" --rpcapi "eth, net, web3, miner, debug, personal, rpc" console --allow-insecure-unlock

    //eth1
    geth --networkid 921 --datadir ~/dev/eth_localdata --nodiscover --port 30303 --rpc --rpcport "8545" --maxpeers 2 --rpccorsdomain "*" console
    ```

## 2. 이더리움 계정 생성

- **이더리움 계정 생성**

    ```jsx
    // 계정 생성 
    geth --datadir ~/dev/eth_localdata account new

    ```

- **Genesis.json 파일 생성 및 init**

    ```jsx
    vi genesis.json

    // 내용
    {
            "config": {
                    "chainId": 15,
                    "homesteadBlock": 0,
                    "eip150Block": 0,
                    "eip155Block": 0,
                    "eip158Block": 0,
    								"byzantiumBlock": 0,
    								"constantinopleBlock": 0
            },
            "alloc": {
                    "0x7fd406124405DA943Def42d8F918F9ed06E45E51": {
                            "balance": "1000000000000000000"
                    }
            },
            "coinbase": "0x7fd406124405DA943Def42d8F918F9ed06E45E51",
            "difficulty": "0x10",
            "extraData": "",
            "gasLimit": "9999999",
            "nonce": "0xdeadbeefdeadbeef",
            "timestamp": "0x00"
    }

    // genesis.json init
    geth --datadir ~/dev/localdata init ~/genesis.json
    ```

- **마이닝 시작 및 결과 확인**

    ![Untitled](Block%20Chain%20Sub1%20eebe99b058b74a459fab44e0eb842eea/Untitled.png)

## 3. 이더리움 트랜잭션 생성

- **트랜잭션 생성 및 전송**

    ```jsx
    // 1이더를 보낼 트랜잭션 생성
    tx = { from :"코인을 보낼 address", to:"코인을 받을 address", value:1e18}

    // 트랜잭션 전송
    tx_hash = eth.sendTransaction(tx)

    // 트랜잭션 상세정보 
    tx_detail = eth.getTransaction(tx_hash)
    ```

- **트랜잭션 결과**
    - 전송

    ![Untitled](Block%20Chain%20Sub1%20eebe99b058b74a459fab44e0eb842eea/Untitled%201.png)

    - 확인

    ![Untitled](Block%20Chain%20Sub1%20eebe99b058b74a459fab44e0eb842eea/Untitled%202.png)

## 4. 스마트 컨트랙트 배포

- **VM 포트 포워딩**

    ```jsx
    // vagrant 설정 파일(VagrantFile)
    node.vm.network "forwarded_port", host: 8545, guest: 8545
    ```

- **eth0의 계정 keystore 저장**

    ```jsx
    cd keystore
    cat [해당 계정]

    // cat 내용을 복사 하여 json 파일을 만들어 주었음 ( scp 플러그인을 사용하여 파일공유 가능)
    ```

- **Metamask 설정**
    1. 메타마스트 설치 및 eth0 keystore json파일을 통해 계정 가져오기
    2. RPC 설정 : 포트 8545로 설정했기 때문에 네트워크 [localhost:8545](http://localhost:8545) 선택

    ![Untitled](Block%20Chain%20Sub1%20eebe99b058b74a459fab44e0eb842eea/Untitled%203.png)

- **스마트 컨트랙트 배포(Remix)**
    1. 마이닝 시작 후 배포를 해야 확인 가능
    2. 기본 solidity(storage, ballot 등) 이 실행이 안되면 genesis.json 파일을 수정해야함

        ```jsx
        // genesis.json 파일 안에 config 추가
        	"byzantiumBlock": 0,
        	"constantinopleBlock": 0
        ```

    3. remix에서 unlock 해주라고 뜨면 eth0 geth console에 들어가서 아래 명령어 실행

        ```jsx
        web3.personal.unlockAccount(web3.personal.listAccounts[0],"uojl852",15000)
        ```

    4. 결과 화면

    ![Untitled](Block%20Chain%20Sub1%20eebe99b058b74a459fab44e0eb842eea/Untitled%204.png)

- **블록 정보 조회**
    1. 스케렐톤 코드 구동 : frontend 파일만 구동 시키면 확인 가능

    ![Untitled](Block%20Chain%20Sub1%20eebe99b058b74a459fab44e0eb842eea/Untitled%205.png)

    1. 각 블록 상세정보 ( 스켈레톤 구동한 웹에서도 확인 가능 )

    ![Untitled](Block%20Chain%20Sub1%20eebe99b058b74a459fab44e0eb842eea/Untitled%206.png)
PJT 1주차 발표 및 회고 
=====================
# 💰 블록체인이란? 

                 
블록체인은 네트워크 안에서 연결된 노드들이 서로간의 데이터를 소유 및 동기화하고 이를 검증해줌으로써                  
데이터의 위/변조 방지는 물론 보안에도 강하기에 4차 산업 혁명에 떠오르는 기술 중 하나라고 할 수 있다.             
그리고 위 내용에서 알 수 있듯이 **블록체인은 특정 기술이 아닌 여러 구성되어있는 네트워크라고도  볼 수 있다.**          

![image](https://user-images.githubusercontent.com/50267433/130375307-5f4635eb-4f9d-45dd-8988-c103671a2449.png)

블록체인을 구성하는 네트워크는 크게 2가지로 나뉜다.   
      
1. public network : 실제 시장 네트워크     
2. private network : 사설/테스트 네트워크   

## 💸 이더리움 

![image](https://user-images.githubusercontent.com/50267433/130375553-28d98843-0cc3-4d39-978a-1e9fddbba5d9.png)
        
이더리움 블록체인 네트워크는 위와 같이 수많은 노드들로 구성되어 있다.               
연결된 모든 노드들은 서로 소유하고 있는 데이터(블록)를 `동기화`해서 가지고 있다.          
우리는 노드 1개를 직접 운영하거나 아니면 노드에 명령을 내리는 터미널/브라우저를 활용해서 이더리움 네트워크를 활용할 수 있다.      
                    
노드의 데이터는 `블록`이라는 형태로 저장 및 전송이 이루어진다.           
이더리움에 참여하기 위해서는 **이더리움 클라이언트가 있어야하는데 대부분 `geth`를 사용한다.**      
     
# 요구사항 명세서    
## Req 1. 프라이빗 이더리움 네트워크 구축    
### Req 1.1 가상머신 구현   
VM 환경을 보다 쉽게 구성하고 관리해주는 Vagrant를 이용했다.         
참고로, Vagrant를 활용하려면 Virtual Box를 미리 설치해두어야한다.     
Vagrant를 이용해서 VM 환경을 손쉽게 구성하려면 `Vagrantfile` 이 있어야한다.   
    
**Vagrantfile**
```
# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.

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
         nodev.memory = 4096
         end
      end
   end
end
```
vagrant 를 이용한 VM 환경 구성 코드이다.             
명세서에 나온 내용 위조로 작성했으며 메모리는 4G를 넘게 해주었는데 비트코인 채굴 때문이다.         
이 외에도 포트 포워드 설정을 넣어주어 바로 포워딩 가능하도록 설정했다.       

```
~$ vagrant up
```
위 명령어를 통해 ssh 비대칭키 암호화를 적용하여 현재 로컬 pc에서만 접속할 수 있도록 만든다.     

```
~$ vagrant ssh eth0 
~$ vagrant ssh eth1
```
현재 가지고 있는 키를 통해서 vm에 접속을 할 수 있다.     
단 무슨 이유에서인지 VirtualBox 에서 수동으로 접속한 후 실행해야 가능하다.      

### Req 1.2 이더리움 eth0 노드 구성 
개인적으로 이번 요구사항에서 순서가 매우 중요햇던 것 같다.   
    
**계정 생성(2번 진행함)**
```
eth0$ geth --datadir ~/dev/eth_localdata account new
```
우선 계정을 생성하고 계정을 생성하면서 나온 해시값을 `genesis.json`에 사용했다.     

**genesis.json**    
```
{
        "config": {
                "chainId": 15,
                "homesteadBlock": 0,
                "eip150Block": 0,
                "eip155Block": 0,
                "eip158Block": 0
        },
        "alloc": {
                "{계정 ID}": { "balance": "1000000000000000000" },
                "{계정 ID }": { "balance": "2000000000000000000" }
        },
        "coinbase": "{블록체인 받을 계정 ID }",
        "difficulty": "0x10",
        "extraData": "",
        "gasLimit": "9999999",
        "nonce": "0xdeadbeefdeadbeef",
        "timestamp": "0x00"
}
```

이더리움 네트워크 구성에 앞서 네트워크에 초기 상태값을 지정해주어야 하는데 그것이 바로 `genesis.json`이다.       
여기에 각각의 계정 ID 를 등록해서 네트워크에서 사용가능함을 알린다.       
추가적으로 코인베이스도 설정해서 생성한 계정 중 하나를 채굴할 수 있도록 지정한다.    

```
 geth --networkid 921 --port 30303 --maxpeers 2 --datadir ~/dev/eth_localdata --nodiscover --rpc --rpcport "8545" --rpcaddr "0.0.0.0" --rpcapi "admin,db,eth,net,web3,miner,personal" --rpccorsdomain "*" --allow-insecure-unlock console
```
그리고 geth 를 통한 접근에서 위와 같은 옵션이 붙은 명령으로 이더리움 네트워크에 접속한다.     

```
 geth --networkid 921 --port 30303 --maxpeers 2 --datadir ~/dev/eth_localdata --nodiscover --rpc --rpcport "8545" --rpcaddr "127.0.0.1" --rpcapi "admin,db,eth,net,web3,miner,personal" --rpccorsdomain "*" --allow-insecure-unlock console
```
eth1 은 위와 같은 방법으로 접속하도록 한다.   

# Req 2. 이더리움 계정생성   
계정생성과 코인베이스 설정은 앞서 언급했기에 넘어간다.      

## Req 2.3 마이닝 시작 
```
> miner.start(스레드수)
```
`miner.start(스레드수)`를 통해 채굴 즉, 마이닝을 시작한다.             
참고로 채굴을 진행하면 트랜잭션/블록을 서버로 내보내는 작업(동기화)를 진행한다.          
즉, 채굴을 시작함으로써 연결된 모든 네트워크의 데이터를 동기화시킨다.     

## Req. 2.4 마이닝 결과 확인 
```
> miner.stop()
```
채굴을 끝낸다.   
한가지 특이점이 있는데 채굴이 끝나면 계정들은 Lock 상태로 돌아간다.      
앞서 언락을 진행하지 않았는데 이후 언락하는 과정에서 이를 느낄 수 있다.    

```
> eth.getBalance(eth.accounts[0])    
```  
```
> web3.fromWei(eth.getBalance(eth.coinbase), "ether")
```

또는 메타마스크 연결 또는 remix 연결후 확인할 수도 있다.     


## Req 3. 이더리움 트랜잭션 생성 
### Req 3.1 트랜잭션 생성    

```
> personal.unlockAccount("계정ID")
> personal.unlockAccount("계정ID")
```
우선 계정간 트랜잭션 전송이 있어야하므로 계정에 있는 락을 해제했다.     
단, 맨처음 geth 접속시에 unlock 을 허용하는 옵션을 넣어줘야 동작한다.      

```
eth.sendTransaction({from:eth.accounts[0], to:eth.accounts[1], value:web3.toWei(2,'ethe'), data:web3.toHex("message")})
```
위와 같은 명령어로 트랜잭션을 준비할 수 있다.(변수로 저장도 가능)   

```
> eth.pendingTransactions 
```
위 명령어를 통해 생성된 트랜잭션 목록을 확인할 수 있다.   


### Req 3.2 트랜잭션 결과 확인

```
> miner.start()
```
앞서 언급했듯이 채굴을 시작하면서 트랜잭션을 서버로 보내고      
네트워크로 연결된 서버들은 동기화가 이루어진다.      

```
> miner.stop()
```
```
> eth.pendingTransactions 
```
```
> personal.unlockAccount("계정ID")
```
채굴 종료후 트랜잭션을 확인하면 비어져있는 것을 알 수 있다.     
또한, 이제 to 계정의 값을 확인해보면 2 이더가 들어옴을 알 수 있다.     

## Req 4. 스마트 컨트랙트 배포 
### Req 4.1 eth0 노드 확인   
포트포워딩은 초기 vagrant 에서 설정했으니 VirtualBox 에서 확인해보자          
그리고 eth0에 접속해서 keystore 에 저장된 json 값을 복사해서            
호스트 pc 아무곳에나 동일한 파일명으로 복사한 값을 넣고 저장한다.       

### Req 4.2 Metamask 설정      
메타 마스크 설정을 진행한다.      
서버를 로컬 8545 로 사용하고 계정을 앞서 복사한 json 에서 가져온다.        
이후 잔고가 나오면 잘 된 것이다.    

### Req 4.3 스마트 컨트랙트 배포  
Remix에 접속 후 아무 sol 을 컴파일 후 배포를 진행하고자 한다.    
    
![image](https://user-images.githubusercontent.com/50267433/131941027-f58c0d0a-039a-4fbb-933e-9d2322744c8c.png)

배포 과정에서 배포할 환경(서버)를 private 서버와 포트 포워딩 연결된 localhost:포트 로 설정한다.           
이 환경설정은 http 를 이용해서 스마트 컨트랙트를 배포하기에 가능한 것이다.         

A계정으로 배포후 새 트랜잭션 같은거 만들지 말고 그냥 위에서 B계정으로 바꾼후 컨트랙트 메서드 버튼을 클릭하면서 값을 확인하자     
참고로 이때 geth 접속이 되어있어야하고 `miner.start()`가 수행되어야 스마트 컨트랙트 배포가 성공적으로 이루어진다.           
사실 생각해보면, `miner.start()`가 수행되지 않으면 private 네트워크는 외부의 데이터를 받지도 동기화 하지도 않기 때문이다.         
콘솔창에 V 가 뜨면 트랜잭션이 잘 처리되었음을(동기화 되었음)을 알 수 있다.      

### Req 4.4 블록 정보 조회    
    
![Image Pasted at 2021-9-2 16-19](https://user-images.githubusercontent.com/50267433/131941528-dde14c5a-36cb-4d57-a33a-6f45d079e6d4.png)
  
  
단순히 프론트앤드 npm install 및 npm run serve 를 실행하고          
블록 정보 url 로 이동해보면 블록과 트랜잭션 정보가 나타난다.        
안 나타난 경우 트랜잭션을 만들고 채굴을 진행해보면 된다.       



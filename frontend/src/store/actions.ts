import axios from "@/lib/axios";
import { User, UserResponse, UserRequest } from "@/types/user";

// 로그인
export async function requestUserLogin(
  commit: any,
  user: User
): Promise<UserResponse> {
  const data = {
    email: user.email,
    password: user.password,
  };
  console.log(data);
  const url = "/login";
  return await axios.post(url, data);
}

// 회원가입
export async function requestUserRegister(
  commit: any,
  user: User
): Promise<UserResponse> {
  console.log(user);
  const data = {
    email: user.email,
    name: user.name,
    password: user.password,
  };
  const url = "/users";
  return await axios.post(url, data);
}

// cid 받아오기
export async function request_pinata(commit: any, image: any) {
  const url = "https://api.pinata.cloud/pinning/pinFileToIPFS";
  const formData = new FormData();
  formData.append("file", image);
  return await axios.post(url, formData, {
    headers: {
      pinata_api_key: "44358c443a728b145d65",
      pinata_secret_api_key:
        "aaf68fd21d73a8fe39ef53749ec9dc24ecd976f73a2d3c934297a6c6bd6f8b4d",
    },
  });
}

// 사진 올리기
export function request_picupload(commit: any, nftRequest: any) {
  console.log(nftRequest);
  const url = "/nft";
  return axios.post(url, nftRequest);
}

// 지갑 주소, 잔액 조회
export function request_walletaddress(commit: any, jwtToken: string) {
  const url = "/wallet/getaddress";
  return axios.get(url, {
    headers: {
      Authorization: `Bearer ${jwtToken}`,
    },
  });
}

// 이더 충전하기
export function request_sendeth(commit: any, receiver: string) {
  const url = "/wallet/sendeth";
  // console.log(receiver); 이전..
  const data = {
    receiver: receiver,
  };
  // return axios.post(url, receiver); 이전
  return axios.post(url, data);
}

export function request_getbalance(commit: any, account: any) {
  const url = "/wallet/balance";
  return axios.get(url, account);
}

// 최근 유저 받아오기
export function request_latestuser(commit: any) {
  const url = "/users/latest";
  return axios.get(url);
}

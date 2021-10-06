import axios from "@/lib/axios";
import { User, UserResponse, UserRequest } from "@/types/user";

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
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

// 최근 유저 받아오기
export function request_latestuser(commit: any) {
  const url = "/users/latest";
  return axios.get(url);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 사진 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
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

// 최근 사진 4개 받아오기
export function request_latest_picture() {
  const url = "/items/latest";
  return axios.get(url);
}

// 랜덤 사진 12개 받아오기
export function request_random_picture() {
  const url = "/items/random";
  return axios.get(url);
}

// 사용자의 컬렉션 사진 받아오기
export function request_collection_picture(commit: any, user: User) {
  const url = `/items/${user.id}`;
  return axios.get(url);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 지갑 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
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

//잔액 조회하기
export function request_getbalance(commit: any, account: any) {
  const url = "/wallet/balance";
  return axios.get(url, account);
}
/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */

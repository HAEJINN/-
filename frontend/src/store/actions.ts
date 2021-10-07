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
export async function request_userinfo_byid(
  commit: any,
  user: User
): Promise<UserResponse> {
  const url = `/users/${user.id}`;
  return await axios.get(url);
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
export async function request_picupload(commit: any, nftRequest: any) {
  console.log(nftRequest);
  const url = "/nft";
  return await axios.post(url, nftRequest);
}

// 최근 사진 4개 받아오기
export async function request_latest_picture(commit: any) {
  const url = "/items/four";
  return await axios.get(url);
}

// 랜덤 사진 12개 받아오기
export async function request_random_picture(commit: any) {
  const url = "/items/random";
  return await axios.get(url);
}

export async function request_random_user(commit: any) {
  const url = "/users/random";
  return await axios.get(url);
}

// 사용자의 컬렉션 사진 받아오기
export async function request_collection_picture(commit: any, user_id: number) {
  const url = `/items/collection/${user_id}`;
  return await axios.get(url);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 지갑 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
import { Payment, PaymentRequest, PaymentResponse } from "@/types/payment";

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
// export function request_sendeth(commit: any, receiver: string) {
//   const url = "/wallet/sendeth";
//   const data = {
//     receiver: receiver,
//   };
//   return axios.post(url, JSON.stringify(data));
// }
export function request_sendeth(
  commit: any,
  payment: PaymentRequest
): Promise<PaymentResponse> {
  console.log(payment);
  const url = "/payments";
  const data = payment.data;
  return axios.post(url, data, {
    headers: {
      Authorization: `Bearer ${payment.jwtToken}`,
    },
  });
}

//잔액 조회하기
export function request_getbalance(commit: any, account: any) {
  const url = "/wallet/balance";
  return axios.get(url, account);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ마이페이지ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */

// 팔로잉/팔로워수
export function request_followcount(commit: any, id: any) {
  const url = `/follow/${id}/count`;
  return axios.get(url);
}

//팔로잉리스트
export function request_followings(commit: any, id: any) {
  const url = `/follow/followings/${id}`;
  return axios.get(url);
}

//팔로워리스트
export function request_followers(commit: any, id: any) {
  const url = `/follow/followers/${id}`;
  return axios.get(url);
}

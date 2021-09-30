import axios from "@/lib/axios";
import { User, UserResponse, UserRequest } from "@/types/user";

// 로그인
export async function request_userLogin(
  // email: any,
  // password: any
  commit: any,
  user: User
): Promise<UserResponse> {
  // const data = {
  //   email: email,
  //   password: password,
  // };
  const data = {
    email: user.email,
    password: user.password,
  };
  console.log(data);
  const url = "/api/v1/login";
  return await axios.post(url, data);
}

// 회원가입
export async function request_userSignup(
  commit: any,
  user: User
): Promise<UserResponse> {
  console.log(user);
  const data = {
    email: user.email,
    name: user.name,
    password: user.password,
  };
  const url = "/api/v1/users";
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

export function request_picupload(commit: any, data: any) {
  console.log(data);
  const url = "/api/v1/items";
  return axios.post(url, data);
}

import axios from "@/lib/axios";
import { User, UserResponse } from "@/types/user";

// 로그인
export async function request_userLogin(
  email: any,
  password: any
): Promise<UserResponse> {
  const data = {
    email: email,
    password: password,
  };
  const url = "/api/users/login";
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
  const url = "/api/users";
  return await axios.post(url, data);
}

import axios from "@/lib/axios";
import { User, UserResponse, UserRequest } from "@/types/user";

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

//asnyc await를 사용할땐 returnType이 : Promise<>로 규약되어있다
//사용하지않으면 : void, : User 사용가능하다

//맨처음 argument는 commit / dispatch / getters / rootGetters / rootState / state 사용
//이후 argument는 ...arrays: any[] or string[] / InterfaceItem

export async function request_userLogin2(): Promise<UserResponse> {
  const url = "/users/login";
  const response = await axios.get(url);
  return response.data;
}

export function request_userCreate(commit: any, users: User): void {
  const url = "/users";
  axios.post(url, users);
}

export async function request_userUpdate(
  user_id: unknown,
  user_name: string
): Promise<UserResponse> {
  const url = `/users/${user_id}`;
  const User = { user_id, user_name };
  const response = await axios.patch<UserRequest>(url, User);
  return response.data;
}

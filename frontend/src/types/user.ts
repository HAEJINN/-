export interface User {
  id: Number;
  name: string;
  email: string;
  password: string;
}

export interface UserRequest {
  data: User;
}

export interface UserResponse {
  data: User;
}

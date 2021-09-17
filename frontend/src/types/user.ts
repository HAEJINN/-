export interface User {
  name: string;
  email: string;
  password: string;
}

export interface UserRequest{
  data: User;
}

export interface UserResponse {
  data: User;
}


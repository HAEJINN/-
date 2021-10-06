export interface User {
  id: number;
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

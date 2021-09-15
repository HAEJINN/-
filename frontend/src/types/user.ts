export interface UserResponse {
  data: User;
}

export interface User {
  email: string;
  name: string;
  password: string;
}

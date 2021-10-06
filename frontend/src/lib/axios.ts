import axios, { AxiosInstance } from "axios";

const instance: AxiosInstance = axios.create({
  // baseURL: "/api/v1",
  // baseURL: "http://localhost:8080",
  baseURL: "http://j5c201.p.ssafy.io/api",
  headers: {
    "Content-type": "application/json",
  },
});

export default instance;

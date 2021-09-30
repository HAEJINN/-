import axios, { AxiosInstance } from "axios";

const instance: AxiosInstance = axios.create({
  // baseURL: "/api",
  baseURL: "http://localhost:8080",
  headers: {
    "Content-type": "application/json",
  },
});

export default instance;

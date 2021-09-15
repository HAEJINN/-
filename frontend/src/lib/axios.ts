import axios, { AxiosInstance } from "axios";

const instance: AxiosInstance = axios.create({
  baseURL: "/api",
  headers: {
    "Content-type": "application/json",
  },
});

export default instance;

import axios from "axios";

export const instanceAxios = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 5000,
  withCredentials: true,
});

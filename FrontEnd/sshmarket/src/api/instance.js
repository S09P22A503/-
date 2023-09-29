/* eslint-disable */
import axios from "axios";

const instance = axios.create({
  baseURL: process.env.REACT_APP_SERVER_URL,
  timeout: 10000,
  headers: {
    "Content-Type": "application/json;charset=UTF-8",
    "Access-Control-Allow-Origin": `http://localhost:3000`,
    "Access-Control-Allow-Credentials": true,
  },
  withCredentials: true,
});

export default instance;

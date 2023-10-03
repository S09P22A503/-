/* eslint-disable */
import axios from "axios";

const pyinstance = axios.create({
  baseURL: process.env.REACT_APP_PYTHON_SERVER_URL
});

export default pyinstance;

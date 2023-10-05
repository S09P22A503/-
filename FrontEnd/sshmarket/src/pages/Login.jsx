import axios from "axios";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useNavigate, useSearchParams } from "react-router-dom";
import styled from "styled-components";
import { Login } from "../modules/memberReducer/action";
import { AiOutlineLoading3Quarters } from "react-icons/ai";

const Container = styled.div`
  margin-top: 400px;
  justify-content: center;
  text-align: center;
  display: flex;
`;

const Icon = styled(AiOutlineLoading3Quarters)`
  animation: rotate_image 6s linear infinite;
  transform-origin: 50% 50%;
  width: 60px;
  height: 60px;
  color: #2e2e2e;
  margin-right: 20px;

  @keyframes rotate_image {
    100% {
      transform: rotate(360deg);
    }
  }
`;

export default function LoginPage() {
  const [params] = useSearchParams();
  const authCode = params.get("code");

  const SERVER_URL = process.env.REACT_APP_SERVER_URL;
  const CLIENT_URL = process.env.REACT_APP_CLIENT_URL;

  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    axios({
      baseURL: SERVER_URL,
      url: "/auth/login",
      method: "POST",
      timeout: 10000,
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
        "Access-Control-Allow-Origin": CLIENT_URL,
        "Access-Control-Allow-Credentials": true,
      },
      withCredentials: true,
      params: {
        code: authCode,
      },
    })
      .then((res) => {
        dispatch(Login({...res.data.data}));
        navigate("/");
      })
      .catch((e) => {
        if (e.response && e.response.status && e.response.status === 303) {
          localStorage.setItem("accessToken", e.response.data.message);
          navigate("/signup");
          return;
        }
        alert(e.response.data.message);
        navigate("/");
      });
  }, []);

  return (
    <Container>
      <Icon></Icon>
      <div
        style={{
          fontSize: "30px",
        }}
      >
        로그인 중입니다.
        <br></br>잠시만 기다리세요.
      </div>
    </Container>
  );
}

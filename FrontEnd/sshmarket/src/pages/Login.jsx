import axios from "axios";
import { useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import styled from "styled-components";

const Container = styled.div`
  height: 100%;
  width: 100%;
  text-align: center;
`;

export default function Login() {
  const [params] = useSearchParams();
  const authCode = params.get("code");

  const CLIENT_URL = process.env.REACT_APP_CLIENT_URL;
  const SERVER_URL = process.env.REACT_APP_SERVER_URL;

  useEffect(() => {
    axios({
      baseURL: SERVER_URL,
      url: SERVER_URL + "login",
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      params: {
        code : authCode,
      }
    })
      .then((res) => {
        alert(res.data.message);
        window.location.replace(CLIENT_URL);
      })
      .catch((e) => {
        if (e.response.status === 303) {
          alert("회원가입 화면으로 이동합니다.")
          localStorage.setItem("accessToken", e.response.data.message)
          window.location.replace(`${CLIENT_URL}signup`)
          return;
        }
        alert(e.response.data.message);
      });
  }, []);

  return <Container>{"로그인 확인 중입니다."}</Container>;
}

import axios from "axios";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useNavigate, useSearchParams } from "react-router-dom";
import styled from "styled-components";
import { Login } from "../modules/memberReducer/action";

const Container = styled.div`
  height: 100%;
  width: 100%;
  text-align: center;
`;

export default function LoginPage() {
  const [params] = useSearchParams();
  const authCode = params.get("code");

  const SERVER_URL = process.env.REACT_APP_SERVER_URL;

  const navigate = useNavigate();
  const dispatch = useDispatch();

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
        dispatch(Login({data: {...res.data.data}}))
        alert(res.data.message);
        navigate("/");
      })
      .catch((e) => {
        console.log(e);
        if (e.response.status === 303) {
          alert("회원가입 화면으로 이동합니다.")
          localStorage.setItem("accessToken", e.response.data.message)
          navigate("/signup")
          return;
        }
        alert(e.response.data.message);
      });
  }, []);

  return <Container>{"로그인 확인 중입니다."}</Container>;
}

import axios from "axios";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { Login } from "../modules/memberReducer/action";

const Container = styled.div`
  
`;

const NicknameInput = styled.input`
  
`

const ProfileInput = styled.input`
  
`

const PreviewProfileBox = styled.img`
  
`

const RegisterButton = styled.button`
  
`



export default function Signup() {

  const SERVER_URL = process.env.REACT_APP_SERVER_URL;

  const [inputNickname, setInputNickname] = useState();
  const [inputProfile, setInputProfile] = useState(new File([],"tmp"));
  const [previewProfile, setPreviewProfile] = useState();

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const changeNickname = (e) => {
    setInputNickname(e.target.value.toString().trim());
  }

  const changeProfile = (e) => {
    const file = e.target.files[0];
    if (!file) {
      alert("정상적인 파일이 아닙니다.")
    }
    setInputProfile(file);
    const reader = new FileReader();
    reader.onload = () => {
      setPreviewProfile(reader.result);
    };
    reader.readAsDataURL(file);
  }

  const register = () => {

    if (inputNickname.length < 4 || inputNickname.length > 20) {
      alert("닉네임은 4자 이상 20자 이하로 입력해주세요.");
      return;
    }

    const accessToken = localStorage.getItem("accessToken");
    if (!accessToken) {
      alert("잘못된 접근입니다.");
      navigate("/");
    }
    
    const formData = new FormData();
    formData.append("code", accessToken);
    formData.append("nickname", inputNickname);
    formData.append("profile", inputProfile);

    axios({
      baseURL: SERVER_URL,
      url: SERVER_URL + "register",
      method: "POST",
      headers: {
        "Content-Type": "multipart/form-data",
      },
      data: formData,
    })
    .then((res) => {
      dispatch(Login({data: {...res.data.data}}))
      alert("회원 가입이 완료되었습니다.");
      localStorage.removeItem("accessToken");
      navigate("/")
    })
    .catch((e) => {
      console.log(e);
      alert(e.response.data.message);
    })
  }

  return (
    <Container>
      <PreviewProfileBox src={previewProfile} alt="프로필 사진"></PreviewProfileBox>
      <ProfileInput onChange={changeProfile} type="file" accept="image/*"></ProfileInput>
      <NicknameInput onChange={changeNickname} type="text"></NicknameInput>
      <RegisterButton onClick={register}>{"회원가입"}</RegisterButton>
      {inputNickname}    
    </Container>
  );
}
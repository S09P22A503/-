import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import MemberProfile from "../common/MemberProfile";
import { useState } from "react";
import { Logout } from "../../modules/memberReducer/action";
import axios from "axios";

const { default: styled } = require("styled-components");

const Container = styled.div`
  height: 80px;
  width: 100%;
  background-color: transparent;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-top: 1em;
  margin-bottom: 2em;
`;

const LogoTitleContainer = styled.div`
  padding-left: 3em;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "HSSummer";
  font-size: xx-large;
  color: var(--primary);
  cursor: pointer;
`;

const SearchContainer = styled.div`
  padding: 3em;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const SearchBar = styled.div`
  width: 30em;
  height: 3em;
  margin: 1em;
  border: 3px solid var(--primary);
  border-radius: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const SearchInput = styled.input`
  width: 33em;
  height: 3em;
  border: 1px solid transparent;
  outline: none;
`;

const BeforeLoginContainer = styled.div`
  padding: 3em;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const AfterLoginContainer = styled.div`
  padding: 3em;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const LoginSignup = styled.a`
  text-decoration: none;
  color: inherit;

  &:active {
    color: inherit;
  }
`;

const ProfileContainer = styled.div`
  position: relative;
  cursor: pointer;
`;

const MenuList = styled.div`
  position: absolute;
  top: 100%;
  right: 0;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
`;

const MenuItem = styled.div`
  border: 1px solid grey;
  padding: 10px;
  cursor: pointer;
`;

export default function Header() {
  const SERVER_URL = process.env.REACT_APP_SERVER_URL;

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const member = useSelector((state) => state.MemberReducer.data);

  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const goMyPage = () => {
    navigate("/mypage");
    closeMenu();
  };

  const goTrade = () => {
    navigate("/trade");
    closeMenu();
  };

  const goWrite = () => {
    navigate("article/write");
    closeMenu();
  };

  const logout = () => {
    axios({
      baseURL: SERVER_URL,
      url: SERVER_URL + "auth/logout",
      method: "POST",
    }).then((res) => {
      dispatch(Logout());
      alert(res.data.message);
      navigate("/");
    });
  };

  const toggleMenu = () => {
    setIsMenuOpen((prev) => !prev);
  };

  const closeMenu = () => {
    setIsMenuOpen(false);
  };

  const menuNameList = ["마이페이지", "생소 Talk", "판매글 등록", "로그아웃"];
  const menuEventList = [goMyPage, goTrade, goWrite, logout];

  return (
    <Container>
      <LogoTitleContainer
        onClick={() => {
          navigate("/");
        }}
      >
        {"생소한 마켓"}
      </LogoTitleContainer>
      <SearchContainer>
        <SearchBar>
          <SearchInput placeholder=" 검색어를 입력해주세요."></SearchInput>
        </SearchBar>
      </SearchContainer>
      {!member ? (
        <BeforeLoginContainer>
          <LoginSignup href="https://accounts.google.com/o/oauth2/auth?client_id=780664099270-6fkn1r7iq6p9eihagmebg9do4j1mm4vd.apps.googleusercontent.com&redirect_uri=http://localhost:3000/login/oauth2/code/google&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email">
            {"로그인/회원가입"}
          </LoginSignup>
        </BeforeLoginContainer>
      ) : (
        <AfterLoginContainer>
          <ProfileContainer
            onClick={() => {
              toggleMenu();
            }}
          >
            <MemberProfile member={member}></MemberProfile>
            <MenuList hidden={!isMenuOpen}>
            {menuNameList.map((name, i) => {
              return (
                <MenuItem key={i} onClick={() => menuEventList[i]()}>
                  {name}
                </MenuItem>
              );
            })}
          </MenuList>
          </ProfileContainer>
        </AfterLoginContainer>
      )}
    </Container>
  );
}

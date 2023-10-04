import styled from "styled-components";
import TradeHistory from "../components/mypage/TradeHistory";
import React, { useEffect, useState } from "react";
import MyArticle from "../components/mypage/MyArticle";
import MyBookmark from "../components/mypage/MyBookmark";
import MyProfile from "../components/mypage/MyProfile";
import MyReview from "../components/mypage/MyReview";
import { useSelector } from "react-redux";
import { useLocation, useNavigate } from "react-router-dom";

const Container = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;
`;

const NavBar = styled.div`
  margin-top: 1em;
  margin-bottom: 2em;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
`;

const NavMenu = styled.div`
  display: flex;
  width: 15%;
  height: 50px;
  justify-content: center;
  align-items: center;
  font-size: large;
  font-weight: bold;
  color: ${(props) => (props.iscurr ? "var(--primary)" : "black")};
  border-bottom: ${(props) => (props.iscurr ? "5px solid var(--secondary)" : "")};
  cursor: pointer;
`;

const CurrMenu = styled.div`
  display: flex;
  justify-content: center;
`;

export default function Mypage({menu}) {

  const member = useSelector((state) => state.MemberReducer);
  const navigate = useNavigate();
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const paramValue = searchParams.get('menu');

  const navMenuList = [
    "내 프로필",
    "등록 상품",
    "찜한 상품",
    "구매 이력",
    "리뷰 관리",
  ];

  const menuComponentList = [MyProfile, MyArticle, MyBookmark, TradeHistory, MyReview];

  const [currMenu, setCurrMenu] = useState(menu?menu:0);
  
  const changeMenu = (menu) => {
    setCurrMenu(menu)
  }

  useEffect(() => {
    if (!member.id) {
      alert("잘못된 접근입니다.");
      navigate("/");
    }
  },[])

  useEffect(() => {
    if (paramValue) {
      setCurrMenu(paramValue)
    }
  },[])

  return (
    <Container>
      <NavBar>
        {navMenuList.map((name, i) => {
          return <NavMenu key={i} onClick={()=>changeMenu(i)} iscurr={name === navMenuList[currMenu]}>{name}</NavMenu>;
        })}
      </NavBar>
      <CurrMenu>
        {React.createElement(menuComponentList[currMenu])}
      </CurrMenu>
    </Container>
  );
}

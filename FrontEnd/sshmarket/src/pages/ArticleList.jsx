import axios from "axios";
import { useEffect, useState } from "react";
import styled from "styled-components";
import ArticleCard from "../components/article/ArticleCard";
import Select from "react-select";
import ArticleCardList from "../components/article/organism/ArticleCardList";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const SelectBoxContainer = styled.div`
  display: flex;
`;

const SelectContainger = styled.div`
  width: 440px;
  margin: 10px 20px 30px 0px;
`;

export default function ArticleList() {
  const SERVER = process.env.REACT_APP_SERVER_URL;
  const config = {
    baseURL: SERVER,
    headers: {
      "Content-Type": "application/json",
    },
  };

  const data = [
    {
      articleId: 6,
      title: "제목1",
      mainImage:
        "https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/fv00/image/24VXzJOiAxtt6hE59p1yGOZDdsQ.jpg",
      mass: null,
      amount: 10,
      price: 10000,
      starRating: 4.3,
      reviewCnt: 10,
    },
    {
      articleId: 6,
      title: "제목1",
      mainImage:
        "https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/fv00/image/24VXzJOiAxtt6hE59p1yGOZDdsQ.jpg",
      mass: null,
      amount: 10,
      price: 10000,
      starRating: 4.3,
      reviewCnt: 10,
    },
    {
      articleId: 6,
      title: "제목1",
      mainImage:
        "https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/fv00/image/24VXzJOiAxtt6hE59p1yGOZDdsQ.jpg",
      mass: null,
      amount: 10,
      price: 10000,
      starRating: 4.3,
      reviewCnt: 10,
    },
    {
      articleId: 6,
      title: "제목1",
      mainImage:
        "https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/fv00/image/24VXzJOiAxtt6hE59p1yGOZDdsQ.jpg",
      mass: null,
      amount: 10,
      price: 10000,
      starRating: 4.3,
      reviewCnt: 10,
    },
    {
      articleId: 6,
      title: "제목5",
      mainImage:
        "https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/fv00/image/24VXzJOiAxtt6hE59p1yGOZDdsQ.jpg",
      mass: null,
      amount: 10,
      price: 10000,
      starRating: 4.3,
      reviewCnt: 10,
    },
    {
      articleId: 5,
      title: "제목6",
      mainImage:
        "https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/fv00/image/24VXzJOiAxtt6hE59p1yGOZDdsQ.jpg",
      mass: null,
      amount: 10,
      price: 10000,
      starRating: 4.3,
      reviewCnt: 10,
    },
  ];

  const handleOptionChange = (option) => {
    // setTradeSelect(option);
    console.log(option);
  };

  const TradeOption = [
    { value: "NONE", label: "거래방식 무관" },
    { value: "DIRECT", label: "직거래" },
    { value: "PARCEL", label: "택배" },
  ];
  const LocationOption = [
    { value: 0, label: "지역 무관" },
    { value: 1, label: "서울" },
    { value: 2, label: "부산" },
    { value: 3, label: "대구" },
    { value: 4, label: "인천" },
    { value: 5, label: "광주" },
    { value: 6, label: "대전" },
    { value: 7, label: "울산" },
    { value: 17, label: "세종특별자치시" },
    { value: 8, label: "경기도" },
    { value: 9, label: "강원도" },
    { value: 10, label: "충청북도" },
    { value: 11, label: "충청남도" },
    { value: 12, label: "전라북도" },
    { value: 13, label: "전라남도" },
    { value: 14, label: "경상북도" },
    { value: 15, label: "경상남도" },
    { value: 16, label: "제주도" },
  ];
  const [TradeSelect, setTradeSelect] = useState(TradeOption[0]);

  return (
    <Container>
      <SelectBoxContainer>
        <SelectContainger>
          <Select
            defaultValue={TradeOption[0]}
            options={TradeOption}
            onChange={setTradeSelect}
            isSearchable={false}
          ></Select>
        </SelectContainger>
        <SelectContainger>
          <Select
            defaultValue={LocationOption[0]}
            options={LocationOption}
            onChange={handleOptionChange}
            isSearchable={false}
          ></Select>
        </SelectContainger>
      </SelectBoxContainer>
      <ArticleCardList data={data}></ArticleCardList>
    </Container>
  );
}

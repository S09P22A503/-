import axios from "axios";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import styled from "styled-components";
import ArticleCard from "../article/ArticleCard";

const Container = styled.div`
  margin-top: 3em;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  flex-direction: row;
`;

const ArticleContainer = styled.div`
  margin-bottom: 2em;
`;

export default function MyArticle() {
  const member = useSelector((state) => state.MemberReducer);

  const data = setData({ member });

  return (
    <Container>
      <ArticleContainer>
        <ArticleCard width={280}></ArticleCard>
      </ArticleContainer>
      <ArticleContainer>
        <ArticleCard width={280}></ArticleCard>
      </ArticleContainer>
      <ArticleContainer>
        <ArticleCard width={280}></ArticleCard>
      </ArticleContainer>
      <ArticleContainer>
        <ArticleCard width={280}></ArticleCard>
      </ArticleContainer>
      <ArticleContainer>
        <ArticleCard width={280}></ArticleCard>
      </ArticleContainer>
      <ArticleContainer>
        <ArticleCard width={280}></ArticleCard>
      </ArticleContainer>
      <ArticleContainer>
        <ArticleCard width={280}></ArticleCard>
      </ArticleContainer>
      <ArticleContainer>
        <ArticleCard width={280}></ArticleCard>
      </ArticleContainer>
    </Container>
  );
}

function setData({ member }) {
  const CLIENT_URL = process.env.REACT_APP_CLIENT_URL;
  const SERVER_URL = process.env.REACT_APP_SERVER_URL;

  let response = undefined;

  // axios({
  //     baseURL: SERVER_URL,
  //     url: `/articles/my-article/${member.id}?page=1&size=12`,
  //     method: "GET",
  //     timeout: 10000,
  //     headers: {
  //       "Content-Type": "application/json;charset=UTF-8",
  //       "Access-Control-Allow-Origin": CLIENT_URL,
  //       "Access-Control-Allow-Credentials": true,
  //     },
  //     withCredentials: true,
  //   }).then((res) => {
  //     response = res.data;
  //   });

  return response;
}

import styled from "styled-components";
import ArticleCard from "../article/ArticleCard";
import ArticleCardList from "../article/organism/ArticleCardList";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import axios from "axios";

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

export default function MyBookmark() {
  const member = useSelector((state) => state.MemberReducer);

  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .create({
        baseURL: process.env.REACT_APP_SERVER_URL,
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Credentials": true,
        },
      })
      .get(`articles/bookmark/${member.id}`)
      .then((res) => {
        setData(res.data.data);
        console.log(res.data.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <Container>
      <ArticleCardList></ArticleCardList>
    </Container>
  );
}

import styled from "styled-components";
import ArticleCard from "../article/ArticleCard";
import ArticleCardList from "../article/organism/ArticleCardList";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import axios from "axios";
import { axiosWithToken } from "../../api/axiosWithToken";

const Container = styled.div`
  margin-top: 3em;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  flex-direction: row;
`;

export default function MyBookmark() {
  const member = useSelector((state) => state.MemberReducer);

  const [data, setData] = useState([]);

  useEffect(() => {
    axiosWithToken
      .get(`articles/bookmark/${member.id}`)
      .then((res) => {
        setData(res.data.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <Container>
      <ArticleCardList data={data}></ArticleCardList>
    </Container>
  );
}

import axios from "axios";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import styled from "styled-components";
import ArticleCard from "../article/ArticleCard";
import ArticleCardList from "../article/organism/ArticleCardList";
import { axiosWithToken } from "../../api/axiosWithToken";

const Container = styled.div`
  margin-top: 3em;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  flex-direction: row;
`;

export default function MyArticle() {
  const member = useSelector((state) => state.MemberReducer);

  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);

  function handleData(page) {
    axiosWithToken
      .get(`articles/member/${member.id}?size=15&page=${page}`)
      .then((res) => {
        setData(res.data.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }

  useEffect(() => {
    setPage(0);
    handleData(0);
  }, []);

  return (
    <Container>
      <ArticleCardList data={data} handleData={handleData} page={page} setPage={setPage}></ArticleCardList>
    </Container>
  );
}

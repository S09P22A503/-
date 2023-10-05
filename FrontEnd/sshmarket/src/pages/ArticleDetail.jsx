import styled from "styled-components";
import ArticlePk from "../components/article/ArticlePk";
import ReviewList from "../components/review/organism/ReviewList";
import { useEffect, useState } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";
import { customAxios } from "../api/customAxios";
import { axiosWithToken } from "../api/axiosWithToken";

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

export default function ArticleDetail() {
  const param = useLocation().pathname.split("/")[2];

  const [data, setData] = useState([]);
  const [review, setReview] = useState([]);

  useEffect(() => {
    axiosWithToken
      .get(`articles/${param}`)
      .then((res) => {
        setData(res.data.data);
        console.log(res.data.data);
      })
      .catch((err) => {
        console.log(err);
      });

    axiosWithToken
      .get(`reviews/${param}`)
      .then((res) => {
        console.log(res.data.data);
        setReview(res.data.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [useLocation().pathname]);

  return (
    <Container>
      <ArticlePk res={data}></ArticlePk>
      <ReviewList data={review}></ReviewList>
    </Container>
  );
}

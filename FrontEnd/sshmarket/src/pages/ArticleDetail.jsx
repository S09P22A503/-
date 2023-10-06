import styled from "styled-components";
import ArticlePk from "../components/article/ArticlePk";
import ReviewList from "../components/review/organism/ReviewList";
import { useEffect, useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import { customAxios } from "../api/customAxios";
import { axiosWithToken } from "../api/axiosWithToken";
import SubButton from "../components/Button/SubButton";
import { IoIosArrowBack } from "react-icons/io";

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

const BoardListButton = styled.span`
  margin-right: 10px;
  font-size: 20px;
  margin-bottom: 20px;
`;

const Back = styled(IoIosArrowBack)`
  margin-top: 4px;
`;

const Text = styled.div``;

export default function ArticleDetail() {
  const param = useLocation().pathname.split("/")[2];
  const navigate = useNavigate();

  const [data, setData] = useState([]);
  const [review, setReview] = useState([]);
  const [averageScore, setAverageScore] = useState([0.0]);
  const [reviewCount, setReviewCount] = useState([0]);

  useEffect(() => {
    customAxios
      .get(`articles/${param}`)
      .then((res) => {
        setData(res.data.data);
        console.log(res.data.data);
      })
      .catch((err) => {
        console.log(err);
      });

    customAxios
      .get(`reviews/${param}`)
      .then((res) => {
        console.log(res.data.data);
        setReview(res.data.data);

        const scores = review.map((review) => review.starRating); // 모든 점수 추출
        const totalScore = scores.reduce(
          (acc, starRating) => acc + starRating,
          0
        ); // 총합 계산

        setAverageScore(totalScore / review.length);
        setReviewCount(review.length);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [useLocation().pathname]);

  return (
    <Container>
      <ArticlePk
        res={data}
        starRating={averageScore}
        reviewCnt={reviewCount}
      ></ArticlePk>
      <ReviewList data={review}></ReviewList>
    </Container>
  );
}

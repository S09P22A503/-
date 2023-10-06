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
import { useSelector } from "react-redux/es/hooks/useSelector";

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

  const member = useSelector((state) => state.MemberReducer);

  const [data, setData] = useState([]);
  const [review, setReview] = useState([]);
  const [averageScore, setAverageScore] = useState([0.0]);
  const [reviewCount, setReviewCount] = useState([0]);

  const [entryTime, setEntryTime] = useState(new Date());

  useEffect(() => {
    console.log("param ", param);
  }, []);

  useEffect(() => {
    axios
      .post(
        `${process.env.REACT_APP_PYTHON_SERVER_URL}/collect/data`,
        {
          user_id: member.id ? member.id : 1,
          article_id: parseInt(param),
          dtype: "viewcount",
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
      .catch(() => {});

    const exitTime = new Date();
    const durationMilliseconds = exitTime - entryTime;
    const durationSeconds = Math.round(durationMilliseconds / 1000);

    // 서버에 POST 요청 보내기
    axios.post(`${process.env.REACT_APP_PYTHON_SERVER_URL}/collect/data`, {
      user_id: member.id ? member.id : 1,
      article_id: param,
      dtype: "viewingtime",
      viewingtime: durationSeconds,
    });

    // 시간 업데이트
    setEntryTime(exitTime);

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

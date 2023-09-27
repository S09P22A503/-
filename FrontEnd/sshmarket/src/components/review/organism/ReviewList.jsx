import styled from "styled-components";
import ReviewBox from "../ReviewBox";
import React from "react";
import { useLocation } from "react-router-dom";

const Container = styled.div`
  margin-top: 80px;
`;

const TextContainer = styled.div`
  font-size: x-large;
  font-weight: bold;
  padding-left: 10px;
`;

export default function ReviewList() {
  const data = [
    {
      id: 1,
      member: {
        id: 1,
        nickname: "ㅂㅂ",
        profile:
          "https://i.namu.wiki/i/WGsJjdq_YZ55OqLwDcVy03tPUDeuy2bFGjbv7hGdqeTxhugt9oQVd9skQTplZArzk64Id35mmLbkbcMwWEo2-g.webp",
      },
      starRating: 4,
      message: "맛있어요. 인생 최고의 감자...!",
      createdAt: "2023-09-23",
      images: [
        "https://kormedi.com/wp-content/uploads/2023/05/unnamed-file-240.jpg",
        "https://mediahub.seoul.go.kr/wp-content/uploads/2016/09/61a2981f41200ac8c513a3cbc0010efe.jpg",
        "https://image.dongascience.com/Photo/2021/08/53df2dd5a36c58b02c0c2353f65cb5c8.png",
      ],
    },
    {
      id: 2,
      member: {
        id: 1,
        nickname: "ㅂㅂ",
        profile:
          "https://i.namu.wiki/i/WGsJjdq_YZ55OqLwDcVy03tPUDeuy2bFGjbv7hGdqeTxhugt9oQVd9skQTplZArzk64Id35mmLbkbcMwWEo2-g.webp",
      },
      starRating: 1,
      message: "맛없어요. 우리집 강아지 줬어요",
      createdAt: "2023-09-23",
      images: [
        "https://kormedi.com/wp-content/uploads/2023/05/unnamed-file-240.jpg",
      ],
    },
  ];

  const isArticle =
    useLocation().pathname.split("/")[1] === "article" ? true : false;

  return (
    <Container>
      <TextContainer>상품 리뷰</TextContainer>

      {data.map((review, idx) => (
        <React.Fragment key={idx}>
          {isArticle ? (
            <ReviewBox
              key={review.id}
              memberId={review.member.id}
              nickname={review.member.nickname}
              profile={review.member.profile}
              starRating={review.starRating}
              content={review.message}
              images={review.images}
              createdAt={review.createdAt}
            />
          ) : (
            <ReviewBox
              key={review.id}
              starRating={review.starRating}
              articleTitle={review.article.title}
              content={review.message}
              images={review.images}
              createdAt={review.createdAt}
            />
          )}
        </React.Fragment>
      ))}
    </Container>
  );
}

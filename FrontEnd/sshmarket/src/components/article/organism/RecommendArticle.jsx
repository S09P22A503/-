import styled from "styled-components";
import ArticleCard from "../ArticleCard";
import React from "react";

const Container = styled.div`
  display: flex;
`;

export default function RecommendArticle({}) {
  const data = [
    {
      title: "[자연맛남] 제주직송 포슬포슬 노지감자 5KG (대)",
      mainImage:
        "https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/fv00/image/24VXzJOiAxtt6hE59p1yGOZDdsQ.jpg",
      price: 10000,
      starRating: 4.7,
      reviewCnt: 15,
      articleId: 2,
    },
    {
      title: "[자연맛남] 제주직송 포슬포슬 노지감자 5KG (대)",
      mainImage:
        "https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/fv00/image/24VXzJOiAxtt6hE59p1yGOZDdsQ.jpg",
      price: 10000,
      starRating: 4.7,
      reviewCnt: 15,
      articleId: 2,
    },
    {
      title: "[자연맛남] 제주직송 포슬포슬 노지감자 5KG (대)",
      mainImage:
        "https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/fv00/image/24VXzJOiAxtt6hE59p1yGOZDdsQ.jpg",
      price: 10000,
      starRating: 4.7,
      reviewCnt: 15,
      articleId: 2,
    },
  ];

  return (
    <Container>
      {data.map((review, idx) => (
        <React.Fragment key={idx}>
          <ArticleCard
            title={review.title}
            mainImage={review.mainImage}
            price={review.price}
            starRating={review.starRating}
            reviewCnt={review.reviewCnt}
            articleId={review.articleId}
          ></ArticleCard>
        </React.Fragment>
      ))}
    </Container>
  );
}

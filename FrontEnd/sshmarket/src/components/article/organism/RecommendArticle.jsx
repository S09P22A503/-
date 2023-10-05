import styled from "styled-components";
import ArticleCard from "../ArticleCard";
import React, { useEffect } from "react";
import { readUserRecommendations } from "../../../api/readUserRecommend";
const Container = styled.div`
  display: flex;
`;

export default function RecommendArticle({memberId}) {
  const data = []
  useEffect(() => {
    async function fetchData() {
      await readUserRecommendations({
        responseFunc: {
          200: (response) => {
            console.log(response)
          },
        },
        memberId
      });
    }
    fetchData();
  }, [])
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

import styled from "styled-components";
import ArticleCard from "../ArticleCard";
import React, { useState, useEffect } from "react";
import {
  readArticles,
  readUserRecommendations,
} from "../../../api/readUserRecommend";
const Container = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
`;

export default function RecommendArticle({ memberId }) {
  const [data, setData] = useState([]);
  const [articleIdList, setArticleIdList] = useState([]);
  useEffect(() => {
    async function fetchData() {
      await readUserRecommendations({
        responseFunc: {
          200: (response) => {
            console.log(response);
            setArticleIdList(response.data.recommendations);
          },
        },
        memberId,
      });
    }
    fetchData();
  }, []);

  useEffect(() => {
    async function fetchData() {
      await readArticles({
        responseFunc: {
          200: (response) => {
            console.log(response.data.data);
            setData(response.data.data);
          },
        },
        articleIdList,
      });
    }
    fetchData();
  }, [articleIdList]);
  return (
    <Container>
      {data
        ? data.map((review, idx) => (
            <React.Fragment key={idx}>
              <ArticleCard
                width={280}
                title={review.title}
                mainImage={review.mainImageUrl}
                price={review.price}
                starRating={review.starRating}
                reviewCnt={review.reviewCnt}
                articleId={review.id}
              ></ArticleCard>
            </React.Fragment>
          ))
        : ""}
    </Container>
  );
}

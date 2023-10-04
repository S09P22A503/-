import styled from "styled-components";
import ArticleCard from "../ArticleCard";

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

const ListContainer = styled.div`
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
`;

const CardContainer = styled.div`
  margin-bottom: 30px;
  margin-right: 20px;
`;

export default function ArticleCardList({ data }) {
  return (
    <Container>
      {data.title && (
        <ListContainer>
          {data.map((res, index) => (
            <CardContainer>
              <ArticleCard
                key={index}
                title={res.title}
                mainImage={res.mainImage}
                price={res.price}
                amount={res.amount}
                mass={res.mass}
                starRating={res.starRating}
                reviewCnt={res.starRating}
                articleId={res.articleId}
                width={210}
                height={200}
              ></ArticleCard>
            </CardContainer>
          ))}
        </ListContainer>
      )}
    </Container>
  );
}

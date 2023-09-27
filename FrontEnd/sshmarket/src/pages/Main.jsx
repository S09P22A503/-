import styled from "styled-components";
import ArticleCard from "../components/article/ArticleCard";

const Container = styled.div`
`;

const MainImage = styled.video`
  width: 100%;
  height: auto;
  background-color: black;
`

const CategoryBoxContainer = styled.div`
  margin-top: 3em;
  border: 1px solid black;
  height: 150px;
`

const CartegoryBox = styled.div`
  
`

const MainArticleContainer = styled.div`
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
  flex-direction: row;
`

export default function Main() {
  return (
    <Container>
      <MainImage autoPlay muted loop>
        <source src="mainimage.mp4"></source>
      </MainImage>
      <CategoryBoxContainer>

      </CategoryBoxContainer>
      <MainArticleContainer>
        <ArticleCard></ArticleCard>
        <ArticleCard></ArticleCard>
        <ArticleCard></ArticleCard>
        <ArticleCard></ArticleCard>
        <ArticleCard></ArticleCard>
        <ArticleCard></ArticleCard>
        <ArticleCard></ArticleCard>
        <ArticleCard></ArticleCard>
      </MainArticleContainer>
    </Container>
  );
}
import styled from "styled-components";
import ArticleCard from "../components/article/ArticleCard";

const Container = styled.div``;

const MainImage = styled.video`
  width: 100%;
  height: auto;
  background-color: black;
  pointer-events: none;
`;

const CategoryBoxContainer = styled.div`
  margin-top: 3em;
  height: 150px;
  display: flex;
  justify-content: space-between;
`;

const CartegoryBox = styled.img`
  border: 5px solid var(--secondary);
  border-radius: 15px;
  cursor: pointer;
`;

const MainArticleContainer = styled.div`
  margin-top: 3em;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  flex-direction: row;
`;

const ArticleContainer = styled.div`
  margin-bottom: 2em;
`

export default function Main() {
  const categoryArray = [
    "없음",
    "쌀/잡곡",
    "채소",
    "견과/건과",
    "축산/계란",
    "수산물/건어물",
    "과일",
  ];
  const categoryIndexArray = [1, 2, 3, 4, 5, 6];

  const clickCategory = (i) => {
    alert(`카테고리 ${categoryArray[i]} 클릭`);
  };

  return (
    <Container>
      <MainImage autoPlay muted loop>
        <source src="mainimage.mp4"></source>
      </MainImage>
      <CategoryBoxContainer>
        {categoryIndexArray.map((i) => {
          let source = "categoryImage/" + i + ".jpg";
          return (
            <CartegoryBox
              key={i}
              src={source}
              onClick={() => clickCategory(i)}
            ></CartegoryBox>
          );
        })}
      </CategoryBoxContainer>
      <MainArticleContainer>
        <ArticleContainer>
          <ArticleCard></ArticleCard>
        </ArticleContainer>
        <ArticleContainer>
          <ArticleCard></ArticleCard>
        </ArticleContainer>
        <ArticleContainer>
          <ArticleCard></ArticleCard>
        </ArticleContainer>
        <ArticleContainer>
          <ArticleCard></ArticleCard>
        </ArticleContainer>
        <ArticleContainer>
          <ArticleCard></ArticleCard>
        </ArticleContainer>
        <ArticleContainer>
          <ArticleCard></ArticleCard>
        </ArticleContainer>
        <ArticleContainer>
          <ArticleCard></ArticleCard>
        </ArticleContainer>
        <ArticleContainer>
          <ArticleCard></ArticleCard>
        </ArticleContainer>
      </MainArticleContainer>
    </Container>
  );
}

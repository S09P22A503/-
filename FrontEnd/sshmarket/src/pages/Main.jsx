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

const CategoryItem = styled.div`
  height: 150px;
  width: auto;
  position: relative;
  display: flex;
`;

const CategoryBox = styled.img`
  border: 5px solid var(--secondary);
  border-radius: 15px;
  cursor: pointer;
`;

const CategoryText = styled.div`
  position: absolute;
  left: 10%;
  bottom: 8px;
  width: 80%;
  text-align: center;
  z-index: 999;
  font-size: larger;
  font-weight: bold;
  background-color: white;
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
`;

export default function Main() {
  const categoryArray = [
    undefined,
    "쌀/잡곡",
    "채소",
    "식용작물",
    "과일",
    undefined,
    "수산물/건어물",
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
          let boxid = "categorybox" + i;
          return categoryArray[i] ? (
            <CategoryItem>
              <CategoryBox
                key={i}
                src={source}
                onClick={() => clickCategory(i)}
                id={boxid}
              ></CategoryBox>
              <CategoryText onClick={() => document.getElementById(boxid).click()}>{categoryArray[i]}</CategoryText>
            </CategoryItem>
          ) : undefined;
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

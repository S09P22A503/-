import styled from "styled-components";
import ArticlePk from "../components/article/ArticlePk";
import ReviewList from "../components/review/organism/ReviewList";

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

export default function ArticleDetail() {
  return (
    <Container>
      <ArticlePk></ArticlePk>
      <ReviewList></ReviewList>
    </Container>
  );
}

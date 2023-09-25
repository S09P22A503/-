import styled from "styled-components";
import ArticlePk from "../components/article/ArticlePk";
import SideBar from "../components/article/SideBar";

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

const ImageContainer = styled.div``;

export default function ArticleDetail() {
  return (
    <Container>
      <ArticlePk></ArticlePk>
    </Container>
  );
}

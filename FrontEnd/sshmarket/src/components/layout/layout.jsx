import styled from "styled-components";
import HeaderBox from "./headerBox";

const Container = styled.div``;

const StyleBar = styled.div`
  width: auto;
  height: 2em;
  background-color: var(--primary);
`;

const LayoutContainer = styled.div`
  width: 100%;
  max-width: 100vw;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
`;

const LayoutBody = styled.div`
  width: 1200px;
  background-color: #ffffff;
  height: 100%;
  margin: 0 auto;
  margin-bottom: 50px;
`;

export default function Layout({ children }) {
  return (
    <Container>
      <StyleBar></StyleBar>
      <LayoutContainer>
        <LayoutBody>
          <HeaderBox />
          {children}
        </LayoutBody>
      </LayoutContainer>
    </Container>
  );
}

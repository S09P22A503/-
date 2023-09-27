import styled from "styled-components";

const Container = styled.div`
  background-color: black;
`;

const MainImage = styled.img`
  width: 100%;
  height: 50px;
`

export default function Main() {
  return (
    <Container>
      <MainImage></MainImage>
    </Container>
  );
}
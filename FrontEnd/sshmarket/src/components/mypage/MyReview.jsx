import styled from "styled-components";
import ReviewBox from "./../review/ReviewBox";
import SubButton from "../Button/SubButton";

const Container = styled.div``;

const ReviewContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-around;
`;

export default function MyReview() {
  return (
    <Container>
      <ReviewContainer>
        <ReviewBox images={[]}></ReviewBox>
        <ButtonContainer>
          <SubButton content={"수정하기"}></SubButton>
          <SubButton content={"삭제하기"}></SubButton>
        </ButtonContainer>
      </ReviewContainer>
      <ReviewContainer>
        <ReviewBox images={[]}></ReviewBox>
        <ButtonContainer>
          <SubButton content={"수정하기"}></SubButton>
          <SubButton content={"삭제하기"}></SubButton>
        </ButtonContainer>
      </ReviewContainer>
      <ReviewContainer>
        <ReviewBox images={[]}></ReviewBox>
        <ButtonContainer>
          <SubButton content={"수정하기"}></SubButton>
          <SubButton content={"삭제하기"}></SubButton>
        </ButtonContainer>
      </ReviewContainer>
    </Container>
  );
}

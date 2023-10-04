import styled from "styled-components";
import ReviewBox from "./../review/ReviewBox";
import SubButton from "../Button/SubButton";
import { useState } from "react";
import ReviewModifyModal from "../review/ReviewModifyModal";

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

const ModalContainer = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999;
`;

export default function MyReview() {
  const [isOpenModifyModal, setIsOpenModifyModal] = useState(false);
  const [targetReview, setTargetReview] = useState();

  const openModifyModal = (review) => {
    setTargetReview(review);
    setIsOpenModifyModal(true);
  };

  const closeModal = () => {
    setTargetReview("");
    setIsOpenModifyModal(false);
  };

  return (
    <Container>
      <ModalContainer hidden={!isOpenModifyModal}>
        <ReviewModifyModal
          review={targetReview}
          closeModal={closeModal}
        ></ReviewModifyModal>
      </ModalContainer>
      <ReviewContainer>
        <ReviewBox images={[]}></ReviewBox>
        <ButtonContainer>
          <SubButton
            content={"수정하기"}
            onClick={() => openModifyModal(undefined)}
          ></SubButton>
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

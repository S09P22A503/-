import styled from "styled-components";
import ReviewBox from "./../review/ReviewBox";
import SubButton from "../Button/SubButton";
<<<<<<< HEAD
import { useState } from "react";
import ReviewModifyModal from "../review/ReviewModifyModal";
=======
import { useEffect, useState } from "react";
import ReviewModifyModal from "../review/ReviewModifyModal";
import axios from "axios";
>>>>>>> 49e650aa35948a4de26f9db73341ea1da7d848ca

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
<<<<<<< HEAD
  const [isOpenModifyModal, setIsOpenModifyModal] = useState(false);
  const [targetReview, setTargetReview] = useState();

=======
  const [reviewList, setReviewList] = useState([]);
  const [isOpenModifyModal, setIsOpenModifyModal] = useState(false);
  const [targetReview, setTargetReview] = useState();

  const SERVER_URL = process.env.REACT_APP_SERVER_URL;
  const CLIENT_URL = process.env.REACT_APP_CLIENT_URL;

>>>>>>> 49e650aa35948a4de26f9db73341ea1da7d848ca
  const openModifyModal = (review) => {
    setTargetReview(review);
    setIsOpenModifyModal(true);
  };

  const closeModal = () => {
    setTargetReview("");
    setIsOpenModifyModal(false);
  };

<<<<<<< HEAD
=======
  useEffect(() => {
    axios({
      baseURL: SERVER_URL,
      url: "/reviews/my-review",
      method: "GET",
      timeout: 10000,
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
        "Access-Control-Allow-Origin": CLIENT_URL,
        "Access-Control-Allow-Credentials": true,
      },
      withCredentials: true,
    })
      .then((res) => {
        setReviewList(res.data.data);
      })
      .catch((e) => {
        alert(e.response.data.message);
      });
  }, []);

  // const deleteReview = () => {

  // }
  
>>>>>>> 49e650aa35948a4de26f9db73341ea1da7d848ca
  return (
    <Container>
      <ModalContainer hidden={!isOpenModifyModal}>
        <ReviewModifyModal
          review={targetReview}
          closeModal={closeModal}
        ></ReviewModifyModal>
      </ModalContainer>
<<<<<<< HEAD
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
=======
      {reviewList.map((review, i) => {
        return (
          <ReviewContainer>
            <ReviewBox
              key={i}
              images={review.images}
              starRating={review.starRating}
              articleTitle={review.article.title}
              content={review.message}
            ></ReviewBox>
            <ButtonContainer>
              <SubButton
                content={"수정하기"}
                onClick={() => openModifyModal(review, closeModal)}
              ></SubButton>
              {/* <SubButton content={"삭제하기"}></SubButton> */}
            </ButtonContainer>
          </ReviewContainer>
        );
      })}
>>>>>>> 49e650aa35948a4de26f9db73341ea1da7d848ca
    </Container>
  );
}

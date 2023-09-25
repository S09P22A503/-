import styled from "styled-components";
import SubButton from "../Button/SubButton";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import StyledButton from "../Button/StyledButton";
import { Carousel } from "react-responsive-carousel";
import { useState } from "react";

const Container = styled.div``;

const ImageContainer = styled.div`
  margin-bottom: 20px;
`;

const ContentsContainer = styled.div``;

const Title = styled.div`
  font-size: x-large;
  font-weight: bold;
  margin-bottom: 20px;
`;

const InfoContainer = styled.div`
  margin: 40px 0px 40px;
`;

const Author = styled.div`
  margin-bottom: 5px;
`;

const Mass = styled.div`
  margin-bottom: 5px;
`;
const Amount = styled.div`
  margin-bottom: 5px;
`;
const Price = styled.div`
  margin-bottom: 5px;
`;

const ButtonContainer = styled.div`
  display: flex;
`;

const ComponentContainer = styled.div`
  height: 300px;
  background-color: #d2d2d2;
  margin-bottom: 30px;
`;

const ContentContainer = styled.div`
  text-align: center;
  font-size: large;
`;

const TextContainer = styled.div`
  font-size: x-large;
  font-weight: bold;
  margin: 40px 0px 20px;
`;

export default function ArticlePk() {
  const data = {
    title: "[자연맛남] 제주직송 포슬포슬 노지감자 5KG (대)",
    content: "내용입니다.",
    member: {
      id: 1,
      nickname: "닉네임",
      image: "/image/NoImage.jpg",
    },
    mass: 100,
    amount: 10,
    price: 10000,
    images: [
      "https://i.namu.wiki/i/WGsJjdq_YZ55OqLwDcVy03tPUDeuy2bFGjbv7hGdqeTxhugt9oQVd9skQTplZArzk64Id35mmLbkbcMwWEo2-g.webp",
      "https://www.kocis.go.kr/CONTENTS/editImage/usr_1691028337236.jpg",
      "https://file2.nocutnews.co.kr/newsroom/image/2023/08/03/202308030957294431_0.jpg",
    ],
  };

  const renderSlides = data.images.map((image, index) => (
    <div>
      <img src={image} key={index} />
    </div>
  ));
  const [currentIndex, setCurrentIndex] = useState();
  function handleChange(index) {
    setCurrentIndex(index);
  }

  return (
    <Container>
      <ImageContainer>
        <Carousel
          showArrows={true}
          autoPlay={true}
          infiniteLoop={true}
          showThumbs={false}
          selectedItem={data.images[currentIndex]}
          onChange={handleChange}
          width={900}
        >
          {renderSlides}
        </Carousel>
      </ImageContainer>
      <ContentsContainer>
        <ButtonContainer>
          <StyledButton content="❤" width={445} marginright={10}></StyledButton>
          <StyledButton content="채팅하기" width={445}></StyledButton>
        </ButtonContainer>
        <InfoContainer>
          <Title>{data.title}</Title>
          <Author>판매자 : {data.member.nickname}</Author>
          <Mass>질량 : {data.mass} g</Mass>
          <Amount>수량 : {data.amount} 개</Amount>
          <Price>가격 : {data.price} 원</Price>
        </InfoContainer>
        <ComponentContainer>
          <p>그래프가 들어갈 예정</p>
        </ComponentContainer>
        <ContentContainer>{data.content}</ContentContainer>
        <TextContainer>유사한 상품 추천</TextContainer>
        <ComponentContainer>상품 컴포넌트가 들어갈 예정</ComponentContainer>
      </ContentsContainer>
    </Container>
  );
}

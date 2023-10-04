import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Carousel } from "react-responsive-carousel";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import styled from "styled-components";
import StyledButton from "../Button/StyledButton";
import RecommendArticle from "./organism/RecommendArticle";
import { postCreateTrade } from "../../api/trade.js";
import { IoMdHeart } from "react-icons/io";
import PriceChart from "../common/PriceChart";
import MemberProfile from "../common/MemberProfile";
import { customAxios } from "../../api/customAxios";

const Container = styled.div``;

const ImageContainer = styled.div`
  margin-bottom: 20px;
`;

const ContentsContainer = styled.div``;

const FillHeart = styled(IoMdHeart)`
  color: #ff0099;
  width: 20px;
  height: 20px;
  margin-top: 2px;
`;

const EmptyHeart = styled(IoMdHeart)`
  color: white;
  width: 20px;
  height: 20px;
  margin-top: 2px;
`;

const BookmarkButton = styled.button`
  width: 390px;
  /* height: ${(props) => `${props.height}px`}; */

  /* background: var(--secondary); */
  background: #b082c0;
  font-family: inherit;
  padding: 0px;
  font-weight: 600;
  color: white;
  border: 2px solid #393939;
  border-radius: 0.4em;
  /* box-shadow: 0.1em 0.1em; */

  font-size: ${(props) => `${props.fontSize}px`};
  margin-right: 10px;
  /* Add styles for :hover state */
  &:hover {
    transform: translate(-0.05em, -0.05em);
    box-shadow: 0.15em 0.15em;
  }

  /* Add styles for :active state */
  &:active {
    transform: translate(0.05em, 0.05em);
    box-shadow: 0.05em 0.05em;
  }
`;

const Title = styled.div`
  font-size: x-large;
  font-weight: bold;
  margin-bottom: 20px;
`;

const InfoContainer = styled.div`
  margin: 40px 0px 40px;
`;

const InfoDetail = styled.div`
  margin-bottom: 5px;
`;

const ButtonContainer = styled.div`
  display: flex;
`;

const ProfileContainer = styled.div`
  margin-bottom: 30px;
`;

const ContentContainer = styled.div`
  margin-top: 80px;
  text-align: center;
  font-size: large;
`;

const TextContainer = styled.div`
  font-size: x-large;
  font-weight: bold;
  margin: 70px 0px 20px;
`;

export default function ArticlePk({ res }) {
  const defaultImage =
    "https://a503.s3.ap-northeast-2.amazonaws.com/memberProfile/ch-4620081_1280.jpg";

  const [currentIndex, setCurrentIndex] = useState();
  function handleChange(index) {
    setCurrentIndex(index);
  }

  // axios
  // .create(config)
  // .get(`articles/${param}`)
  // .then((res) => {
  //   setData(res.data.data);
  //   console.log(res.data.data);
  // })
  // .catch((err) => {
  //   console.log(err);
  // });

  const handleLike = () => {
    customAxios()
      .post(`/${res.articleId}/bookmarks`)
      .then((res) => {
        res.isLike = !res.isLike;
        console.log("버튼 눌렸음");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handleDislike = () => {
    customAxios()
      .delete(`/${res.articleId}/bookmarks`)
      .then((res) => {
        res.isLike = !res.isLike;
      })
      .catch((err) => console.log(err));
  };

  //articleId, buyerId, sellerId 수정필요
  const navigate = useNavigate();
  const articleId = 50;
  const buyerId = 10;
  const sellerId = 20;
  useEffect(() => {
    console.log(res);
  }, [res]);
  const createTrade = async () => {
    await postCreateTrade({
      responseFunc: {
        200: (response) => {
          const tradeId = response.data.data;
          console.log(tradeId);
          navigate(`/trade/${tradeId}`, { state: { tradeId } });
        },
      },
      data: { articleId, buyerId, sellerId },
    });
  };

  return (
    <Container>
      {res.title && (
        <ImageContainer>
          <Carousel
            showArrows={false}
            autoPlay={true}
            infiniteLoop={true}
            showThumbs={false}
            selectedItem={res.images[currentIndex]}
            onChange={handleChange}
            width={900}
          >
            {res.images.length > 0 ? (
              res.images.map((image, index) => (
                <div>
                  <img src={image} key={index} alt={index} />
                </div>
              ))
            ) : (
              <div>
                <img src={defaultImage} alt={1} />
              </div>
            )}
          </Carousel>
        </ImageContainer>
      )}
      {res.title && (
        <ContentsContainer>
          <ButtonContainer>
            <BookmarkButton>
              {res.isLike ? (
                <FillHeart
                // onClick={handleDislike()}
                ></FillHeart>
              ) : (
                <EmptyHeart
                // onClick={handleLike()}
                ></EmptyHeart>
              )}
            </BookmarkButton>
            <StyledButton
              content="채팅하기"
              width={500}
              onClick={createTrade}
            ></StyledButton>
          </ButtonContainer>
          <InfoContainer>
            <ProfileContainer>
              <MemberProfile member={res.member}></MemberProfile>
            </ProfileContainer>
            <Title>{res.title}</Title>
            <InfoDetail>가격 : {res.price} 원</InfoDetail>
            {res.mass == null ? (
              <></>
            ) : (
              <InfoDetail>질량 : {res.mass} g</InfoDetail>
            )}
            {res.amount == null ? (
              <></>
            ) : (
              <InfoDetail>수량 : {res.amount} 개</InfoDetail>
            )}
            {res.location == null ? (
              <></>
            ) : (
              <InfoDetail>판매지 : {res.location}</InfoDetail>
            )}
          </InfoContainer>
          <PriceChart itemId={res.itemId}></PriceChart>
          <ContentContainer>{res.content}</ContentContainer>
          <TextContainer>유사한 상품 추천</TextContainer>
          <RecommendArticle></RecommendArticle>
        </ContentsContainer>
      )}
    </Container>
  );
}

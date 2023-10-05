import { useLocation, useNavigate, useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
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
import axios from "axios";
import { axiosWithToken } from "../../api/axiosWithToken";

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
  const [bookmark, setBookmark] = useState(res.isLike);

  const [currentIndex, setCurrentIndex] = useState();
  function handleChange(index) {
    setCurrentIndex(index);
  }

  const param = useLocation().pathname.split("/")[2];

  const handleLike = () => {
    bookmark
      ? axiosWithToken
          .delete(`articles/${param}/bookmarks`)
          .then((res) => {
            setBookmark(!bookmark);
          })
          .catch((err) => console.log(err))
      : axiosWithToken
          .post(`articles/${param}/bookmarks`)
          .then((res) => {
            setBookmark(!bookmark);
          })
          .catch((err) => {
            console.log(err);
          });
  };

  const Heart = (like) => {
    return like ? <FillHeart></FillHeart> : <EmptyHeart></EmptyHeart>;
  };

  const navigate = useNavigate();
  const { articleId } = useParams();
  const { id } = useSelector((state) => state.MemberReducer);
  const buyerId = id;
  const [sellerId, setSellerId] = useState("");
  useEffect(() => {
    setSellerId(res.member?.id);
  }, [res]);
  const createTrade = async () => {
    await postCreateTrade({
      responseFunc: {
        200: (response) => {
          const tradeId = response.data.data;
          console.log("ArticlePk-buyerId", buyerId);
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
                <img src={res.mainImage} alt={1} />
              </div>
            )}
          </Carousel>
        </ImageContainer>
      )}
      {res.title && (
        <ContentsContainer>
          <ButtonContainer>
            <BookmarkButton onClick={handleLike}>
              {Heart(bookmark)}
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
          <PriceChart itemId={res.itemId} orientation="vertical"></PriceChart>
          <ContentContainer>{res.content}</ContentContainer>
          <TextContainer>유사한 상품 추천</TextContainer>
          <RecommendArticle></RecommendArticle>
        </ContentsContainer>
      )}
    </Container>
  );
}

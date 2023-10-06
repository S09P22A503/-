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
import Star from "../common/Star";

const Container = styled.div``;

const FlexContainer = styled.div`
  display: flex;
  margin-bottom: 50px;
`;

const ImageContainer = styled.div`
  /* border: 1px solid #c2c2c2; */
  margin-bottom: 20px;
  width: ${({ width }) => width}px; /* 원하는 가로 크기로 설정 */
  height: ${({ height }) => height}px; /* 원하는 세로 크기로 설정 */
  overflow: hidden;
`;

const Image = styled.img`
  object-fit: cover; /* 이미지를 가운데 정렬하고, 비율 유지하지 않고 크롭 */
  z-index: -1;
`;

const StarContainer = styled.div`
  margin: 40px 0px 50px;
  display: flex;
`;

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
  width: 240px;
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
  font-size: 40px;
  font-weight: 500;
  margin-bottom: 30px;
  width: 500px;
  line-height: 50px;
  color: #282828;
`;

const Line = styled.div`
  border-top: 1px solid #a5a5a5;
  margin: 30px 0px;
`;

const InfoContainer = styled.div`
  padding-left: 40px;
  padding-top: 20px;
`;

const InfoDetail = styled.div`
  margin-bottom: 20px;
  margin-left: 10px;
  font-size: 25px;
  color: #2c2c2c;
`;

const ButtonContainer = styled.div`
  margin-top: 20px;
  display: flex;
`;

const ProfileContainer = styled.div`
  /* padding-left: 40px; */
  margin-top: 10px;
  margin-bottom: 40px;
`;

const ScoreContainer = styled.div`
  font-size: 33px;
  font-weight: 300;
  margin-top: 20px;
`;

const CountContainer = styled.div`
  font-size: 30px;
  font-weight: 100;
  margin-top: 20px;
`;
const ContentContainer = styled.div`
  text-align: center;
  font-size: 25px;
  margin-top: 80px;
  margin-bottom: 80px;
`;

const TextContainer = styled.div`
  font-size: 25px;
  font-weight: 600;
  margin: 70px 0px 30px 10px;
`;

export default function ArticlePk({ res, starRating, reviewCnt }) {
  const [bookmark, setBookmark] = useState(res.isLike);

  const [currentIndex, setCurrentIndex] = useState();
  function handleChange(index) {
    setCurrentIndex(index);
  }

  function priceToString(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  }
  const param = useLocation().pathname.split("/")[2];

  const handleLike = () => {
    bookmark
      ? axiosWithToken
          .delete(`articles/${param}/bookmarks`)
          .then((res) => {
            setBookmark(!bookmark);
            res.isLike = bookmark;
          })
          .catch((err) => console.log(err))
      : axiosWithToken
          .post(`articles/${param}/bookmarks`)
          .then((res) => {
            setBookmark(!bookmark);
            res.isLike = bookmark;
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
      <FlexContainer>
        {res.title && (
          <ImageContainer width={700} height={600}>
            <Carousel
              showArrows={false}
              autoPlay={true}
              infiniteLoop={true}
              showThumbs={false}
              selectedItem={res.images[currentIndex]}
              onChange={handleChange}
              width={700}
              dynamicHeight={false}
            >
              {res.images.length > 0 ? (
                res.images.map((image, index) => (
                  <div>
                    <Image height={600} src={image} key={index} alt={index} />
                  </div>
                ))
              ) : (
                <div>
                  <Image src={res.mainImage} alt={1} />
                </div>
              )}
            </Carousel>
          </ImageContainer>
        )}
        {res.title && (
          <InfoContainer>
            <Title>{res.title}</Title>
            <ProfileContainer>
              <MemberProfile member={res.member}></MemberProfile>
            </ProfileContainer>
            <InfoDetail>가격 : {priceToString(res.price)}원</InfoDetail>
            {res.mass == null ? (
              <></>
            ) : (
              <InfoDetail>질량 : {res.mass}g</InfoDetail>
            )}
            {res.amount == null ? (
              <></>
            ) : (
              <InfoDetail>수량 : {res.amount}개</InfoDetail>
            )}
            {res.location == null ? (
              <></>
            ) : (
              <InfoDetail>판매지 : {res.location}</InfoDetail>
            )}
            <StarContainer>
              <Star rating={starRating ? starRating : 0} fontSize={60}></Star>
              <ScoreContainer>
                &nbsp;{starRating ? starRating : 0}
              </ScoreContainer>
              <CountContainer>({reviewCnt ? reviewCnt : 0})</CountContainer>
            </StarContainer>
            <ButtonContainer>
              <BookmarkButton onClick={handleLike}>
                {Heart(bookmark)}
              </BookmarkButton>
              <StyledButton
                content="채팅하기"
                width={240}
                height={45}
                onClick={createTrade}
              ></StyledButton>
            </ButtonContainer>
          </InfoContainer>
        )}
      </FlexContainer>
      <Line></Line>
      <TextContainer>도소매 가격 추이 그래프</TextContainer>
      <PriceChart itemId={res.itemId} orientation="vertical"></PriceChart>
      <ContentContainer>{res.content}</ContentContainer>
      <Line></Line>
      <TextContainer>유사한 상품 추천</TextContainer>
      <RecommendArticle></RecommendArticle>
      <Line></Line>
    </Container>
  );
}

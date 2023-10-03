import styled from "styled-components";
import Star from "../common/Star";

const Container = styled.div`
  width: ${({width}) => width}px;
  margin-right: 15px;
  border-radius: 15px;
  /* box-shadow: 2px 2px 2px #989797; */
`;

const ImageContainer = styled.div`
  width: ${({ width }) => width}px; /* 원하는 가로 크기로 설정 */
  height: ${({ height }) => height}px; /* 원하는 세로 크기로 설정 */
  overflow: hidden;
`;

const Image = styled.img`
  border-radius: 15px 15px 0px 0px;
  width: 100%;
  height: 100%;
  object-fit: cover; /* 이미지를 가운데 정렬하고, 비율 유지하지 않고 크롭 */
`;

const InfoContainer = styled.div`
  border: 1px solid #c2c2c2;
  padding: 10px 10px 0px 10px;
  border-radius: 0px 0px 15px 15px;
`;

const PriceContainer = styled.div`
  margin-top: 8px;
`;

const TitleContainer = styled.div`
  /* font-size: larger; */
  font-weight: bold;
  margin-top: 8px;
`;

const ScoreContainer = styled.div`
  font-size: 100%;
  font-weight: bolder;
  margin-top: 8px;
`;

const StarContainer = styled.div`
  display: flex;
  margin-top: 8px;
`;

const CountContainer = styled.div`
  font-size: 100%;
  margin-top: 8px;
`;

export default function ArticleCard({
  title,
  mainImage,
  price,
  starRating,
  reviewCnt,
  articleId,
  width,
  height
}) {
  return (
    <Container width={width?width:290}>
      <ImageContainer width={width?width:290} height={height?height:300}>
        <Image src={mainImage}></Image>
      </ImageContainer>
      <InfoContainer>
        <TitleContainer>{title}</TitleContainer>
        <PriceContainer>{price}원</PriceContainer>
        <StarContainer>
          <Star rating={starRating}></Star>
          <ScoreContainer>&nbsp;{starRating}</ScoreContainer>
          <CountContainer>({reviewCnt})</CountContainer>
        </StarContainer>
      </InfoContainer>
    </Container>
  );
}

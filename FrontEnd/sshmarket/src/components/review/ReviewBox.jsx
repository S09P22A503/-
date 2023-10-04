import { useLocation } from "react-router-dom";
import styled from "styled-components";
import Star from "../common/Star";
import MemberProfile from "../common/MemberProfile";

const Container = styled.div`
  margin-top: 40px;
  padding-left: 20px;
`;

const Line = styled.div`
  border-top: 1px solid #c2c2c2;
  margin: 30px auto;
  margin-left: -20px;
  width: 920px;
`;

const ProfileContainer = styled.div``;

const ContentContainer = styled.div`
  margin-bottom: 15px;
`;

const TitleContainer = styled.div``;

const InfoContainer = styled.div`
  //별점과 리뷰 작성일이 담긴 컨테이너
  display: flex;
`;

const DateContainer = styled.div`
  color: #595959;
  font-size: small;
  margin-top: 10px;
`;

const ImageListContainer = styled.div`
  display: flex;
`;

const ImageContainer = styled.div`
  width: ${({ width }) => width}px; /* 원하는 가로 크기로 설정 */
  height: ${({ height }) => height}px; /* 원하는 세로 크기로 설정 */
  overflow: hidden;
  margin-right: 10px;
`;

const Image = styled.img`
  width: 100%;
  height: 100%;
  object-fit: cover; /* 이미지를 가운데 정렬하고, 비율 유지하지 않고 크롭 */
  border-radius: 15px;
`;

export default function ReviewBox({
  member,
  key,
  starRating,
  articleTitle,
  content,
  createdAt,
  images,
}) {
  const isMyPage =
    useLocation().pathname.split("/")[1] === "article" ? false : true;

  /*
프로필(판매글) / 글 제목(마이페이지)
별점 & 날짜
내용
사진 좌좍
*/

  return (
    <Container>
      <Line></Line>
      {isMyPage ? (
        <ProfileContainer>
          <MemberProfile member={member}></MemberProfile>
        </ProfileContainer>
      ) : (
        <TitleContainer>{articleTitle}</TitleContainer>
      )}
      <InfoContainer>
        <Star rating={starRating}></Star>

        <DateContainer>&nbsp; &nbsp;{createdAt}</DateContainer>
      </InfoContainer>
      <ContentContainer>{content}</ContentContainer>
      <ImageListContainer>
        {images.map((image, index) => (
          <ImageContainer width={280} height={280}>
            <Image src={image.imageUrl} key={index} />
          </ImageContainer>
        ))}
      </ImageListContainer>
    </Container>
  );
}

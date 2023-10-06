import styled from "styled-components";
import { BsStarFill, BsStarHalf, BsStar } from "react-icons/bs";

const StarContainer = styled.div`
  display: flex;
  margin-bottom: 20px;
  font-size: ${({ fontSize }) => fontSize}px;
`;

const StarFill = styled(BsStarFill)`
  color: #b388eb;
`;

const StarHalf = styled(BsStarHalf)`
  color: #b388eb;
`;

const StarEmpty = styled(BsStar)`
  color: #b388eb;
`;

export default function Star({ rating, fontSize }) {
  const filledStars = Math.floor(rating); // 채워진 별의 개수
  const hasHalfStar = rating % 1 !== 0; // 0.5 이상인 경우 반 별이 존재

  // 별 아이콘을 생성하는 함수
  const renderStars = () => {
    const stars = [];

    for (let i = 0; i < 5; i++) {
      if (i < filledStars) {
        stars.push(<StarFill key={i}></StarFill>);
      } else if (hasHalfStar && i === filledStars) {
        stars.push(<StarHalf key={i}></StarHalf>);
      } else {
        stars.push(<StarEmpty key={i}></StarEmpty>);
      }
    }

    return stars;
  };

  return (
    <StarContainer fontSize={fontSize ? fontSize : 23}>
      {renderStars()}
    </StarContainer>
  );
}

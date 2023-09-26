import styled from "styled-components";
import TradeHistory from "../components/mypage/TradeHistory";

const Container = styled.div`
  display: flex;
  justify-content: center;
`;

export default function Mypage() {
  return (
    <Container>
      <TradeHistory></TradeHistory>
    </Container>
  );
}

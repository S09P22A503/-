/* eslint-disable react-hooks/exhaustive-deps */
import styled from "styled-components";
import { ReactComponent as NoData } from "../../assets/icons/no-data.svg";

function NoChat() {
  return (
    <TradeContainer>
      <NoDataContainer>
        <NoData />
        <NoDataWrapper>대화 기록이 없어요!</NoDataWrapper>
      </NoDataContainer>
    </TradeContainer>
  );
}

const TradeContainer = styled.div``;

const NoDataContainer = styled.div`
  width: 656px;
  height: 600px;
  border: 0.1px solid rgba(0, 0, 0, 0.1);
  background: rgba(0, 0, 0, 0.02);
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.02);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const NoDataWrapper = styled.div`
  color: #000;
  font-size: 30px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  margin-top: 20px;
  opacity: 0.25;
`;

export default NoChat;

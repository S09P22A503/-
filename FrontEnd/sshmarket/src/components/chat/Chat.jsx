/* eslint-disable react-hooks/exhaustive-deps */
import styled from "styled-components";
import { useEffect, useState, useRef } from "react";
import { useSelector } from "react-redux";
import { ReactComponent as Send } from "../../assets/icons/send.svg";
import { useChat } from "../../hooks";
import {
  getTradeMessage,
  getTradeDetail,
  setTradeSell,
  setTradeBuy,
} from "../../api/trade.js";

function Chat({ tradeId, setMessageFlag, member }) {
  const { message, sendMessage, newMessages, ChangeMessages } = useChat({
    tradeId,
  });
  const [messages, setMessages] = useState();
  const [tradeDetail, setTradeDetail] = useState();
  const { id } = useSelector((state) => state.MemberReducer);
  const memberId = id;
  const [showButtons, setShowButtons] = useState(true);

  // TradeBox를 참조하기 위한 useRef 생성
  const tradeBoxRef = useRef(null);

  const handleTradeFinish = async (isSeller, status) => {
    try {
      if (isSeller) {
        // 판매자인 경우에 수행할 동작 추가
        if (status === "CHAT") {
          await setTradeSell({ data: { tradeId } });
          tradeDetail.staus = "ACCEPT";
          alert("판매 완료 처리되었습니다.");
          setShowButtons(false);
        } else if (status === "ACCEPT") {
          alert("이미 판매 완료 처리되었습니다.");
        }
      } else {
        // 구매자인 경우에 수행할 동작 추가
        if (status === "ACCEPT") {
          const requestBody = {
            price: tradeDetail.price,
            title: tradeDetail.articleTitle,
            mainImage: tradeDetail.mainImage,
          };
          await setTradeBuy({ data: { tradeId, requestBody } });
          tradeDetail.staus = "FINISH";
          setShowButtons(false);
          alert("구매 확정 처리되었습니다.");
        } else if (status === "FINISH") {
          alert("이미 구매 확정 처리되었습니다.");
        }
      }
      // Trade가 성공적으로 완료되면 메시지나 상태 업데이트 등을 처리할 수 있음
      // 예: setMessageFlag(true);
    } catch (error) {
      console.error("거래 완료 처리에 실패했습니다.", error);
    }
  };

  useEffect(() => {
    async function fetchData() {
      if (tradeId !== null) {
        await getTradeMessage({
          responseFunc: {
            200: (response) => {
              setMessages(response.data.data);
            },
          },
          data: { tradeId },
        });

        await getTradeDetail({
          responseFunc: {
            200: (response) => {
              setTradeDetail(response.data.data);
            },
          },
          data: { tradeId },
        });
      }
    }
    fetchData();
  }, [tradeId]);

  useEffect(() => {
    // messages나 newMessages가 업데이트될 때마다 스크롤을 맨 아래로 이동
    const tradeBox = tradeBoxRef.current;
    tradeBox.scrollTop = tradeBox.scrollHeight;
    setMessageFlag(newMessages);
  }, [messages, newMessages]);

  return (
    <TradeContainer>
      <TradeTitleContainer>
        <TradeTitleBox>
          <ProfileBox>
            <ProfileImageWrapper src={member.profile}></ProfileImageWrapper>
            <ProfileWrapper>
              <NameWrapper>{member.nickname}</NameWrapper>
              <TitleWrapper>{tradeDetail?.articleTitle}</TitleWrapper>
            </ProfileWrapper>
          </ProfileBox>
          <TradeWrapper>
            <PriceWrapper>{tradeDetail?.price}원</PriceWrapper>
            <TradeButtonBox>
              <TradeFinishButton
                style={{
                  display:
                    !showButtons ||
                    (tradeDetail?.isSeller === false &&
                      tradeDetail?.status === "FINISH") ||
                    (tradeDetail?.isSeller === true &&
                      tradeDetail?.status === "ACCEPT")
                      ? "none"
                      : "block",
                }}
              >
                <TradeFinishWrapper
                  onClick={() => {
                    handleTradeFinish(
                      tradeDetail?.isSeller,
                      tradeDetail.status
                    );
                  }}
                >
                  {tradeDetail?.isSeller === false ? "구매확정" : "판매완료"}
                </TradeFinishWrapper>
              </TradeFinishButton>
              <TradeCancelButton
                style={{
                  display:
                    !showButtons ||
                    (tradeDetail?.isSeller === false &&
                      tradeDetail?.status === "FINISH") ||
                    (tradeDetail?.isSeller === true &&
                      tradeDetail?.status === "ACCEPT")
                      ? "none"
                      : "block",
                }}
              >
                <TradeCancelWrapper
                  onClick={() => {
                    // 여기에 거래취소 버튼 클릭 시 수행할 작업 추가
                    setShowButtons(false); // 거래취소 버튼 클릭 시 버튼 숨기기
                  }}
                >
                  거래취소
                </TradeCancelWrapper>
              </TradeCancelButton>
            </TradeButtonBox>
          </TradeWrapper>
        </TradeTitleBox>
      </TradeTitleContainer>
      <TradeBox ref={tradeBoxRef}>
        {messages &&
          messages.map((message) =>
            message.memberId === memberId ? (
              <RightMessageBox key={message.id}>
                {message.message}
              </RightMessageBox>
            ) : (
              <MessageBox key={message.id}>
                <ProfileImageWrapper src={member.profile}></ProfileImageWrapper>
                <LeftMessageBox>{message.message}</LeftMessageBox>
              </MessageBox>
            )
          )}
        {newMessages &&
          newMessages.map((newMessage, index) =>
            newMessage.memberId === memberId ? (
              <RightMessageBox key={index}>
                {newMessage.message}
              </RightMessageBox>
            ) : (
              <MessageBox key={index}>
                <ProfileImageWrapper src={member.profile}></ProfileImageWrapper>
                <LeftMessageBox>{newMessage.message}</LeftMessageBox>
              </MessageBox>
            )
          )}
      </TradeBox>
      <TypingBox onSubmit={sendMessage}>
        <TypingMessageWrapper
          value={message}
          onChange={ChangeMessages}
          placeholder="메세지를 입력하세요"
        />
        <SendIconWrapper type="submit">
          <Send />
        </SendIconWrapper>
      </TypingBox>
    </TradeContainer>
  );
}

const TradeContainer = styled.div``;

const TradeTitleContainer = styled.div`
  width: 656px;
  height: 95px;
  border: 0.1px solid rgba(0, 0, 0, 0.1);
  background: #fff;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.02);
  display: inline-flex;
`;

const TradeTitleBox = styled.div`
  width: 592px;
  height: 64.5px;
  margin: 12px 20px 12px 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const ProfileBox = styled.div`
  display: inline-flex;
  justify-content: center;
  align-items: center;
  width: 181px;
  height: 43px;
  margin-right: 235px;
`;

const ProfileImageWrapper = styled.img`
  width: 36px;
  height: 36px;
  border-radius: 50%;
`;

const ProfileWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 16px;
  width: 219px;
  height: 43px;
`;

const NameWrapper = styled.div`
  font-size: 18px;
  font-style: normal;
  font-weight: 500;
  line-height: 26px;
  cursor: pointer;
`;

const TitleWrapper = styled.div`
  color: rgba(0, 0, 0, 0.4);
  font-size: 11px;
  font-style: normal;
  font-weight: 400;
  line-height: 16px;
  display: flex;
  justify-content: space-between;
  cursor: pointer;
`;

const TradeWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 176px;
  height: 63px;
  margin-top: 10px;
`;

const PriceWrapper = styled.div`
  font-size: 20px;
  font-style: normal;
  font-weight: 500;
  line-height: 28px;
  text-align: end;
`;

const TradeButtonBox = styled.div`
  display: inline-flex;
  align-items: flex-start;
  margin-top: 5px;
  gap: 8px;
`;

const TradeFinishButton = styled.div`
  display: flex;
  padding: 4px 16px;
  justify-content: center;
  align-items: center;
  gap: 8px;
  border-radius: 4px;
  background: #5f0080;
  border: 1px solid #5f0080;
  cursor: pointer;
`;

const TradeFinishWrapper = styled.div`
  color: #fff;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
`;

const TradeCancelButton = styled.div`
  display: flex;
  padding: 4px 16px;
  justify-content: center;
  align-items: center;
  gap: 8px;
  border-radius: 4px;
  border: 1px solid #5f0080;
  cursor: pointer;
`;

const TradeCancelWrapper = styled.div`
  color: #5f0080;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
`;

const TradeBox = styled.div`
  width: 656px;
  height: 458px;
  overflow-y: auto;
  overflow-x: hidden;
  flex-shrink: 0;
  border: 0.1px solid rgba(0, 0, 0, 0.1);
  background: #f5eaf9;
  display: flex;
  flex-direction: column;
`;

const MessageBox = styled.div`
  flex-shrink: 0;
  display: flex;
  margin: 16px;
`;

const LeftMessageBox = styled.div`
  max-width: 237px;
  min-height: 16px;
  flex-shrink: 0;
  border-radius: 0px 8px 8px 8px;
  background: #fff;
  margin-left: 7px;
  padding: 10px;
`;

const RightMessageBox = styled.div`
  max-width: 237px;
  min-height: 16px;
  flex-shrink: 0;
  border-radius: 8px 0px 8px 8px;
  background: #fff;
  margin: 16px 16px 16px auto;
  padding: 10px;
`;

const TypingBox = styled.form`
  justify-content: center;
  align-items: center;
  text-align: start;
  gap: 50px;
  width: 656px;
  height: 40px;
  border: 0.1px solid rgba(0, 0, 0, 0.1);
  background: #fff;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.02);
  display: inline-flex;
`;

const TypingMessageWrapper = styled.input`
  color: rgba(0, 0, 0, 0.5);
  font-size: 14px;
  font-style: normal;
  width: 650px;
  font-weight: 400;
  line-height: 20px;
  padding-left: 20px;
  border: none;
  outline: none;
`;

const SendIconWrapper = styled.button`
  border: none;
  outline: none;
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px;
  margin-top: 5px;
  background: #fff;
  cursor: pointer;
`;

export default Chat;

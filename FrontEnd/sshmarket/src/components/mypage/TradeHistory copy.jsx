import React, { useEffect, useState } from "react";
import styled from "styled-components";

function TradeHistory() {
  const [tradeHistory, setTradeHistory] = useState([]);
  const memberId = 1; // 실제 회원 ID로 대체하세요

  useEffect(() => {
    // API 엔드포인트 URL을 정의합니다.
    const apiUrl = `/trades/history/${memberId}`;

    // fetch 함수를 사용하여 API 호출을 수행합니다.
    fetch(apiUrl)
      .then((response) => {
        if (!response.ok) {
          throw new Error("네트워크 응답이 올바르지 않습니다");
        }
        return response.json();
      })
      .then((data) => {
        // 상태에서 거래 내역 데이터를 설정합니다.
        setTradeHistory(data); // API 응답이 TradeHistoryResponseDto 배열로 가정합니다
      })
      .catch((error) => {
        console.error("fetch 작업에 문제가 있습니다:", error);
      });
  }, [memberId]);

  return (
    <TradeHistoryContainer>
      {tradeHistory.map((tradeItem, index) => (
        <TradeHistoryListBox key={index}>
          <ProductBox>
            <ProductImage src={tradeItem.mainImage} />
            <ProductInfoBox>
              <ProductTitleWrapper>{tradeItem.title}</ProductTitleWrapper>
              <ProductPriceWrapper>{tradeItem.price}원</ProductPriceWrapper>
              <TradeDateWrapper>{tradeItem.boughtAt}</TradeDateWrapper>
            </ProductInfoBox>
          </ProductBox>
          <ReviewButton>
            <ReviewWrapper>
              {tradeItem.isReviewed ? "리뷰완료" : "리뷰쓰기"}
            </ReviewWrapper>
          </ReviewButton>
        </TradeHistoryListBox>
      ))}
    </TradeHistoryContainer>
  );
}

const TradeHistoryContainer = styled.div``;

const TradeHistoryListBox = styled.div`
  display: flex;
  width: 900px;
  height: 120px;
  justify-content: center;
  align-items: center;
  gap: 100px;
  background: #fff;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  margin-top: 20px;
`;

const ProductBox = styled.div`
  display: flex;
  width: 484px;
  height: 100px;
`;

const ProductImage = styled.img`
  width: 100px;
  height: 100px;
`;

const ProductInfoBox = styled.div`
  margin-left: 20px;
  width: 362px;
  height: 81px;
`;

const ProductTitleWrapper = styled.div`
  width: 362px;
  height: 19px;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  margin-bottom: 20px;
`;

const ProductPriceWrapper = styled.div`
  font-size: 14px;
  font-style: normal;
  font-weight: 600;
  line-height: normal;
  margin-bottom: 20px;
`;

const TradeDateWrapper = styled.div`
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  margin-bottom: 20px;
`;

const ReviewButton = styled.div`
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

const ReviewWrapper = styled.div`
  color: #fff;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
`;

export default TradeHistory;

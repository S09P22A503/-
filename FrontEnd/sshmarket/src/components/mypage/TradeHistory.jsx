import React, { useEffect, useState } from "react";
import styled from "styled-components";
import ReviewWriteModal from "../review/ReviewWriteModal";
import { getTradeHistory } from "../../api/trade.js";
import { useSelector } from "react-redux";
import Pagination from "../common/pagination";

function TradeHistory() {
  const [isOpenReviewModal, setIsOpenReviewModal] = useState(false);
  const [targetArticleId, setTargetArticleId] = useState();
  const [targetTradeId, setTargetTradeId] = useState();
  const [tradeHistory, setTradeHistory] = useState([]);
  const memberId = useSelector((state) => state.MemberReducer).id;
  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);

  const openReviewModal = (tradeId, articleId) => {
    setTargetTradeId(tradeId);
    setTargetArticleId(articleId);
    setIsOpenReviewModal(true);
  };

  const closeModal = () => {
    setTargetTradeId("");
    setTargetArticleId("");
    setIsOpenReviewModal(false);
  };

  function handleData (page) {
    getTradeHistory({
      responseFunc: {
        200: handleApiResponse,
      },
      data: { memberId, page },
    });
  }

  useEffect(() => {
    setPage((prev) => 0);
    // API 엔드포인트 URL을 정의합니다.
    getTradeHistory({
      responseFunc: {
        200: handleApiResponse,
      },
      data: { memberId, page },
    });
  }, [memberId]);

  const handleApiResponse = (response) => {
    if (response.status === 200) {
      // API 호출이 성공하면 데이터를 설정합니다.
      setData(response.data.data)
      setTradeHistory(response.data.data.content);
    } else {
      // API 호출이 실패하면 오류를 처리합니다.
      console.error("API 호출에 실패했습니다.", response);
    }
  };

  return (
    <TradeHistoryContainer>
      <ModalContainer hidden={!isOpenReviewModal}>
        <ReviewWriteModal
          tradeId={targetTradeId}
          articleId={targetArticleId}
          closeModal={closeModal}
        ></ReviewWriteModal>
      </ModalContainer>
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
          {tradeItem.reviewed ? (
            <ReviewFinishButton>
              <RevieFinishwWrapper>리뷰완료</RevieFinishwWrapper>
            </ReviewFinishButton>
          ) : (
            <ReviewWriteButton>
              <ReviewWriteWrapper
                onClick={() =>
                  openReviewModal(tradeItem.id, tradeItem.articleId)
                }
              >
                리뷰쓰기
              </ReviewWriteWrapper>
            </ReviewWriteButton>
          )}
        </TradeHistoryListBox>
      ))}
      <Pagination
        handleData={handleData}
        maxPage={data.totalPages - 1}
        setPage={setPage}
        page={page}
      ></Pagination>
    </TradeHistoryContainer>
  );
}

const TradeHistoryContainer = styled.div``;

const TradeHistoryListBox = styled.div`
  display: flex;
  width: 900px;
  height: 120px;
  justify-content: space-around;
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

const ReviewFinishButton = styled.div`
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

const RevieFinishwWrapper = styled.div`
  color: #fff;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
`;

const ReviewWriteButton = styled.div`
  display: flex;
  padding: 4px 16px;
  justify-content: center;
  align-items: center;
  gap: 8px;
  border-radius: 4px;
  border: 1px solid #5f0080;
  cursor: pointer;
`;

const ReviewWriteWrapper = styled.div`
  color: #5f0080;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
`;

const ModalContainer = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999;
`;

export default TradeHistory;

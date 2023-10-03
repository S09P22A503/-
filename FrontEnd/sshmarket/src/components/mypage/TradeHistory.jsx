import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import ReviewWriteModal from "../review/ReviewWriteModal";
import { getTradeHistory } from '../../api/trade.js';

function TradeHistory() {
    const [isOpenReviewModal, setIsOpenReviewModal] = useState(false);
    const [targetArticleId, setTargetArticleId] = useState();
    const [tradeHistory, setTradeHistory] = useState([]);
    const memberId = 1; // 실제 회원 ID로 대체하세요

    const openReviewModal = (articleId) => {
      setTargetArticleId(articleId);
      setIsOpenReviewModal(true);
    };
  
    const closeModal = () => {
      setTargetArticleId("");
      setIsOpenReviewModal(false);
    }

    useEffect(() => {
        // API 엔드포인트 URL을 정의합니다.
        getTradeHistory({
            responseFunc: {
                200: handleApiResponse,
            },
            data: { memberId },
        });
    }, [memberId]);

    const handleApiResponse = (response) => {
        if (response.status === 200) {
            // API 호출이 성공하면 데이터를 설정합니다.
            console.log(response.data.data.content)
            setTradeHistory(response.data.data.content);
        } else {
            // API 호출이 실패하면 오류를 처리합니다.
            console.error('API 호출에 실패했습니다.', response);
        }
    };

    return (
        <TradeHistoryContainer>
          <ModalContainer hidden={!isOpenReviewModal}>
            <ReviewWriteModal
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
                    <ReviewButton>
                        <ReviewWrapper onClick={tradeItem.reviewed ? null : () => setIsOpenReviewModal((prev) => !prev)}
                        >{tradeItem.reviewed ? '리뷰완료' : '리뷰쓰기'}</ReviewWrapper>
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

const ModalContainer = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999;
`;

export default TradeHistory;

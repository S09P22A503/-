import styled from "styled-components";
import ReviewWriteModal from "../review/ReviewWriteModal";
import { useState } from "react";

function TradeHistory() {
  const [isOpenReviewModal, setIsOpenReviewModal] = useState(false);
  const [targetArticleId, setTargetArticleId] = useState();

  const openReviewModal = (articleId) => {
    setTargetArticleId(articleId);
    setIsOpenReviewModal(true);
  };

  const closeModal = () => {
    setTargetArticleId("");
    setIsOpenReviewModal(false);
  }

  return (
    <TradeHistoryContainer>
      <button onClick={() => setIsOpenReviewModal((prev) => !prev)}>{"버튼"}</button>
      <button onClick={closeModal}>{"닫기"}</button>
      <ModalContainer hidden={!isOpenReviewModal}>
        <ReviewWriteModal
          articleId={targetArticleId}
          closeModal={closeModal}
        ></ReviewWriteModal>
      </ModalContainer>
      <TradeHistoryListBox>
        <ProductBox>
          <ProductImage src="https://health.chosun.com/site/data/img_dir/2020/05/07/2020050702573_0.jpg" />
          <ProductInfoBox>
            <ProductTitleWrapper>
              [자연맛남] 제주직송 포슬포슬 노지감자 5KG (대)
            </ProductTitleWrapper>
            <ProductPriceWrapper>32,000원</ProductPriceWrapper>
            <TradeDateWrapper>2023.09.26</TradeDateWrapper>
          </ProductInfoBox>
        </ProductBox>
        <ReviewButton>
          <ReviewWrapper>리뷰쓰기</ReviewWrapper>
        </ReviewButton>
      </TradeHistoryListBox>
      <TradeHistoryListBox>
        <ProductBox>
          <ProductImage src="https://health.chosun.com/site/data/img_dir/2020/05/07/2020050702573_0.jpg" />
          <ProductInfoBox>
            <ProductTitleWrapper>
              [자연맛남] 제주직송 포슬포슬 노지감자 5KG (대)
            </ProductTitleWrapper>
            <ProductPriceWrapper>32,000원</ProductPriceWrapper>
            <TradeDateWrapper>2023.09.26</TradeDateWrapper>
          </ProductInfoBox>
        </ProductBox>
        <ReviewButton>
          <ReviewWrapper>리뷰쓰기</ReviewWrapper>
        </ReviewButton>
      </TradeHistoryListBox>
      <TradeHistoryListBox>
        <ProductBox>
          <ProductImage src="https://health.chosun.com/site/data/img_dir/2020/05/07/2020050702573_0.jpg" />
          <ProductInfoBox>
            <ProductTitleWrapper>
              [자연맛남] 제주직송 포슬포슬 노지감자 5KG (대)
            </ProductTitleWrapper>
            <ProductPriceWrapper>32,000원</ProductPriceWrapper>
            <TradeDateWrapper>2023.09.26</TradeDateWrapper>
          </ProductInfoBox>
        </ProductBox>
        <ReviewButton>
          <ReviewWrapper>리뷰쓰기</ReviewWrapper>
        </ReviewButton>
      </TradeHistoryListBox>
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

const ModalContainer = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999;
`;

export default TradeHistory;

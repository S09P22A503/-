/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useState, useRef } from "react";
import styled from "styled-components";
import { ReactComponent as Search } from "../../assets/icons/search.svg";
import { ReactComponent as Profile } from "../../assets/icons/profile.svg";
import Dropdown from "./Dropdown";
import { getTradeList, getTradeListByKeyword } from "../../api/trade.js";

function formatDateTime(inputDateString) {
  const inputDate = new Date(inputDateString);
  const today = new Date();

  if (
    inputDate.getDate() === today.getDate() &&
    inputDate.getMonth() === today.getMonth() &&
    inputDate.getFullYear() === today.getFullYear()
  ) {
    const options = {
      hour: "numeric", // 시간을 1자리 숫자 형태(예: "2")로 표시
      minute: "2-digit", // 분을 2자리 숫자 형태(예: "29")로 표시
    };
    return new Intl.DateTimeFormat("ko-KR", options).format(inputDate);
  } else {
    const options = {
      month: "long", // 월을 긴 형태(예: "9월")로 표시
      day: "numeric", // 일을 숫자 형태(예: "29")로 표시
    };
    return new Intl.DateTimeFormat("ko-KR", options).format(inputDate);
  }
}

function ChatList({ setSelectedTradeId }) {
  const keywordInputRef = useRef(null);
  const [keyword, setKeyword] = useState("");

  const states = [
    { id: 1, label: "전체대화", status: "ALL" },
    { id: 2, label: "대화중", status: "CHAT" },
    { id: 3, label: "접수완료", status: "ACCEPT" },
    { id: 4, label: "거래완료", status: "FINISH" },
  ];
  const [selectedState, setSelectedState] = useState({
    id: 1,
    label: "전체대화",
    status: "ALL",
  });
  const [trades, setTrades] = useState();
  const memberId = 10;

  useEffect(() => {
    async function fetchData() {
      await getTradeList({
        responseFunc: {
          200: (response) => {
            console.log("trade", response.data.data);
            setTrades(response.data.data);
          },
        },
        data: { memberId: memberId, status: selectedState.status },
      });
    }
    fetchData();
  }, [selectedState]);

  // 키워드 조회
  const handleKeywordChange = (event) => {
    setKeyword(event.target.value.trim());
  };

  const handleKeywordSubmit = async () => {
    async function fetchData() {
      await getTradeListByKeyword({
        responseFunc: {
          200: (response) => {
            setTrades(response.data.data);
          },
        },
        data: {
          status: selectedState.status,
          keyword: keyword,
        },
      });
    }
    fetchData();
  };

  const handleTradeClick = (tradeId) => {
    setSelectedTradeId(tradeId); // 선택한 거래 ID 업데이트
  };

  return (
    <TradeContainer>
      <TradeStateContainer>
        <TradeStateBox>
          <Dropdown
            width="160px"
            data={states}
            shape="negative"
            selectedOption={selectedState}
            placeholder="전체대화"
            onChange={setSelectedState}
          />
          <TradeSearchBox>
            <TradeSearchWrapper
              placeholder="대화방 검색"
              ref={keywordInputRef}
              value={keyword}
              onChange={handleKeywordChange}
              onKeyDown={(event) => {
                if (event.key === "Enter") {
                  event.preventDefault();
                  keywordInputRef.current.blur();
                  handleKeywordSubmit(event);
                }
              }}
            />
            <SearchIconWrapper onClick={handleKeywordSubmit}>
              <Search />
            </SearchIconWrapper>
          </TradeSearchBox>
        </TradeStateBox>
      </TradeStateContainer>
      <TradeListContainer>
        {trades &&
          trades.map((trade) => (
            <TradeListBox
              key={trade.tradeId}
              onClick={() => handleTradeClick(trade.tradeId)}
            >
              <ProfileImageWrapper>
                <Profile />
              </ProfileImageWrapper>
              <ProfileWrapper>
                <ProfileNameWrapper>
                  {trade.memberResponseDto.nickname}
                </ProfileNameWrapper>
                <LastMessageWrapper>{trade.lastChatMessage}</LastMessageWrapper>
              </ProfileWrapper>
              <DateWrapper>{formatDateTime(trade.createdAt)}</DateWrapper>
            </TradeListBox>
          ))}
      </TradeListContainer>
    </TradeContainer>
  );
}

const TradeContainer = styled.div``;

const TradeStateContainer = styled.div`
  display: flex;
  width: 350px;
  height: 132px;
  padding: 16px 8px;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 12px;
  background: #eee3f1;
  border: 0.1px solid #eee3f1;
`;

const TradeListContainer = styled.div`
  width: 350px;
  height: 400px;
  overflow-y: auto;
  overflow-x: hidden;
  padding: 16px 8px;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 0.1px solid rgba(0, 0, 0, 0.1);
`;

const TradeStateBox = styled.div`
  display: flex;
  width: 340px;
  height: 44px;
  justify-content: space-between;
  align-items: center;
  align-content: center;
  flex-shrink: 0;
  flex-wrap: wrap;
  background: #eee3f1;
`;

const TradeSearchBox = styled.div`
  display: flex;
  width: 340px;
  height: 44px;
  justify-content: center;
  align-items: center;
  gap: 10px;
  flex-shrink: 0;
  background: #fff;
`;

const TradeSearchWrapper = styled.input`
  color: rgba(0, 0, 0, 0.4);
  border: none;
  outline: none;
  text-align: start;
  width: 300px;
  height: 40px;
  font-size: 12px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  margin-left: 15px;
`;

const SearchIconWrapper = styled.div`
  cursor: pointer;
  margin-right: 15px;
`;

const TradeListBox = styled.div`
  cursor: pointer;
  display: flex;
  width: 350px;
  padding: 15px 0px;
  justify-content: center;
  align-items: flex-end;
  gap: 10px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  background: #fff;
`;

const ProfileImageWrapper = styled.div`
  width: 36px;
  height: 36px;
  margin-bottom: 3px;
  margin-left: -20px;
`;

const ProfileWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 10px;
  width: 200px;
  height: 43px;
`;

const ProfileNameWrapper = styled.div`
  color: #000;
  font-size: 18px;
  font-style: normal;
  font-weight: 500;
  line-height: 26px;
`;

const LastMessageWrapper = styled.div`
  color: #000;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: 16px;
  overflow: hidden;
`;

const DateWrapper = styled.div`
  color: #000;
  font-size: 11px;
  font-style: normal;
  font-weight: 400;
  line-height: 16px;
`;

export default ChatList;

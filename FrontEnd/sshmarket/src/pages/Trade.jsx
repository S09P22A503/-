import styled from "styled-components";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import Chat from "../components/chat/Chat";
import NoChat from "../components/chat/NoChat";
import ChatList from "../components/chat/ChatList";

const Container = styled.div``;

const ChatBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 40px 80px;
`;

export default function Trade() {
  const [selectedTradeId, setSelectedTradeId] = useState(null);
  const [messageFlag, setMessageFlag] = useState(null);
  const { tradeId } = useParams();
  const [member, setMember] = useState(null);

  useEffect(() => {
    if (tradeId !== undefined) {
      setSelectedTradeId(tradeId);
      console.log("tradeId", tradeId);
    }
  }, [tradeId]);

  return (
    <Container>
      <ChatBox>
        <ChatList
          setMember={setMember}
          setSelectedTradeId={setSelectedTradeId}
          messageFlag={messageFlag}
        ></ChatList>
        {selectedTradeId === null ? ( // tradeId가 null인 경우 NoDataContainer 출력
          <NoChat />
        ) : (
          <Chat
            member={member}
            tradeId={selectedTradeId}
            setMessageFlag={setMessageFlag}
          ></Chat>
        )}
      </ChatBox>
    </Container>
  );
}

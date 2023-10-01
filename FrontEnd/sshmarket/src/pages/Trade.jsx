import styled from "styled-components";
import { useState } from "react";
import Chat from "../components/chat/Chat";
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
  return (
    <Container>
      <ChatBox>
        <ChatList setSelectedTradeId={setSelectedTradeId}></ChatList>
        <Chat tradeId={selectedTradeId}></Chat>
      </ChatBox>
    </Container>
  );
}

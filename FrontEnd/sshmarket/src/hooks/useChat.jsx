import stomp from "stompjs";
import SockJS from "sockjs-client";
import { useEffect, useState, useRef } from "react";

function useChat({ tradeId }) {
  const REACT_APP_SOCKET_URL = process.env.REACT_APP_SOCKET_URL;
  const stompCilent = useRef({});
  const [message, setMessage] = useState("");
  const [curTradeId, setCurTradeId] = useState(null);
  const [newMessages, setNewMessages] = useState([]);
  const memberId = 10;
  const headers = {
    Authorization:
      "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWQiOjIsIm5pY2tuYW1lIjoiQkJCQkIiLCJpYXQiOjE2OTU2MTMxNzAsImV4cCI6MTY5NjQ3NzE3MH0.AUmzgtVXUN5IGJQGhlrAOwLQ38s8emLFRR_PEE2rMCWaO_MyJfuf7ZVjYZksJWruaJH7haN_xBDGAa1xdb_XYg",
    chatNumber: tradeId,
  };

  /* ------------------ chat ------------------ */
  function onConnected() {
    // user 개인 구독
    stompCilent.current.subscribe(
      `/sub/trade/${tradeId}`,
      function (curMessage) {
        console.log("onConnected 성공", curMessage);
        setNewMessages((newMessages) => [
          ...newMessages,
          JSON.parse(curMessage.body),
        ]);
        console.log("newMessage ", newMessages);
      }
    );
  }

  function connect() {
    const socket = new SockJS(REACT_APP_SOCKET_URL); //REACT_APP_SOCKET_URL=http://localhost:8080/trade
    console.log("connect url", REACT_APP_SOCKET_URL);
    stompCilent.current = stomp.over(socket);
    stompCilent.current.connect(headers, () => {
      setTimeout(function () {
        onConnected();
        setCurTradeId(tradeId);
      }, 500);
    });
  }

  const ChangeMessages = (event) => {
    setMessage(event.target.value);
  };

  const sendMessage = async (e) => {
    e.preventDefault();
    await stompCilent.current.send(
      "/pub/send",
      headers,
      JSON.stringify({
        tradeId: tradeId,
        memberId: memberId,
        message: message,
      })
    );
    setMessage("");
  };

  useEffect(() => {
    if (curTradeId != null) {
      stompCilent.current?.unsubscribe({
        Authorization: window.localStorage.getItem("accessToken"),
        chatNumber: curTradeId,
      });
      stompCilent.current?.disconnect();
    }
    connect();
    setNewMessages([]);
  }, [tradeId]);

  return { message, sendMessage, newMessages, ChangeMessages };
}

export default useChat;

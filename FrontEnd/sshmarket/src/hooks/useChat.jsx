import stomp from "stompjs";
import SockJS from "sockjs-client";
import { useSelector } from "react-redux";
import { useEffect, useState, useRef } from "react";

function useChat({ tradeId }) {
  const REACT_APP_SOCKET_URL = process.env.REACT_APP_SOCKET_URL;
  const stompCilent = useRef({});
  const [message, setMessage] = useState("");
  const [curTradeId, setCurTradeId] = useState(null);
  const [newMessages, setNewMessages] = useState([]);
  const { id } = useSelector((state) => state.MemberReducer);
  function getJwtFromCookie() {
    const cookies = document.cookie.split(";");
    for (const cookie of cookies) {
      const [name, value] = cookie.trim().split("=");
      if (name === "jwt") {
        return value;
      }
    }
    return null; // 'jwt' 이름의 쿠키를 찾지 못한 경우 null 반환
  }

  // JWT 쿠키 가져오기
  const jwtToken = getJwtFromCookie();
  const memberId = id;
  const headers = {
    Authorization: jwtToken,
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
        Authorization: jwtToken,
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

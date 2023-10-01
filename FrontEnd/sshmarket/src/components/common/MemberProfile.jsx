import React from "react";
import styled from "styled-components";

const Container = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 13em;
`;

const Proflie = styled.img`
  max-width: 3em;
  max-height: 3em;
  min-height: 3em;
  min-width: 3em;
  border: transparent;
  border-radius: 50%;
  background-color: black;
  margin-right: 2em;
`;

const Nickname = styled.div`
  text-align: center;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 8em;
`;

export default function MemberProfile({ member }) {
  return (
    <Container>
      <Proflie
        src={member.profile ? member.profile : "defaultProfile.png"}
      ></Proflie>
      <Nickname>{member.nickname}</Nickname>
    </Container>
  );
}

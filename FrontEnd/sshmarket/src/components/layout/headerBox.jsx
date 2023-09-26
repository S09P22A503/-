
const { default: styled } = require("styled-components");


const Container = styled.div`
  height: 80px;
  width: 100%;
  background-color: aquamarine;
  border: 1px solid black;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;

const LogoTitleContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const SearchContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const BeforeLoginContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const AfterLoginContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const LoginSignup = styled.a`
    
`

export default function Header() {
  return (
    <Container>
      <LogoTitleContainer>{"로고가 들어갈 자리"}</LogoTitleContainer>
      <SearchContainer>{"검색 창"}</SearchContainer>
      {true ? (
        <BeforeLoginContainer>
            <LoginSignup href="https://accounts.google.com/o/oauth2/auth?client_id=780664099270-6fkn1r7iq6p9eihagmebg9do4j1mm4vd.apps.googleusercontent.com&redirect_uri=http://localhost:3000/login/oauth2/code/google&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email">
                {"로그인/회원가입"}
            </LoginSignup>
        </BeforeLoginContainer>
      ) : (
        <AfterLoginContainer>{"로그인됨"}</AfterLoginContainer>
      )}
    </Container>
  );
}
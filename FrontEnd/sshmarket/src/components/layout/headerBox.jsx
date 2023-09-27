const { default: styled } = require("styled-components");

const Container = styled.div`
  height: 80px;
  width: 100%;
  background-color: transparent;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-top: 1em;
  margin-bottom: 2em;
`;

const LogoTitleContainer = styled.div`
  padding-left: 3em;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "HSSummer";
  font-size: xx-large;
  color: var(--primary);
`;

const SearchContainer = styled.div`
  padding: 3em;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const SearchBar = styled.div`
  width: 35em;
  height: 3em;
  margin: 1em;
  border: 3px solid var(--primary);
  border-radius: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const SearchInput = styled.input`
  width: 40em;
  height: 3em;
  border: 1px solid transparent;
  outline: none;
`

const BeforeLoginContainer = styled.div`
  padding: 3em;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const AfterLoginContainer = styled.div`
  padding: 3em;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const LoginSignup = styled.a`
  text-decoration: none;
  color: inherit;

  &:active{
    color: inherit;
  }
`;

export default function Header() {
  return (
    <Container>
      <LogoTitleContainer>{"생소한 마켓"}</LogoTitleContainer>
      <SearchContainer>
        <SearchBar>
          <SearchInput placeholder=" 검색어를 입력해주세요."></SearchInput>
        </SearchBar>
      </SearchContainer>
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

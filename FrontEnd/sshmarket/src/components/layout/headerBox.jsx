const { default: styled } = require("styled-components");


const Container = styled.div`
    height: 80px;
    background-color: aquamarine;
    border: 1px solid black;
`

export default function Header() {
    return <Container>{"헤더"}</Container>
}
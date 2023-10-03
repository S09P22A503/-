import { useEffect, useState } from "react";
import styled from "styled-components";

const Container = styled.div`
`

export default function MyArticle() {

    const data = setData()

    return (
        <Container>{data}</Container>
    )
}

function setData() {
    return Math.random();
}
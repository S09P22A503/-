/* eslint-disable react-hooks/exhaustive-deps */
import styled from "styled-components";
import { ReactComponent as Profile } from "../../assets/icons/profile.svg";
import { ReactComponent as Send } from "../../assets/icons/send.svg";
import { ReactComponent as Close } from "../../assets/icons/close-icon.svg";

function Chat() {
    return (
        <>
            <TradeTitleContainer>
                <TradeTitleBox>
                    <ProfileBox>
                        <ProfileImageWrapper>
                            <Profile />
                        </ProfileImageWrapper>
                        <ProfileWrapper>
                            <NameWrapper>생소한 마켓</NameWrapper>
                            <TitleWrapper>23년산 햇감자 3KG (중) 단품</TitleWrapper>
                        </ProfileWrapper>
                    </ProfileBox>
                    <TradeWrapper>
                        <PriceWrapper>20000원</PriceWrapper>
                        <TradeButtonBox>
                            <TradeFinishButton>
                                <TradeFinishWrapper>거래완료</TradeFinishWrapper>
                            </TradeFinishButton>
                            <TradeCancelButton>
                                <TradeCancelWrapper>거래취소</TradeCancelWrapper>
                            </TradeCancelButton>
                        </TradeButtonBox>
                    </TradeWrapper>
                </TradeTitleBox>
                <CloseWrapper>
                    <Close />
                </CloseWrapper>
            </TradeTitleContainer>
            <TradeBox>
                <MessageBox>
                    <Profile />
                    <LeftMessageBox />
                </MessageBox>
                <RightMessageBox />
            </TradeBox>
            <TypingBox>
                <TypingMessageWrapper>메세지를 입력하세요</TypingMessageWrapper>
                <SendIconWrapper>
                    <Send />
                </SendIconWrapper>
            </TypingBox>
        </>
    );
}

const TradeTitleContainer = styled.div`
    width: 656px;
    height: 88px;
    border: 0.1px solid rgba(0, 0, 0, 0.1);
    background: #fff;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.02);
    display: inline-flex;
`;

const TradeTitleBox = styled.div`
    width: 592px;
    height: 64.5px;
    margin: 12px 20px 12px 12px;
    display: flex;
    justify-content: space-between;
    align-items: center;
`;

const ProfileBox = styled.div`
    display: inline-flex;
    justify-content: center;
    align-items: center;
    width: 181px;
    height: 43px;
    margin-right: 235px;
`;

const ProfileImageWrapper = styled.div`
    width: 36px;
    height: 36px;
`;

const ProfileWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    margin-left: 16px;
    width: 219px;
    height: 43px;
`;

const NameWrapper = styled.div`
    font-size: 18px;
    font-style: normal;
    font-weight: 500;
    line-height: 26px;
    cursor: pointer;
`;

const TitleWrapper = styled.div`
    color: rgba(0, 0, 0, 0.4);
    font-size: 11px;
    font-style: normal;
    font-weight: 400;
    line-height: 16px;
    display: flex;
    justify-content: space-between;
    cursor: pointer;
`;

const TradeWrapper = styled.div`
    display: flex;
    flex-direction: column;
    width: 176px;
    height: 63px;
`;

const PriceWrapper = styled.div`
    font-size: 20px;
    font-style: normal;
    font-weight: 500;
    line-height: 28px;
    text-align: end;
`;

const TradeButtonBox = styled.div`
    display: inline-flex;
    align-items: flex-start;
    margin-top: 5px;
    gap: 8px;
`;

const TradeFinishButton = styled.div`
    display: flex;
    padding: 4px 16px;
    justify-content: center;
    align-items: center;
    gap: 8px;
    border-radius: 4px;
    background: #5f0080;
    border: 1px solid #5f0080;
    cursor: pointer;
`;

const TradeFinishWrapper = styled.div`
    color: #fff;
    font-size: 14px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
`;

const TradeCancelButton = styled.div`
    display: flex;
    padding: 4px 16px;
    justify-content: center;
    align-items: center;
    gap: 8px;
    border-radius: 4px;
    border: 1px solid #5f0080;
    cursor: pointer;
`;

const TradeCancelWrapper = styled.div`
    color: #5f0080;
    font-size: 14px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
`;

const CloseWrapper = styled.div`
    display: flex;
    width: 16px;
    height: 16px;
    cursor: pointer;
`;

const TradeBox = styled.div`
    width: 656px;
    height: 458px;
    flex-shrink: 0;
    border: 0.1px solid rgba(0, 0, 0, 0.1);
    background: #f5eaf9;
    display: flex;
    flex-direction: column;
`;

const MessageBox = styled.div`
    width: 237px;
    height: 151px;
    flex-shrink: 0;
    display: flex;
    margin: 16px;
`;

const LeftMessageBox = styled.div`
    width: 202px;
    height: 151px;
    flex-shrink: 0;
    border-radius: 0px 8px 8px 8px;
    background: #fff;
    margin-left: 7px;
`;

const RightMessageBox = styled.div`
    width: 202px;
    height: 151px;
    flex-shrink: 0;
    border-radius: 8px 0px 8px 8px;
    background: #fff;
    margin-left: auto;
    margin-right: 12px;
`;

const TypingBox = styled.div`
    justify-content: center;
    align-items: center;
    gap: 488px;

    width: 656px;
    height: 40px;
    border: 0.1px solid rgba(0, 0, 0, 0.1);
    background: #fff;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.02);
    display: inline-flex;
`;

const TypingMessageWrapper = styled.div`
    color: rgba(0, 0, 0, 0.4);
    font-family: Pretendard;
    font-size: 14px;
    font-style: normal;
    font-weight: 400;
    line-height: 20px;
`;

const SendIconWrapper = styled.div`
    color: rgba(0, 0, 0, 0.4);
    font-family: Pretendard;
    font-size: 14px;
    font-style: normal;
    font-weight: 400;
    line-height: 20px;
    cursor: pointer;
`;

export default Chat;

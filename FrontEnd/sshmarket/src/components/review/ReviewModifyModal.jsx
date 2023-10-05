import styled from "styled-components";
import { BsStar, BsStarFill } from "react-icons/bs";
import { useEffect, useState } from "react";
import StyledButton from "../Button/StyledButton";
import axios from "axios";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: var(--background);
  width: 400px;
  height: 580px;
  border-radius: 20px;
`;

const MessageContainer = styled.div`
  margin-top: 1em;
  margin-bottom: 1em;
  text-align: center;
  font-size: x-large;
  font-weight: bold;
`;

const StarContainer = styled.div`
  display: flex;
  justify-content: space-around;
  width: 80%;
`;

const ContentContainer = styled.div`
  margin-top: 1em;
  margin-bottom: 1em;
  width: 80%;
`;

const ContentInput = styled.textarea`
  width: 100%;
  resize: none;
  font-family: "pretendard";
  background-color: var(--gray-200);
`;

const FileInputContainer = styled.div`
  display: flex;
  flex-direction: row;
  width: 80%;
  justify-content: space-between;
`;

const FileInputBox = styled.div`
  width: 30%;
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: var(--gray-300);
  border: 1px solid var(--gray-300);
`;

const FilePreview = styled.img`
  min-height: auto;
  max-height: 100px;
  width: auto;
  max-width: 100px;
`;

const FileInput = styled.input``;

const StarFill = styled(BsStarFill)`
  font-size: 50px;
  color: var(--secondary);
  cursor: pointer;
`;

const StarEmpty = styled(BsStar)`
  font-size: 50px;
  color: var(--secondary);
  cursor: pointer;
`;

const ButtonContainer = styled.div`
  margin-top: 1em;
  margin-bottom: 1em;
  display: flex;
  width: 80%;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
`;

const CloseCheck = styled.div``;

export default function ReviewModifyModal({ review, closeModal }) {
  const SERVER_URL = process.env.REACT_APP_SERVER_URL;
  const CLIENT_URL = process.env.REACT_APP_CLIENT_URL;

<<<<<<< HEAD
  const [rating, setRating] = useState(
    review
      ? new Array(review.starRating)
          .fill(true)
          .concat(new Array(5 - review.starRating).fill(false))
      : ["true", "true", "true", "true", "true"]
  );
  const [content, setContent] = useState(review ? review.message : "");
=======
  const [rating, setRating] = useState([true, true, true, true, true]);
  const [content, setContent] = useState("");
>>>>>>> 49e650aa35948a4de26f9db73341ea1da7d848ca
  const [fileList, setFileList] = useState([
    new File([], "tmp"),
    new File([], "tmp"),
    new File([], "tmp"),
  ]);
<<<<<<< HEAD
  const [previewList, setPreviewList] = useState(
    review
      ? review.images.concat(new Array(3 - review.images.length).fill(""))
      : ["", "", ""]
  );
  const [fileIndex, setFileIndex] = useState(
    review ? review.images.length : 0
  );

  useEffect(() => {
    if (!review || !review.images) return;
    let newFileList = [];
    review.images.forEach(async (e,i) => {
      try {
        const res = await fetch(e);
        const blob = await res.blob();
        newFileList.push(new File(blob,"origin"+i));
      } catch {
        alert("리뷰 이미지를 불러오는 중 문제가 발생했습니다.");
      }
    })
    newFileList.concat(new Array(3-newFileList.length).fill(new File([],"tmp")));
    setFileList((prev) => newFileList);
  },[])
=======
  const [previewList, setPreviewList] = useState(["", "", ""]);
  const [fileIndex, setFileIndex] = useState(0);

  useEffect(() => {
    if (!review) return;
    const oldImageCount = review.images.length;
    setFileIndex(oldImageCount);
    setRating(new Array(review.starRating).fill(true).concat(new Array(5 - review.starRating).fill(false)));
    setContent(review.message);
    setPreviewList(review.images.concat(new Array(3 - oldImageCount).fill("")));
    let newFileList = [];
    review.images.forEach(async (e, i) => {
      try {
        let blob = undefined;
        await fetch(e).then((res) => {
          blob = res.blob();
        });
        newFileList.push(new File(blob, "origin" + i));
      } catch {
        alert("리뷰 이미지를 불러오는 중 문제가 발생했습니다.");
      }
    });
    newFileList.concat(
      new Array(3 - newFileList.length).fill(new File([], "tmp"))
    );
    setFileList((prev) => newFileList);
  }, [review]);
>>>>>>> 49e650aa35948a4de26f9db73341ea1da7d848ca

  const changeRate = (i) => {
    let newRate = [];
    for (let index = 0; index < i + 1; index++) {
      newRate.push(true);
    }
    for (let index = i + 1; index < 5; index++) {
      newRate.push(false);
    }
    setRating(newRate);
  };

  const changeContent = (e) => {
    setContent(e.target.value.trim());
  };

  const uploadFile = (e) => {
    if (fileIndex >= 3) return;
    const file = e.target.files[0];
    if (!file) {
      alert("정상적인 파일이 아닙니다.");
      return;
    }
    setFileList((prev) => {
      let newFileList = [...prev];
      newFileList[fileIndex] = file;
      return newFileList;
    });
    const reader = new FileReader();
    reader.onload = () => {
      setPreviewList((prev) => {
        let newPreviewList = [...prev];
        newPreviewList[fileIndex] = reader.result;
        return newPreviewList;
      });
    };
    reader.readAsDataURL(file);
    setFileIndex((prev) => prev + 1);
  };

  const deleteFile = (i) => {
    let newFileList = fileList;
    let newPreviewList = previewList;
    for (let index = i; index < 2; index++) {
      newFileList[index] = newFileList[index + 1];
      newPreviewList[index] = newPreviewList[index + 1];
    }
    newFileList[2] = new File([], "tmp");
    newPreviewList[2] = "";
    setFileList(newFileList);
    setPreviewList(newPreviewList);
    setFileIndex((prev) => prev - 1);
  };

  const resetState = () => {
    setRating([true, true, true, true, true]);
    setContent("");
    setFileList([
      new File([], "tmp"),
      new File([], "tmp"),
      new File([], "tmp"),
    ]);
    setPreviewList(["", "", ""]);
    setFileIndex(0);
    document.getElementById("contentinput").value = "";
  };

  const updateReview = () => {
    if (!content) {
      alert("리뷰를 작성해주세요.");
    }
    let formData = new FormData();
    let reviewImages = fileList.filter((e) => e.size !== 0);
    formData.append("id", review.id);
    formData.append("message", content);
    formData.append("starRating", rating.filter((e) => e === true).length);
    formData.append("savedReviewIds", []);
    reviewImages.forEach((e) => {
      formData.append("newReviewImages", e);
    });

    axios({
      baseURL: SERVER_URL,
      url: "/reviews",
      method: "PATCH",
      timeout: 10000,
      headers: {
        "Content-Type": "multipart/form-data",
        "Access-Control-Allow-Origin": CLIENT_URL,
        "Access-Control-Allow-Credentials": true,
      },
      withCredentials: true,
      data: formData,
    })
      .then((res) => {
        alert(res.data.message);
        window.location.replace("/mypage?menu=4");
      })
      .catch((e) => {
        alert(e.response.data.message);
      });
  };

  return (
    <Container>
      <MessageContainer>{"이 상품을 추천하시겠어요?"}</MessageContainer>
      <StarContainer>
        {rating.map((value, i) => {
          return value ? (
            <StarFill key={i} onClick={() => changeRate(i)}></StarFill>
          ) : (
            <StarEmpty key={i} onClick={() => changeRate(i)}></StarEmpty>
          );
        })}
      </StarContainer>
      <ContentContainer>
        <ContentInput
          rows={15}
          placeholder={
            "사진과 함께 리뷰 작성해주시면 더욱 좋아요! \n(200자 이내)"
          }
          onChange={changeContent}
          id="contentinput"
<<<<<<< HEAD
          defaultValue={review?review.message:""}
=======
          defaultValue={review ? review.message : ""}
>>>>>>> 49e650aa35948a4de26f9db73341ea1da7d848ca
        ></ContentInput>
      </ContentContainer>
      <FileInputContainer>
        {previewList.map((value, i) => {
          return (
            <FileInputBox key={i}>
              <FilePreview
                src={value ? value : "defaultProfile.png"}
                onClick={
                  fileIndex > i
                    ? () => deleteFile(i)
                    : () => window.document.getElementById("fileInput").click()
                }
              ></FilePreview>
            </FileInputBox>
          );
        })}

        <FileInput
          type="file"
          accept=".jpg,.jpeg,.png"
          id="fileInput"
          hidden={true}
          onChange={uploadFile}
        ></FileInput>
      </FileInputContainer>
      <ButtonContainer>
        <CloseCheck onClick={resetState} id="reset">
          <StyledButton
            content={"취소"}
            onClick={closeModal}
            id="closebtn"
          ></StyledButton>
        </CloseCheck>
        <StyledButton content={"수정"} onClick={updateReview}></StyledButton>
      </ButtonContainer>
    </Container>
  );
}

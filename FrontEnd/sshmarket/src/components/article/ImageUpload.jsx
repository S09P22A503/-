import React, { useState, useRef,useEffect} from "react";

function ImageUpload({ image, setImage, mainImageChanged=false,setMainImageChanged=undefined}) {
  const [previewURL, setPreviewURL] = useState(null);
  const fileInputRef = useRef(null); // 파일 입력 요소 참조 생성

  const handleChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImage(file);
      setPreviewURL(URL.createObjectURL(file));
    } else {
      resetImage();
    }
    if(setMainImageChanged) setMainImageChanged(true);
  };

  useEffect(()=>{
    if(image){
      setPreviewURL(URL.createObjectURL(image));
    }
  },[image])

  const resetImage = () => {
    setImage(null);
    setPreviewURL(null);
    if (fileInputRef.current) {
      fileInputRef.current.value = ""; // 파일 입력 요소의 값을 초기화
    }
  };

  return (
    <div>
      <input
        type="file"
        onChange={handleChange}
        accept="image/*"
        ref={fileInputRef} // 참조 연결
      />
      {previewURL && (
        <div>
          <img
            src={previewURL}
            alt="Uploaded preview"
            style={{
              maxWidth: "400px",
              border: "3px solid #B388EB",
              borderRadius: "10px",
              boxShadow: "5px 5px 10px rgba(0, 0, 0, 0.1)",
              marginTop: "20px",
            }}
          />
          <button
            onClick={resetImage}
            style={{ display: "block", marginTop: "10px" }}
          >
            이미지 제거
          </button>
        </div>
      )}
    </div>
  );
}

export default ImageUpload;

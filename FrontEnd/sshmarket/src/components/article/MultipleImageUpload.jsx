import React from 'react';

function MultipleImageUpload({ images, setImages }) {
  const fileInputRef = React.createRef(); // 파일 입력 참조 생성

  const handleChange = (e) => {
    const files = Array.from(e.target.files).slice(0, 3);  // 최대 3개의 파일만 선택
    const updatedImages = [...images, ...files];
    if (updatedImages.length > 3) {
      alert('최대 3개의 이미지만 업로드할 수 있습니다.');
      return;
    }
    setImages(updatedImages);
  };

  const handleRemove = (indexToRemove) => {
    setImages(images.filter((_, index) => index !== indexToRemove));
  };

  const handleFileClick = () => {
    if (fileInputRef.current) {
      fileInputRef.current.click();
    }
  };

  return (
    <div>
      {images.map((image, index) => (
        <div key={index} style={{ display: 'inline-block', marginRight: '10px' }}>
          <img 
            src={URL.createObjectURL(image)} 
            alt="Uploaded preview" 
            style={{ maxWidth: '300px' }}
          />
          <button onClick={() => handleRemove(index)}>제거</button>
        </div>
      ))}
      {images.length < 3 && 
        <div>
          <input 
            type="file" 
            onChange={handleChange} 
            accept="image/*" 
            style={{ display: 'none' }} 
            ref={fileInputRef}
          />
          <button onClick={handleFileClick}>이미지 추가</button>
        </div>
      }
    </div>
  );
}

export default MultipleImageUpload;
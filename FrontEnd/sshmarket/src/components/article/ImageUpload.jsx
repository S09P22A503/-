import React, { useState } from 'react';

function ImageUpload() {
  const [image, setImage] = useState(null);
  const [previewURL, setPreviewURL] = useState(null);

  const handleChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImage(file);
      setPreviewURL(URL.createObjectURL(file));
    } else {
      setImage(null);
      setPreviewURL(null);
    }
  };

  return (
    <div>
      <input type="file" onChange={handleChange} accept="image/*" />
      {previewURL && (
        <img 
          src={previewURL} 
          alt="Uploaded preview" 
          style={{ 
            maxWidth: '400px', 
            border: '3px solid #B388EB', 
            borderRadius: '10px',
            boxShadow: '5px 5px 10px rgba(0, 0, 0, 0.1)',
            marginTop: '20px'
          }} 
        />
      )}
    </div>
  );
}

export default ImageUpload;
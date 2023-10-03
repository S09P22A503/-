import axios from "axios";
import processApiResponse from "../utils/api";

const writeArticle = async ({ responseFunc, data }) => {
  const { 
    memberId,productId,price,amount,mass,locationId,title,mainImage,content,tradeType,images 
  } = data;

  const formData = new FormData();
  const request = { memberId,productId,price,amount,mass,locationId,title,content,tradeType};
  console.log(images)
  images.forEach((image) => formData.append("images", image));

  formData.append("mainImage",mainImage)
  formData.append('memberId', memberId);
  formData.append('productId', productId);
  formData.append('price', price);
  formData.append('amount', amount);
  formData.append('mass', mass);
  formData.append('locationId', locationId);
  formData.append('title', title);
  formData.append('content', content);
  formData.append('tradeType', tradeType);
  console.log(formData)
  try {
    const response = await axios.post(`${process.env.REACT_APP_SERVER_URL}/articles`,formData,{
      headers: {
        "Content-Type": "multipart/form-data"
      },
      withCredentials: true,
    });
    console.log(response);
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};


export {
    writeArticle
};

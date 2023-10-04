import axios from "axios";
import processApiResponse from "../utils/api";

const modifyArticle = async ({ responseFunc, data }) => {
  const {
    articleId,
    memberId,
    productId,
    price,
    amount,
    mass,
    locationId,
    title,
    mainImage,
    content,
    tradeType,
    images,
    mainImageChanged,
    deletedUrls
  } = data;

  const formData = new FormData();
  images.forEach((image) => formData.append("images", image));
  formData.append("id",articleId)
  formData.append("mainImageChanged", mainImageChanged);
  deletedUrls.forEach((url)=> formData.append("deletedUrls",url));
  formData.append("mainImage", mainImage);
  formData.append("memberId", memberId);
  formData.append("itemId", productId);
  formData.append("price", price);
  if(amount != null) formData.append("amount", amount);
  if( mass!= null) formData.append("mass", mass);
  formData.append("locationId", locationId);
  formData.append("title", title);
  formData.append("content", content);
  formData.append("tradeType", tradeType);
  console.log(formData);
  try {
    const response = await axios.patch(
      `${process.env.REACT_APP_SERVER_URL}/articles/${articleId}`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
        withCredentials: true,
      }
    );
    console.log(response);
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

export { modifyArticle };

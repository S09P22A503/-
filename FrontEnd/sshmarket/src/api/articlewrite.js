import instance from "./instance";
import processApiResponse from "../utils/api";

const writeArticle = async ({ responseFunc, data }) => {
  const { 
    memberId,productId,price,amount,mass,locationId,title,mainImage,content,tradeType,images 
  } = data;

  const formData = new FormData();
  const request = { memberId,productId,price,amount,mass,locationId,title,content,tradeType};
  images.forEach((image) => formData.append("images", image));
  formData.append("mainImage",mainImage)

  formData.append(
    "request",
    new Blob([JSON.stringify(request)], { type: "application/json" })
  );
  try {
    const response = await instance.post(`/articles`,formData);
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

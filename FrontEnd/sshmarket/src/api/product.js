import pyinstance from "./pyinstance";
import processApiResponse from "../utils/api";

const getProductData = async ({ responseFunc}) => {
  
    try {
      const response = await pyinstance.get(`/products/`)
      console.log(response);
      processApiResponse({ responseFunc, response });
      return response;
    } catch (e) {
      return e.response;
    }
  };

  const getProductDataWithItemId = async ({ itemId,responseFunc}) => {
  
    try {
      const response = await pyinstance.get(`/products/?itemId=${itemId}`)
      console.log(response);
      processApiResponse({ responseFunc, response });
      return response;
    } catch (e) {
      return e.response;
    }
  };

export {getProductData,getProductDataWithItemId}
  
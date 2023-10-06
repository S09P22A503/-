import pyinstance from "./pyinstance";
import processApiResponse from "../utils/api";

const getRetailPriceData = async ({ responseFunc, data }) => {
  const { nameId, itemId } = data;

  try {
    const response = await pyinstance.get(
      `/products/history/retail?nameId=${nameId}&itemId=${itemId}`
    );
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

const getWholesalePriceData = async ({ responseFunc, data }) => {
  const { nameId, itemId } = data;

  try {
    const response = await pyinstance.get(
      `/products/history/wholesale?nameId=${nameId}&itemId=${itemId}`
    );
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

export { getRetailPriceData, getWholesalePriceData };

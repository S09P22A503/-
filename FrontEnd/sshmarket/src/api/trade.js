import instance from "./instance";
import processApiResponse from "../utils/api";

const getTradeList = async ({ responseFunc, data }) => {
  const { memberId, status } = data;

  try {
    const response = await instance.get(`/trades/${memberId}?status=${status}`);
    console.log(response);
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

const getTradeListByKeyword = async ({ responseFunc, data }) => {
  const { keyword, status } = data;

  try {
    const response = await instance.get(
      `/trades/search/${keyword}?status=${status}`
    );
    console.log(response);
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

const getTradeMessage = async ({ responseFunc, data }) => {
  const { tradeId } = data;

  try {
    const response = await instance.get(`/trades/${tradeId}/messages`);
    console.log(response);
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

const postCreateTrade = async ({ responseFunc, data }) => {
  try {
    const response = await instance.post(`/trades`, data);
    console.log(response);
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

export {
  getTradeList,
  getTradeListByKeyword,
  getTradeMessage,
  postCreateTrade,
};

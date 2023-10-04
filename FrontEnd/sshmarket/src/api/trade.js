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

const getTradeHistory = async ({ responseFunc, data }) => {
  const { memberId } = data;

  try {
    const response = await instance.get(`/trades/history/${memberId}`);
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

const getTradeDetail = async ({ responseFunc, data }) => {
  const { tradeId } = data;

  try {
    const response = await instance.get(`/trades/detail/${tradeId}`);
    console.log("TradeDetail!!!!!");
    console.log(response);
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

const getTraderProfile = async ({ responseFunc, data }) => {
  const { tradeId } = data;

  try {
    const response = await instance.get(`/trades/trader/${tradeId}`);
    console.log("trader", response);
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

const setTradeSell = async ({ responseFunc, data }) => {
  const { tradeId } = data;

  try {
    instance.patch(`/trades/${tradeId}/sell`);
  } catch (e) {
    return e.response;
  }
};

const setTradeBuy = async ({ responseFunc, data }) => {
  const { tradeId, requestBody } = data;

  try {
    console.log(requestBody);
    instance.post(`/trades/${tradeId}/buy`, requestBody);
  } catch (e) {
    return e.response;
  }
};

export {
  getTradeList,
  getTradeListByKeyword,
  getTradeMessage,
  postCreateTrade,
  getTradeHistory,
  getTradeDetail,
  getTraderProfile,
  setTradeSell,
  setTradeBuy,
};

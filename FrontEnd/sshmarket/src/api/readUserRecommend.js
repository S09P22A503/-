import axios from "axios";
import processApiResponse from "../utils/api";

const readUserRecommendations = async ({ responseFunc, memberId }) => {
  try {
    const response = await axios.get(
      `${process.env.REACT_APP_PYTHON_SERVER_URL}/recommend/user?userId=${memberId}`,
      {
        headers: {
          "Content-Type": "application/json;charset=UTF-8",
        },
      }
    );
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

const readArticles = async ({ responseFunc, articleIdList }) => {
  let rUrl = process.env.REACT_APP_SERVER_URL + "/articles/recommend?";
  for (const articleId of articleIdList) {
    rUrl = rUrl + "articleId=" + articleId + "&";
  }
  try {
    const response = await axios.get(rUrl, {
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
      },
    });
    processApiResponse({ responseFunc, response });
    return response;
  } catch (e) {
    return e.response;
  }
};

export { readUserRecommendations, readArticles };

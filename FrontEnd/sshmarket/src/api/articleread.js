import axios from "axios";
import processApiResponse from "../utils/api";

const readArticle = async ({ responseFunc, articleId }) => {
  try {
    const response = await axios.get(
      `${process.env.REACT_APP_SERVER_URL}/articles/${articleId}`,
      {
        headers: {
          "Content-Type": "application/json;charset=UTF-8",
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

export { readArticle };

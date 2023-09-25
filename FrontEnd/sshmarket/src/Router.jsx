import { createBrowserRouter } from "react-router-dom";
import ArticleDetail from "./pages/ArticleDetail";
import ArticleList from "./pages/ArticleList";
import ArticleWrite from "./pages/ArticleWrite";
import Login from "./pages/Login";
import Main from "./pages/Main";
import Mypage from "./pages/Mypage";
import Signup from "./pages/Signup";
import Trade from "./pages/Trade";
import Root from "./root";
import NotFound from "./pages/NotFound";
import SideBar from "./components/article/SideBar";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    children: [
      {
        path: "",
        element: <Main />,
      },
      {
        path: "login",
        element: <Login />,
      },
      {
        path: "signup",
        element: <Signup />,
      },
      {
        path: "trade",
        element: <Trade />,
      },
      {
        path: "article",
        element: <SideBar />,
        children: [
          {
            path: "",
            element: <ArticleList />,
          },
          {
            path: ":articleId",
            element: <ArticleDetail />,
          },
        ],
      },
      {
        path: "article/write",
        element: <ArticleWrite />,
      },
      {
        path: "mypage",
        element: <Mypage />,
      },
    ],
  },
  {
    path: "*",
    element: <NotFound />,
  },
]);

export default router;

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

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        children: [
            {
                path: "",
                element: <Main />
            },
            {
                path: "login",
                element: <Login />
            },
            {
                path: "signup",
                element: <Signup />
            },
            {
                path: "trade",
                element: <Trade />
            },
            {
                path: "article",
                element: <ArticleList />
            },
            {
                path: "article/write",
                element: <ArticleWrite />
            },
            {
                path: "article/:articleId",
                element: <ArticleDetail />
            },
            {
                path: "mypage",
                element: <Mypage />
            },
        ]
    },
    {
        path: "*",
        element: <NotFound />
    }
])

export default router;
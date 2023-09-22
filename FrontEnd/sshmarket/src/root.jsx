import { Outlet } from "react-router-dom";
import Layout from "./components/layout/layout";

export default function Root() {
  return (
    <>
      <Layout>
        <Outlet />
      </Layout>
    </>
  );
}

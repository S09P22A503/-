import { styled } from "styled-components";
import { Link, Outlet, useLocation, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

const Container = styled.div`
  display: flex;
  padding: 20px;
`;

const SidebarContainer = styled.div`
  width: 222px;
  height: 600px;

  margin-right: 50px;
  border-top: 1px solid #ebebebde;
`;

const Menu = styled.div`
  width: 150px;
  height: 30px;
  font-weight: 300;
  padding-top: 13px;
  padding-left: 15px;
  color: var(--gray-800);
  border-bottom: 1px solid #ebebebde;
  text-decoration: none;
  cursor: pointer;

  &:hover {
    background-color: #ebdaff;
  }
  ${({ $active }) =>
    $active &&
    `
    background-color: #ebdaff;
  `}
`;

const SubMenu = styled.div`
  display: ${(props) => {
    if (props.$dropdown) {
      return "block";
    } else {
      return "none";
    }
  }};
  width: 125px;
  height: 25px;
  font-weight: 300;
  padding-top: 13px;
  padding-left: 15px;
  color: var(--gray-800);
  border-bottom: 1px solid #ebebebde #d1cef2;
  text-decoration: none;
  padding-left: 40px;
  font-size: 14px;
  cursor: pointer;

  ${({ $active }) =>
    $active &&
    `
    background-color: #d7b7fd;
  `}
`;

const Category = styled.div`
  width: 150px;
  height: 30px;
  font-weight: 300;
  padding-top: 13px;
  padding-left: 15px;
  color: var(--gray-800);
  border-bottom: 1px solid #ebebebde;
  text-decoration: none;
  font-weight: bold;
  font-size: large;
  background-color: #dfc3ff;
`;

const StyledLink = styled(Link)`
  text-decoration: none;
  cursor: pointer;
`;
export default function SideBar() {
  const navigate = useNavigate();
  const location = useLocation();
  // const [clicked, setClicked] = useState(location.pathname);

  const url = new URL(document.URL);
  const query = url.searchParams; //?appliedCompany=%E3%85%87%E3%85%87&job=hh&careerLevel=ALL
  // console.log(query.get("appliedCompany"));

  const clicked = query.get("category");

  function handleClick(menu) {
    window.location.replace(linkList[menu]);
  }

  const menuList = [
    { id: 1, name: "쌀/잡곡" },
    { id: 2, name: "채소" },
    { id: 3, name: "식용작물" },
    { id: 4, name: "과일" },
    { id: 6, name: "수산물/건어물" },
  ];
  const linkList = [
    `/article?category=1`,
    `/article?category=2`,
    `/article?category=3`,
    `/article?category=4`,
    `/article?category=6`,
  ];

  const category = [1, 2, 3, 4, 6];

  const menuListDoms = menuList.map((menu, idx) => {
    return (
      <StyledLink
        // to={linkList[idx]}
        onClick={() => handleClick(idx)}
        key={idx}
      >
        <Menu $active={clicked == menuList[idx].id}>{menu.name}</Menu>
      </StyledLink>
    );
  });

  return (
    <Container>
      <SidebarContainer>
        <Category>카테고리</Category>
        {menuListDoms}
      </SidebarContainer>
      <div>
        <Outlet />
      </div>
    </Container>
  );
}

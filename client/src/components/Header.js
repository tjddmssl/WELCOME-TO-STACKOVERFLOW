import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { BsSearch } from 'react-icons/bs';

//* 상단 바 (아이콘, 서치바, 유저 아바타, 로그아웃 버튼)
//로그인한 상태랑 안한 상태랑 헤더 다름
const HeaderContainer = styled.header`
  width: 100%;
  min-width: auto;
  height: 50px;
  position: fixed !important;
  left: 0 !important;
  top: 0 !important;
  border-top: 3px solid #f48225;
  vertical-align: baseline;
  background-color: #f8f9f9;
  z-index: 10;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
    0 2px 8px hsla(0, 0%, 0%, 0.05);
  .div__header-container {
    width: 1300px;
    max-width: 100%;
    height: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .h1__stackoverflow {
    height: 100%;
    margin: 0;
    padding: 0 8px;
    display: flex;
    align-items: center;
  }
  .img__stackoverflow-logo {
    display: block;
    width: 150px;
    height: 30px;
    margin-top: -4px;
  }
  .ul__header-list {
    display: flex;
    cursor: pointer;
    position: relative;
    text-align: left;
    align-items: center;
    padding: 2px 0;
    margin: -2px;
    color: #a1a6a9;
    font-size: 13px;
    li {
      padding: 6px 12px;
      margin: 2px;
      white-space: nowrap;
    }
  }
`;

const SearchContainer = styled.form`
  position: relative;
  flex-grow: 1;
  min-width: 184px;
  .searchIcon {
    position: absolute;
    left: 0px;
    opacity: 0.5;
    margin: 0.5rem;
  }
`;

const SearchInput = styled.input`
  padding: 1rem 1rem 1rem 2rem;
  width: 100%;
  height: 2rem;
  border: 1px solid hsl(210deg 8% 75%);
  border-radius: 3px;
  outline: none;
  color: hsl(210, 8%, 25%);
  font-size: 13px;
  &:focus {
    box-shadow: 0px 0px 0px 4px hsl(205, 53%, 88%);
    border-color: hsl(206, 100%, 40%);
  }
`;
const ButtonContainer = styled.div`
  display: flex;
  > button {
    padding: 8px 10px;
    box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);
    border-radius: 3px;
    font-size: 13px;
    font-weight: 400;
    opacity: 1;
    border: 1px solid hsl(205, 41%, 63%);
    position: relative;
  }
`;
const LoginButton = styled.button`
  width: 58.2px;
  height: 33px;
  margin: 4px;
  color: hsl(205, 47%, 42%);
  background-color: hsl(205, 46%, 92%);
  border: hsl(205, 41%, 63%);
  a:hover,
  a:active,
  a:visited {
    text-decoration: none;
    color: hsl(205, 46%, 32%);
  }
  &:hover {
    text-decoration: none;
    background-color: hsl(205, 57%, 81%);
  }
`;

const SignupButton = styled.button`
  width: 66.88px;
  height: 33px;
  color: #fff;
  background-color: hsl(206, 100%, 52%);
  border: transparent;
  line-height: 15px;
  align-self: center;
  text-align: center;
  white-space: nowrap;
  a:hover,
  a:active,
  a:visited {
    text-decoration: none;
    color: hsl(0, 0%, 100%);
  }
  &:hover {
    text-decoration: none;
    background-color: hsl(205, 46%, 32%);
  }
`;
function Header() {
  return (
    <HeaderContainer className="header__header">
      <div className="div__header-container">
        <h1 className="h1__stackoverflow">
          <Link to="/">
            <img
              className="img__stackoverflow-logo"
              alt="logo"
              src="img/logo.png"
            />
          </Link>
        </h1>
        <ul className="ul__header-list">
          <li>About</li>
          <li>Products</li>
          <li>For Teams</li>
        </ul>
        <SearchContainer className="form__searchinput">
          <BsSearch className="searchIcon" />
          <SearchInput className="input__search" placeholder="Search..." />
        </SearchContainer>
      </div>
      <ButtonContainer className="div__button-container">
        <LoginButton className="button__login-button">
          <Link to="/login">Log in</Link>
        </LoginButton>
        <SignupButton className="button__signup-button">
          <Link to="/signup"> Sign up</Link>
        </SignupButton>
      </ButtonContainer>
    </HeaderContainer>
  );
}

export default Header;

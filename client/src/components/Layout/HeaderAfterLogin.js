import { Link } from 'react-router-dom';
import styled from 'styled-components';
import {
  faInbox,
  faTrophy,
  faCircleQuestion,
} from '@fortawesome/free-solid-svg-icons';
import { faStackExchange } from '@fortawesome/free-brands-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useState } from 'react';
import { FaStackOverflow } from 'react-icons/fa';
import { BsSearch } from 'react-icons/bs';

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
      &:hover {
        background-color: hsl(210deg 8% 90%);
        border-radius: 1000px;
      }
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
  display: block;
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
const OlList = styled.ol`
  display: flex;
  > li {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 40px;
    height: 3rem;
    color: #727272;
    font-size: 18px;
    &:hover {
      background-color: hsl(210deg 8% 90%);
    }
    &:first-child {
      width: 64px;
      margin: 0 5px;
    }
    &:last-child {
      position: relative;
    }
  }
`;
const UserImg = styled.img`
  width: 30px;
  border-radius: 8px;
`;

const CommunityModal = styled.div`
  width: 375px;
  position: absolute;
  top: 50px;
  right: 0px;
  background-color: white;
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
    0 2px 8px hsla(0, 0%, 0%, 0.05);
`;

const ModalTitle = styled.div`
  width: 100%;
  height: 30px;
  font-size: 11px;
  font-weight: 700;
  color: #0074cc;
  display: flex;
  align-items: center;
  padding-left: 10px;
  background-color: #f1f2f3;
  &:hover {
    cursor: pointer;
    color: hsl(206, 100%, 52%);
  }
`;

const ModalContent = styled.div`
  width: 100%;
  height: 60px;
  font-size: 12px;
  font-weight: 400;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-left: 8px;
  .span__community-content {
    color: #0074cc;
    font-weight: 700;
    &:hover {
      color: hsl(206, 100%, 52%);
    }
  }
  .span__content {
    color: #3b4045;
    padding: 2px 0;
    &:hover {
      cursor: pointer;
    }
  }
  .loginout {
    color: #0074cc;
    &:hover {
      color: hsl(206, 100%, 52%);
    }
  }
`;
export default function HeaderAfterLogin() {
  const [modal, setModal] = useState(null);

  const handleClickMenu = (menu) => {
    if (menu === modal) setModal(null);
    else setModal(menu);
  };
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
          <li>Products</li>
        </ul>
        <SearchContainer className="form__searchinput">
          <BsSearch className="searchIcon" />
          <SearchInput className="input__search" placeholder="Search..." />
        </SearchContainer>
      </div>
      <OlList className="ol__header-list">
        <li>
          <UserImg alt="user" src="img/user.png" />
        </li>
        <li>
          <FontAwesomeIcon icon={faInbox} />
        </li>
        <li>
          <FontAwesomeIcon icon={faTrophy} />
        </li>
        <li>
          <FontAwesomeIcon icon={faCircleQuestion} />
        </li>

        <li>
          <FontAwesomeIcon
            onClick={() => handleClickMenu('community')}
            icon={faStackExchange}
          />
          {modal === 'community' ? (
            <CommunityModal className="div__community-modal">
              <ModalTitle className="div__community-modal-Title">
                CURRENT COMMUNITY
              </ModalTitle>
              <ModalContent className="div__community-content">
                <span className="span__community-content">
                  <FaStackOverflow />
                  Stack Overflow
                </span>
                <Link to="/">
                  <span className="span__content logout">Logout</span>
                </Link>
              </ModalContent>

              <ModalTitle className="div__community-modal-Title">
                MORE STACK EXCHANGE COMMUNITIES
              </ModalTitle>
              <ModalContent className="div__community-content">
                <span className="span__content">3D Printing</span>
                <span className="span__content">Academia</span>
                <span className="span__content">Amateur Radio</span>
              </ModalContent>
            </CommunityModal>
          ) : null}
        </li>
      </OlList>
    </HeaderContainer>
  );
}

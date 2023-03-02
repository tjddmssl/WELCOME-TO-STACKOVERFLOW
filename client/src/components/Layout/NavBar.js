import styled from 'styled-components';
import SvgIcon from '@mui/material/SvgIcon';
import PublicIcon from '@mui/icons-material/Public';
import { Link, useLocation } from 'react-router-dom';
import { useEffect, useState } from 'react';

const Container = styled.div`
  width: 164px;
  display: block;
  position: relative;
  border-right: 1px solid #d6d9dc;
`;

const StickyContainer = styled.div`
  top: 50px;
  position: sticky;
  max-height: 100vh;
  height: 380px;
  padding-top: 24px;

  .nav-selected__a {
    font-weight: bold;
    color: black;
  }
  .nav-selected__li {
    background-color: #f1f2f3;
    border-right: 3px solid #f48224;
  }
  .nav-public__li {
    font-weight: lighter;
    padding: 10px;
  }
  .nav-public__a {
    padding-left: 40px;
  }
  .nav-questions__span {
    padding-left: 5px;
  }
  li {
    font-size: 15px;
    height: 34px;
    display: flex;
    vertical-align: center;
    width: 164px;
  }
  a {
    color: #51595f;
    vertical-align: center;
    font-size: 15px;
    display: flex;
    align-items: center;
    padding-left: 10px;
  }
  a:hover {
    color: black;
  }
`;

function NavBar() {
  const location = useLocation();

  const [selectedPath, setSeletctedPath] = useState('');

  useEffect(() => {
    setSeletctedPath(location.pathname.split('/')[1]);
  }, [selectedPath]);

  return (
    <Container>
      <StickyContainer>
        <nav>
          <ul>
            <li className={selectedPath === '' ? 'nav-selected__li' : ''}>
              <Link
                className={selectedPath === '' ? 'nav-selected__a' : ''}
                to="/"
              >
                <span>Home</span>
              </Link>
            </li>
            <li>
              <ul>
                <li className="nav-public__li">PUBLIC</li>
                <li
                  className={
                    selectedPath === 'questions' ? 'nav-selected__li' : ''
                  }
                >
                  <Link
                    className={
                      selectedPath === 'questions' ? 'nav-selected__a' : ''
                    }
                    to="/questions"
                  >
                    <SvgIcon component={PublicIcon} inheritViewBox />
                    <span className="nav-questions__span">Questions</span>
                  </Link>
                </li>
                <li
                  className={selectedPath === 'tags' ? 'nav-selected__li' : ''}
                >
                  <Link
                    className={
                      selectedPath === 'tags'
                        ? 'nav-public__a nav-selected__a'
                        : 'nav-public__a'
                    }
                    to="/tags"
                  >
                    <span>Tags</span>
                  </Link>
                </li>
                <li
                  className={selectedPath === 'users' ? 'nav-selected__li' : ''}
                >
                  <Link
                    className={
                      selectedPath === 'users'
                        ? 'nav-public__a nav-selected__a'
                        : 'nav-public__a'
                    }
                    to="/users"
                  >
                    <span>Users</span>
                  </Link>
                </li>
                <li>
                  <a className="nav-public__a" href="/">
                    <span>Companies</span>
                  </a>
                </li>
              </ul>
            </li>
          </ul>
        </nav>
      </StickyContainer>
    </Container>
  );
}

export default NavBar;

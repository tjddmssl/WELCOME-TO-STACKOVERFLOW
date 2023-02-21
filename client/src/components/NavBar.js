import styled from 'styled-components';
import SvgIcon from '@mui/material/SvgIcon';
import PublicIcon from '@mui/icons-material/Public';
import { useLocation } from 'react-router-dom';
import { useEffect, useState } from 'react';

const Container = styled.div`
  width: 164px;
  display: block;
  margin-top: 20px;
  height: auto;
  position: relative;
`;

const StickyContainer = styled.div`
  position: sticky;
  max-height: 100vh;
  height: 380px;
  top: 10px;

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
  }
  .nav-public__a {
    padding-left: 40px;
  }
  .nav-questions__span {
    padding-left: 5px;
  }
  li {
    padding: 10px;
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
              <a
                className={selectedPath === '' ? 'nav-selected__a' : ''}
                href="/"
              >
                <span>Home</span>
              </a>
            </li>
            <li>
              <ul>
                <li className="nav-public__li">PUBLIC</li>
                <li
                  className={
                    selectedPath === 'questions' ? 'nav-selected__li' : ''
                  }
                >
                  <a
                    className={
                      selectedPath === 'questions' ? 'nav-selected__a' : ''
                    }
                    href="/questions"
                  >
                    <SvgIcon component={PublicIcon} inheritViewBox />
                    <span className="nav-questions__span">Questions</span>
                  </a>
                </li>
                <li
                  className={selectedPath === 'tags' ? 'nav-selected__li' : ''}
                >
                  <a
                    className={
                      selectedPath === 'tags'
                        ? 'nav-public__a nav-selected__a'
                        : 'nav-public__a'
                    }
                    href="/tags"
                  >
                    <span>Tags</span>
                  </a>
                </li>
                <li
                  className={selectedPath === 'users' ? 'nav-selected__li' : ''}
                >
                  <a
                    className={
                      selectedPath === 'users'
                        ? 'nav-public__a nav-selected__a'
                        : 'nav-public__a'
                    }
                    href="/users"
                  >
                    <span>Users</span>
                  </a>
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

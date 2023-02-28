import Header from '../components/Header';
import QButton from '../components/QButton';
import QLists from '../components/QLists';
import NavBar from '../components/NavBar';
import Sidebar from '../components/Sidebar';
import Footer from '../components/Footer';
import QPageNation from '../components/QPageNation';
import styled from 'styled-components';
import { useState } from 'react';
import { useSelector } from 'react-redux';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  padding-top: 50px;
`;

const MainContainer = styled.div`
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  .mainside-wrap__div {
    max-width: 1100px;
    width: calc(100% - 164px);
    display: flex;
  }
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  padding: 24px;
  width: calc(100% - 276px);
  @media screen and (max-width: 980px) {
    width: 100%;
  }
`;

const MainTop = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: baseline;
  margin-bottom: 10px;
  white-space: nowrap;
  h1 {
    font-weight: 400;
    margin: 0 0 25px;
  }
`;

const ListHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
  span {
    font-size: 17px;
  }

  .allques-sorttab__div {
    font-size: 12px;
    display: flex;
    > button:first-child {
      border-left: 0.1px solid hsl(210, 8%, 55%);
      border-right: 0.1px solid hsl(210, 8%, 55%);
      border-radius: 5px 0px 0px 5px;
    }
    > button:last-child {
      border-left: 0.1px solid hsl(210, 8%, 55%);
      border-right: 0.1px solid hsl(210, 8%, 55%);
      border-radius: 0px 5px 5px 0px;
    }
    .allques-sorttab__button {
      background-color: white;
      border: none;
      border-top: 0.1px solid hsl(210, 8%, 55%);
      border-bottom: 0.1px solid hsl(210, 8%, 55%);
      padding: 10px;
      color: hsl(210, 8%, 45%);
      :hover {
        color: hsl(210, 8%, 35%);
        background-color: hsl(210, 8%, 97.5%);
      }
    }
    .allques-selectedtab__button {
      background-color: #e3e6e8 !important;
    }
  }
  .allques-count__span {
    margin-right: 5px;
    font-size: 15px;
  }
`;

//* HOME_002
function Questions() {
  const [selectedTab, setSelectedTab] = useState('Newest');
  const count = useSelector((state) => {
    return state.getAllQuestion.response.content;
  });

  return (
    <div>
      <Container>
        <Header />
        <MainContainer>
          <NavBar />
          <div className="mainside-wrap__div">
            <ContentContainer>
              <MainTop>
                <h1>All Questions</h1>
                <QButton />
              </MainTop>
              <ListHeader>
                <div>
                  {count && (
                    <span className="allques-count__span">{count.length}</span>
                  )}
                  <span>questions</span>
                </div>
                <div className="allques-sorttab__div">
                  <button
                    className={
                      selectedTab === 'Newest'
                        ? 'allques-selectedtab__button allques-sorttab__button'
                        : 'allques-sorttab__button'
                    }
                    onClick={(e) => {
                      setSelectedTab(e.target.textContent);
                      console.log(selectedTab);
                    }}
                  >
                    Newest
                  </button>
                  {/* <div>Newest</div> */}
                  <button
                    className={
                      selectedTab === 'Active'
                        ? 'allques-selectedtab__button allques-sorttab__button'
                        : 'allques-sorttab__button'
                    }
                    onClick={(e) => {
                      setSelectedTab(e.target.textContent);
                      console.log(selectedTab);
                    }}
                  >
                    Active
                  </button>
                  <button
                    className={
                      selectedTab === 'Unanswered'
                        ? 'allques-selectedtab__button allques-sorttab__button'
                        : 'allques-sorttab__button'
                    }
                    onClick={(e) => {
                      setSelectedTab(e.target.textContent);
                      console.log(selectedTab);
                    }}
                  >
                    Unanswered
                  </button>
                  {/* <a href="/questions">
                    <div>Unanswered</div>
                  </a> */}
                </div>
              </ListHeader>
              <QLists />
              <QPageNation />
            </ContentContainer>
            <Sidebar />
          </div>
        </MainContainer>
        <Footer />
      </Container>
    </div>
  );
}

export default Questions;

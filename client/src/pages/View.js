import { useSelector } from 'react-redux';
import Header from '../components/Header';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import Sidebar from '../components/Sidebar';
import QViewDetail from '../components/QViewDetail';
import QView from '../components/QView';
import styled from 'styled-components';
import AnswerForm from '../components/AnswerForm';

//* VIEW_01

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
    /* display: flex; */
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

//* View 001
function View() {
  const data = useSelector((state) => {
    return state.getQView.question;
  });
  return (
    <div>
      <Container>
        <Header />
        <MainContainer>
          <NavBar />
          <div className="mainside-wrap__div">
            <ContentContainer>
              <QView>
                <QViewDetail />
              </QView>
            </ContentContainer>
            <Sidebar />
            <AnswerForm question={data} />
          </div>
        </MainContainer>

        <Footer />
      </Container>
    </div>
  );
}

export default View;

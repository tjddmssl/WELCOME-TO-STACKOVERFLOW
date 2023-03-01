import { useSelector, useDispatch } from 'react-redux';
import { useEffect } from 'react';
import axios from 'axios';
import Header from '../components/Layout/Header';
import NavBar from '../components/Layout/NavBar';
import Footer from '../components/Layout/Footer';
import Sidebar from '../components/Layout/Sidebar';
import QViewDetail from '../components/QuestionDetail/Question/QViewDetail';
import QView from '../components/QuestionDetail/Question/QView';
import styled from 'styled-components';
import AnswerForm from '../components/QuestionDetail/Answeer/AnswerForm';
import AnswerView from '../components/QuestionDetail/Answeer/AnswerView';
import NoAnswerView from '../components/QuestionDetail/Answeer/NoAnswerView';
import getAnswerSlice from '../redux/slice/getAnswer';

//* VIEW_01
// TODO 코멘트 map해서 보여주기
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
  const dispatch = useDispatch();

  const question = useSelector((state) => {
    return state.getQView.question;
  });

  const answer = useSelector((state) => {
    return state.getAnswer.answer;
  });

  useEffect(() => {
    const getAnswerDetail = async () => {
      try {
        const response = await axios.get('http://localhost:3002/answer');
        dispatch(getAnswerSlice.actions.get(response.data));
      } catch (error) {
        console.log(error);
      }
    };
    getAnswerDetail();
  }, []);

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
              {answer ? <AnswerView answer={answer} /> : <NoAnswerView />}
            </ContentContainer>
            <Sidebar />
            <AnswerForm question={question} />
          </div>
        </MainContainer>

        <Footer />
      </Container>
    </div>
  );
}

export default View;

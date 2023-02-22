import AskForm from '../components/AskForm';
import Footer from '../components/Footer';
import Header from '../components/Header';
import styled from 'styled-components';
import './Ask.css';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 25px;
  h1 {
    margin-left: 25px;
    margin-top: 100px;
  }
`;
const Guide = styled.div`
  background-color: #edf4fa;
  border: 1px solid #aecdea;
  border-radius: 3px;
  width: 1000px;
  height: auto;
  padding: 5px 15px;
  padding-bottom: 20px;
  margin-top: 25px;
  margin-bottom: 25px;
  margin-left: 25px;

  h2 {
    font-weight: lighter;
  }
  ul li {
    list-style-type: disc;
    list-style-position: inside;
    margin-left: 20px;
  }
`;

const Sidebar = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 450px;
  float: right;
  width: 310px;
  height: auto;
  border: darkgrey;
  border-radius: 1.5px;
  box-shadow: 1px 1px 1px 1px lightgrey;

  ul {
    background-color: #f8f9f9;
    padding: 14px;
  }
  img {
    width: 100px;
  }
  li {
    padding: 14px;
  }
`;
//* VIEW_02 질문 새로 입력하기
function Ask() {
  return (
    <>
      <Header />
      <Sidebar>
        <ul>Writing a good title</ul>
        <img src="/img/Ask-sidebar.png" alt="ask sidebar"></img>
        <li>Your title should summarize the problem.</li>
        <li>
          You might find that you have a better idea of your title after writing
          out the rest of the question.
        </li>
      </Sidebar>
      <Container>
        <h1>Ask a public question</h1>

        <Guide>
          <h2>Writing a good question</h2>
          <p>
            You{"'"}re ready to ask a programming-related question and this form
            will help guide you through the process.
            <br />
            Looking to ask a non-programming question? See the topics here to
            find a relevant site.
            <br />
          </p>
          <ul>
            <strong>Steps</strong>
            <li>Summarize your problem in a one-line title.</li>
            <li>Describe your problem in more detail.</li>
            <li>Describe what you tried and what you expected to happen.</li>
            <li>
              Add {"'tags'"} which help surface your question to members of the
              community.
            </li>
          </ul>
        </Guide>
        <AskForm />
      </Container>
      <Footer />
    </>
  );
}

export default Ask;

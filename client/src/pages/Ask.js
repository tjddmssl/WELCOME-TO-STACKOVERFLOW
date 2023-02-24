import AskForm from '../components/AskForm';
import Footer from '../components/Footer';
import Header from '../components/Header';
import styled from 'styled-components';
import './Ask.css';
import TagAdd from '../components/TagAdd';
import { useState } from 'react';
// import { useForm } from 'react-hook-form';
import { v4 as uuidv4 } from 'uuid';
import axios from 'axios';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 25px;
`;
const AskHeader = styled.div`
  display: flex !important;
  justify-content: space-between;
  text-align: left;
  height: 150px;
  width: 1400px;
  margin-left: 25px;
  margin-top: 70px;
  img {
    width: 700px;
    height: 150px;
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
  @media screen and (max-width: 1369px) {
    max-width: 100%;
  }
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
  @media screen and (max-width: 1361px) {
    display: none;
  }
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
  const [askTags, setAskTags] = useState([]);
  const [askTitle, setAskTitle] = useState('');
  const [askFirstBody, setFirstAskBody] = useState('');
  const [askSecondBody, setSecondAskBody] = useState('');

  // const { register, handleSubmit } = useForm();

  // const validateTitle = value => {
  //   if (value.length < 15) {
  //     return 'Title must be at least 15 characters.';
  //   }
  //   return true;
  // };

  const handleSubmit = (title, content) => {
    const newData = {
      id: uuidv4(),
      title,
      content,
    };
    const postData = async () => {
      try {
        await axios({
          url: 'http://localhost:3002/question',
          method: 'post',
          data: newData,
        });
      } catch (error) {
        console.log(error);
      }
    };
    postData();
    window.location.href = 'http://localhost:3000/';
  };

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
        <AskHeader>
          <h1>Ask a public question</h1>
          <img alt="header" src="img/background.svg" />
        </AskHeader>
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
        <AskForm
          askTitle={askTitle}
          setAskTitle={setAskTitle}
          askFirstBody={askFirstBody}
          setFirstAskBody={setFirstAskBody}
          askSecondBody={askSecondBody}
          setSecondAskBody={setSecondAskBody}
          handleSubmit={handleSubmit}
        />
        <TagAdd askTags={askTags} setAskTags={setAskTags} />
      </Container>
      <Footer />
    </>
  );
}

export default Ask;

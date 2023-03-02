// import { Button } from '@mui/material';
import { Editor } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import Footer from '../components/Layout/Footer';
import Header from '../components/Layout/Header';
import styled from 'styled-components';
import './Ask.css';
import { useRef, useState } from 'react';
import TagAdd from '../components/AskQuestion/TagAdd';
import axios from 'axios';

const AllContainer = styled.div`
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

const TitleForm = styled.div`
  display: flex;
  flex-direction: column;
  width: 1000px;
  height: auto;
  margin-left: 25px;
  margin-bottom: 25px;
  border: 1px solid lightgrey;
  padding: 5px 20px;
  padding-bottom: 10px;
  @media screen and (max-width: 1369px) {
    width: 100%;
  }
  h4 {
    margin-bottom: 10px;
  }
  p {
    margin-top: 0px;
  }
  input {
    width: 750px;
    padding: 10px;
    justify-content: center;
    align-items: center;
    border: 1px solid lightgrey;
    &:focus-within {
      outline: none;
      border-color: hsl(206deg 100% 52%);
      box-shadow: 0px 0px 0px 5px #e1ecf4;
    }
  }
`;

const Container = styled.div`
  width: 1000px;
  margin-left: 25px;
  margin-bottom: 25px;
  border: 1px solid lightgrey;
  padding: 5px 20px;
  @media screen and (max-width: 1369px) {
    width: 100%;
  }
  h4 {
    margin-bottom: 10px;
  }
  p {
    margin-top: 10px;
  }
`;
const TagForm = styled.div`
  display: flex;
  flex-direction: column;
  width: 1000px;
  height: auto;
  margin-left: 25px;
  margin-bottom: 40px;
  border: 1px solid lightgrey;
  padding: 5px 20px;
  padding-bottom: 10px;
  @media screen and (max-width: 1369px) {
    width: 100%;
  }

  h4 {
    margin-bottom: 10px;
  }
  p {
    margin-top: 0px;
  }
`;
const Form = styled.div`
  padding-left: 25px;
  .discard {
    text-transform: capitalize;
    margin-bottom: 30px;
    color: red;
  }
  .discard:hover {
    background-color: lightpink;
  }
`;
const EditorWrapper = styled.div`
  margin-top: 10px;
  border-radius: 3px;
  &:focus-within {
    outline: 1px solid #58a4de;
    border-radius: 2px solid #58a4de;
    box-shadow: 0px 0px 10px #ddeaf7;
  }
`;
const SendBtn = styled.button`
  margin-right: 1rem;
  margin-bottom: 4rem;
  padding: 10px;
  border: 1px solid #79a7c7;
  border-radius: 3px;
  background: #0995fd;
  color: white;
  &:hover {
    background-color: hsl(206deg 100% 40%);
    cursor: pointer;
  }
`;
//* VIEW_02 질문 새로 입력하기
function Ask() {
  const [title, setTitle] = useState('');
  const [problemBody, setProblemBody] = useState('');
  const [tryBody, setTryBody] = useState('');
  const [tags, setTags] = useState([]);

  const problemRef = useRef();
  const tryRef = useRef();

  // const submitClickHandler = () => {
  //   let newData = {
  //     id: uuidv4(),
  //     title,
  //     content: problemBody,
  //     trycontent: tryBody,
  //     tag: tags,
  //   };
  //   const postData = async () => {
  //     try {
  //       await axios({
  //         url: 'http://localhost:3002/question',
  //         method: 'post',
  //         data: newData,
  //       });
  //     } catch (error) {
  //       console.log(error);
  //     }
  //   };
  //   postData();
  //   window.location.href = 'http://localhost:3000/';
  // };

  // TODO id 없이 보내기
  // TODO 에러 data에서 문구로 뜨는 코드로 변경 (alert)
  const submitClickHandler = () => {
    let newData = {
      title,
      content: problemBody + tryBody,
      tag: tags,
      id: 53,
    };
    const postData = async () => {
      try {
        await axios({
          url: 'http://13.125.211.79:8080/questions',
          method: 'post',
          data: newData,
        });
      } catch (error) {
        console.log(error);
      }
    };
    postData();
    // window.location.href = 'http://localhost:3000/';
  };

  const onChangeProblem = () => {
    const problemdata = problemRef.current.getInstance().getMarkdown();
    setProblemBody(problemdata);
  };

  const onChangeTry = () => {
    const trydata = tryRef.current.getInstance().getMarkdown();
    setTryBody(trydata);
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
      <AllContainer>
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
        <TitleForm>
          <h4>Title</h4>
          <p>
            Be specific and imagin you{"'"}re asking a question to another
            person.
          </p>
          <input
            type="text"
            placeholder="e.g. is there an R function for finding the index of an element in a vector?"
            value={title}
            onChange={(event) => setTitle(event.target.value)}
          ></input>
        </TitleForm>
        <Container className="problem">
          <h4>What are the details of your problem?</h4>
          <p>
            Introduce the problem and expand on what you put in the title.
            Minumum 20 characters.
          </p>
          <EditorWrapper>
            <Editor
              ref={problemRef}
              placeholder={'please write here'}
              initialValue={' '}
              previewStyle="tap" // 미리보기 스타일 지정
              height="300px" // 에디터 창 높이
              initialEditType="wysiwyg" //초기값
              toolbarItems={[
                // 툴바 옵션 설정
                ['heading', 'bold', 'italic', 'strike'],
                ['hr', 'quote'],
                ['ul', 'ol', 'task', 'indent', 'outdent'],
                ['table', 'image', 'link'],
                ['code', 'codeblock'],
              ]}
              autofocus={false}
              hideModeSwitch={true}
              onChange={onChangeProblem}
            ></Editor>
          </EditorWrapper>
        </Container>
        <Container className="try">
          <h4>What did you try and what were you expecting?</h4>
          <p>
            Describe what you tried, what you expected to happen, and what
            actually resulted. Minumum 20 characters.
          </p>
          <EditorWrapper>
            <Editor
              ref={tryRef}
              placeholder={'please write here'}
              initialValue={' '}
              initialEditType="wysiwyg" //초기값
              previewStyle="tap" // 미리보기 스타일 지정
              height="300px" // 에디터 창 높이
              toolbarItems={[
                // 툴바 옵션 설정
                ['heading', 'bold', 'italic', 'strike'],
                ['hr', 'quote'],
                ['ul', 'ol', 'task', 'indent', 'outdent'],
                ['table', 'image', 'link'],
                ['code', 'codeblock'],
              ]}
              autofocus={false}
              hideModeSwitch={true}
              onChange={onChangeTry}
            ></Editor>
          </EditorWrapper>
        </Container>
        <TagForm>
          <h4>Tags</h4>
          <p>
            Add up to 5 tags to describe what your question is about. Start
            typing to see sugestions.
          </p>
          <TagAdd tags={tags} setTags={setTags} />
        </TagForm>
        <Form>
          <SendBtn onClick={submitClickHandler}> Post your question</SendBtn>
          {/* <Button className="discard">Discard draft</Button> */}
        </Form>
      </AllContainer>
      <Footer />
    </>
  );
}

export default Ask;

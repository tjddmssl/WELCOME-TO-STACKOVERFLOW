import { Editor } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import styled from 'styled-components';
import { Button } from '@mui/material';
import './AnswerForm.css';
import { useRef, useState } from 'react';
import { v4 as uuidv4 } from 'uuid';
import axios from 'axios';

const Header = styled.div`
  border-top: 1px solid #e3e6e8;
  margin-left: 25px;
  h3 {
    font-weight: lighter;
  }
`;

const Container = styled.div`
  margin-left: 25px;
  margin-right: 25px;
  width: 800px;
`;

const Footer = styled.div`
  margin-top: 40px;
  button {
    border: none;
    border-radius: 2px;
    background-color: #e1ecf4;
    color: #39739d;
    width: 55px;
    padding: 5px 2px;
    margin: 5px;
    margin-top: 30px;
  }
  button:hover {
    background-color: #d0e3f1;
  }
`;

function AnswerForm({ question }) {
  const [answerContent, setAnswerContent] = useState('');
  const questionRef = useRef();

  const handleChangeAnswer = () => {
    const Answer = questionRef.current?.getInstance().getMarkdown();
    setAnswerContent(Answer);
  };

  //todo 데이터 형식 바뀌면 그에 맞게 바꾸기
  const submitAnswerHander = () => {
    let newanswer = {
      id: uuidv4(),
      content: answerContent,
      voteCount: '0',
      createdDate: '2023-02-21T05:42:15.661Z',
      member: {
        id: 13,
        displayName: 'qwe',
        profileImage: '',
      },
    };

    const postAnswer = async () => {
      try {
        await axios({
          url: 'http://localhost:3002/answer',
          method: 'post',
          data: newanswer,
        });
      } catch (error) {
        console.log(error);
      }
    };
    postAnswer();
    window.location.href = 'http://localhost:3000/view';
  };

  return (
    <div>
      <Header>
        <h3>Your Answer</h3>
      </Header>
      <Container>
        <Editor
          onChange={handleChangeAnswer}
          ref={questionRef}
          placeholder={'please write here'}
          initialValue={' '}
          hideModeSwitch={true}
          initialEditType="wysiwyg"
          previewStyle="tap" // 미리보기 스타일 지정
          height="300px" // 에디터 창 높이
          toolbarItems={[
            // 툴바 옵션 설정
            ['heading', 'bold', 'italic', 'strike'],
            ['hr', 'quote'],
            ['ul', 'ol', 'task'],
            ['image', 'link'],
            ['code', 'codeblock'],
          ]}
        ></Editor>
        <Button onClick={submitAnswerHander} className="post">
          Post Your Answer
        </Button>
        <Footer>
          Not the answer you{"'"}re looking for? Browse other questions tagged
          {question.tag &&
            question.tag.map((el, idx) => <button key={idx}>{el}</button>)}
          or <a href="./ask"> ask your own question</a>.
        </Footer>
      </Container>
    </div>
  );
}

export default AnswerForm;

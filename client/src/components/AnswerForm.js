import { Editor } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import styled from 'styled-components';
import { Button } from '@mui/material';
import './AnswerForm.css';

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
  return (
    <div>
      <Header>
        <h3>Your Answer</h3>
      </Header>
      <Container>
        <Editor
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
        <Button className="post">Post Your Answer</Button>
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

import { Editor } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import styled from 'styled-components';
import { Button } from '@mui/material';

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
  }
  button {
    margin-top: 10px;
    width: 5%;
    background-color: #0a95ff;
    color: white;
    font-size: x-small;
  }
  button:hover {
    background-color: #0a95ff;
    color: lightgray;
  }
`;

const Container = styled.div`
  width: 1000px;
  margin-left: 25px;
  margin-bottom: 25px;
  border: 1px solid lightgrey;
  padding: 5px 20px;

  h4 {
    margin-bottom: 10px;
  }

  p {
    margin
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
  }
  button {
    margin-top: 10px;
    width: 5%;
    background-color: #0a95ff;
    color: white;
    font-size: x-small;
  }
  button:hover {
    background-color: #0a95ff;
    color: lightgray;
  }
`;
function AskForm() {
  return (
    <div>
      <TitleForm>
        <h4>Title</h4>
        <p>
          Be specific and imagin you{"'"}re asking a question to another person.
        </p>
        <input
          type="text"
          placeholder="e.g. is there an R function for finding the index of an element in a vector?"
        ></input>
        <Button>Next</Button>
      </TitleForm>
      <Container className="problem">
        <h4>What are the details of your problem?</h4>
        <p>
          Introduce the problem and expand on what you put in the title. Minumum
          20 characters.
        </p>
        <Editor
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
        ></Editor>
        <Button>Next</Button>
      </Container>
      <Container className="try">
        <h4>What did you try and what were you expecting?</h4>
        <p>
          Describe what you tried, what you expected to happen, and what
          actually resulted. Minumum 20 characters.
        </p>
        <Editor
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
        ></Editor>
        <Button>Next</Button>
      </Container>
      <TagForm>
        <h4>Tags</h4>
        <p>
          Add up to 5 tags to describe what your question is about. Start typing
          to see sugestions.
        </p>
        <input type="text" placeholder="e.g.(vba css json)"></input>
        <Button>Next</Button>
      </TagForm>
      <Button className="discard">Discard draft</Button>
    </div>
  );
}

export default AskForm;

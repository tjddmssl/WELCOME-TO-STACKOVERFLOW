import { Editor } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import styled from 'styled-components';
import { Button } from '@mui/material';
import { useRef, useState } from 'react';

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
    &:focus {
      outline: none;
      border-color: hsl(206deg 100% 52%);
      box-shadow: 0px 0px 0px 5px #e1ecf4;
    }
  }
  button {
    text-transform: capitalize;
    margin-top: 10px;
    width: 5%;
    background-color: #0a95ff;
    color: white;
    font-size: small;
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
  @media screen and (max-width: 1369px) {
    width: 100%;
  }

  h4 {
    margin-bottom: 10px;
  }

  p {
    margin-top: 10px;
  }
  button {
    text-transform: capitalize;
    font-size: small;
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
function AskForm() {
  const [isFocused, setIsFocused] = useState(false);
  const editorRef = useRef(); // DOM 선택용
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
        <EditorWrapper focus={isFocused}>
          <Editor
            ref={editorRef} // DOM 선택용
            initialValue={'please write here'}
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
            onFocus={() => setIsFocused(true)}
            onBlur={() => setIsFocused(false)}
            autofocus={false}
            hideModeSwitch={true}
          ></Editor>
        </EditorWrapper>
        <Button>Next</Button>
      </Container>
      <Container className="try">
        <h4>What did you try and what were you expecting?</h4>
        <p>
          Describe what you tried, what you expected to happen, and what
          actually resulted. Minumum 20 characters.
        </p>
        <EditorWrapper>
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
            autofocus={false}
            hideModeSwitch={true}
          ></Editor>
        </EditorWrapper>
        <Button>Next</Button>
      </Container>
      <Form>
        <Button className="discard">Discard draft</Button>
      </Form>
    </div>
  );
}

export default AskForm;

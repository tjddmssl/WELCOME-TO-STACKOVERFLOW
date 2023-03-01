import { useDispatch } from 'react-redux';
// import viewEditSlice from '../redux/slice/viewEditSlice';
import { useEffect, useRef, useState } from 'react';
import { Editor } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import getQuestionSlice from '../../redux/slice/getQuestionSlice';
import Loading from './Loading';

function ViewEditEditor({ question }) {
  const dispatch = useDispatch();
  const editorRef = useRef();
  const [isLoading, setIsLoading] = useState(true);
  //   const question = useSelector((state) => {
  //     return state.getQuestion.response;
  //   });
  useEffect(() => {
    setTimeout(() => {
      setIsLoading(false);
    }, 100);
  });
  const handleChangeInput = () => {
    dispatch(
      getQuestionSlice.actions.get({
        ...question,
        content: editorRef.current?.getInstance().getMarkdown(),
      })
    );
  };

  return (
    <div>
      {isLoading ? (
        <Loading />
      ) : (
        <Editor
          ref={editorRef}
          id="body"
          initialValue={`${question.content ? question.content : ' '}`}
          onChange={handleChangeInput}
        />
      )}
    </div>
  );
}

export default ViewEditEditor;

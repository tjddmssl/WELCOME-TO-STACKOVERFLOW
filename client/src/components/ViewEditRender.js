import { useSelector } from 'react-redux';
import '@toast-ui/editor/dist/toastui-editor.css';
import ReactMarkdown from 'react-markdown';

function ViewEditRender() {
  const content = useSelector((state) => {
    return state.getQuestion.response.content;
  });

  return <ReactMarkdown>{content}</ReactMarkdown>;
}

export default ViewEditRender;

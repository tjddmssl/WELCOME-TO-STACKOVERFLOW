import QList from './QList';
import axios from 'axios';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import getAllQuestionSlice from '../../redux/slice/getAllQuestionSlice';

//* 질문 리스트 조건부 렌더링하기
function QLists() {
  const dispatch = useDispatch();
  const questions = useSelector((state) => {
    return state.getAllQuestion.response.content;
  });
  useEffect(() => {
    const getData = async () => {
      try {
        const response = await axios.get('http://13.125.211.79:8080/questions');
        console.log(response.data);
        dispatch(
          getAllQuestionSlice.actions.get(response.data.response.content)
        );
      } catch (error) {
        console.log(error);
      }
    };
    getData();
  }, []);
  return (
    <div>
      {questions &&
        questions.map((el) => {
          return <QList key={el.id} question={el} />;
        })}
    </div>
  );
}

export default QLists;

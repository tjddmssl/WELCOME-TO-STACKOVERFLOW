import { useSelector, useDispatch } from 'react-redux';
import { useEffect } from 'react';
import axios from 'axios';
import QViewDetail from './QViewDetail';
import getQViewSlice from '../../../redux/slice/getQView';

function QView() {
  const dispatch = useDispatch();
  const data = useSelector((state) => {
    return state.getQView.question;
  });

  useEffect(() => {
    const getQuestion = async () => {
      try {
        const response = await axios.get('http://localhost:3002/question');
        dispatch(getQViewSlice.actions.get(response.data));
      } catch (error) {
        console.log(error);
      }
    };
    getQuestion();
  }, []);

  return (
    <div>
      <QViewDetail key={data.id} question={data} />
    </div>
  );
}

export default QView;

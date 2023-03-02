import { useSelector, useDispatch } from 'react-redux';
import { useEffect } from 'react';
import axios from 'axios';
import QViewDetail from './QViewDetail';
import getQViewSlice from '../../../redux/slice/getQView';
import { useParams } from 'react-router-dom';

function QView() {
  const params = useParams();
  const dispatch = useDispatch();
  const data = useSelector((state) => {
    return state.getQView.question;
  });
  useEffect(() => {
    const getQuestion = async () => {
      try {
        const response = await axios.get(
          `https://siglee.site/questions/${params.id}`
        );
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

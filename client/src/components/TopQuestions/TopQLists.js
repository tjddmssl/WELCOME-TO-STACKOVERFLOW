import TopQList from './TopQList';
import { useSelector, useDispatch } from 'react-redux';
import { useEffect } from 'react';
import getTopQListSlice from '../../redux/slice/getTopQListSlice';
import axios from 'axios';

//* 질문 리스트 조건부 렌더링하기
function TopQLists() {
  const dispatch = useDispatch();
  const data = useSelector((state) => {
    return state.getTopQList.content;
  });
  useEffect(() => {
    const getData = async () => {
      try {
        const response = await axios.get('https://siglee.site/questions');
        console.log(response.data.response.content);
        dispatch(getTopQListSlice.actions.get(response.data.response.content));
      } catch (error) {
        console.log(error);
      }
    };
    getData();
  }, []);

  return (
    <div>
      {data.map((el) => {
        return <TopQList key={el.id} content={el} />;
      })}
    </div>
  );
}

export default TopQLists;

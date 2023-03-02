import styled from 'styled-components';
import { Button } from '@mui/material';
import { useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const Container = styled.div`
  margin-top: 40px;
  display: flex;
  input {
    width: 500px;
    border: 1px solid grey;
    border-radius: 3px;
  }
  input:focus {
    outline: 1px solid orange;
    border: none;
  }

  button {
    padding: 10px;
    margin-left: 10px;
    font-size: 10px;
    width: auto;
  }
`;
// TODO isClicked, setIsClicked props 내려받기
// TODO 버튼 클릭 시 이벤트 핸들러 함수 연결하기
//TODO 버튼 클릭 시 isClicked false로 변경하기
//TODO 댓글 내용 입력 후 버튼 클릭 시 axios POST로 내용 보내주기

function ViewComment({ isClicked, setIsClicked }) {
  const params = useParams();
  const [comment, setComment] = useState({});
  console.log(comment);

  const addCommentClickHandler = () => {
    const postComment = async () => {
      try {
        await axios({
          url: `https://siglee.site/questions/${params.id}/comments`,
          method: 'post',
          data: {
            content: comment,
          },
        });
      } catch (err) {
        console.log(err);
      }
    };
    postComment();
    setIsClicked(!isClicked);
  };

  const handleChanged = (e) => {
    setComment(e.target.value);
    console.log(e.target.value);
  };
  return (
    <Container>
      <input onChange={handleChanged} type="text"></input>
      <Button onClick={addCommentClickHandler}>Add Comment</Button>
    </Container>
  );
}

export default ViewComment;

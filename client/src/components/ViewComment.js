import styled from 'styled-components';
import { Button } from '@mui/material';
import { useEffect, useState } from 'react';
import axios from 'axios';

const Container = styled.div`
  margin-top: 20px;
  input {
    width: 600px;
    border: 1px solid grey;
    border-radius: 3px;
  }
  input:focus {
    outline: 1px solid orange;
    border: none;
  }
`;

//TODO 버튼 클릭 시 isClicked false로 변경하기
//TODO 댓글 내용 입력 후 버튼 클릭 시 axios POST로 내용 보내주기

function ViewComment({ isClicked, setIsClicked }) {
  const [comment, setComment] = useState({});
  console.log(comment);

  useEffect((comment) => {
    const submitComment = async () => {
      try {
        await axios.post('http://localhost:3002/question', {
          comment,
        });
      } catch (error) {
        console.log(error);
      }
    };
    submitComment();
    setIsClicked(!isClicked);
  }, []);

  const handleChanged = (e) => {
    setComment(e.target.value);
    console.log(e.target.value);
  };
  return (
    <Container>
      <input onChange={handleChanged} type="text"></input>
      <Button onClick={useEffect}>Add Comment</Button>
    </Container>
  );
}

export default ViewComment;

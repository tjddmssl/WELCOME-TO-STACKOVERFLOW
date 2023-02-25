import styled from 'styled-components';
// import { Button } from '@mui/material';
// import { useEffect, useState } from 'react';
// import axios from 'axios';

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

//TODO 버튼 클릭 시 isClicked 변경?
//TODO 댓글 내용 axios POST

function ViewComment() {
  // const [comment, setComment] = useState('');
  // const handleClicked = () => {
  //   useEffect((comment) => {
  //     const submitComment = async () => {
  //       try {
  //         await axios({})
  //       }
  //     }
  //   }, []) ;
  // };
  // const handleChanged = (e) => {
  //   setComment(e.target.value);
  // };
  return (
    <Container>
      {/* <input onChange={handleChanged(e)} type="textarea"></input>
      <Button onClick={handleClicked}>Add Comment</Button> */}
    </Container>
  );
}

export default ViewComment;

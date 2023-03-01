import styled from 'styled-components';
import axios from 'axios';
import { useEffect, useState } from 'react';

// * 질문 댓글 get으로 받아오기
// * 해당 데이터 state으로 관리하기
// * 해당 데이터 map으로 뿌려주기

const Container = styled.div`
  border-top: 1px solid #e3e6e8;
  display: flex;
  flex-direction: column;
  width: 600px;
  margin-right: 20px;
  div {
    display: flex;
    border-bottom: 1px solid #e3e6e8;
  }
  p {
    margin: 15px;
  }
  a {
    margin: 15px;
  }
`;

function QComment() {
  const [comments, setComments] = useState([]);

  useEffect(() => {
    const getQuestion = async () => {
      try {
        const response = await axios.get('http://localhost:3002/question');
        setComments(response.data.comment);
      } catch (error) {
        console.log(error);
      }
    };
    getQuestion();
  }, []);
  return (
    <Container>
      {comments &&
        comments.map((el, idx) => (
          <div key={idx}>
            <p>
              {el.content} {'-'}
            </p>
            <a href="/">{el.member.displayName}</a>
            <p>{el.createdDate}</p>
          </div>
        ))}
    </Container>
  );
}

export default QComment;

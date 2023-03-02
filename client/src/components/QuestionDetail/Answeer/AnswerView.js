import styled from 'styled-components';
import Vote from '../Question/Vote';
import { detailDate } from '../../detailDate';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const Container = styled.div`
  display: flex;
  margin-top: 15px;
  border-bottom: 1px solid #e3e6e8;
`;

const AnswersContainer = styled.div`
  width: 100%;
  button {
    border: none;
    border-radius: 2px;
    background-color: transparent;
    color: #6b737c;
    width: auto;
    padding: 5px 2px;
    margin-right: 8px;
  }
  button:hover {
    color: lightgrey;
    background-color: transparent;
  }

  .footer {
    display: flex;
    justify-content: space-between;
  }

  .comment {
    color: #b6babf !important;
    width: 100px !important;
    margin-top: 40px;
    background-color: transparent;
  }

  .comment:hover {
    color: #0174cc !important;
    background-color: transparent;
  }
`;

const ButtonContainer = styled.div`
  display: flex;
  align-items: center;
`;

const UserContainer = styled.div`
  display: flex;
  flex-direction: column;
  /* width: 320px; */
  font-size: small;
  color: #6b737c;
  width: 200px;
  padding: 5px 10px;
  border: 1px solid black;
  font-size: small;
  color: #6b737c;
  border: none;
  border-radius: 3px;
  background-color: #d9eaf7;
  img {
    width: 40px;
    margin-top: 5px;
    margin-right: 10px;
  }

  .usercard {
    color: #0174cc;
  }
`;

const Comment = styled.div``;

function AnswerView({ answer }) {
  const params = useParams();
  const handleDelete = () => {
    const deleteAnswer = async () => {
      try {
        await axios.delete(
          `https://siglee.site/questions/${params.id}/answers/${answer.id}`
        );
      } catch (error) {
        console.log(error);
      }
    };
    deleteAnswer();
    window.location.href = 'https://urban-adventure-ovr7jln.pages.github.io/';
  };

  return (
    <>
      <Container>
        <Vote voteCount={answer.voteCount} />
        <AnswersContainer>
          {answer.content}
          <div className="footer">
            <ButtonContainer>
              <button>Share</button>
              <button>Edit</button>
              <button>Follow</button>
              <button onClick={handleDelete}>Delete</button>
            </ButtonContainer>
            <UserContainer>
              Answered {detailDate(new Date(answer.createdDate))}
              {/* //TODO 이미지 백엔드 통신으로 수정 */}
              <div className="usercard">
                <img src="/img/user.png" alt="useravatar" />
                {answer.member.displayName && answer.member.displayName}
              </div>
            </UserContainer>
          </div>
          <button className="comment">Add a comment</button>
        </AnswersContainer>
        <Comment>
          {answer.comment &&
            answer.comment.map((el, idx) => <p key={idx}>{idx}</p>)}
        </Comment>
      </Container>
    </>
  );
}

export default AnswerView;

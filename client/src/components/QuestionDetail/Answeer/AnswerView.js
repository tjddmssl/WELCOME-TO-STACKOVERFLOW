import styled from 'styled-components';
import Vote from '../Question/Vote';

const Container = styled.div`
  display: flex;
  margin-top: 15px;
`;

const AnswersContainer = styled.div`
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
`;

const UserContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 80px;
  margin-left: 200px;
  width: 320px;
  font-size: small;
  color: #6b737c;
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

// TODO map으로 여러개 뿌려줘야 함
function AnswerView({ answer }) {
  return (
    <>
      <h3>N Answers</h3>
      <Container>
        <Vote voteCount={answer.voteCount} />
        <AnswersContainer>
          {answer.content}
          <div className="footer">
            <ButtonContainer>
              <button>Share</button>
              <button>Edit</button>
              <button>Follow</button>
            </ButtonContainer>
            <UserContainer>
              answered {answer.createdDate}
              {/* //TODO 이미지 백엔드 통신으로 수정 */}
              <div className="usercard">
                <img src="/img/user image.png" alt="useravatar" />
                {/* {answer.member.displayName && answer.member.displayName} */}
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

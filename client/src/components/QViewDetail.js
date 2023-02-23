import styled from 'styled-components';
import QButton from './QButton';
import Vote from './Vote';
import { MdWavingHand } from 'react-icons/md';

const TitleContainer = styled.div`
  display: flex;
  flex-direction: column;

  .title {
    display: flex;
    flex-direction: row;
  }
  h2 {
    margin-top: 10px;
    max-width: 500px;
  }
  button {
    margin-left: 890px;
    width: 100px;
  }
`;

const TitleDate = styled.div`
  border-bottom: 1px solid #f2f3f4;
`;

const ContentContainer = styled.div`
  display: flex;
  margin-top: 20px;
`;

const Content = styled.div`
  margin-left: 20px;
  display: flex;
  flex-direction: column;
  .tag {
    display: flex;
  }
  button {
    border: none;
    border-radius: 2px;
    background-color: #e1ecf4;
    color: #39739d;
    width: 55px;
    padding: 5px 2px;
    margin-right: 8px;
    margin-top: 30px;
  }
  button:hover {
    background-color: #d0e3f1;
  }
`;

const ButtonContainer = styled.div`
  display: flex;

  button {
    background-color: transparent;
    color: #6b737c;
  }

  button:hover {
    color: lightgrey;
    background-color: transparent;
  }

  .usercard {
    width: 200px;
    margin-left: 300px;
    margin-top: 100px;
    padding: 5px 10px;
    border: 1px solid black;
    font-size: small;
    color: #6b737c;
    border: none;
    border-radius: 3px;
    background-color: #d9eaf7;
  }
  .userinfo {
    color: #0174cc;
  }
  img {
    width: 40px;
    margin-right: 10px;
    margin-top: 5px;
  }
  .contributor {
    background-color: #d0e3f1;
    padding-top: 5px;
    padding-bottom: 5px;
    height: 25px;
    color: #3b4045;
  }
`;

function QViewDetail({ question }) {
  return (
    <>
      <TitleContainer>
        <div className="title">
          <h2>{question.title}</h2>
          <QButton />
        </div>
        <TitleDate>
          <p>
            Asked {question.createdDate} Modified {question.lastModifiedDate}
          </p>
        </TitleDate>
      </TitleContainer>
      <ContentContainer>
        <Vote />
        <Content>
          {question.content}
          <div className="tag">
            {question.tag &&
              question.tag.map((el, idx) => <button key={idx}>{el}</button>)}
          </div>
          <ButtonContainer>
            <button>Share</button>
            <button>Edit</button>
            <button>Follow</button>
            {/* // TODO user card 클릭 시 유저 상세조회 페이지로 이동 */}
            <div className="usercard">
              asked {question.createdDate}
              <div className="userinfo">
                {/* // TODO user image 파일 경로 수정 */}
                <img src="/img/user image.png" alt="user avatar" />
                {question.member && question.member.displayName}
              </div>
              <div className="contributor">
                <MdWavingHand />
                {' New contributor'}
              </div>
            </div>
          </ButtonContainer>
        </Content>
      </ContentContainer>
    </>
  );
}

export default QViewDetail;

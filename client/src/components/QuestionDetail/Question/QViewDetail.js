import styled from 'styled-components';
import { useNavigate, useParams } from 'react-router-dom';
import QButton from '../../Layout/QButton';
import Vote from './Vote';
import { MdWavingHand } from 'react-icons/md';
import { useState } from 'react';
import ViewComment from './ViewComment';
import QComment from './QComment';
import axios from 'axios';
import { detailDate } from '../../detailDate';
import ReactMarkdown from 'react-markdown';

const TitleContainer = styled.div`
  display: flex;
  flex-direction: column;

  .title {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  h2 {
    margin-top: 10px;
    max-width: 500px;
    white-space: nowrap;
  }
  button {
    width: 100px;
  }
`;

const TitleDate = styled.div`
  border-bottom: 1px solid #f2f3f4;
  .qview-date__span {
    color: hsl(210, 8%, 45%);
    font-size: 13px;
    margin-right: 5px;
  }
  .qview-dateinfo__span {
    font-size: 13px;
    margin-right: 10px;
  }
`;

const ContentContainer = styled.div`
  display: flex;
  margin-top: 20px;
  width: 100%;
`;

const Content = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  /* flex-grow: 1; */
  .tag {
    display: flex;
    width: 100%;
  }
  button {
    border: none;
    border-radius: 2px;
    background-color: #e1ecf4;
    color: #39739d;
    /* width: 55px; */
    padding: 5px 10px;
    margin-right: 8px;
    margin-top: 30px;
  }
  button:hover {
    background-color: #d0e3f1;
  }
  .comment {
    color: #b6babf !important;
    width: 100px !important;
    margin-top: 40px;
    background-color: transparent;
    white-space: nowrap;
  }

  .comment:hover {
    color: #0174cc !important;
    background-color: transparent;
  }
  .qview-content__reactmarkdown {
    width: 300px;
  }
`;

const ButtonContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
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
  .qview-modify__button {
    margin-top: 0;
    margin-right: 0;
  }
`;

function QViewDetail({ question }) {
  const params = useParams();
  const navigate = useNavigate();

  const navigateToEdit = () => {
    navigate(`./edit`);
  };

  const [isClicked, setIsClicked] = useState(false);

  const handleClicked = () => {
    setIsClicked(!isClicked);
  };

  //* 질문 삭제 요청
  // TODO handleDelete 주소 수정
  const handleDelete = () => {
    const deleteQuestion = async () => {
      try {
        await axios.delete(`https://siglee.site/questions/${params.id}`);
      } catch (error) {
        console.log(error);
      }
    };
    deleteQuestion();
  };

  // TODO Delete 버튼에 로그인 상태 확인 로직 추가

  return (
    <>
      <TitleContainer>
        <div className="title">
          <h2>{question.title}</h2>
          <a href="/ask">
            <QButton />
          </a>
        </div>
        <TitleDate>
          <p>
            <span className="qview-date__span">Asked</span>
            <sapn className="qview-dateinfo__span">
              {detailDate(new Date(question.createdDate))} {` `}
            </sapn>
            <span className="qview-date__span">Modified</span>
            <span className="qview-dateinfo__span">
              {detailDate(new Date(question.lastModifiedDate))}
            </span>
          </p>
        </TitleDate>
      </TitleContainer>
      <ContentContainer>
        <Vote />
        <Content>
          <ReactMarkdown
            className="qview-content__reactmarkdown"
            style={{ maxWidth: '100%' }}
            components={{
              img: ({ ...props }) => (
                <img style={{ maxWidth: '50%' }} {...props} alt="" />
              ),
            }}
          >
            {question.content}
          </ReactMarkdown>
          <div className="tag">
            {question.tag &&
              question.tag.map((el, idx) => <button key={idx}>{el}</button>)}
          </div>
          <ButtonContainer>
            <div>
              <button className="qview-modify__button">Share</button>
              <button className="qview-modify__button" onClick={navigateToEdit}>
                Edit
              </button>
              <button className="qview-modify__button">Follow</button>
              <button className="qview-modify__button" onClick={handleDelete}>
                Delete
              </button>
            </div>
            {/* // TODO user card 클릭 시 유저 상세조회 페이지로 이동 */}
            <div className="usercard">
              {`Asked `} {detailDate(new Date(question.createdDate))}
              <div className="userinfo">
                {/* // TODO user image 파일 경로 수정 */}
                <img src="/img/user.png" alt="user avatar" />
                {question.member && question.member.displayName}
              </div>
              <div className="contributor">
                <MdWavingHand />
                {' New contributor'}
              </div>
            </div>
          </ButtonContainer>
          <QComment />
          {isClicked ? (
            <ViewComment isClicked={isClicked} setIsClicked={setIsClicked} />
          ) : (
            <button className="comment" onClick={handleClicked}>
              Add a comment
            </button>
          )}
        </Content>
      </ContentContainer>
    </>
  );
}

export default QViewDetail;

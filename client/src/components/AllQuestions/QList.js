import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { detailDate } from '../detailDate';

const Container = styled.div`
  display: flex;
  align-items: center;
  padding: 16px;
  border-top: 1px solid #d6d9dc;
  margin-left: -24px;
`;

const QStatus = styled.div`
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
  justify-content: space-between;
  width: 108px;
  align-items: flex-end;
  margin-right: 16px;
  gap: 6px;
  .allques-votecount__span {
    font-size: 13px;
    font-weight: bold;
    margin-right: 3px;
  }
  .allques-qstatuscount__span {
    font-size: 13px;
    color: #8d939a;
    font-weight: bold;
    margin-right: 3px;
  }
  .allques-qstatus__span {
    color: #8d939a;
  }
  div {
    font-size: 13px;
  }
`;

const QContent = styled.div`
  flex-grow: 1;
  max-width: 100%;
  .allques-title__h3 {
    font-weight: 400;
    vertical-align: text-top;
    margin: 0;
    a {
      color: hsl(206, 100%, 40%, 1);
      :hover {
        color: hsl(206, 100%, 52%, 1);
      }
    }
  }
  .allques-content__div {
    font-size: 13px;
    overflow: hidden;
    display: -webkit-box;
    width: 100%;
    white-space: normal;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
  }
`;
const QInfo = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
  .allques-user__img {
    width: 20px;
    height: 20px;
    border-radius: 3px;
  }
  .allques-taglist__ul {
    li {
      float: left;
      margin-right: 5px;
      margin-bottom: 5px;
      white-space: nowrap;
    }
    a {
      background-color: #e1ecf4;
      padding: 3px;
      border-radius: 5px;
      color: #39739d;
      font-size: 12px;
      :hover {
        background-color: #d0e3f1;
        color: #2c5877;
      }
    }
  }
  .allques-reputation__div {
    font-weight: bold;
  }
  div {
    margin-right: 3px;
  }
`;

const UserInfoWrap = styled.div`
  display: flex;
  font-size: 12px;
  padding-top: 5px;
  .allques-username__a {
    color: #39739d;
    :hover {
      color: #2c5877;
    }
  }
`;

function QList({ question }) {
  const titleUrl = `/view/${question.id}`;
  const tagLinkUrl = '/';
  const userProfileUrl = '/';
  return (
    <Container>
      <QStatus>
        <div>
          <span className="allques-votecount__span">{question.voteCount}</span>
          <span className="allques-vote__span">votes</span>
        </div>
        <div>
          <span className="allques-qstatuscount__span">0</span>
          <span className="allques-qstatus__span">answers</span>
        </div>
        <div>
          <span className="allques-qstatuscount__span">
            {question.viewCount}
          </span>
          <span className="allques-qstatus__span">views</span>
        </div>
      </QStatus>
      <QContent>
        <h3 className="allques-title__h3">
          <Link href={titleUrl}>{question.title}</Link>
        </h3>
        <div className="allques-content__div">{question.content}</div>
        <QInfo>
          <div>
            <ul className="allques-taglist__ul">
              {question.tags &&
                question.tags.map((el, idx) => {
                  return (
                    <li key={idx}>
                      <a href={tagLinkUrl}>{el}</a>
                    </li>
                  );
                })}
            </ul>
          </div>
          <UserInfoWrap>
            <div>
              {/* 유저프로필 링크 */}
              <a href={userProfileUrl}>
                <img
                  className="allques-user__img"
                  src="/img/user.png"
                  alt="userprofile"
                ></img>
              </a>
            </div>
            <div>
              {/* 유저프로필 링크 */}
              <a className="allques-username__a" href={userProfileUrl}>
                {question.member.displayName}
              </a>{' '}
            </div>
            <div className="allques-reputation__div">1</div>
            <div>asked {detailDate(new Date(question.createdDate))}</div>
          </UserInfoWrap>
        </QInfo>
      </QContent>
    </Container>
  );
}

export default QList;

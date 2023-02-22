import styled from 'styled-components';

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
  .topques-votecount__span {
    font-size: 13px;
    font-weight: bold;
    margin-right: 3px;
  }
  .topques-qstatuscount__span {
    font-size: 13px;
    color: #8d939a;
    font-weight: bold;
    margin-right: 3px;
  }
  .topques-qstatus__span {
    color: #8d939a;
  }
  div {
    font-size: 13px;
  }
`;

const QContent = styled.div`
  flex-grow: 1;
  max-width: 100%;
  .topques-title__h3 {
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
`;
const QInfo = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
  .topques-user__img {
    width: 20px;
    height: 20px;
    border-radius: 3px;
  }
  .topques-taglist__ul {
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
  .topques-reputation__div {
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
  .topques-username__a {
    color: #39739d;
    :hover {
      color: #2c5877;
    }
  }
`;

function TopQList() {
  const titleUrl = '/';
  const tagLinkUrl = '/';
  const userProfileUrl = '/';
  return (
    <Container>
      <QStatus>
        <div>
          <span className="topques-votecount__span">0</span>
          <span className="topques-vote__span">votes</span>
        </div>
        <div>
          <span className="topques-qstatuscount__span">0</span>
          <span className="topques-qstatus__span">answers</span>
        </div>
        <div>
          <span className="topques-qstatuscount__span">0</span>
          <span className="topques-qstatus__span">views</span>
        </div>
      </QStatus>
      <QContent>
        <h3 className="topques-title__h3">
          <a href={titleUrl}>
            title title title title title title title title title title title
            title
          </a>
        </h3>
        <QInfo>
          <div>
            <ul className="topques-taglist__ul">
              <li>
                {/* tag 링크 */}
                <a href={tagLinkUrl}>태그명</a>
              </li>
            </ul>
          </div>
          <UserInfoWrap>
            <div>
              {/* 유저프로필 링크 */}
              <a href={userProfileUrl}>
                <img
                  className="topques-user__img"
                  src="/img/user.png"
                  alt="userprofile"
                ></img>
              </a>
            </div>
            <div>
              {/* 유저프로필 링크 */}
              <a className="topques-username__a" href={userProfileUrl}>
                username
              </a>{' '}
            </div>
            <div className="topques-reputation__div">1</div>
            <div>asked 00 ago</div>
          </UserInfoWrap>
        </QInfo>
      </QContent>
    </Container>
  );
}

export default TopQList;

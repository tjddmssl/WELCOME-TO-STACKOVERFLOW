import styled from 'styled-components';

const UserContainer = styled.li`
  display: flex;
  padding: 5px 6px 7px 7px;
  .usercard-profile__img {
    width: 48px;
    height: 48px;
  }
  .usercard-displayname__div {
    font-size: 15px;
    color: #0074cc;
    cursor: pointer;
  }
  .usercard-country__div {
    font-size: 12px;
  }
  .usercard-tag__div {
    font-size: 12px;
    color: #0074cc;
    cursor: pointer;
    display: flex;
    flex-wrap: wrap;
    span {
      margin: 2px;
    }
  }
`;

const UserInfoWrap = styled.div`
  margin-left: 5px;
`;

function UserCard({ user }) {
  return (
    <UserContainer>
      <img
        className="usercard-profile__img"
        src="/img/user.png"
        alt="profileimage"
      ></img>
      <UserInfoWrap>
        <div className="usercard-displayname__div">{user.displayName}</div>
        <div className="usercard-country__div">{user.location}</div>
        <div className="usercard-tag__div">
          {user.tags.map((el, idx) => {
            return <span key={idx}>{el}</span>;
          })}
        </div>
      </UserInfoWrap>
    </UserContainer>
  );
}

export default UserCard;

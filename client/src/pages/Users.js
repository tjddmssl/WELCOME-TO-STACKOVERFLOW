import UserCard from '../components/Users/UserCard';
import Header from '../components/Layout/Header';
import NavBar from '../components/Layout/NavBar';
import Footer from '../components/Layout/Footer';
import styled from 'styled-components';
import SearchIcon from '@mui/icons-material/Search';
import { Button } from '@mui/material';
import { useEffect, useState } from 'react';
import axios from 'axios';
//* HOME_004

const Container = styled.div`
  display: flex;
  flex-direction: column;
  padding-top: 50px;
`;

const MainContainer = styled.div`
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  .mainside-wrap__div {
    max-width: 1100px;
    width: calc(100% - 164px);
  }
`;

const UserContainer = styled.div`
  display: flex;
  flex-direction: column;
  /* width: 1088px; */
  margin-left: 24px;
`;

const UsersHeader = styled.div`
  margin-top: 20px;
  margin-bottom: 70px;
  h1 {
    font-weight: 400;
    margin: 0 0 25px;
  }

  .header {
    margin-top: 30px;
    display: flex;
  }

  .search {
    display: flex;
    align-items: center;
    height: 36px;
    width: 300px;
    border: 1px solid #bbbfc4;
    border-radius: 3px;
    padding-left: 5px;
    color: #838c95;

    &:focus-within {
      box-shadow: 0px 0px 0px 4px hsl(205, 53%, 88%);
      border-color: hsl(206, 100%, 40%);
    }
  }

  .search > input {
    font-size: 15px;
    margin-left: 5px;
    border: none;
    outline-width: 0px;
  }

  .buttons {
    margin-left: 350px;
    display: flex;
    width: auto;
    border: 1px solid #9fa6ad;
    border-radius: 3px;
    white-space: nowrap;
  }
  button {
    border: 1px solid #9fa6ad;
    border-radius: 0%;
    text-transform: capitalize;
    width: auto;
    color: #6b737c;
  }

  .first {
    background-color: #e3e6e8;
    color: #3b4045;
  }

  .first:hover {
    background-color: #e3e6e8;
  }
`;

// const UserCardList = styled.div`
//   display: flex;
//   flex-wrap: wrap;
// `;

const UserCardList = styled.ul`
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  grid-gap: 20px;
  width: 100%;
  margin-bottom: 50px;
  @media screen and (max-width: 1345px) {
    grid-template-columns: repeat(3, minmax(0, 1fr));
  }
  @media screen and (max-width: 1028px) {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
  @media screen and (max-width: 711px) {
    grid-template-columns: repeat(1, minmax(0, 1fr));
  }
`;

function Users() {
  const [userLists, setUserLists] = useState([]);
  const getUserLists = async () => {
    try {
      const response = await axios.get(
        'http://13.125.211.79:8080/users?page=0&size=28'
      );
      setUserLists([...response.data.content]);
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    getUserLists();
  }, []);
  return (
    <div>
      <Container>
        <Header />
        <MainContainer>
          <NavBar />
          <div className="mainside-wrap__div">
            <UserContainer>
              <UsersHeader>
                <h1>Users</h1>
                <div className="header">
                  <form className="search">
                    <SearchIcon />
                    <input type="text" placeholder="Filter by user"></input>
                  </form>
                  <div className="buttons">
                    <Button className="first">Reputation</Button>
                    <Button>New users</Button>
                    <Button>Voters</Button>
                    <Button>Editors</Button>
                    <Button>Moderators</Button>
                  </div>
                </div>
              </UsersHeader>
              <UserCardList>
                {userLists.map((el) => {
                  return <UserCard user={el} key={el.id} />;
                })}
              </UserCardList>
            </UserContainer>
          </div>
        </MainContainer>
        <Footer />
      </Container>
    </div>
  );
}

export default Users;

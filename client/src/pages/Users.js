import React, { useEffect } from 'react';
import UserCard from '../components/UserCard';
import Header from '../components/Header';
import NavBar from '../components/NavBar';
import axios from 'axios';

//* HOME_004
function Users() {
  // const [users, setUsers] = useState();

  //! GET 요청
  const url = 'url 주소 받아서 넣기';
  useEffect(() => {
    const usersGetData = async () => {
      try {
        const res = await axios.get(url);
        // setUsers(res.data); //?
        console.log(res);
      } catch (err) {
        throw err;
      }
    };
    usersGetData();
  }, []);

  //! POST 요청
  useEffect(() => {
    const usersPostData = async () => {
      try {
        const res = await axios.post(url, {});
        // setUsers(res.data); //?
        console.log(res);
      } catch (err) {
        throw err;
      }
    };
    usersPostData();
  }, []);

  //! DELETE 요청
  useEffect(() => {
    const usersDeleteData = async () => {
      try {
        const res = await axios.delete(url);
        // setUsers(res.data); //?
        console.log(res);
      } catch (err) {
        throw err;
      }
    };
    usersDeleteData();
  }, []);

  return (
    <div>
      <Header />
      <NavBar />
      <h1>Users page</h1>
      Users <br />
      <input type="text" placeholder="Filter by user"></input>
      <UserCard />
    </div>
  );
}

export default Users;

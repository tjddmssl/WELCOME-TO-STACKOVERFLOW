import UserCard from '../components/UserCard';
import Header from '../components/Header';
import NavBar from '../components/NavBar';

//* HOME_004
function Users() {
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

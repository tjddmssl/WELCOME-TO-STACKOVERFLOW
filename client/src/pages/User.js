import Header from '../components/Layout/Header';
import NavBar from '../components/Layout/NavBar';

//* USER_001 유저 상세조회
function User() {
  return (
    <div>
      <Header />
      <NavBar />
      <h1>User page</h1> <br />
      avatar, name, information, answers, questions
    </div>
  );
}

export default User;

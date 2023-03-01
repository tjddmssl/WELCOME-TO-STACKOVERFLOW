import Header from '../components/Layout/Header';
import LoginForm from '../components/Login/LoginForm';

//* LOGIN_001
// TODO  로그아웃 시, alert 창 + 홈으로 돌아가기
function Login() {
  return (
    <div>
      <Header />
      <LoginForm />
    </div>
  );
}

export default Login;

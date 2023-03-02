import { Link } from 'react-router-dom';
import styled from 'styled-components';
import LoginSocialButtons from './LoginSocialButtons';
import { useState } from 'react';
import axios from 'axios';
import { userSlice } from '../../redux/slice/userSlice';
import { useDispatch } from 'react-redux';

const LoginContainer = styled.div`
  height: 100vh;
  background-color: #f2f2f3;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  .div__login-content {
    flex-direction: column;
    width: 60%;
    display: flex;
    justify-content: center;
    align-items: center;
    .div__account-links {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 2rem;
      margin-top: 2rem;
      font-size: 0.9rem;
    }
    .div__employer-link {
      margin-top: 1rem;
    }
  }
  a {
    text-decoration: none;
    color: hsl(206, 100%, 40%);
  }
  .div__signup {
    text-decoration: none;
    color: hsl(206, 100%, 40%);
  }
`;
const Logo = styled.img`
  width: 52px;
  height: 57px;
  cursor: pointer;
  text-align: center;
  vertical-align: bottom;
`;

const LoginForms = styled.form`
  border-radius: 10px;
  width: 18.5rem;
  height: 15rem;
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  .div__inputforms {
    display: flex;
    justify-content: flex-start;
    align-items: flex-end;
    flex-wrap: wrap;
    width: 80%;
    height: 70%;
  }
  .label {
    display: flex;
    width: 100%;
    margin: 0px;
    font-weight: bold;
    font-size: 1rem;
  }
`;
const LoginInput = styled.input`
  width: 100%;
  height: 2rem;
  border: 1px solid lightgray;
  border-radius: 3px;
  &:focus {
    box-shadow: 0px 0px 0px 4px hsl(205, 53%, 88%);
    border-color: hsl(206, 100%, 40%);
    outline: 4px solid #cde9fe;
  }
`;
const ForgetLink = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
  width: 70%;
  font-size: 0.5rem;
  float: right;
  color: rgba(51, 160, 255, 1);
`;

const LoginButton = styled.button`
  width: 80%;
  height: 2.5rem;
  background-color: rgba(51, 160, 255, 1);
  border: 1px solid rgba(51, 160, 255, 1);
  border-radius: 5px;
  margin: 0.7rem 0px 1rem 0px;
  color: white;

  &:hover {
    background-color: rgba(0, 122, 230, 1);
  }
`;

function LoginForm() {
  //* 입력받은 email, password + navigate 상태 관리
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [memberId, setmemberId] = useState('');
  const dispatch = useDispatch();
  // const navigate = useNavigate();

  //* 입력받은 email, password 백엔드로 전송 + navigate 처리
  const submit = async (e) => {
    e.preventDefault();
    //* 이메일, 비밀번호 입력하지 않은 경우
    if (!email || !password) {
      setErrorMessage('이메일과 비밀번호를 입력하세요');
      return;
    } else {
      setErrorMessage('');
    }

    // 응답으로 받은 access token을 data 에 담음
    // refresh token 을 쿠키로 받으려면 withCredentials: true가 필요함
    // 개발자도구의 application -> cookie에 refresh token이 담김
    await axios
      .post(
        'https://siglee.site/login',
        { username: email, password },
        { withCredentials: true }
      )
      .then((res) => {
        //! accessToken어떤 키로 주는지 보고 수정
        console.log(res);
        if (!res) {
          console.log(res);
          if (res.status === 401) alert('이메일 또는 비밀번호가 틀렸습니다.');
        }
        if (res.status === 200) {
          alert('로그인 되었습니다.');
          const accessToken = res.headers.get('Authorization');
          //!응답보기
          const refreshToken = res.headers.get('refresh');
          localStorage.setItem('accessToken', accessToken);
          localStorage.setItem('refreshToken', refreshToken);

          axios.defaults.headers.common[
            'Authorization'
          ] = `Bearer ${accessToken}`;
          setmemberId(res.memberId);
        }
      })

      .catch((err) => {
        console.log(err);
      });

    // every request after we log in, we will heave A header with Bearer token

    //* '/user'에서 내 정보 조회해서 가져오기
    axios
      .get(`http://13.125.211.79:8080/users/${memberId}`)
      .then((res) => {
        console.log(res.data);
        dispatch(
          userSlice.actions.login({
            ...res.data,
            isLogin: true,
          })
        );
      })
      .catch((err) => {
        console.log(err);
      });
    // navigate('/');
  };

  // //* 로그인 성공 시, Home 으로 이동
  // TODO 이메일 전송 완료 alert 쓰기

  return (
    <LoginContainer className="div__login-container">
      <div className="div__login-content">
        <div className="div__logo">
          <Link to="/">
            <Logo alt="stackoverflow-logo" src="img/stackoverflow.png" />
          </Link>
        </div>

        <LoginSocialButtons />

        <LoginForms className="form__loginform" onSubmit={submit}>
          <div className="div__inputforms">
            <span className="label">Email</span>
            <LoginInput
              className="input__email"
              type="text"
              onChange={(e) => setEmail(e.target.value)}
            ></LoginInput>
            <div className="label">
              Password
              <ForgetLink className="div__forgot-email">
                Forgot password?
              </ForgetLink>
            </div>
            <LoginInput
              className="input__password"
              type="password"
              onChange={(e) => setPassword(e.target.value)}
            ></LoginInput>
          </div>
          <LoginButton className="a__lofin-submit"> Log in</LoginButton>{' '}
          {errorMessage ? { errorMessage } : ''}
        </LoginForms>
        <div className="div__account-links">
          <div>
            Do not have an account?
            <a href="/signup">Sign up</a>
          </div>
          <div className="div__employer-link">
            Are you an employer?
            <div className="div__signup">Sign up on Talent</div>
          </div>
        </div>
      </div>
    </LoginContainer>
  );
}

export default LoginForm;

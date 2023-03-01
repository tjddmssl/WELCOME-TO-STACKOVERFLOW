import { Link } from 'react-router-dom';
import styled from 'styled-components';
import LoginSocialButtons from './LoginSocialButtons';

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
  return (
    <LoginContainer className="div__login-container">
      <div className="div__login-content">
        <div className="div__logo">
          <Link to="/">
            <Logo alt="stackoverflow-logo" src="img/stackoverflow.png" />
          </Link>
        </div>

        <LoginSocialButtons />

        <LoginForms className="form__loginform">
          <div className="div__inputforms">
            <span className="label">Email</span>
            <LoginInput className="input__email" type="text"></LoginInput>
            <div className="label">
              Password
              <ForgetLink className="div__forgot-email">
                Forgot password?
              </ForgetLink>
            </div>
            <LoginInput
              className="input__password"
              type="password"
            ></LoginInput>
          </div>
          <LoginButton className="a__lofin-submit"> Log in</LoginButton>{' '}
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

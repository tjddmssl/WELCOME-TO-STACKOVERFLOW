import styled from 'styled-components';
import SvgIcon from '@mui/material/SvgIcon';
import QuestionAnswerIcon from '@mui/icons-material/QuestionAnswer';
import ThumbsUpDownIcon from '@mui/icons-material/ThumbsUpDown';
import LocalOfferIcon from '@mui/icons-material/LocalOffer';
import EmojiEventsIcon from '@mui/icons-material/EmojiEvents';
import GoogleIcon from '@mui/icons-material/Google';
import GitHubIcon from '@mui/icons-material/GitHub';
import FacebookIcon from '@mui/icons-material/Facebook';
import { useState } from 'react';
import axios from 'axios';
import { Navigate } from 'react-router-dom';

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f1f2f3;
  width: 100%;
  height: 100vh;
`;

const SignUpContents = styled.div`
  display: flex;
  align-items: center;
  .signup-division__div {
    margin: 15px;
  }
  .signup-infosection__div {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }
  .signup-info__icon {
    color: #0995ff;
    margin-right: 5px;
  }
  .signup-infobottom__div {
    font-size: 13px;
  }
  .signup-oauth__div {
    display: flex;
    flex-direction: column;
  }
  .signup-oauth__button {
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 5px;
    border: 0.1px solid;
    height: 38px;
    margin-bottom: 10px;
    span {
      margin-left: 5px;
    }
  }
  .oauth-google__button {
    background-color: white;
    :hover {
      background-color: #f8f9f9;
    }
    :active {
      background-color: #f1f2f3;
    }
  }
  .oauth-github__button {
    background-color: #2f3337;
    color: white;
    :hover {
      background-color: #232629;
    }
    :active {
      background-color: #0c0d0e;
    }
  }
  .oauth-facebook__button {
    background-color: #385499;
    color: white;
    :hover {
      background-color: #304986;
    }
    :active {
      background-color: #2a3f73;
    }
  }
  .signup-form__div {
    padding: 24px;
    background-color: white;
    border-radius: 7px;
    box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05),
      0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1);
  }
  .signup-input__div {
    display: flex;
    flex-direction: column;
    width: 256px;
    margin-bottom: 15px;
    margin-top: 10px;
  }
  .signup-input__input {
    margin-top: 10px;
    height: 33px;
    font-size: 15px;
    padding-left: 10px;
    &:focus {
      box-shadow: 0px 0px 0px 4px hsl(205, 53%, 88%);
      border-color: hsl(206, 100%, 40%);
      outline: 4px solid #cde9fe;
    }
  }
  .signup-incorrect__input {
    box-shadow: 0px 0px 0px 4px hsl(0, 53%, 88%);
    border-color: red;
    outline: 4px solid hsl(0, 53%, 88%);
  }

  .signup-incorrect__div {
    color: red;
    font-size: 14px;
    height: 14px;
    padding: 3px;
  }
  .signup-correct__div {
    visibility: hidden;
    height: 14px;
  }
  .signup-button__div {
    display: flex;
    justify-content: center;
  }
  .signup-already__div {
    display: flex;
    justify-content: center;
    padding-top: 15px;
  }
  h1 {
    font-size: 27px;
  }
  input {
    border-radius: 3px;
    border: 1px solid #89afd4;
  }
  label {
    font-weight: 600;
    font-size: 15px;
  }
  p {
    font-size: 12px;
  }
  span {
    font-size: 13px;
  }
  a {
    color: #0995ff;
    font-size: 13px;
    margin-left: 7px;
  }
`;

const SignUpButton = styled.button`
  background-color: #0995ff;
  width: 268px;
  height: 38px;
  border: none;
  border-radius: 3px;
  box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05),
    0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1);
  color: white;
  :hover {
    background-color: #0074cc;
  }
  :active {
    background-color: #0162bf;
  }
`;

function SignUpForm() {
  const [inputName, setInputName] = useState('');
  const [inputEmail, setInputEmail] = useState('');
  const [inputPassword, setInputPassword] = useState('');
  const [isError, setIsError] = useState(false);
  const [navigate, setNavigate] = useState(false);

  const emailCheck = (email) => {
    let regex =
      /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return email != '' && email != 'undefined' && regex.test(email);
  };

  const signUpButtonHandler = async (e) => {
    if (
      inputName.length < 4 ||
      !emailCheck(inputEmail) ||
      inputPassword.length < 8
    ) {
      return setIsError(true);
    }
    e.preventDefault();
    await axios.post('/signup', {
      name: inputName,
      email: inputEmail,
      password: inputPassword,
    });
    setNavigate(true);
  };

  if (navigate) {
    return <Navigate to="/login" />;
  }

  return (
    <Container>
      <SignUpContents>
        <div className="signup-division__div">
          <h1>Join the Stack Overflow community</h1>
          <div className="signup-infosection__div">
            <SvgIcon
              className="signup-info__icon"
              component={QuestionAnswerIcon}
            />
            <div>Get unstuck — ask a question</div>
          </div>
          <div className="signup-infosection__div">
            <SvgIcon
              className="signup-info__icon"
              component={ThumbsUpDownIcon}
            />
            <div>Unlock new privileges like voting and commenting</div>
          </div>
          <div className="signup-infosection__div">
            <SvgIcon className="signup-info__icon" component={LocalOfferIcon} />
            <div>Save your favorite tags, filters, and jobs</div>
          </div>
          <div className="signup-infosection__div">
            <SvgIcon
              className="signup-info__icon"
              component={EmojiEventsIcon}
            />
            <div>Earn reputation and badges</div>
          </div>
          <div className="signup-infobottom__div">
            Collaborate and share knowledge with a private group for FREE.
          </div>
          <div className="signup-infobottom__div">
            Get Stack Overflow for Teams free for up to 50 users.
          </div>
        </div>
        <div className="signup-division__div">
          <div className="signup-oauth__div">
            <button className="signup-oauth__button oauth-google__button">
              <SvgIcon component={GoogleIcon} />
              <span>Sign Up with Google</span>
            </button>
            <button className="signup-oauth__button oauth-github__button">
              <SvgIcon component={GitHubIcon} />
              <span>Sign Up with Github</span>
            </button>
            <button className="signup-oauth__button oauth-facebook__button">
              <SvgIcon component={FacebookIcon} />
              <span>Sign Up with Facebook</span>
            </button>
          </div>
          <div className="signup-form__div">
            <div className="signup-input__div">
              <label htmlFor="inputDisplayName">Display name</label>
              <input
                className={
                  isError && inputName.length < 4
                    ? 'signup-input__input signup-incorrect__input'
                    : 'signup-input__input'
                }
                type="text"
                id="inputDisplayName"
                onChange={(e) => setInputName(e.target.value)}
              ></input>
              <div
                className={
                  isError && inputName.length < 4
                    ? 'signup-incorrect__div'
                    : 'signup-correct__div'
                }
              >
                닉네임은 4글자 이상 입력하세요.
              </div>
            </div>
            <div className="signup-input__div">
              <label htmlFor="inputEmail">Email</label>
              <input
                className={
                  isError && !emailCheck(inputEmail)
                    ? 'signup-input__input signup-incorrect__input'
                    : 'signup-input__input'
                }
                type="text"
                id="inputEmail"
                onChange={(e) => setInputEmail(e.target.value)}
              ></input>
              <div
                className={
                  isError && !emailCheck(inputEmail)
                    ? 'signup-incorrect__div'
                    : 'signup-correct__div'
                }
              >
                올바른 이메일 형식이 아닙니다.
              </div>
            </div>
            <div className="signup-input__div">
              <label htmlFor="inputPassword">password</label>
              <input
                className={
                  isError && inputPassword.length < 8
                    ? 'signup-input__input signup-incorrect__input'
                    : 'signup-input__input'
                }
                type="password"
                id="inputPassword"
                onChange={(e) => setInputPassword(e.target.value)}
              ></input>
              <div
                className={
                  isError && inputPassword.length < 8
                    ? 'signup-incorrect__div'
                    : 'signup-correct__div'
                }
              >
                패스워드는 8글자 이상 입력하세요.
              </div>
              <p>
                Passwords must contain at least eight characters, including at
                least 1 letter and 1 number.
              </p>
            </div>
            <div className="signup-button__div">
              <SignUpButton onClick={signUpButtonHandler}>Sign up</SignUpButton>
            </div>
          </div>
          <div>
            <div className="signup-already__div">
              <span>Already have an account?</span>
              <a href="/login">Log in</a>
            </div>
          </div>
        </div>
      </SignUpContents>
    </Container>
  );
}

export default SignUpForm;

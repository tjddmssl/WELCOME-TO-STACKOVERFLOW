import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faSquareFacebook,
  faGithub,
  faGoogle,
} from '@fortawesome/free-brands-svg-icons';

const ButtonContent = styled.div`
  display: flex;
  flex-direction: column;
  width: 18.5rem;
  font-size: 13px;
`;
const Buttons = styled(ButtonContent)`
  display: flex;
  margin: 0.3rem;
  padding: 0.6rem;
  justify-content: center;
  border-radius: 5px;
  border: none;
  align-items: center;
`;
const GoogleButton = styled(Buttons)`
  color: black;
  background-color: white;
  border: 1px solid hsl(210, 8%, 85%);
  &:hover {
    background-color: #d3d3d4;
  }
`;
const GithubButton = styled(Buttons)`
  color: white;
  background-color: rgba(15, 26, 57, 0.99);

  &:hover {
    background-color: black;
  }
`;
const FacebookButton = styled(Buttons)`
  color: white;
  background-color: rgba(52, 76, 141, 0.99);
  &:hover {
    background-color: #232c61;
  }
`;
const Span = styled.span`
  padding-left: 0.5rem;
`;
export default function LoginSocialButtons() {
  return (
    <ButtonContent className="div__sociallogin-site">
      <Buttons className="div__socillogin-buttons">
        <GoogleButton className="button__login-google">
          <div>
            <FontAwesomeIcon icon={faGoogle} />
            <Span>Log in with Goolge</Span>
          </div>
        </GoogleButton>
        <GithubButton className="button__login-github">
          <div>
            <FontAwesomeIcon icon={faGithub} />
            <Span>Log in with Github</Span>
          </div>
        </GithubButton>
        <FacebookButton className="button__login-facebook">
          <div>
            <FontAwesomeIcon icon={faSquareFacebook} />
            <Span>Log in with Facebook</Span>
          </div>
        </FacebookButton>
      </Buttons>
    </ButtonContent>
  );
}

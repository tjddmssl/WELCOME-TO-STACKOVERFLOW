import { GoTriangleUp, GoTriangleDown } from 'react-icons/go';
import { FiBookmark } from 'react-icons/fi';
import { RxCountdownTimer } from 'react-icons/rx';
import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin-right: 10px;
  button {
    border: none;
    background: transparent;
    font-size: 45px;
    color: #babfc4;
  }
  button:active {
    color: #f58225;
  }
  div {
    font-size: large;
    margin-left: 24px;
    color: #6b737c;
  }
`;

const IconContainer = styled.div`
  display: flex;
  flex-direction: column;
  color: #babfc4;
  margin-right: 10px;
  margin-left: 20px;

  svg {
    margin-bottom: 10px;
  }
`;
function Vote() {
  return (
    <div>
      <Container>
        <button>
          <GoTriangleUp />
        </button>
        <div>0</div>
        <button>
          <GoTriangleDown />
        </button>
      </Container>
      <IconContainer>
        <FiBookmark />
        <RxCountdownTimer />
      </IconContainer>
    </div>
  );
}

export default Vote;

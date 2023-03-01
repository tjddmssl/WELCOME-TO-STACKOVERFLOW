import { Button } from '@mui/material';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

//* 질문하기 버튼 -> Ask 페이지로 이동
const Container = styled.div`
  button {
    background-color: #0b95ff;
    color: white;
    font-size: 12px;
    padding: 10px;
    text-transform: capitalize;
  }
`;

function QButton() {
  const navigate = useNavigate();
  const navigateToAsk = () => {
    navigate('./ask');
  };
  return (
    <Container>
      <Button onClick={navigateToAsk}>Ask Question</Button>
    </Container>
  );
}

export default QButton;

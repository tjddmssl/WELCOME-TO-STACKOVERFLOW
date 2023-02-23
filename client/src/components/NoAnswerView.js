import styled from 'styled-components';

const Container = styled.div`
  margin-top: 40px;
  margin-bottom: 20px;
  font-size: 20px;
`;

function NoAnswerView() {
  return (
    <Container>
      Know someone who can answer? Share a link to this question via email,
      Twitter, or Facebook.
    </Container>
  );
}

export default NoAnswerView;

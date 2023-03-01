import styled from 'styled-components';

const PageNationContainer = styled.div`
  button {
    border: 1px solid hsl(210, 8%, 85%);
    background-color: white;
    border-radius: 3px;
    padding: 5px 10px;
    margin-right: 5px;
    :hover {
      background-color: rgb(206, 203, 203);
    }
  }
`;

const PageButton = () => {
  return <button>1</button>;
};

function QPageNation() {
  return (
    <PageNationContainer>
      <button>Prev</button>
      <PageButton />
      <button>Next</button>
    </PageNationContainer>
  );
}

export default QPageNation;

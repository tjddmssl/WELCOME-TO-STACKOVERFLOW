import styled from 'styled-components';
import { Button } from '@mui/material';

const TagForm = styled.div`
  display: flex;
  flex-direction: column;
  width: 1000px;
  height: auto;
  margin-left: 25px;
  margin-bottom: 40px;
  border: 1px solid lightgrey;
  padding: 5px 20px;
  padding-bottom: 10px;
  @media screen and (max-width: 1369px) {
    width: 100%;
  }

  h4 {
    margin-bottom: 10px;
  }
  p {
    margin-top: 0px;
  }
  input {
    width: 750px;
    padding: 10px;
    justify-content: center;
    align-items: center;
    border: 1px solid lightgrey;
    &:focus {
      outline: none;
      border-color: hsl(206deg 100% 52%);
      box-shadow: 0px 0px 0px 5px #e1ecf4;
    }
  }
  button {
    text-transform: capitalize;
    margin-top: 10px;
    width: 5%;
    background-color: #0a95ff;
    color: white;
    font-size: small;
  }
  button:hover {
    background-color: #0a95ff;
    color: lightgray;
  }
`;

export default function TagAdd() {
  return (
    <TagForm>
      <h4>Tags</h4>
      <p>
        Add up to 5 tags to describe what your question is about. Start typing
        to see sugestions.
      </p>
      <input type="text" placeholder="e.g.(vba css json)"></input>
      <Button>Next</Button>
    </TagForm>
  );
}

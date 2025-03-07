import { useState } from 'react';
import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  /* overflow: scroll; */
  /* width: 750px; */
  max-width: 100%;
  justify-content: center;
  padding-left: 10px;
  border: 1px solid #babfc4;
  border-radius: 5px;
  color: black;
  margin: 10px 0 0;

  &:focus-within {
    outline: none;
    border-color: hsl(206deg 100% 52%);
    box-shadow: 0px 0px 0px 5px #e1ecf4;
  }
`;
const Input = styled.input`
  width: 100%;
  min-width: 50%;
  border: none;
  border-radius: 5px;
  padding: 14px;
  padding-left: 14px;

  &:focus {
    outline: none;
  }
`;
const Tags = styled.div`
  display: flex;
  align-items: center;
  margin: 8px 0;
  margin-right: 10px;
  padding: 0 8px;
  padding-right: 5px;
  border-radius: 5px;
  background-color: #e1ecf4;
  white-space: nowrap;
  color: #4a80a7;
  font-size: 0.7rem;
`;
const Button = styled.button`
  display: flex;
  padding: 6px;
  border: none;
  background-color: unset;
  cursor: pointer;
  color: #4a80a7;
`;

export default function TagAdd({ tags, setTags }) {
  const [input, setInput] = useState('');
  const [isKeyReleased, setIsKeyReleased] = useState(false);

  const onChange = (e) => {
    setInput(e.target.value);
  };

  const onKeyDown = (e) => {
    const trimmedInput = input.trim();

    if ((e.keyCode === 13 || e.keyCode === 188) && trimmedInput.length) {
      e.preventDefault();
      setTags((prevState) => [...prevState, trimmedInput]);
      setInput('');
    }
    if (e.keyCode === 8 && !input.length && tags.length && isKeyReleased) {
      const tagsCopy = [...tags];
      const poppedTag = tagsCopy.pop();
      e.preventDefault();
      setTags(tagsCopy);
      setInput(poppedTag);
    }
    setIsKeyReleased(false);
  };

  const onKeyUp = () => {
    setIsKeyReleased(true);
  };

  const deleteTag = (index) => {
    setTags((prevState) => prevState.filter((tag, i) => i !== index));
  };
  return (
    <Container>
      {tags &&
        tags.map((tag, index) => (
          <Tags key={`${index.toString()}-${tag}`}>
            {tag}
            <Button onClick={() => deleteTag(index)}>x</Button>
          </Tags>
        ))}
      <Input
        value={input}
        placeholder="e.g.(vba css json)"
        onKeyUp={onKeyUp}
        onKeyDown={onKeyDown}
        onChange={onChange}
      />
    </Container>
  );
}

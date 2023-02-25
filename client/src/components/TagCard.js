import styled from 'styled-components';

const TagContents = styled.li`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 177px;
  padding: 13px;
  border: 1px solid #d6d9dc;
  border-radius: 3px;
`;

const Tagtitle = styled.button`
  display: inline-block;
  width: min-content;
  padding: 6px 6px;
  margin: 0 6px 13px 0;
  background-color: #e1ecf4;
  font-size: 12px;
  color: #39739d;
  border: none;
  border-radius: 2px;
  cursor: pointer;
  &:hover {
    background-color: #d0e3f1;
    color: #2c5877;
  }
`;

const TagtextContainer = styled.div`
  display: inline-block;
`;
const Tagtext = styled.div`
  display: inline-block;
  width: 99%;
  height: 70px;
  margin-bottom: 13px;
  overflow: hidden;
  text-overflow: ellipsis;
  font-size: 13px;
  white-space: normal;
  line-height: 1.4;
  height: 5.6em;
  color: #3b4045;
  text-align: left;
  word-wrap: break-word;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
`;
const TagCounter = styled.div`
  display: flex;
`;
const Number = styled.div`
  color: #838c95;
  font-size: 13px;
`;

export default function TagCard({ data }) {
  return (
    <TagContents>
      <Tagtitle>{data.title}</Tagtitle>
      <TagtextContainer>
        <Tagtext>{data.text}</Tagtext>
      </TagtextContainer>
      <TagCounter>
        <Number>{data.counter} quewstions</Number>
      </TagCounter>
    </TagContents>
  );
}

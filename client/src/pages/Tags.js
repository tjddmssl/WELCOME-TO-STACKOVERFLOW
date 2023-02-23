// import Tag from '../components/Tag'; <Tag />
import styled from 'styled-components';
import Header from '../components/Header';
import NavBar from '../components/NavBar';
import { BsSearch } from 'react-icons/bs';

const Container = styled.div`
  max-width: 1100px;
  padding: 24px;
  display: flex;
  justify-content: center;
  text-align: left;
`;

const TagContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 1088px;
  padding: 24px;
`;
const TagHeader = styled.header`
  display: flex;
  flex-direction: column;
  padding-bottom: 16px;
  font-size: 13px;
  color: #232639;
  line-height: 1.4;
  p {
    width: 640px;
    font-size: 15px;
    margin: 0 0 16px;
    line-height: 18.7px;
    vertical-align: baseline;
  }
`;
const Title = styled.h1`
  font-size: 26px !important;
  margin: 0 0 16px !important;
`;
const ShowSynonyms = styled.a`
  text-decoration: none;
  color: #0074cc;
  cursor: pointer;
  margin-bottom: 24px;
  :visited {
    text-decoration: none;
  }
  &:hover {
    color: #0a95ff;
  }
`;
const FormButtonContent = styled.div`
  display: flex;
  justify-content: space-between;
`;
const Form = styled.form`
  display: flex;
  width: 188px;
  height: 37px;
  max-width: 100%;
  align-items: center;
  position: relative;
  .searchIcon {
    position: absolute;
    left: 0px;
    opacity: 0.5;
    margin: 0 0.5rem;
  }
`;
const InPut = styled.input`
  padding: 1rem 1rem 1rem 2rem;
  width: 100%;
  height: 2.3rem;
  border: 1px solid hsl(210deg 8% 75%);
  border-radius: 3px;

  &:focus {
    outline: none;
    border-color: hsl(206deg 90% 70%);
    box-shadow: 0px 0px 0px 5px #e1ecf4;
  }
  &::placeholder {
    color: rgb(190, 192, 195);
  }
`;
const FilterButtons = styled.div`
  vertical-align: baseline;
  border: 1px solid rgb(148, 156, 163);
  border-radius: 5px;
`;
const FilterButton = styled.button`
  width: 63px;
  height: 35px;
  color: #6a737c;
  background-color: white;
  border: none;
  font-size: 12px;
  &.popular {
    border-top-left-radius: 5px 5px;
    border-bottom-left-radius: 5px 5px;
    border-right: 1px solid rgb(148, 156, 163);
  }
  &.name {
    border-right: 1px solid rgb(148, 156, 163);
  }
  &.new {
    border-top-right-radius: 5px 5px;
    border-bottom-right-radius: 5px 5px;
  }
  &:hover {
    background-color: rgb(247, 247, 247);
    color: #525960;
  }
`;
const TagGridContainer = styled.ul`
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  grid-gap: 20px;
  width: 100%;
  @media screen and (max-width: 1345px) {
    grid-template-columns: repeat(3, minmax(0, 1fr));
  }
  @media screen and (max-width: 1028px) {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
  @media screen and (max-width: 711px) {
    grid-template-columns: repeat(1, minmax(0, 1fr));
  }
`;
const TagContents = styled.li`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 177px;
  padding: 13px;
  border: 1px solid #d6d9dc;
  border-radius: 3px;
`;
const Tag = styled.button`
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

const Content = styled.div`
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
  word-wrap: break-word; // 단어 단위로 줄바꿈
  display: -webkit-box; // 유연하게 height를 증감시킬 수 있는 플렉스 박스형태로 변환
  -webkit-line-clamp: 4; // 보여줄 줄 수
  -webkit-box-orient: vertical; // 플렉스 박스의 방향 설정(가로)
`;

function Tags() {
  return (
    <>
      <Header />
      <Container>
        <NavBar />
        <TagContainer>
          <TagHeader>
            <Title>Tags</Title>
            <p>
              A tag is a keyword or label that categorizes your question with
              other, similar questions. Using the right tags makes it easier for
              others to find and answer your question.
            </p>
            <ShowSynonyms>Show all tag synonyms</ShowSynonyms>
            <FormButtonContent>
              <Form>
                <BsSearch className="searchIcon" />
                <InPut type="text" placeholder="Filter by tag name"></InPut>
              </Form>
              <FilterButtons>
                <FilterButton className="popular">Popular</FilterButton>
                <FilterButton className="name">Name</FilterButton>
                <FilterButton className="new">New</FilterButton>
              </FilterButtons>
            </FormButtonContent>
          </TagHeader>
          <TagGridContainer>
            <TagContents>
              <Tag>javascript</Tag>
              <Content>
                {`For questions about programming in ECMAScript (JavaScript/JS) and its different dialects/implementations (except for ActionScript). Keep in mind that JavaScript is NOT the same as Java! Include all labels that are relevant to your question; e.g., [node.js], [jQuery], [JSON], [ReactJS], [angular], [ember.js], [vue.js], [typescript], [svelte], etc.`}
              </Content>
            </TagContents>
          </TagGridContainer>
        </TagContainer>
      </Container>
    </>
  );
}

export default Tags;

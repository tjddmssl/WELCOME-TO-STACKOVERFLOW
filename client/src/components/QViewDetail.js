import styled from 'styled-components';
import QButton from './QButton';
import Vote from './Vote';

const TitleContainer = styled.div`
  display: flex;
  flex-direction: column;

  .title {
    display: flex;
    flex-direction: row;
  }
  h2 {
    margin-top: 10px;
    max-width: 500px;
  }
  button {
    margin-left: 890px;
    width: 100px;
  }
`;

const TitleDate = styled.div`
  border-bottom: 1px solid #f2f3f4;
`;

const ContentContainer = styled.div`
  display: flex;
`;

const Content = styled.div`
  margin-left: 20px;
  display: flex;
  flex-direction: column;
  button {
    background-color: red;
  }
`;

function QViewDetail({ question }) {
  return (
    <>
      <TitleContainer>
        <div className="title">
          <h2>{question.title}</h2>
          <QButton />
        </div>
        <TitleDate>
          <p>
            Asked {question.createdDate} Modified {question.lastModifiedDate}
          </p>
        </TitleDate>
      </TitleContainer>
      <ContentContainer>
        <Vote />
        <Content>{question.content}</Content>
      </ContentContainer>
    </>
  );
}

export default QViewDetail;

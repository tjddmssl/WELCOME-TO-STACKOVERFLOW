import styled from 'styled-components';
import Header from '../components/Layout/Header';
import NavBar from '../components/Layout/NavBar';
import Footer from '../components/Layout/Footer';
import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';
import { useEffect } from 'react';
import getTagsSLice from '../redux/slice/getTags';
import TagCard from '../components/Tags/TagCard';
import TagHeader from '../components/Tags/TagHeader';

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding-top: 50px;
`;

const Container = styled.div`
  flex-wrap: nowrap;
  display: flex;
  justify-content: center;
  text-align: left;
  .main-wrap {
    max-width: 1100px;
    width: calc(100% - 164px);
    display: flex;
  }
`;

const TagContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 1088px;
  padding: 24px;
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

function Tags() {
  const dispatch = useDispatch();
  const data = useSelector((state) => {
    return state.getTags.tags;
  });
  useEffect(() => {
    const getTagsData = async () => {
      try {
        const response = await axios.get('http://13.125.211.79:8080/tags');
        console.log(response.data);
        dispatch(getTagsSLice.actions.get(response.data.response.content));
      } catch (error) {
        console.log(error);
      }
    };
    getTagsData();
  }, []);

  return (
    <>
      <Wrapper>
        <Header />
        <Container>
          <NavBar />
          <div className="main-wrap">
            <TagContainer>
              <TagHeader />
              <TagGridContainer>
                {data?.map((el) => {
                  return <TagCard key={el.id} data={el} />;
                })}
              </TagGridContainer>
            </TagContainer>
          </div>
        </Container>
        <Footer />
      </Wrapper>
    </>
  );
}

export default Tags;

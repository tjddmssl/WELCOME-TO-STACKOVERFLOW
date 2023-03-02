import styled from 'styled-components';
import Header from '../components/Layout/Header';
import NavBar from '../components/Layout/NavBar';
import Footer from '../components/Layout/Footer';
import '@toast-ui/editor/dist/toastui-editor.css';
import ViewEditEditor from '../components/QuestionEdit/ViewEditEditor';
import ViewEditRender from '../components/QuestionEdit/ViewEditRender';
import { useSelector, useDispatch } from 'react-redux';
import { useEffect, useState } from 'react';
import getQuestionSlice from '../redux/slice/getQuestionSlice';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  padding-top: 50px;
`;

const MainContainer = styled.div`
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  .mainside-wrap__div {
    max-width: 1100px;
    width: calc(100% - 164px);
    display: flex;
  }
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  padding: 24px;
  width: calc(100% - 276px);
  @media screen and (max-width: 980px) {
    width: 100%;
  }
`;

const EditContainer = styled.div`
  .edit-notice__div {
    background-color: #fdf7e2;
    border-color: hsl(47, 69%, 69%);
    border-radius: 3px;
    border-width: 1px;
    border-style: solid;
    padding: 16px;
    font-size: 13px;
  }
`;

const EditForm = styled.div`
  margin-top: 10px;
  .edit-title__div {
    text-align: left;
    display: flex;
    flex-direction: column;
    padding-bottom: 15px;

    input {
      font-size: 13px;
      padding: 7.8px 9.1px;
      border: 1px solid hsl(210, 8%, 75%);
      border-radius: 3px;
    }
  }
  .edit-body__div {
  }

  .edit-tags__div {
    margin-top: 15px;
  }
  .edit-tag__div {
    display: flex;
    align-items: center;
    margin-top: 15px;
    padding: 0px 9.1px;
    box-sizing: border-box;
    margin-bottom: 0px;
    width: 100%;
    height: 37px;
    padding-left: 2px !important;
    border: 1px solid hsl(210, 8%, 75%);
    border-radius: 3px;
    input {
      border: none;
    }
  }
  .edit-tag__span {
    background-color: #e1ecf4;
    padding: 5px;
    margin-right: 3px;
    border-radius: 5px;
    color: #39739d;
    font-size: 12px;
    white-space: nowrap;
    :hover {
      background-color: #d0e3f1;
      color: #2c5877;
    }
  }
  .edit-tagdel__button {
    background-color: transparent;
    border: none;
    color: #39739d;
  }
  .edit-tag__input {
    position: relative;
    border: none;
    width: 100%;
    outline: none;
  }
  .edit-buttons__div {
    margin-top: 15px;
  }
  .edit-save__button {
    background-color: hsl(206, 100%, 52%);
    border: 1px solid transparent;
    border-radius: 3px;
    box-shadow: inset 0 1px 0 0 hsla(0, 0%, 100%, 0.4);
    color: white;
    font-size: 13px;
    padding: 0.8em;
    margin-right: 5px;
    :hover {
      background-color: hsl(206, 100%, 40%);
    }
    :active {
      background-color: hsl(209, 100%, 37.5%);
    }
  }
  .edit-cancel__button {
    background-color: transparent;
    border: 1px solid transparent;
    border-radius: 3px;
    color: hsl(206, 100%, 40%);
    font-size: 13px;
    padding: 0.8em;
    :hover {
      background-color: hsl(206, 100%, 97%);
      color: hsl(209, 100%, 37.5%);
    }
    :active {
      background-color: transparent;
      color: hsl(206, 100%, 40%);
    }
  }
  label {
    font-size: 15px;
    font-weight: bold;
  }
`;

const EditSideBar = styled.div`
  padding: 24px;
  width: 365px;
  @media screen and (max-width: 980px) {
    display: none;
  }
  .edit-sidebarheader__div {
    font-size: 15px;
    padding: 12px 15px;
    background-color: hsl(47, 83%, 91%);
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    border-color: hsl(47, 65%, 84%);
    border: 1px solid hsl(47, 65%, 84%);
    box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
      0 2px 8px hsla(0, 0%, 0%, 0.05);
  }
  .edit-sidebarlist__div {
    border: 1px solid hsl(47, 65%, 84%);
    border-top: 0px;
    border-bottom-left-radius: 3px;
    border-bottom-right-radius: 3px;
    box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
      0 2px 8px hsla(0, 0%, 0%, 0.05);
  }
  .edit-sidebarlist__ul {
    padding: 16px 12px 16px 12px;
    list-style: circle;
    background-color: #fdf7e3;
    li {
      list-style-position: inside !important;
      list-style: disc;
      font-size: 13px;
      margin-bottom: 5px;
    }
  }
`;

//* VIEW_03 질문 수정하기
function ViewEdit() {
  const params = useParams();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const question = useSelector((state) => {
    return state.getQuestion.response;
  });
  useEffect(() => {
    const getData = async () => {
      try {
        const response = await axios.get(
          `https://siglee.site/questions/${params.id}`
        );
        console.log(response.data);
        dispatch(getQuestionSlice.actions.get(response.data.response));
        setTags([...response.data.response.tag]);
      } catch (error) {
        console.log(error);
      }
    };
    getData();
  }, []);

  const [tags, setTags] = useState([]);
  const [tagInput, setTagInput] = useState('');

  const titleChangeHandler = (e) => {
    dispatch(
      getQuestionSlice.actions.get({ ...question, title: e.target.value })
    );
  };
  const handleOnKeyPress = (e) => {
    if (e.key === 'Enter') {
      setTags([...tags, tagInput]);
      e.target.value = '';
    }
  };
  const tagDeleteHandler = (idx) => {
    const newArr = [...tags];
    newArr.splice(idx, 1);
    setTags([...newArr]);
  };
  const saveClickHandler = () => {
    if (window.confirm('수정하시겠습니까?')) {
      console.log(tags);
      const patchData = async () => {
        try {
          await axios({
            url: `https://siglee.site/questions/${params.id}`,
            method: 'patch',
            data: {
              title: question.title,
              content: question.content,
              tags: tags,
            },
          }).then((res) => console.log(res.data));
        } catch (error) {
          console.log(error);
        }
      };
      patchData();
      navigate(`/view/${params.id}`);
    }
  };
  const cancelClickHandler = () => {
    navigate(`/view/${params.id}`);
  };
  return (
    <div>
      <Container>
        <Header />
        <MainContainer>
          <NavBar />
          <div className="mainside-wrap__div">
            <ContentContainer>
              <EditContainer>
                <div className="edit-notice__div">
                  <p>
                    Your edit will be placed in a queue until it is peer
                    reviewed.
                  </p>
                  <p>
                    We welcome edits that make the post easier to understand and
                    more valuable for readers. Because community members review
                    edits, please try to make the post substantially better than
                    how you found it, for example, by fixing grammar or adding
                    additional resources and hyperlinks.
                  </p>
                </div>
                <EditForm>
                  <div className="edit-title__div">
                    <label htmlFor="title">Title</label>
                    <input
                      id="title"
                      value={question.title}
                      onChange={(e) => {
                        titleChangeHandler(e);
                      }}
                    ></input>
                  </div>
                  <div className="edit-body__div">
                    <label htmlFor="body">Body</label>
                    {question && (
                      <ViewEditEditor question={question} id="body" />
                    )}
                    {/* <ViewEditEditor question={question} id="body" /> */}
                    <ViewEditRender />
                  </div>
                  <div className="edit-tags__div">
                    <label htmlFor="tags">Tags</label>
                    <div className="edit-tag__div">
                      {tags &&
                        tags.map((el, idx) => {
                          return (
                            <span key={idx} className="edit-tag__span">
                              {el}
                              <button
                                className="edit-tagdel__button"
                                onClick={() => tagDeleteHandler(idx)}
                              >
                                X
                              </button>
                            </span>
                          );
                        })}
                      <input
                        className="edit-tag__input"
                        type="text"
                        onChange={(e) => {
                          setTagInput(e.target.value);
                        }}
                        onKeyDown={(e) => handleOnKeyPress(e)}
                      ></input>
                    </div>
                  </div>
                  <div className="edit-buttons__div">
                    <button
                      className="edit-save__button"
                      type="submit"
                      onClick={saveClickHandler}
                    >
                      Save edits
                    </button>
                    <button
                      className="edit-cancel__button"
                      type="button"
                      onClick={cancelClickHandler}
                    >
                      Cancel
                    </button>
                  </div>
                </EditForm>
              </EditContainer>
            </ContentContainer>
            <EditSideBar>
              <div>
                <div className="edit-sidebarheader__div">How to Edit</div>
                <div className="edit-sidebarlist__div">
                  <ul className="edit-sidebarlist__ul">
                    <li>Correct minor typos or mistakes</li>
                    <li>Clarify meaning without changing it</li>
                    <li>Add related resources or links</li>
                    <li>Always respect the author’s intent</li>
                    <li>Don’t use edits to reply to the author</li>
                  </ul>
                </div>
              </div>
            </EditSideBar>
          </div>
        </MainContainer>
        <Footer />
      </Container>
    </div>
  );
}

export default ViewEdit;

import { Button } from '@mui/material';
import styled from 'styled-components';
import VisibilityIcon from '@mui/icons-material/Visibility';
import EditIcon from '@mui/icons-material/Edit';
import ChatBubbleOutlineIcon from '@mui/icons-material/ChatBubbleOutline';
import { BsStackOverflow } from 'react-icons/bs';

//* 오른쪽 사이드바

const Container = styled.div`
  float: right;
`;
//? 노란 박스
const Memo = styled.ul`
  display: flex;
  flex-direction: column;
  width: 310px;
  /* float: right; */
  outline: 2px solid beige;
  box-shadow: 2px 1px 1px 1px lightgrey;
  margin-bottom: 20px;

  li {
    height: auto;
    background-color: #fef7e2;
    padding: 14px;
  }

  .title {
    color: #525960;
    font-weight: bold;
    background-color: #fbf3d5;
  }

  li:hover {
    cursor: pointer;
  }

  .title:hover {
    cursor: default;
  }
`;

//? 노란 박스 아래
const CustomF = styled.ul`
  display: flex;
  flex-direction: column;
  width: 310px;
  /* float: right; */
  border: darkgrey;
  border-radius: 1.5px;
  box-shadow: 1px 1px 1px 1px lightgrey;
  margin-bottom: 20px;

  li {
    padding: 14px;
    background-color: #f8f9f9;
  }
  a {
    padding: 14px;
  }
`;

const WatchedT = styled.ul`
  display: flex;
  flex-direction: column;
  width: 310px;
  /* float: right; */
  border: darkgrey;
  border-radius: 1.5px;
  box-shadow: 1px 1px 1px 1px lightgrey;
  margin-bottom: 20px;

  li {
    padding: 14px;
    background-color: #f8f9f9;
  }
  div {
    flex-direction: column;
    margin: 0 auto;
  }
  img {
    margin-top: 15px;
    width: 120px;
  }

  button {
    width: 160px;
    margin: 20px;
    background-color: #e1ecf4;
    color: #39739d;
  }

  button:hover {
    background-color: #b4d3ea;
  }
`;

const IgnoredT = styled.ul`
  display: flex;
  flex-direction: column;
  width: 310px;
  /* float: right; */
  border: darkgrey;
  border-radius: 1.5px;
  box-shadow: 1px 1px 1px 1px lightgrey;
  margin-bottom: 20px;

  li {
    padding: 14px;
    background-color: #f8f9f9;
  }
  div {
    margin: 0 auto;
  }
  button {
    background-color: #e1ecf4;
    color: #39739d;
    margin: 30px;
  }
  button:hover {
    background-color: #b4d3ea;
  }
`;

const Collectives = styled.ul`
  display: flex;
  flex-direction: column;
  width: 310px;
  /* float: right; */
  border: darkgrey;
  border-radius: 1.5px;
  box-shadow: 1px 1px 1px 1px lightgrey;
  margin-bottom: 20px;

  li {
    padding: 14px;
  }
  .title {
    background-color: #f8f9f9;
  }
`;

function Sidebar() {
  return (
    <Container>
      <Memo>
        {
          <>
            <li className="title">The Overflow Blog</li>
            <li>
              <EditIcon sx={{ fontSize: 15 }} />
              {` Developer with ADHD? You are not alone.`}
            </li>
            <li>
              <EditIcon sx={{ fontSize: 15 }} />
              {` Because the only thing worse than building internal tools is
              maintaining them...`}
            </li>
            <li className="title">Featured on Meta</li>
            <li>
              <ChatBubbleOutlineIcon fontSize="small" color="primary" />
              {` Ticket smash for [status-review] tag: Part Deux`}
            </li>
            <li>
              <ChatBubbleOutlineIcon fontSize="small" color="primary" />
              {` We've added a "Necessary cookies only" option to the cookie consent popup`}
            </li>
            <li>
              <BsStackOverflow /> Microsoft Azure Collective launch and proposed
              tag changes.
            </li>
            <li>
              <BsStackOverflow /> Temporary policy: ChatGPT is banned
            </li>
            <li>
              <BsStackOverflow /> The [amazon] tag is being burninated
            </li>
            <li>
              <BsStackOverflow />{' '}
              {`We've made changes to our Privacy Notice for
              Collectives`}
            </li>
          </>
        }
      </Memo>

      <CustomF>
        <li>Custom Filters</li>
        <a href="http://localhost:3000/">Create a custom filter</a>
      </CustomF>

      <WatchedT>
        <li>Watched Tags</li>
        <div>
          <center>
            <img alt="search_icon" src="/img/sidebar-watched tags.png" />
          </center>
          <p>Watch tags to curate your list of questions</p>
        </div>
        <div>
          <Button variant="contained" startIcon={<VisibilityIcon />}>
            Watch a tag
          </Button>
        </div>
      </WatchedT>

      <IgnoredT>
        <li>Ignored Tags</li>
        <div>
          <Button variant="contained">Add an ignored tag</Button>
        </div>
      </IgnoredT>

      <Collectives>
        <li className="title">Collectives</li>
        <li>Google Cloud</li>
        <li>Twilio</li>
        <li>AWS</li>
      </Collectives>
    </Container>
  );
}

export default Sidebar;

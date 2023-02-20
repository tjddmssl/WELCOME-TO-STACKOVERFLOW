import { Button } from '@mui/material';
import styled from 'styled-components';
import VisibilityIcon from '@mui/icons-material/Visibility';
import EditIcon from '@mui/icons-material/Edit';
import { BsStackOverflow } from 'react-icons/bs';

//* 오른쪽 사이드바
const Memo = styled.ul`
  display: flex;
  flex-direction: column;
  width: 300px;
  float: right;
  outline: 2px solid beige;
  margin: 10px;

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

const Tags = styled.ul`
  display: block;
  width: 230px;
  float: right;
  outline: 1px solid black;
`;
// const Blog = styled.div`
//   width: 140px;
//   float: right;
// `;

// const Featured = styled.div`
//   width: 140px;
//   float: right;
// `;

// const CustomF = styled.div`
//   width: 140px;
//   float: right;
// `;

// const WatchedT = styled.div`
//   width: 140px;
//   float: right;
// `;

// const IgnoredT = styled.div`
//   width: 140px;
//   float: right;
// `;

// const Collectives = styled.div`
//   width: 140px;
//   float: right;
// `;

// const Related = styled.div`
//   width: 140px;
//   float: right;
// `;

// const HNQ = styled.div`
//   width: 140px;
//   float: right;
// `;

function Sidebar() {
  return (
    <>
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
      <Tags>
        {
          <>
            <li>Custom Filters</li>
            <a href="http://localhost:3000/">Create a custom filter</a>
            <li>Watched Tags</li>
            <Button variant="contained" startIcon={<VisibilityIcon />}>
              Watch a tag
            </Button>
            <li>Ignored Tags</li>
            <Button variant="contained">Add an ignored tag</Button>
            <li>Collectives</li>
            <li>Google Cloud</li>
            <li>Twilio</li>
            <li>AWS</li>
          </>
        }
      </Tags>
      {/* <Blog>blog</Blog>
      <Featured>featured</Featured>
      <CustomF>custom filters</CustomF>
      <WatchedT>watched tags</WatchedT>
      <IgnoredT>ignored tags</IgnoredT>
      <Collectives>collecties</Collectives>

      <Related>related</Related>
      <HNQ>Hot network questions</HNQ> */}
    </>
  );
}

export default Sidebar;

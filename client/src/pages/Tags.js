import Tag from '../components/Tag';
import Header from '../components/Header';
import NavBar from '../components/NavBar';

//* HOME_003
function Tags() {
  return (
    <div>
      <h1>Tags Page</h1>
      <Header />
      <NavBar />
      Tags <br />
      A tag is a keyword or label ~~~ <br />
      <input type="text" placeholder="Filter by tag name"></input>
      <Tag />
    </div>
  );
}

export default Tags;

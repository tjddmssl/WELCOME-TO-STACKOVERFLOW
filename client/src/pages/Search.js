import QButton from '../components/QButton';
import QLists from '../components/QLists';
import Header from '../components/Header';
import NavBar from '../components/NavBar';

//* SEARCH_001 검색 결과
function Search() {
  return (
    <div>
      <h1>Search page</h1>
      <Header />
      <NavBar />
      Search Results <QButton />
      <QLists />
    </div>
  );
}

export default Search;

import QButton from '../components/Layout/QButton';
import QLists from '../components/AllQuestions/QLists';
import Header from '../components/Layout/Header';
import NavBar from '../components/Layout/NavBar';

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

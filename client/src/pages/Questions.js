import Header from '../components/Header';
import QButton from '../components/QButton';
import QLists from '../components/QLists';
import NavBar from '../components/NavBar';
import Sidebar from '../components/Sidebar';

//* HOME_002
function Questions() {
  return (
    <div>
      <h1>Questions page</h1>
      <Header />
      <NavBar />
      <Sidebar />
      All questions
      <QButton /> <br />
      <QLists />
    </div>
  );
}

export default Questions;

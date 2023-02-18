import AnswerForm from '../components/AnswerForm';
import QButton from '../components/QButton';
import Header from '../components/Header';
import NavBar from '../components/NavBar';

//* VIEW_01
function View() {
  return (
    <div>
      <h1>View page</h1> <br />
      <Header />
      <NavBar />
      title, informatino, body <QButton />
      <AnswerForm />
    </div>
  );
}

export default View;

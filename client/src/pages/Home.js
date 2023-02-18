import Header from '../components/Header';
import QButton from '../components/QButton';
import QLists from '../components/QLists';
import NavBar from '../components/NavBar';
import Sidebar from '../components/Sidebar';
import Footer from '../components/Footer';

//* HOME_001
function Home() {
  return (
    <div>
      <h1>Home page</h1>
      <Header />
      <NavBar />
      <Sidebar />
      <QButton />
      <QLists />
      <Footer />
    </div>
  );
}

export default Home;

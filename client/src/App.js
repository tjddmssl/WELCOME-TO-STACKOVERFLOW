import './App.css';
import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Search from './pages/Search';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Questions from './pages/Questions';
import View from './pages/View';
import Tags from './pages/Tags';
import Users from './pages/Users';
// import QButton from './components/QButton';
import Ask from './pages/Ask';
import ViewEdit from './pages/ViewEdit';

function App() {
  return (
    <div className="App">
      {/* <div>
        <Link to="/">Home</Link>
        <Link to="/search">Search</Link>
        <Link to="/login">Login</Link>
        <Link to="/signup">Sign Up</Link>
        <Link to="/questions">Questions</Link>
        <Link to="/view">View</Link>
        <Link to="/tags">Tags</Link>
        <Link to="/users">Users</Link>
        <Link to="/ask">{<QButton />}</Link>
      </div> */}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/search" element={<Search />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/questions" element={<Questions />} />
        <Route path="/view" element={<View />} />
        <Route path="/tags" element={<Tags />} />
        <Route path="/users" element={<Users />} />
        <Route path="/ask" element={<Ask />} />
        <Route path="/view/ask" element={<Ask />} />
        <Route path="/view/edit" element={<ViewEdit />} />
      </Routes>
    </div>
  );
}

export default App;

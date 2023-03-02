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
import Ask from './pages/Ask';
import ViewEdit from './pages/ViewEdit';

function App() {
  //* 로그인 상태, 유저 정보 상태 관리

  return (
    <div className="App">
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

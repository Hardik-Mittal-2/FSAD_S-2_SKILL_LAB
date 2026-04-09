import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link, useLocation } from 'react-router-dom';
import Dashboard from './Dashboard';
import LocalUserList from './LocalUserList';
import UserList from './UserList';
import FakePostList from './FakePostList';
import './App.css';

const Navigation = () => {
  const location = useLocation();
  const isHome = location.pathname === '/';

  return !isHome && (
    <nav className="top-nav">
      <Link to="/" className="back-link">← Back to Dashboard</Link>
    </nav>
  );
};

function App() {
  return (
    <Router>
      <div className="app-container">
        <Navigation />
        <main className="main-content">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/local-users" element={<LocalUserList />} />
            <Route path="/users-api" element={<UserList />} />
            <Route path="/fake-posts" element={<FakePostList />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;

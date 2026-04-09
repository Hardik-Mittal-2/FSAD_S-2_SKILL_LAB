import Navbar from './Navbar';

function Home() {
  const storedUser = localStorage.getItem('user');
  const user = storedUser ? JSON.parse(storedUser) : null;

  return (
    <div>
      <Navbar />
      <div className="page-center">
        <div className="card content-card">
          <h2>Home</h2>
          <p>Welcome {user?.username || 'User'}.</p>
          <p>You are logged in and your session is stored in localStorage.</p>
        </div>
      </div>
    </div>
  );
}

export default Home;

import { useEffect, useState } from 'react';
import Navbar from './Navbar';
import { getUserByUsername } from '../services/api';

function Profile() {
  const [profile, setProfile] = useState(null);
  const [error, setError] = useState('');

  useEffect(() => {
    const storedUser = localStorage.getItem('user');
    if (!storedUser) {
      return;
    }

    const parsedUser = JSON.parse(storedUser);
    const username = parsedUser.username;

    if (!username) {
      setError('No username found in session storage');
      return;
    }

    const fetchProfile = async () => {
      try {
        const response = await getUserByUsername(username);
        setProfile(response.data);
      } catch (err) {
        setError(err.response?.data || 'Failed to fetch profile');
      }
    };

    fetchProfile();
  }, []);

  return (
    <div>
      <Navbar />
      <div className="page-center">
        <div className="card content-card">
          <h2>Profile</h2>
          {error && <p className="error">{error}</p>}
          {profile && (
            <div className="profile-details">
              <p>
                <strong>ID:</strong> {profile.id}
              </p>
              <p>
                <strong>Username:</strong> {profile.username}
              </p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Profile;

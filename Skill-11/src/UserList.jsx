import React, { useState, useEffect } from 'react';

const UserList = () => {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch('https://jsonplaceholder.typicode.com/users')
            .then(res => {
                if (!res.ok) {
                    throw new Error('Failed to fetch users from API');
                }
                return res.json();
            })
            .then(data => {
                setUsers(data);
                setLoading(false);
            })
            .catch(err => {
                setError(err.message);
                setLoading(false);
            });
    }, []);

    if (loading) return <div className="loading">Loading public API users...</div>;
    if (error) return <div className="error">Error: {error}</div>;

    return (
        <div className="component-container">
            <h2>🌍 Public API Users</h2>
            <div className="grid-container">
                {users.map(user => (
                    <div key={user.id} className="card user-api-card">
                        <h3>{user.name}</h3>
                        <p><strong>Email:</strong> {user.email}</p>
                        <p><strong>Phone:</strong> {user.phone}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default UserList;

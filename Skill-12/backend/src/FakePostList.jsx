import React, { useState, useEffect } from 'react';
import axios from 'axios';

const FakePostList = () => {
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedUserId, setSelectedUserId] = useState('all');

    const fetchPosts = () => {
        setLoading(true);
        setError(null);
        axios.get('https://dummyjson.com/posts')
            .then(response => {
                setPosts(response.data.posts);
                setLoading(false);
            })
            .catch(err => {
                setError(err.message);
                setLoading(false);
            });
    };

    useEffect(() => {
        fetchPosts();
    }, []);

    const userIds = [...new Set(posts.map(post => post.userId))].sort((a, b) => a - b);

    const filteredPosts = selectedUserId === 'all'
        ? posts
        : posts.filter(post => post.userId.toString() === selectedUserId);

    if (loading) return <div className="loading">Loading posts...</div>;
    if (error) return <div className="error">Error: {error}</div>;

    return (
        <div className="component-container">
            <div className="header-actions">
                <h2>📝 Fake API Posts</h2>
                <div style={{ display: 'flex', gap: '1rem', alignItems: 'center' }}>
                    <select
                        className="dropdown-filter"
                        value={selectedUserId}
                        onChange={(e) => setSelectedUserId(e.target.value)}
                    >
                        <option value="all">All Users</option>
                        {userIds.map(id => (
                            <option key={id} value={id}>User {id}</option>
                        ))}
                    </select>
                    <button className="refresh-btn" onClick={fetchPosts}>
                        ↻ Refresh Data
                    </button>
                </div>
            </div>
            <div className="grid-container">
                {filteredPosts.map(post => (
                    <div key={post.id} className="card post-card">
                        <h3>{post.title}</h3>
                        <p>{post.body}</p>
                        <p style={{ marginTop: '0.5rem', fontSize: '0.8rem', color: 'var(--text-secondary)' }}>
                            <strong>User ID:</strong> {post.userId}
                        </p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default FakePostList;

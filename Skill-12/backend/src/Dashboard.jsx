import React from 'react';
import { Link } from 'react-router-dom';

const Dashboard = () => {
    return (
        <div className="dashboard-container">
            <h1>API Integration Playground</h1>
            <p className="subtitle">Select a data source to view the integration output</p>

            <div className="dashboard-links">
                <Link to="/local-users" className="dashboard-card local-card">
                    <div className="icon">📁</div>
                    <h2>Local Users</h2>
                    <p>Fetch users from public/users.json using fetch()</p>
                </Link>

                <Link to="/users-api" className="dashboard-card placeholder-card">
                    <div className="icon">🌐</div>
                    <h2>Users API</h2>
                    <p>Fetch public API user records using fetch()</p>
                </Link>

                <Link to="/fake-posts" className="dashboard-card dummy-card">
                    <div className="icon">📝</div>
                    <h2>Fake API Posts</h2>
                    <p>Fetch and reload posts using Axios</p>
                </Link>
            </div>
        </div>
    );
};

export default Dashboard;

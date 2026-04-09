import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Content-Type': 'application/json'
  }
});

export const registerUser = (payload) => api.post('/register', payload);
export const loginUser = (payload) => api.post('/login', payload);
export const getUserByUsername = (username) => api.get(`/user/${username}`);

export default api;

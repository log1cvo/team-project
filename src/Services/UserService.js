// src/Services/UserService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080'; // 根据实际后端 API 地址调整

const register = (username, password, role) => {
  return axios.post(`${API_URL}/auth/register`, {
    username,
    password,
    role,
  });
};

const login = (username, password) => {
  return axios.post(`${API_URL}/auth/login`, {
    username,
    password,
  });
};

export default {
  register,
  login,
};

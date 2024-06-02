// src/Services/IssueService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080';

const getIssues = () => {
  return axios.get(`${API_URL}/issues`);
};

const getIssueById = (id) => {
  return axios.get(`${API_URL}/issues/${id}`);
};

const createIssue = (issue) => {
  return axios.post(`${API_URL}/issues`, issue);
};

export default {
  getIssues,
  getIssueById,
  createIssue,
};

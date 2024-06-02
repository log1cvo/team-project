// src/Pages/IssueList.js
import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import AuthContext from '../context/AuthContext';

const IssueList = () => {
  const { user } = useContext(AuthContext);
  const [issues, setIssues] = useState([]);
  const [searchParams, setSearchParams] = useState({
    assignee: '',
    status: '',
    reporter: ''
  });

  const fetchIssues = async () => {
    try {
      const response = await axios.get('http://localhost:8080/issues', {
        headers: {
          'Authorization': `Bearer ${user.token}`
        },
        params: searchParams
      });
      setIssues(response.data);
    } catch (error) {
      console.error('Error fetching issues:', error);
    }
  };

  useEffect(() => {
    fetchIssues();
  }, [searchParams]);

  const handleSearchChange = (e) => {
    setSearchParams({ ...searchParams, [e.target.name]: e.target.value });
  };

  const handleSearch = (e) => {
    e.preventDefault();
    fetchIssues();
  };

  return (
    <div>
      <h2>Issue List</h2>
      <form onSubmit={handleSearch}>
        <div>
          <label>Assignee:</label>
          <input type="text" name="assignee" value={searchParams.assignee} onChange={handleSearchChange} />
        </div>
        <div>
          <label>Status:</label>
          <input type="text" name="status" value={searchParams.status} onChange={handleSearchChange} />
        </div>
        <div>
          <label>Reporter:</label>
          <input type="text" name="reporter" value={searchParams.reporter} onChange={handleSearchChange} />
        </div>
        <button type="submit">Search</button>
      </form>
      <ul>
        {issues.map((issue) => (
          <li key={issue.id}>
            <Link to={`/issues/${issue.id}`}>{issue.title}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default IssueList;

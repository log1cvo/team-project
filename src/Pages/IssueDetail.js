// src/Pages/IssueDetail.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const IssueDetail = () => {
  const { id } = useParams();
  const [issue, setIssue] = useState(null);

  useEffect(() => {
    const fetchIssue = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/issues/${id}`);
        setIssue(response.data);
      } catch (error) {
        console.error('Error fetching issue:', error);
      }
    };

    fetchIssue();
  }, [id]);

  if (!issue) return <div>Loading...</div>;

  return (
    <div>
      <h2>{issue.title}</h2>
      <p>{issue.description}</p>
      <p><strong>Reporter:</strong> {issue.reporter}</p>
      <p><strong>Assignee:</strong> {issue.assignee}</p>
      <p><strong>Status:</strong> {issue.status}</p>
      <p><strong>Priority:</strong> {issue.priority}</p>
      <h3>Comments</h3>
      <ul>
        {issue.comments.map((comment, index) => (
          <li key={index}>{comment}</li>
        ))}
      </ul>
    </div>
  );
};

export default IssueDetail;

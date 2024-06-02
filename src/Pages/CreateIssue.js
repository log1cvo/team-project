// src/Pages/CreateIssue.js
import React, { useState, useContext } from 'react';
import axios from 'axios';
import AuthContext from '../context/AuthContext';

const CreateIssue = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const { user } = useContext(AuthContext);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem('token'); // 从localStorage获取令牌
      const response = await axios.post(
        'http://localhost:8080/issues',
        { title, description },
        {
          headers: {
            Authorization: `Bearer ${token}`, // 添加身份验证头
            'Content-Type': 'application/json',
          },
        }
      );
      console.log(response.data);
      // 处理成功的创建问题
    } catch (error) {
      console.error('Error creating issue:', error);
    }
  };

  return (
    <div>
      <h2>Create Issue</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Title:</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Description:</label>
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </div>
        <button type="submit">Create Issue</button>
      </form>
    </div>
  );
};

export default CreateIssue;

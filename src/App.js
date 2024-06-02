// src/App.js
import React from 'react';
import AppRouter from './AppRouter';
import './Styles/App.css';
import { Link } from 'react-router-dom';

const App = () => (
  <div className="App">
    <nav>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/login">Login</Link></li>
        <li><Link to="/register">Register</Link></li>
        <li><Link to="/issues">Issue List</Link></li>
        <li><Link to="/create-issue">Create Issue</Link></li>
      </ul>
    </nav>
    <AppRouter />
  </div>
);

export default App;

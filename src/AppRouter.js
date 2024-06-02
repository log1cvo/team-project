// src/AppRouter.js
import React, { useContext } from 'react';
import { Route, Routes, Navigate } from 'react-router-dom';
import Home from './Pages/Home';
import Login from './Pages/Login';
import Register from './Pages/Register';
import IssueList from './Pages/IssueList';
import IssueDetail from './Pages/IssueDetail';
import CreateIssue from './Pages/CreateIssue';
import AuthContext from './context/AuthContext';

const PrivateRoute = ({ children }) => {
  const { user } = useContext(AuthContext);
  return user ? children : <Navigate to="/login" />;
};

const AppRouter = () => (
  <Routes>
    <Route path="/" element={<Home />} />
    <Route path="/login" element={<Login />} />
    <Route path="/register" element={<Register />} />
    <Route path="/issues" element={<PrivateRoute><IssueList /></PrivateRoute>} />
    <Route path="/issues/:id" element={<PrivateRoute><IssueDetail /></PrivateRoute>} />
    <Route path="/create-issue" element={<PrivateRoute><CreateIssue /></PrivateRoute>} />
  </Routes>
);

export default AppRouter;

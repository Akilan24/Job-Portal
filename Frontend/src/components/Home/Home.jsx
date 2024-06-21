import React from "react";
import "./Home.css";
import { Route, Routes } from "react-router-dom";
import Register from "../Register/Register.jsx";
import ForgotPassword from "../Login/ForgotPassword.jsx";
import ResetPassword from "../Login/ResetPassword.jsx";
import Login from "../Login/Login.jsx";
import Profile from "../Profile/Profile.jsx";
function Home() {
  return (
    <>
      <div className="homeclass">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/forgotpassword" element={<ForgotPassword />} />
          <Route path="/resetpassword" element={<ResetPassword />} />
          <Route path="/profile" element={<Profile />} />
        </Routes>
      </div>
    </>
  );
}

export default Home;

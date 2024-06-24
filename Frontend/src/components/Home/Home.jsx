import React from "react";
import "./Home.css";
import { Route, Routes } from "react-router-dom";
import Applicant from "../Register/Applicant.jsx";
import Recruiter from "../Register/Recruiter.jsx";
import Search from "../Search/Search.jsx";
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
          <Route path="/applicant" element={<Applicant />} />
          <Route path="/recruiter" element={<Recruiter />} />
          <Route path="/forgotpassword" element={<ForgotPassword />} />
          <Route path="/resetpassword" element={<ResetPassword />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/search" element={<Search />} />
        </Routes>
      </div>
    </>
  );
}

export default Home;

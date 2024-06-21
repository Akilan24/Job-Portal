import React, { useState } from "react";
import axios from "axios";
import "./Register.css";
import { useNavigate } from "react-router-dom";

function Register() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    role: "applicant",
    firstName: "",
    lastName: "",
    emailId: "",
    city: "",
    state: "",
    country: "",
    pincode: "",
    Skills: "",
    experience: [],
    education: [],
  });

  function onchangeinput(e) {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  }

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const skillsSet = new Set(
        formData.Skills.split(",").map((skill) => skill.trim())
      );
      const response = await axios.post(
        "http://localhost:8080/CS/Auth/register",
        { ...formData, Skills: skillsSet }
      );
      window.alert("User registered successfully");
      navigate("/login");
    } catch (error) {
      window.alert("Error registering user");
      console.error("Error registering user:", error);
      setTimeout(() => {
        navigate("/register");
      }, 1000);
    }
  }

  return (
    <div className="register">
      <img id="logo" src="./jplogo.png" alt="Logo" />
      <h2 id="register">Registration</h2>
      <form className="registerclass" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            name="username"
            value={formData.username}
            onChange={onchangeinput}
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={formData.password}
            onChange={onchangeinput}
          />
        </div>
        <div className="form-group">
          <label htmlFor="firstName">First Name:</label>
          <input
            type="text"
            id="firstName"
            name="firstName"
            value={formData.firstName}
            onChange={onchangeinput}
          />
        </div>
        <div className="form-group">
          <label htmlFor="lastName">Last Name:</label>
          <input
            type="text"
            id="lastName"
            name="lastName"
            value={formData.lastName}
            onChange={onchangeinput}
          />
        </div>
        <div className="form-group">
          <label htmlFor="emailId">Email:</label>
          <input
            type="email"
            id="emailId"
            name="emailId"
            value={formData.emailId}
            onChange={onchangeinput}
          />
        </div>
        <div className="form-group">
          <label htmlFor="city">City:</label>
          <input
            type="text"
            id="city"
            name="city"
            value={formData.city}
            onChange={onchangeinput}
          />
        </div>
        <div className="form-group">
          <label htmlFor="state">State:</label>
          <input
            type="text"
            id="state"
            name="state"
            value={formData.state}
            onChange={onchangeinput}
          />
        </div>
        <div className="form-group">
          <label htmlFor="country">Country:</label>
          <input
            type="text"
            id="country"
            name="country"
            value={formData.country}
            onChange={onchangeinput}
          />
        </div>
        <div className="form-group">
          <label htmlFor="pincode">Pincode:</label>
          <input
            type="text"
            id="pincode"
            name="pincode"
            value={formData.pincode}
            onChange={onchangeinput}
          />
        </div>
        <div className="form-group">
          <label htmlFor="Skills">Skills (comma-separated):</label>
          <input
            type="text"
            id="Skills"
            name="Skills"
            value={formData.Skills}
            onChange={onchangeinput}
          />
        </div>
        <div>
          <button type="submit">Register</button>
        </div>
      </form>
    </div>
  );
}

export default Register;

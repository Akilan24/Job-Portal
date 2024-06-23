import React, { useState } from "react";
import axios from "axios";
import "./Register.css";
import { useNavigate } from "react-router-dom";

function Register() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    password: "",
    role: "applicant",
    firstName: "",
    lastName: "",
    emailId: "",
    mobile: "",
    city: "",
    state: "",
    country: "",
    pincode: "",
    workStatus: "",
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
        "http://localhost:8080/JP/Auth/register",
        formData
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
      <div className="container">
        <img id="logo" src="./jplogo.png" />
        <p>
          Already Registered?&nbsp;
          <a id="signin" onClick={(e) => navigate("/")}>
            Login
          </a>
          &nbsp; here
        </p>
      </div>
      <div className="section">
        <div className="leftpane">
          <img id="reg" src="./register.png" />
          <p>On registering, you can</p>
          <div>
            <div>
              <i className="fa fa-check-circle" />
              <p>Build your profile and let recruiter find you</p>
            </div>
            <div>
              <i className="fa fa-check-circle" />
              <p>Get job postings delivered right to your email</p>
            </div>
            <div>
              <i className="fa fa-check-circle" />
              <p>Find a job and grow your career</p>
            </div>
          </div>
        </div>
        <form className="registerclass" onSubmit={handleSubmit}>
          <h2 id="register">Create your Career Flow profile</h2>
          <p>Search & apply to jobs from India's No.1 Job Site</p>
          <div className="form-group">
            <label htmlFor="firstName">
              First Name<span style={{ color: "red" }}>*</span>
            </label>
            <input
              type="text"
              id="firstName"
              name="firstName"
              value={formData.firstName}
              placeholder="First name"
              onChange={onchangeinput}
            />
          </div>
          <div className="form-group">
            <label htmlFor="lastName">
              Last Name<span style={{ color: "red" }}>*</span>
            </label>
            <input
              type="text"
              id="lastName"
              name="lastName"
              value={formData.lastName}
              placeholder="Last name"
              onChange={onchangeinput}
            />
          </div>
          <div className="form-group">
            <label htmlFor="emailId">
              Email<span style={{ color: "red" }}>*</span>
            </label>
            <input
              type="email"
              id="emailId"
              name="emailId"
              value={formData.emailId}
              placeholder="Tell us your Email ID"
              onChange={onchangeinput}
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">
              Password<span style={{ color: "red" }}>*</span>
            </label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              placeholder="(Minimum 8 characters)"
              onChange={onchangeinput}
            />
          </div>
          <div className="form-group">
            <label htmlFor="mobile">
              Mobile Number<span style={{ color: "red" }}>*</span>
            </label>
            <input
              type="tel"
              id="mobile"
              name="mobile"
              value={formData.mobile}
              placeholder="Enter your mobile number"
              onChange={onchangeinput}
            />
          </div>
          <div className="form-group">
            <label htmlFor="city">City</label>
            <input
              type="text"
              id="city"
              name="city"
              value={formData.city}
              placeholder="City name"
              onChange={onchangeinput}
            />
          </div>
          <div className="form-group">
            <label htmlFor="state">State</label>
            <input
              type="text"
              id="state"
              name="state"
              value={formData.state}
              placeholder="State name"
              onChange={onchangeinput}
            />
          </div>
          <div className="form-group">
            <label htmlFor="country">Country</label>
            <input
              type="text"
              id="country"
              name="country"
              value={formData.country}
              placeholder="Country name"
              onChange={onchangeinput}
            />
          </div>
          <div className="form-group">
            <label htmlFor="pincode">Pincode</label>
            <input
              type="text"
              id="pincode"
              name="pincode"
              value={formData.pincode}
              placeholder="eg: 620011"
              onChange={onchangeinput}
            />
          </div>
          <div className="form-group">
            <label htmlFor="workStatus">Work Status</label>
            <div>
              <div>
                <input
                  type="radio"
                  id="workStatus"
                  name="workStatus"
                  value="experienced"
                  onChange={onchangeinput}
                />
                <label>I'm experienced</label>
              </div>
              <div>
                <input
                  type="radio"
                  id="workStatus"
                  name="workStatus"
                  value="fresher"
                  onChange={onchangeinput}
                />
                <label>I'm a fresher</label>
              </div>
            </div>
          </div>
          <div>
            <button type="submit">Register</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Register;

import React, { useState } from "react";
import "./Login.css";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
function Login() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const navigate = useNavigate();
  function onchangeinput(e) {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  }

  async function onlogin(e) {
    e.preventDefault();

    try {
      const login = {
        emailId: formData.email,
        password: formData.password,
      };
      const token = await axios.post(
        "http://localhost:8080/JP/Auth/login",
        login
      );
      localStorage.setItem(`accesstoken`, token.data.accessToken);
      localStorage.setItem(`refreshtoken`, token.data.refreshToken);
      localStorage.setItem(`email`, formData.email);
      console.log(token);
      console.log(token.data.accessToken + ` ` + token.data.refreshToken);
      if (token.status == 200) navigate("search");
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  return (
    <div className="loginclass">
      <div id="container">
        <form className="formclass" onSubmit={onlogin}>
          <img id="logo" src="./jplogo.png" />
          <p>Welcome to your professional community</p>
          <div>
            <label htmlFor="email">Email</label>
            <input
              type="text"
              name="email"
              value={formData.email}
              id="email"
              onChange={onchangeinput}
            />
          </div>
          <div>
            <label htmlFor="password">Password </label>
            <input
              type="password"
              name="password"
              value={formData.password}
              id="password"
              onChange={onchangeinput}
            />
            <div id="tag">
              <Link id="a" to="/forgotpassword">
                Forgot Password?
              </Link>
            </div>
          </div>

          <div>
            <button id="log" type="submit">
              Sign in
            </button>
          </div>
        </form>
      </div>
      <div id="img">
        <button onClick={(e) => navigate("/applicant")}>Join now</button>
        <img id="home" src="./home.jpg" />
      </div>
    </div>
  );
}
export default Login;

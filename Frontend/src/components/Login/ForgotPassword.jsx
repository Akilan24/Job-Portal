import React, { useState } from "react";
import "./ForgotPassword.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function ForgotPassword() {
  const [formData, setFormData] = useState("");
  const navigate = useNavigate();
  function onchangeinput(e) {
    setFormData(e.target.value);
  }

  async function onsubmit(e) {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/JP/Auth/forgotpassword/" + formData
      );
      console.log("Email send");
      window.alert("Email send");
      setTimeout(() => {
        navigate("/");
      }, 1000);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }
  return (
    <div className="forgotclass">
      <div className="container">
        <img id="logo" src="./jplogo.png" />
        <div>
          <button id="signin" onClick={(e) => navigate("/")}>
            Sign in
          </button>
          <button id="joinnow" onClick={(e) => navigate("/applicant")}>
            Join now
          </button>
        </div>
      </div>

      <form className="formclass" onSubmit={onsubmit}>
        <div id="contain">
          <h2>Forgot password</h2>
          <input
            type="text"
            name="email"
            value={formData}
            id="email"
            placeholder="Email"
            onChange={onchangeinput}
          />
          <p>
            We’ll send a verification code to this email if it matches an
            existing Career Flow account.
          </p>
        </div>
        <div className="buttons">
          <button id="send" type="submit">
            Send
          </button>
          <button id="back" onClick={(e) => navigate(-1)}>
            Back
          </button>
        </div>
      </form>
    </div>
  );
}

export default ForgotPassword;

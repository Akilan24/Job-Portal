import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Search.css";
import { useNavigate } from "react-router-dom";

function Search() {
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);
  const [display, setDisplay] = useState(false);
  const [applicant, setApplicant] = useState("");
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  const [postDetails, setPostDetails] = useState({
    emailId: localStorage.getItem("accesstoken"),
    post: "",
  });
  const [isExpanded, setIsExpanded] = useState(false); // State to manage textarea size

  async function handleAllPost(e) {
    e.preventDefault();
    try {
      setDisplay(true);
      const response = await axios.post(
        "http://localhost:8080/JP/User/getallPost",
        config
      );
      setPosts(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
      setTimeout(() => {
        navigate("/register");
      }, 1000);
    }
  }
  useEffect(() => {
    async function fetchApplicantDetails() {
      try {
        const response = await axios.get(
          "http://localhost:8080/JP/User/getapplicantbyid",
          config
        );
        setApplicant(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error.response.data.message);
      }
    }
    fetchApplicantDetails();
  }, []);
  async function handlePost(e) {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/JP/User/addPost",
        postDetails,
        config
      );
      setIsExpanded(false);
      setPostDetails({ ...postDetails, post: "" });
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
      setTimeout(() => {
        navigate("/register");
      }, 1000);
    }
  }

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPostDetails({ ...postDetails, [name]: value });
  };

  const toggleTextareaSize = () => {
    setIsExpanded(!isExpanded);
  };

  const closeTextarea = () => {
    setIsExpanded(false);
    setPostDetails({ ...postDetails, post: "" });
  };

  return (
    <div className="searchClass">
      <div id="tabs">
        <div id="leftpane">
          <img id="logo" src="./logo.png" alt="Logo" />
          <input type="text" placeholder="Search by title, skill, or company" />
        </div>
        <div id="rightpane">
          <div onClick={handleAllPost}>
            <img id="icon" src="./home.png" alt="Home" />
            <p>Home</p>
          </div>
          <div>
            <img id="icon" src="./job.png" alt="Job" />
            <p>Job</p>
          </div>
          <div>
            <img id="icon" src="./notification.png" alt="Status" />
            <p>Status</p>
          </div>
          <div>
            <img id="icon" src="./profile.png" alt="Profile" />
            <p>Profile</p>
          </div>
        </div>
      </div>
      <div>
        <div className="applicantDetails">
          <p>
            {applicant.firstname}&nbsp;{applicant.lastname}
          </p>
          <p>{applicant.headline}</p>
        </div>
        <div className="contain">
          {display && (
            <div className="postContainer">
              <div className="post">
                <div className="textareaContainer">
                  <textarea
                    name="post"
                    value={postDetails.post}
                    placeholder="Start a post..."
                    onChange={handleChange}
                    className={isExpanded ? "expanded" : ""}
                    onClick={toggleTextareaSize}
                  />
                  {isExpanded && (
                    <button className="closeButton" onClick={closeTextarea}>
                      &times;
                    </button>
                  )}
                </div>
                <button id="postButton" onClick={handlePost}>
                  Post
                </button>
              </div>
              <div className="postDetails">
                {posts.length > 0 ? (
                  posts.map((post, index) => (
                    <div key={index} className="post">
                      <p>{post.username}</p>
                      <p>{post.description}</p>
                    </div>
                  ))
                ) : (
                  <p>No posts are available</p>
                )}
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Search;

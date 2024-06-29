import React, { useState, useEffect } from "react";
import axios from "axios";
import "./SearchRecruiter.css";
import { useNavigate, useParams } from "react-router-dom";

function Search() {
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);
  const [recruiter, setRecruiter] = useState(null);
  const [applicants, setApplicants] = useState(null);
  const [display, setDisplay] = useState("profile");
  const [error, setError] = useState(null);
  const [search, setSearch] = useState("");
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  const [postDetails, setPostDetails] = useState({
    emailId: localStorage.getItem("accesstoken"),
    name: "",
    description: "",
  });
  const [isExpanded, setIsExpanded] = useState(false);

  async function handleAllPost() {
    try {
      setDisplay("post");
      const response = await axios.get(
        "http://localhost:8080/JP/User/getallPost",
        config
      );
      setPosts(response.data);
    } catch (error) {
      setError(error.response?.data?.message || "An error occurred");
    }
  }

  useEffect(() => {
    handleProfileDetails();
  }, []);

  async function fetchApplicant(e) {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/User/getApplicantbysearch/${search}`,
        config
      );
      setJobs(response.data);
      console.log(response);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }
  async function handlePost() {
    try {
      const response = await axios.post(
        "http://localhost:8080/JP/User/addpost",
        postDetails,
        config
      );
      setIsExpanded(false);
      setPostDetails({ ...postDetails, description: "" });
      handleAllPost();
    } catch (error) {
      setError(error.response?.data?.message || "An error occurred");
    }
  }

  async function handleProfileDetails() {
    try {
      const response = await axios.get(
        `http://localhost:8080/JP/User/getRecruiter/${localStorage.getItem(
          "email"
        )}`,
        config
      );
      setRecruiter(response.data);
    } catch (error) {
      setError(error.response?.data?.message || "An error occurred");
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
    setPostDetails({ ...postDetails, description: "" });
  };

  return (
    <div className="searchRecruiter">
      <div id="tabs">
        <div id="leftpane">
          <img id="logo" src="./logo.png" alt="Logo" />
          <input
            type="text"
            onChange={(e) => setApplicants(e.target.value)}
            placeholder="Search by title, skill or name"
          />
          <button id="search" onClick={fetchApplicant}>
            Search
          </button>{" "}
        </div>
        <div id="rightpane">
          <div onClick={handleAllPost}>
            <img id="icon" src="./home.png" alt="Home" />
            <p>Home</p>
          </div>
          <div>
            <img id="icon" src="./business.png" alt="Business" />
            <p>Business</p>
          </div>
          <div>
            <img id="icon" src="./notification.png" alt="Status" />
            <p>Status</p>
          </div>
          <div onClick={() => setDisplay("profile")}>
            <img id="icon" src="./profile.png" alt="Profile" />
            <p>Profile</p>
          </div>
        </div>
      </div>
      <div className="details">
        <div className="contain">
          {display === "post" && (
            <div className="postContainer">
              <div className="post">
                <div className="textareaContainer">
                  <textarea
                    name="description"
                    value={postDetails.description}
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
                      <p>{post.emailId}</p>
                      <p>{post.description}</p>
                    </div>
                  ))
                ) : (
                  <p>No posts are available</p>
                )}
              </div>
            </div>
          )}
          {display === "business" && <div className="business"></div>}
          {display === "status" && <div className="jobStatus"></div>}
          {display === "profile" && recruiter && (
            <div className="profileDetails">
              <p>Name: {recruiter.name}</p>
              <p>Email: {recruiter.emailId}</p>
              <p>Company: {recruiter.company}</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Search;

import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./SearchRecruiter.css";

function Search() {
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);
  const [recruiter, setRecruiter] = useState(null);
  const [applicants, setApplicants] = useState(null);
  const [jobs, setJobs] = useState([]);
  const [display, setDisplay] = useState("profile");
  const [error, setError] = useState(null);
  const [search, setSearch] = useState("");
  const [application, setApplication] = useState([]);
  const [formData, setFormData] = useState({
    jobTitle: "",
    jobDescription: "",
    salary: 0,
    experience: 0,
    jobType: "",
    jobCategory: "",
    requiredSkills: "",
  });
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
        "http://localhost:8080/JP/Job/getallposts",
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
        `http://localhost:8080/JP/Job/getJobbysearch//${search}`,
        config
      );
      console.log(response);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }
  async function handlePost() {
    try {
      const response = await axios.post(
        "http://localhost:8080/JP/Job/addpost",
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
  async function handleJobSubmit() {
    try {
      const response = await axios.post(
        `http://localhost:8080/JP/Job/addJob/${localStorage.getItem("email")}`,
        formData,
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
      setError(error.response.data);
    }
  }
  async function getJobApplication(jobId) {
    try {
      const response = await axios.get(
        `http://localhost:8080/JP/Job/getapplication/${jobId}`,
        config
      );
      setApplication(response.data);
    } catch (error) {
      setError(error.response.data);
    }
  }
  async function getAllJobs() {
    try {
      setDisplay("status");
      const response = await axios.get(
        `http://localhost:8080/JP/Job/getJobbyemail/${localStorage.getItem(
          "email"
        )}`,
        config
      );
      setJobs(response.data);
    } catch (error) {
      setError(error.response.data);
    }
  }
  const handleChange = (e) => {
    const { name, value } = e.target;
    setPostDetails({ ...postDetails, [name]: value });
  };

  const toggleTextareaSize = () => {
    setIsExpanded(!isExpanded);
  };
  function onchangeinput(e) {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  }
  const closeTextarea = () => {
    setIsExpanded(false);
    setPostDetails({ ...postDetails, description: "" });
  };
  function handleLogout() {
    localStorage.setItem(`accesstoken`, "");
    localStorage.setItem(`refreshtoken`, "");
    localStorage.setItem(`email`, "");
    navigate("/recruiterLogin");
  }
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
          <div onClick={() => setDisplay("business")}>
            <img id="icon" src="./business.png" alt="Business" />
            <p>Business</p>
          </div>
          <div onClick={getAllJobs}>
            <img id="icon" src="./notification.png" alt="Status" />
            <p>Status</p>
          </div>
          <div onClick={() => setDisplay("profile")}>
            <img id="icon" src="./profile.png" alt="Profile" />
            <p>Profile</p>
          </div>
          <div onClick={handleLogout}>
            <img id="icon" src="./logout.png" alt="Logout" />
            <p>Logout</p>
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
          {display === "business" && (
            <div className="business">
              <h2 id="register">Register job</h2>
              <form className="registerclass" onSubmit={handleJobSubmit}>
                <div id="form">
                  <div className="form-group">
                    <label htmlFor="jobTitle">
                      Job Title<span style={{ color: "red" }}>*</span>
                    </label>
                    <input
                      type="text"
                      id="jobTitle"
                      name="jobTitle"
                      value={formData.jobTitle}
                      placeholder="jobTitle"
                      required
                      onChange={onchangeinput}
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="jobType">
                      Job Type<span style={{ color: "red" }}>*</span>
                    </label>
                    <input
                      type="text"
                      id="jobType"
                      name="jobType"
                      value={formData.jobType}
                      placeholder="Enter job type"
                      required
                      onChange={onchangeinput}
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="jobCategory">
                      Job Category<span style={{ color: "red" }}>*</span>
                    </label>
                    <input
                      type="text"
                      id="jobCategory"
                      name="jobCategory"
                      value={formData.jobCategory}
                      placeholder="Enter job category"
                      required
                      onChange={onchangeinput}
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="requiredSkills">
                      Required Skills<span style={{ color: "red" }}>*</span>
                    </label>
                    <input
                      type="text"
                      id="requiredSkills"
                      name="requiredSkills"
                      value={formData.requiredSkills}
                      placeholder="eg: java, spring boot"
                      required
                      onChange={onchangeinput}
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="salary">
                      Salary<span style={{ color: "red" }}>*</span>
                    </label>
                    <input
                      type="number"
                      id="salary"
                      name="salary"
                      value={formData.salary}
                      required
                      onChange={onchangeinput}
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="experience">
                      Experience<span style={{ color: "red" }}>*</span>
                    </label>
                    <input
                      type="number"
                      id="experience"
                      name="experience"
                      value={formData.experience}
                      required
                      onChange={onchangeinput}
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="jobDescription">
                      Description<span style={{ color: "red" }}>*</span>
                    </label>
                    <textarea
                      id="jobDescription"
                      name="jobDescription"
                      value={formData.jobDescription}
                      placeholder="About your job..."
                      required
                      onChange={onchangeinput}
                    />
                  </div>
                  <div>
                    <button type="submit">Register</button>
                  </div>
                </div>
              </form>
            </div>
          )}
          {display === "status" && (
            <div className="jobStatus">
              <div id="div1">
                {jobs.length > 0 ? (
                  jobs.map((job, _) => (
                    <div
                      key={job.jobId}
                      className="job"
                      onClick={(e) => getJobApplication(job.jobId)}
                    >
                      <p>
                        <span>Title:&nbsp;</span>
                        {job.jobTitle}
                      </p>
                      <p>
                        <span>Description:&nbsp;</span>
                        {job.jobDescription}
                      </p>
                      <p>
                        <span>Required Skills:&nbsp;</span>
                        {job.requiredskills}
                      </p>
                      <p>
                        <span>Posted Date:&nbsp;</span>
                        {job.postedDate}
                      </p>
                      <p>
                        <span>Experience:</span>
                        {job.experience}
                      </p>
                    </div>
                  ))
                ) : (
                  <p id="none">No job status is available</p>
                )}
              </div>

              {application.length > 0 &&
                application.map((app, _) => (
                  <div id="div2">
                    <div className="list" key={app.applicationId}>
                      <p>
                        <span>Name:&nbsp;</span>
                        {app.name}
                      </p>
                      <p>
                        <span>Job Title:&nbsp;</span>
                        {app.jobTitle}
                      </p>
                      <p>
                        <span>Company:&nbsp;</span>
                        {app.company}
                      </p>
                      <p>
                        <span>Applied Date:&nbsp;</span>
                        {app.appliedDate}
                      </p>
                      <p>
                        <span>Status:&nbsp;</span>
                        {app.status}
                      </p>
                    </div>
                  </div>
                ))}
            </div>
          )}
          {display === "profile" && recruiter && (
            <div className="profileDetails">
              <p>
                <span>Company:</span> {recruiter.company}
              </p>
              <p>
                <span>Email:</span> {recruiter.emailId}
              </p>
              <p>
                <span>City:</span> {recruiter.city}
              </p>
              <p>
                <span>State:</span> {recruiter.state}
              </p>
              <p>
                <span>Country:</span> {recruiter.country}
              </p>
              <p>
                <span>Pincode:</span> {recruiter.pincode}
              </p>
              <p id="about">
                <span>About:</span>
              </p>
              <p id="description">{recruiter.about}</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Search;

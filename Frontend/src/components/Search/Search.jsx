import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Search.css";
import { useNavigate } from "react-router-dom";

function Search() {
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);
  const [jobs, setJobs] = useState([]);
  const [jobDetails, setJobDetails] = useState(null);
  const [display, setDisplay] = useState("");
  const [applicant, setApplicant] = useState("");
  const [appliedJobs, setAppliedJobs] = useState(new Set());
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  const [postDetails, setPostDetails] = useState({
    emailId: localStorage.getItem("accesstoken"),
    post: "",
  });
  const [isExpanded, setIsExpanded] = useState(false);

  async function handleAllPost(e) {
    e.preventDefault();
    try {
      setDisplay("post");
      const response = await axios.get(
        "http://localhost:8080/JP/User/getallPost",
        config
      );
      setPosts(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function handleAllJob(e) {
    e.preventDefault();
    try {
      setDisplay("job");
      const response = await axios.get(
        "http://localhost:8080/JP/Job/getallJob",
        config
      );
      setJobs(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function handleAllAppliedJob(e) {
    e.preventDefault();
    try {
      setDisplay("status");
      const response = await axios.get(
        "http://localhost:8080/JP/Job/getallAppliedJob",
        localStorage.getItem("username"),
        config
      );
      setAppliedJobs(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }
  useEffect(() => {
    handleAllAppliedJob();
  }, []);
  async function handleViewJob(jobId) {
    try {
      const response = await axios.get(
        `http://localhost:8080/JP/Job/getJobbyId/${jobId}`,
        config
      );
      setJobDetails(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
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

  const handleApplyJob = async (jobId) => {
    try {
      const response = await axios.post(
        "http://localhost:8080/JP/Job/applyJob",
        jobId,
        config
      );
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
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
          <div onClick={handleAllJob}>
            <img id="icon" src="./job.png" alt="Job" />
            <p>Job</p>
          </div>
          <div onClick={handleAllAppliedJob}>
            <img id="icon" src="./notification.png" alt="Status" />
            <p>Status</p>
          </div>
          <div>
            <img id="icon" src="./profile.png" alt="Profile" />
            <p>Profile</p>
          </div>
        </div>
      </div>
      <div className="details">
        <div className="applicantDetails">
          <p>
            {applicant.firstname}&nbsp;{applicant.lastname}
          </p>
          <p>{applicant.headline}</p>
        </div>
        <div className="contain">
          {display == "post" && (
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
          {display == "job" && (
            <div className="jobDetails">
              <div>
                {jobs.length > 0 ? (
                  jobs.map((job) => (
                    <div
                      key={job.jobId}
                      className="job"
                      onClick={() => handleViewJob(job.jobId)}
                    >
                      <img src={job.logo} alt="Company logo" />
                      <div>
                        <p>{job.jobTitle}</p>
                        <p>{job.company}</p>
                        <p>
                          {job.city},{job.state},{job.country}
                        </p>
                      </div>
                    </div>
                  ))
                ) : (
                  <p>No jobs are available</p>
                )}
              </div>
              <div>
                {jobDetails && (
                  <div>
                    <p>{jobDetails.jobTitle}</p>
                    <p>{jobDetails.company}</p>
                    <p>{jobDetails.jobType}</p>
                    <p>
                      {jobDetails.city},{jobDetails.state},{jobDetails.country}
                    </p>
                    <p>{jobDetails.experience}</p>
                    <p>{jobDetails.description}</p>
                    <div>
                      <button
                        onClick={() => handleApplyJob(jobDetails.jobId)}
                        disabled={appliedJobs.has(jobDetails.jobId)}
                      >
                        {appliedJobs.has(jobDetails.jobId)
                          ? "Applied"
                          : "Apply"}
                      </button>
                      <button>Save</button>
                    </div>
                  </div>
                )}
              </div>
            </div>
          )}
          {display == "status" && (
            <div className="jobStatus">
              {appliedJobs.length > 0 ? (
                jobs.map((job) => (
                  <div key={job.jobId} className="job">
                    <img src={job.logo} alt="Company logo" />
                    <div>
                      <p>{job.jobTitle}</p>
                      <p>{job.company}</p>
                      <p>{job.appliedDate}</p>
                      <p>{job.status}</p>
                    </div>
                  </div>
                ))
              ) : (
                <p>No jobs status are available</p>
              )}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Search;

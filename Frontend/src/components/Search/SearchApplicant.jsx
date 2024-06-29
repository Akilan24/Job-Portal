import React, { useState, useEffect } from "react";
import axios from "axios";
import "./SearchApplicant.css";
import { useNavigate, useParams } from "react-router-dom";

function Search() {
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);
  const [jobs, setJobs] = useState([]);
  const [jobDetails, setJobDetails] = useState(null);
  const [display, setDisplay] = useState("");
  const [search, setSearch] = useState("");
  const [applicant, setApplicant] = useState("");
  const [appliedJobs, setAppliedJobs] = useState(new Set());
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  const [postDetails, setPostDetails] = useState({
    emailId: localStorage.getItem("accesstoken"),
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
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function handleAllJob() {
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

  async function handleAllAppliedJob() {
    try {
      setDisplay("status");
      const response = await axios.get(
        "http://localhost:8080/JP/Job/getallAppliedJo",
        { email: localStorage.getItem("email") },
        config
      );
      setAppliedJobs(new Set(response.data.map((job) => job.jobId)));
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
        `http://localhost:8080/JP/Job/getJobbyid/${jobId}`,
        config
      );
      setJobDetails(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  useEffect(() => {
    handleProfileDetails();
  }, []);

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
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function handleProfileDetails() {
    try {
      setDisplay("profile");
      const response = await axios.get(
        `http://localhost:8080/JP/User/getApplicant/${localStorage.getItem(
          "email"
        )}`,
        config
      );
      setApplicant(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
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

  const handleApplyJob = async (jobId) => {
    try {
      const response = await axios.post(
        `http://localhost:8080/JP/Job/applyJob/${jobId}/${localStorage.getItem(
          "email"
        )}`,
        config
      );
      setAppliedJobs(new Set([...appliedJobs, jobId]));
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  };
  async function fetchJobs(e) {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/User/getJobbysearch/${search}`,
        config
      );
      setJobs(response.data);
      console.log(response);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }
  const [educationPost, setEducationPost] = useState({
    degree: "",
    startDate: "",
    endDate: "",
  });
  const [education, setEducation] = useState([]);
  const [update, setUpdate] = useState(0);

  function handleEducationChange(e) {
    const { name, value } = e.target;
    setEducationPost({ ...educationPost, [name]: value });
  }

  useEffect(() => {
    fetchEducation();
  }, []);

  async function fetchEducation() {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/User/getallEducation/${localStorage.getItem(
          "email"
        )}`,
        config
      );
      setEducation(response.data);
      console.log(response);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function handleSubmitEducation(e) {
    e.preventDefault();
    try {
      const response = await axios.post(
        `http://localhost:8080/CS/User/addEducation/${localStorage.getItem(
          "email"
        )}`,
        educationPost,
        config
      );
      setEducationPost({
        degree: "",
        startDate: "",
        endDate: "",
      });
      fetchEducation();
      console.log(response.data);
      toggleEducationForm();
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function getEducation(e) {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/User/getEducation/${localStorage.getItem(
          "username"
        )}/${e.target.value}`,
        config
      );
      setEducationPost(response.data);
      setUpdate(1);
      toggleEducationForm();
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function updateEducation(e) {
    e.preventDefault();
    try {
      const response = await axios.put(
        `http://localhost:8080/CS/User/updateEducation/${localStorage.getItem(
          "email"
        )}`,
        educationPost,
        config
      );
      fetchEducation();
      setEducationPost({
        degree: "",
        startDate: "",
        endDate: "",
      });
      setUpdate(0);
      toggleEducationForm();
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function deleteEducation(e) {
    try {
      const response = await axios.delete(
        `http://localhost:8080/CS/User/deleteEducation/${localStorage.getItem(
          "email"
        )}/${e.target.value}`,
        config
      );
      fetchEducation();
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  function toggleEducationForm() {
    const element = document.getElementById("tc");
    if (element.style.display === "none") {
      element.style.display = "flex";
    } else {
      element.style.display = "none";
    }
  }

  const [experiencePost, setExperiencePost] = useState({
    company: "",
    startDate: "",
    endDate: "",
  });
  const [experience, setExperience] = useState([]);
  const [update1, setUpdate1] = useState(0);

  function handleExperienceChange(e) {
    const { name, value } = e.target;
    setExperiencePost({ ...experiencePost, [name]: value });
  }

  useEffect(() => {
    fetchExperience();
  }, []);

  async function fetchExperience() {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/User/getallExperience/${localStorage.getItem(
          "email"
        )}`,
        config
      );
      setExperience(response.data);
      console.log(response);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function handleSubmitExperience(e) {
    e.preventDefault();
    try {
      const response = await axios.post(
        `http://localhost:8080/CS/User/addExperience/${localStorage.getItem(
          "email"
        )}`,
        experiencePost,
        config
      );
      setExperiencePost({
        company: "",
        startDate: "",
        endDate: "",
      });
      fetchExperience();
      console.log(response.data);
      toggleExperienceForm();
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function getExperience(e) {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/User/getExperience/${localStorage.getItem(
          "username"
        )}/${e.target.value}`,
        config
      );
      setExperiencePost(response.data);
      setUpdate1(1);
      toggleExperienceForm();
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function updateExperience(e) {
    e.preventDefault();
    try {
      const response = await axios.put(
        `http://localhost:8080/CS/User/updateExperience/${localStorage.getItem(
          "email"
        )}`,
        experiencePost,
        config
      );
      fetchExperience();
      setExperiencePost({
        company: "",
        startDate: "",
        endDate: "",
      });
      setUpdate1(0);
      toggleExperienceForm();
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function deleteExperience(e) {
    try {
      const response = await axios.delete(
        `http://localhost:8080/CS/User/deleteExperience/${localStorage.getItem(
          "email"
        )}/${e.target.value}`,
        config
      );
      fetchExperience();
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  function toggleExperienceForm() {
    const element = document.getElementById("tc1");
    if (element.style.display === "none") {
      element.style.display = "flex";
    } else {
      element.style.display = "none";
    }
  }
  return (
    <div className="searchApplicant">
      <div id="tabs">
        <div id="leftpane">
          <img id="logo" src="./logo.png" alt="Logo" />
          <input
            type="text"
            onChange={(e) => setSearch(e.target.value)}
            placeholder="Search by title, skill, or company"
          />
          <button id="search" onClick={fetchJobs}>
            Search
          </button>
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
          <div onClick={handleProfileDetails}>
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
                  <p id="none">No posts are available</p>
                )}
              </div>
            </div>
          )}
          {display === "job" && (
            <div className="jobDetails">
              <div className="container1">
                {jobs.length > 0 ? (
                  jobs.map((job) => (
                    <div key={job.jobId} className="jobContain">
                      <div id="detail">
                        <p id="title">{job.jobTitle}</p>
                        <p id="company">{job.company}</p>
                        <p id="location">
                          {job.city}, {job.state}, {job.country}
                        </p>
                      </div>
                      <button onClick={() => handleViewJob(job.jobId)}>
                        View
                      </button>
                    </div>
                  ))
                ) : (
                  <p id="none">No jobs are available</p>
                )}
              </div>

              {jobDetails && (
                <div className="container2">
                  <p id="title">{jobDetails.jobTitle}</p>
                  <p id="company">{jobDetails.company}</p>
                  <p id="location">
                    {jobDetails.city}, {jobDetails.state}, {jobDetails.country}
                  </p>
                  <p>Job Type: {jobDetails.jobType}</p>
                  <p>Job Category: {jobDetails.jobCategory}</p>
                  <p>Skills: {jobDetails.requiredSkills}</p>
                  <p>Experience: {jobDetails.experience}</p>
                  <p>Description: {jobDetails.jobDescription}</p>
                  <p>Salary: {jobDetails.salary}</p>
                  <p>Posted Date: {jobDetails.postedDate}</p>
                  <button
                    onClick={() => handleApplyJob(jobDetails.jobId)}
                    disabled={appliedJobs.has(jobDetails.jobId)}
                  >
                    {appliedJobs.has(jobDetails.jobId) ? "Applied" : "Apply"}
                  </button>
                </div>
              )}
            </div>
          )}
          {display === "status" && (
            <div className="jobStatus">
              {Array.from(appliedJobs).length > 0 ? (
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
                <p id="none">No job status is available</p>
              )}
            </div>
          )}
          {display === "profile" && (
            <div className="profileDetails">
              {applicant && (
                <div className="profile">
                  <p>{applicant.name}</p>
                  <p>{applicant.headline}</p>
                  <p>{applicant.emailId}</p>
                  <p>{applicant.workStatus}</p>
                  {applicant.experience.length > 0 && (
                    <div className="experience">
                      {applicant.experience.map((exp, index) => (
                        <div key={index}>
                          <p>{exp.company}</p>
                          <p>{exp.position}</p>
                          <p>{exp.startDate}</p>
                          <p>{exp.endDate}</p>
                        </div>
                      ))}
                    </div>
                  )}
                  <div className="stc">
                    <div className="savedEducationclass">
                      <div id="stdiv">
                        {education.map((item, index) => (
                          <div id="st" key={index} className="education-item">
                            <div className="input">
                              <p>
                                <strong>Degree:</strong> {item.degree}
                              </p>
                              <p>
                                <strong>Start Date:</strong> {item.startDate}
                              </p>
                              <p>
                                <strong>End Date:</strong> {item.endDate}
                              </p>
                            </div>
                            <div className="button">
                              <button
                                id="s"
                                value={item.degree}
                                onClick={(e) => getEducation(e)}
                              >
                                Edit
                              </button>
                              <button
                                id="c"
                                value={item.degree}
                                onClick={(e) => deleteEducation(e)}
                              >
                                Delete
                              </button>
                            </div>
                          </div>
                        ))}
                        <div>
                          <button id="c" onClick={() => navigate(-1)}>
                            Cancel
                          </button>
                          <button id="s" onClick={toggleEducationForm}>
                            Add
                          </button>
                        </div>
                      </div>
                    </div>
                    <form
                      id="tc"
                      className="educationclass"
                      onSubmit={
                        update > 0 ? updateEducation : handleSubmitEducation
                      }
                      style={{ display: "none" }}
                    >
                      <div>
                        <h2>{update > 0 ? "Update" : "Add"} Education</h2>
                      </div>
                      <div>
                        <label htmlFor="degree">Degree</label>
                        <input
                          type="text"
                          name="degree"
                          id="degree"
                          value={educationPost.degree}
                          onChange={handleEducationChange}
                          required
                        />
                      </div>
                      <div>
                        <label htmlFor="startDate">Start Date</label>
                        <input
                          type="date"
                          name="startDate"
                          id="startDate"
                          value={educationPost.startDate}
                          onChange={handleEducationChange}
                          required
                        />
                      </div>
                      <div>
                        <label htmlFor="endDate">End Date</label>
                        <input
                          type="date"
                          name="endDate"
                          id="endDate"
                          value={educationPost.endDate}
                          onChange={handleEducationChange}
                          required
                        />
                      </div>

                      <div id="submit">
                        <button
                          id="c"
                          type="button"
                          onClick={toggleEducationForm}
                        >
                          Cancel
                        </button>
                        <button id="s" type="submit">
                          Save
                        </button>
                      </div>
                    </form>
                  </div>
                  <div className="stc1">
                    <div className="savedExperienceclass">
                      <div id="stdiv">
                        {experience.map((item, index) => (
                          <div id="st" key={index} className="experience-item">
                            <div className="input">
                              <p>
                                <strong>Company:</strong> {item.company}
                              </p>
                              <p>
                                <strong>Start Date:</strong> {item.startDate}
                              </p>
                              <p>
                                <strong>End Date:</strong> {item.endDate}
                              </p>
                            </div>
                            <div className="button">
                              <button
                                id="s"
                                value={item.company}
                                onClick={(e) => getExperience(e)}
                              >
                                Edit
                              </button>
                              <button
                                id="c"
                                value={item.company}
                                onClick={(e) => deleteExperience(e)}
                              >
                                Delete
                              </button>
                            </div>
                          </div>
                        ))}
                        <div>
                          <button id="c" onClick={() => navigate(-1)}>
                            Cancel
                          </button>
                          <button id="s" onClick={toggleExperienceForm}>
                            Add
                          </button>
                        </div>
                      </div>
                    </div>
                    <form
                      id="tc1"
                      className="experienceclass"
                      onSubmit={
                        update1 > 0 ? updateExperience : handleSubmitExperience
                      }
                      style={{ display: "none" }}
                    >
                      <div>
                        <h2>{update1 > 0 ? "Update" : "Add"} Experience</h2>
                      </div>
                      <div>
                        <label htmlFor="company">Company</label>
                        <input
                          type="text"
                          name="company"
                          id="company"
                          value={experiencePost.company}
                          onChange={handleExperienceChange}
                          required
                        />
                      </div>
                      <div>
                        <label htmlFor="startDate">Start Date</label>
                        <input
                          type="date"
                          name="startDate"
                          id="startDate"
                          value={experiencePost.startDate}
                          onChange={handleExperienceChange}
                          required
                        />
                      </div>
                      <div>
                        <label htmlFor="endDate">End Date</label>
                        <input
                          type="date"
                          name="endDate"
                          id="endDate"
                          value={experiencePost.endDate}
                          onChange={handleExperienceChange}
                          required
                        />
                      </div>

                      <div id="submit">
                        <button
                          id="c"
                          type="button"
                          onClick={toggleExperienceForm}
                        >
                          Cancel
                        </button>
                        <button id="s" type="submit">
                          Save
                        </button>
                      </div>
                    </form>
                  </div>
                </div>
              )}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Search;

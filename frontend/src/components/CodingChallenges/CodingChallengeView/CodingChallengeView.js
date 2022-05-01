import { Box, Tab, Tabs, Typography } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import PropTypes from 'prop-types';
import './CodingChallengeView.css';
import CodingChallengeSolve from "./CodingChallangeSolve";
import NavBar from "../../NavBar/NavBar";
import CodindChallengeSubmissions from "./CodingChallengeSubmissions"
import CodingChallengeInformation from "./CodingChallengeInformation";
import { CodingChallengeApi } from "../CodingChallengeApi";

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
    return {
      id: `simple-tab-${index}`,
      'aria-controls': `simple-tabpanel-${index}`,
    };
  }

function CodingChallengeView() {

  const codingChallengeApi = new CodingChallengeApi();
  const[codingChallenge, setCodingChallenge] = useState({
    challenge_id: -1,
    question: "",
    title: "",
    difficulity: -1,
    points: -1,
    attempt_number:  -1,
    solved_number: -1,
    solution: "",
    publicity: -1
  });

  const[info, setInfo] = useState({
    authorName: "",
    categories: []
  })

  function fetchCodingQuestion(){
      codingChallengeApi.getCodingChallenge().then(data => setCodingChallenge(data));
  }

  function fetchCodingChallengeInformation() {
      codingChallengeApi.getCodingChallengeInformation()
                        .then(data => {setInfo(data);});
}
  useEffect(() => {
    fetchCodingQuestion();
    fetchCodingChallengeInformation();
},[]);



    const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

    return(
        <div className= "coding-challenge-view-root">

          <NavBar/>
          
            <Box sx={{ width: '100%' }}>
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
          <Tab label="Solve Challenge" {...a11yProps(0)} />
          <Tab label="Submissions" {...a11yProps(1)} />
          <Tab label="Information" {...a11yProps(2)} />
        </Tabs>
      </Box>
      <TabPanel value={value} index={0}>
        <CodingChallengeSolve title = {codingChallenge.title} description = {codingChallenge.question}/>
      </TabPanel>
      <TabPanel value={value} index={1}>
        <CodindChallengeSubmissions/>
      </TabPanel>
      <TabPanel value={value} index={2}>
        <CodingChallengeInformation title = {codingChallenge.title}
                                    difficulty = {codingChallenge.difficulty}
                                    point = {codingChallenge.points}
                                    attemptNo = {codingChallenge.attempt_number}
                                    acceptedNo = {codingChallenge.solved_number}
                                    info = {info}
        
        />
      </TabPanel>
    </Box>

        </div>

    );
}

export default CodingChallengeView;
import { Box, Tab, Tabs, Typography } from "@material-ui/core";
import React from "react";
import PropTypes from 'prop-types';
import './NonCodingChallengeView.css';
import NonCodingChallengeSolve from "./NonCodingChallangeSolve";
import NavBar from "../../NavBar/NavBar";
import NonCodindChallengeSubmissions from "./NonCodingChallengeSubmissions"
import NonCodingChallengeInformation from "./NonCodingChallengeInformation";
import OtherAnswerList from "./OtherAnswersList";

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

const otherAnswers= 
[
  {
  img:"./img.png",
  name: "X coder",  
  positions: "Google engineer",
  answer: "Answer: There are 3 types of relationships in Database One-to-one: One table has a relationship with another table having the similar kind of column. Each primary key relates to only one or no record in the related table. One-to-many: One table has a relationship with another table that has primary and foreign key relations. The primary key table contains only one record that relates to none, one or many records in the related table. Many-to-many: Each record in both the tables can relate to many numbers of records in another table."
  },

  {
  img:"./img.png",
  name: "Y coder",  
  positions: "FB engineer",
  answer: "Answer: There are 3 types of relationships in Database One-to-one: One table has a relationship with another table having the similar kind of column. Each primary key relates to only one or no record in the related table. One-to-many: One table has a relationship with another table that has primary and foreign key relations. The primary key table contains only one record that relates to none, one or many records in the related table. Many-to-many: Each record in both the tables can relate to many numbers of records in another table."
  },
  {
    img:"./img.png",
    name: "Z coder",  
    positions: "Amazon engineer",
    answer: "Answer: There are 3 types of relationships in Database One-to-one: One table has a relationship with another table having the similar kind of column. Each primary key relates to only one or no record in the related table. One-to-many: One table has a relationship with another table that has primary and foreign key relations. The primary key table contains only one record that relates to none, one or many records in the related table. Many-to-many: Each record in both the tables can relate to many numbers of records in another table."
    }
  ];

function a11yProps(index) {
    return {
      id: `simple-tab-${index}`,
      'aria-controls': `simple-tabpanel-${index}`,
    };
  }

function CodingChallengeView() {

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
          <Tab label="Submission" {...a11yProps(1)} />
          <Tab label="Information" {...a11yProps(2)} />
          <Tab label="Other Answers" {...a11yProps(3)} />
        </Tabs>
      </Box>
      <TabPanel value={value} index={0}>
        <NonCodingChallengeSolve/>
      </TabPanel>
      <TabPanel value={value} index={1}>
        <NonCodindChallengeSubmissions/>
      </TabPanel>
      <TabPanel value={value} index={2}>
        <NonCodingChallengeInformation/>
      </TabPanel>
      <TabPanel value={value} index={3}>
        <OtherAnswerList content={otherAnswers}></OtherAnswerList>
      </TabPanel>
    </Box>

        </div>

    );
}

export default CodingChallengeView;
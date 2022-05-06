import React, {useState} from "react";
import PropTypes from 'prop-types';
import NavBar from "../../NavBar/NavBar";
import Autocomplete from '@mui/material/Autocomplete';
import Stack from '@mui/material/Stack';
import { Box, FormControl, TextField, MenuItem, Typography, Button, Select} from "@material-ui/core";
import Grid from '@mui/material/Grid';
import { AttendInterviewAPI } from "./AttendInterviewAPI";

function startInterview()
{
    window.location.href = "http://localhost:3000/AttendInterview";
}
function StartInterview(props) {
//Get interview
let interviewID = localStorage.getItem('interviewID');
const attendInterviewAPI = new AttendInterviewAPI();

let company =  attendInterviewAPI.getCompany(interviewID);
let interview =  attendInterviewAPI.getInterview(interviewID);
console.log(company);
console.log(interview);

  let isSet = localStorage.getItem('interviewID');
  const [username, setUsername] = React.useState('');
  let location = "Company location";
  let position = "Position";
  let duration = "234 min";

  async function handleSubmit() {

    window.location.href = "http://localhost:3000/home";

}

    return(
        <div>
            <NavBar></NavBar>
            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
                sx={{m:3}}
            >
                <Grid item>
                <Typography variant="h3" component="h3">
                   Interview
                </Typography>
                </Grid>
            </Grid>
            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center" 
                sx={{mb:6}}>
                <Grid item xs={6}>
                    <Grid container  direction="row" justifyContent="center" alignItems="center">
                        <Typography variant="h4" component="h4">
                        {company}
                        </Typography>
                    </Grid>
                </Grid>
                <Grid item xs={6}>
                    <Grid container  direction="row" justifyContent="center" alignItems="center">
                        <Typography variant="h4" component="h4">
                            {location}
                        </Typography>
                    </Grid>
                </Grid>
            </Grid>
            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center" >
                <Grid item xs={6}>
                    <Grid container  direction="row" justifyContent="center" alignItems="center">
                        <Typography variant="h5" component="h5">
                            {position}
                        </Typography>
                    </Grid>
                </Grid>
                <Grid item xs={6}>
                    <Grid container  direction="row" justifyContent="center" alignItems="center">
                        <Typography variant="h5" component="h5">
                            {duration}
                        </Typography>
                    </Grid>
                </Grid>
            </Grid>
            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center" 
                sx={{mt:11}}>
                <Grid item xs={6}>
                    <Grid container  direction="row" justifyContent="center" alignItems="center">
                    <Button
                        variant="contained"
                        color="secondary"
                        onClick= {startInterview}
                        > START
                    </Button>
                    </Grid>
                </Grid>
            </Grid>
            
        </div>
    );
    
}

export default StartInterview;
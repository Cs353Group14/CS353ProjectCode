import React, {useState, useEffect} from "react";
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
let interviewID = localStorage.getItem('startInterview');
const attendInterviewAPI = new AttendInterviewAPI();

const[comp, setComp] = useState([]);
const[inter, setInter] = useState([]);


    function fetchInterview() {
        attendInterviewAPI.getCompany(interviewID).then(data => {
            setComp(data)});;
        attendInterviewAPI.getInterview(interviewID).then(data => {
            setInter(data)});;

    }
    
    useEffect(() => {
        fetchInterview();
    },[]);



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
                        {comp.name}
                        </Typography>
                    </Grid>
                </Grid>
                <Grid item xs={6}>
                    <Grid container  direction="row" justifyContent="center" alignItems="center">
                        <Typography variant="h4" component="h4">
                            {comp.location}
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
                            {inter.position}
                        </Typography>
                    </Grid>
                </Grid>
                <Grid item xs={6}>
                    <Grid container  direction="row" justifyContent="center" alignItems="center">
                        <Typography variant="h5" component="h5">
                            {inter.duration} min
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
                        onClick= {()=> startInterview()}
                        > START
                    </Button>
                    </Grid>
                </Grid>
            </Grid>
            
        </div>
    );
    
}

export default StartInterview;
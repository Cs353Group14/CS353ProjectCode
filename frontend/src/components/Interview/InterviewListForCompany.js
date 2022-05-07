import React, {useEffect, useState} from "react";
import PropTypes from 'prop-types';
import NavBar from "../NavBar/NavBar";
import Autocomplete from '@mui/material/Autocomplete';
import Stack from '@mui/material/Stack';
import { Box, FormControl, TextField, MenuItem, Typography, Button, Select} from "@material-ui/core";
import Grid from '@mui/material/Grid';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {InterviewAPI} from './InterviewAPI'

function createData(position, dateCreated) {
    return { position, dateCreated };
  }
  
  function seeCandidates(id)
  {
    console.log(id);
    localStorage.setItem('Candidates_Of_Interview', id);
    window.location.href = "http://localhost:3000/ViewCandidates";

  }  

function InterviewListForCompany(props) {
    let interviewList = [];
    const[rows, setRows] = useState([]);
    const interviewAPI = new InterviewAPI();

    function fetchInterviews() {
        interviewAPI.getInterviewListCompany().then(data => {
            setRows(data)});;

    }
    
    useEffect(() => {
        fetchInterviews();
    },[]);

  
    return(
        <div>
            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
            >
                <Grid item>
                <Typography variant="h3" component="h3">
                    Interviews
                </Typography>
                </Grid>
            </Grid>
            
            <Grid container
                direction="row"
                justifyContent="center"
                alignItems="center"
                sx={{ mt: 8 , mb: 8, ml: 3, mr:3}}>
                    <Grid item xs={8} >
            <TableContainer component={Paper}>
            <Table  aria-label="simple table">
                <TableHead>
                <TableRow>
                    <TableCell align="left">Position</TableCell>
                    <TableCell align="center">Duration</TableCell>
                    <TableCell align="right">See Candidates</TableCell>
                </TableRow>
                </TableHead>
                <TableBody>
                {rows.map((row) => (
                    <TableRow
                    key = {row.interview_id}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell align="left">{row.position}</TableCell>
                    <TableCell align="center">{row.duration}</TableCell>
                    <TableCell  id={row.interview_id} align="right">
                    <Button
                        variant="contained"
                        color="default"
                        id={row.interview_id}
                        onClick={(e) => seeCandidates(row.interview_id)}
                        > See Candidates
                    </Button>
                    </TableCell>
                    </TableRow>
                ))}
                </TableBody>
            </Table>
        </TableContainer>
        </Grid>
        </Grid>
        </div>
    );
    
}

export default InterviewListForCompany;
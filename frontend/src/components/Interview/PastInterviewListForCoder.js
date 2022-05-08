import React, {useState, useEffect} from "react";
import PropTypes from 'prop-types';
import NavBar from "../NavBar/NavBar";
import Autocomplete from '@mui/material/Autocomplete';
import Stack from '@mui/material/Stack';
import { Typography, Button, Select} from "@material-ui/core";
import Grid from '@mui/material/Grid';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {InterviewAPI} from './InterviewAPI'

function createData(company, location, position, date, result) {
    return { company, location, position, date, result };
  }
  
  const rows = [
    createData('Company1', 'Location1','Position1', '12/12/2021', 'Hire'),
    createData('Company1', 'Location1','Position1', '12/11/2021', 'Hire'),
    createData('Company1', 'Location1','Position1', '12/10/2021', 'Hire'),
    createData('Company1', 'Location1','Position1', '12/9/2021', 'Hire'),
    createData('Company1', 'Location1','Position1', '12/8/2021', 'Hire'),
    createData('Company1', 'Location1','Position1', '12/7/2021', 'Hire')
  ];


function PastInterviewListForCoder(props) {
    const[rows, setRows] = useState([]);
    const interviewAPI = new InterviewAPI();

    function fetchPastInterviews() {
        interviewAPI.getPastInterviewList().then(data => {
            setRows(data)});;

    }
    
    useEffect(() => {
        fetchPastInterviews();
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
                    Past Interviews
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
                    <TableCell align="left">Company</TableCell>
                    <TableCell align="center">Duration</TableCell>
                    <TableCell align="center">Position</TableCell>
                    <TableCell align="center">Start time</TableCell>
                    <TableCell align="center">End time</TableCell>
                    <TableCell align="right">Result</TableCell>
                </TableRow>
                </TableHead>
                <TableBody>
                {rows.map((row) => (
                    <TableRow
                    key={row.interviewId}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell align="left">{row.companyName}</TableCell>
                    <TableCell align="center">{row.duration}</TableCell>
                    <TableCell align="center">{row.position}</TableCell>
                    <TableCell align="center">{row.startTime}</TableCell>
                    <TableCell align="center">{row.endTime}</TableCell>
                    <TableCell align="center">{row.interviewResult && row.interviewResult}{!row.interviewResult && 'Not out yet'}</TableCell>
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

export default PastInterviewListForCoder;
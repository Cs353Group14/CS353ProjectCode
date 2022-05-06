import React, {useState} from "react";
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
    const interviewAPI = new InterviewAPI();

    if(localStorage.getItem('userType') === 1)
    {
        let interviewList = interviewAPI.getPastInterviewList();
    }
  
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
                    <TableCell align="center">Location</TableCell>
                    <TableCell align="center">Position</TableCell>
                    <TableCell align="center">Date</TableCell>
                    <TableCell align="right">Result</TableCell>
                </TableRow>
                </TableHead>
                <TableBody>
                {rows.map((row) => (
                    <TableRow
                    key={row.date}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell align="left">{row.company}</TableCell>
                    <TableCell align="center">{row.location}</TableCell>
                    <TableCell align="center">{row.position}</TableCell>
                    <TableCell align="center">{row.date}</TableCell>
                    <TableCell align="center">{row.result}</TableCell>
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
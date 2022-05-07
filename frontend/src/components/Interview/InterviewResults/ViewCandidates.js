import React, {useState, useEffect} from "react";
import PropTypes from 'prop-types';
import NavBar from "../../NavBar/NavBar";
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
import {InterviewResultAPI} from './InterviewResultAPI'

function createData(name) {
    return { name };
  }
  /*
  const rows = [
    createData('Name1'),
    createData('Name2'),
    createData('Name3'),
    createData('Name4'),
    createData('Name5'),
    createData('Name6'),
    createData('Name7'),
    createData('Name8')
  ];
  */
  function seeResultsOfCandidate()
  {
    window.location.href = "http://localhost:3000/ResultOfCandidate";
  }

function ViewCandidates(props) {

  const [position, setPosition] = React.useState('');
  const [timeUnit, setTimeUnit] = useState('min');
  const [duration, setDuration] = useState(timeUnit[0].value);
  let interviewId = localStorage.getItem('Candidates_Of_Interview');

  const[rows, setRows] = useState([]);
    const interviewResultAPI = new InterviewResultAPI();

    function fetchCandidates() {
        interviewResultAPI.getCandidiates(interviewId).then(data => {
            setRows(data)});;
            console.log(rows)

    }
    
    useEffect(() => {
        fetchCandidates();
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
                    Candidates
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
                    <TableCell align="left">Name</TableCell>
                    <TableCell align="right">See results</TableCell>
                </TableRow>
                </TableHead>
                <TableBody>
                {rows.map((row) => (
                    <TableRow
                    key={row.name}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell align="left">{row.name}</TableCell>
                    <TableCell align="right">
                    <Button
                        variant="contained"
                        color="default"
                        onClick={seeResultsOfCandidate}
                        > See Results
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

export default ViewCandidates;
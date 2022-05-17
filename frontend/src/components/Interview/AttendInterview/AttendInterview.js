import React, {useState, useEffect} from "react";
import PropTypes from 'prop-types';
import NavBar from "../../NavBar/NavBar";
import {  Typography, Button, Box} from "@material-ui/core";
import Grid from '@mui/material/Grid';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Countdown from 'react-countdown';
import { AttendInterviewAPI } from "./AttendInterviewAPI";


function createData(type, title) {
    return { type, title };
  }
  
  const rows = [
    createData('Non coding question', 'Title1'),
    createData('Non coding question', 'Title2'),
    createData('Non coding question', 'Title3'),
    createData('Coding question', 'Title4'),
    createData('Coding question', 'Title5'),
    createData('Coding question', 'Title6')
  ];

function AttendInterview(props) {

    let interviewID = localStorage.getItem('startInterview');
    const attendInterviewAPI = new AttendInterviewAPI();

    const[nonCoding, setNonCoding] = useState([]);
    const[coding, setCoding] = useState([]);

    function fetchInterviewQuestions() {
        attendInterviewAPI.getNonCodingQuestionsOfInterview(interviewID).then(data => {
            setNonCoding(data)});;
        attendInterviewAPI.getCodingQuestionsOfInterview(interviewID).then(data => {
            setCoding(data)});;

    }
    
    useEffect(() => {
        fetchInterviewQuestions();
    },[]);


  
    function handleSubmit() 
    {
        localStorage.setItem('menuId', 12);
        window.location.href = "http://localhost:3000/home";
    }

    function solveCodingQuestion(questionId)
    {
        //localStorage.setItem('menuId', 1);
        localStorage.setItem('codingId', questionId );
        window.location.href = "http://localhost:3000/coding-challenges/"+questionId;

    }

    function solveNonCodingQuestion(questionId)
    {
       /// localStorage.setItem('menuId', 2);
        localStorage.setItem('nonCodingId', questionId );
        window.location.href = "http://localhost:3000/non-coding-challenges/" +questionId;;
    }

    return(
        <div>
            <NavBar></NavBar>
            <div >
            <Grid
                container
                direction="row"
                justifyContent="center"
                sx={{ mt: 8 }}
            >
                <Grid item xs={6} >
                    
                </Grid>
                <Grid item xs={6}>
                        <Grid container justifyContent="flex-end">
                        <Box  m={2}>
                        <Button  variant="contained"
                                color="primary" 
                                onClick={()=> handleSubmit()}>Finish
                                </Button>
                            </Box>
                            </Grid>
                            
         
                </Grid>
            </Grid>
            </div>
            <TableContainer component={Paper}>
            <Table  aria-label="simple table">
                <TableHead>
                </TableHead>
                <TableBody>
                {nonCoding.map((row) => (
                    <TableRow
                    key={row.non_challenge_id}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell component="th" scope="row">
                                <Typography variant="h5" component="div">
                                {row.title}
                                </Typography>
                                <Typography variant="body2">
                                Non coding question
                                </Typography>
                                <Button size="small" onClick={()=> solveNonCodingQuestion(row.non_challenge_id)}>Solve</Button>
                    </TableCell>
                    </TableRow>
                ))}
                {coding.map((row) => (
                    <TableRow
                    key={row.challenge_id}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell component="th" scope="row">

                                <Typography variant="h5" component="div">
                                {row.title}
                                </Typography>
                                <Typography variant="body2">
                                Coding question
                                </Typography>
                                <Button size="small" onClick={()=> solveCodingQuestion(row.challenge_id)}>Solve</Button>
                    </TableCell>
                    </TableRow>
                ))}
                </TableBody>
            </Table>
        </TableContainer>
        </div>
    );
    
}

export default AttendInterview;
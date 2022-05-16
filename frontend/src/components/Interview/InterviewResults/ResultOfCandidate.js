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
import EvaluateDialog from './EvaluateDialog'
import {InterviewResultAPI} from './InterviewResultAPI'
import {AttendInterviewAPI} from '../AttendInterview/AttendInterviewAPI'


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

function ResultOfCandidate(props) {

    let interviewID = localStorage.getItem('Candidates_Of_Interview');
    const attendInterviewAPI = new AttendInterviewAPI();
    const interviewResultAPI = new InterviewResultAPI();


    const[nonCoding, setNonCoding] = useState([]);
    const[coding, setCoding] = useState([]);
    const[finished, setFinished] = useState(0);

    function fetchInterviewQuestions() {
        attendInterviewAPI.getNonCodingQuestionsOfInterview(interviewID).then(data => {
            setNonCoding(data)});;
        attendInterviewAPI.getCodingQuestionsOfInterview(interviewID).then(data => {
            setCoding(data)});;
        interviewResultAPI.checkIfFinished(localStorage.getItem('Candidates_Of_Interview'), localStorage.getItem('Candidate')).then(data => {
            setFinished(data)});;

    }
    useEffect(() => {
        fetchInterviewQuestions();
    },[]);


  function seeNonCodingQuestionSolution(id )
  {
      
    localStorage.setItem('isInterview', true);
    localStorage.setItem('nonCodingId', id);
  }

  function seeCodingQuestionSolution(id)
  {
    localStorage.setItem('isInterview', true);
    localStorage.setItem('codingId', id);
  }
   function handleSubmit(submit)
   {

    alert(submit);
    setOpenDialog(false);

    const newResult = {
        interviewId: interviewID,
        interviewResult: submit,
        userId: localStorage.getItem('Candidate')
      }
    interviewResultAPI.evaluateCandidate(newResult);
   // window.location.href = "http://localhost:3000/home";
  }


//Handle dialogs
const [openDialogName, setOpenDialog] = React.useState(null);

const openDialog = () => {
    setOpenDialog(true);
};

const closeDialog = () => {
    setOpenDialog(false);
};

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
                            {finished > 0 &&
                        <Button  variant="contained"
                                color="primary" onClick={openDialog}>Evaluate
                                </Button>
}
{ finished == 0 &&
    <div>Interview has not finished yet</div>
}
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
                                <Button size="small" onClick={()=> seeNonCodingQuestionSolution(row.non_challenge_id)}>See submission</Button>

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
                                <Button size="small" onClick={()=> seeCodingQuestionSolution(row.challenge_id)}>See submission</Button>
                    </TableCell>
                    </TableRow>
                ))}
                </TableBody>
            </Table>
        </TableContainer>
        <EvaluateDialog open={openDialogName === true} handleClose={closeDialog} handleSubmit={handleSubmit}></EvaluateDialog>
        </div>
    );
    
}

export default ResultOfCandidate;
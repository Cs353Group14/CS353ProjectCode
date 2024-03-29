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
import {CreateNewInterviewAPI} from './CreateInterviewAPI'
import { AttendInterviewAPI } from "../AttendInterview/AttendInterviewAPI";
import { MessageType } from "../../Common/Message";
import { ToastContainer,toast } from 'react-toastify';

const timeUnits = [
  {
    value: 'min'
  },
  {
    value: 'hour(s)'
  },
  {
    value: 'day(s)'
  }
]
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

function CreateInterview(props) {

  
  let stateButton = false;
  if(localStorage.getItem('interviewID') !== null)
  {
    stateButton = true;
  }
  else
  {
    stateButton = false;
  }
  let statePos = false;
  const [disable, setDisable] = useState(stateButton);

  if(localStorage.getItem('position') !== null)
  {
    statePos = localStorage.getItem('position');
  }
  else
  {
    statePos = "";
  }
  const [position, setPosition] = React.useState(statePos);

  let durationState = false;
  if(localStorage.getItem('duration') !== null)
  {
    durationState = localStorage.getItem('duration');
  }
  else
  {
    durationState = 0;
  }
  const [duration, setDuration] = useState(durationState);

  let timeState = false;
  if(localStorage.getItem('duration') !== null)
  {
    timeState = localStorage.getItem('timeUnit');
  }
  else
  {
    timeState = timeUnits[0].value;
  }
  const [timeUnit, setTimeUnit] = useState(timeState);
  

  const createNewInterview = new CreateNewInterviewAPI();
  let durationInMin = 0;
  let interviewId;
  let isSet = localStorage.getItem('interviewID');
  //alert(isSet);



  let interviewID = localStorage.getItem('interviewID');
    const attendInterviewAPI = new AttendInterviewAPI();

    const[nonCoding, setNonCoding] = useState([]);
    const[coding, setCoding] = useState([]);

    function fetchInterviewQuestions() {
        if(isSet > 0)
       {
         attendInterviewAPI.getNonCodingQuestionsOfInterview(interviewID).then(data => {
            setNonCoding(data)});;
        attendInterviewAPI.getCodingQuestionsOfInterview(interviewID).then(data => {
            setCoding(data)});;
        }

    }
    
    useEffect(() => {
        fetchInterviewQuestions();
    },[]);

 async function addCodingQuestions()
  {
      if(localStorage.getItem('interviewID')<  0)
      {
        localStorage.setItem('interviewID', interviewId);

      }
    localStorage.setItem('addingToInterview', true);
    

    window.location.href = "http://localhost:3000/CreateCodingChallenge";

  }

  async function addNonCodingQuestions()
  {
    if(localStorage.getItem('interviewID')<  0)
      {
        localStorage.setItem('interviewID', interviewId);

      }
    localStorage.setItem('addingToInterview', true);
    

    window.location.href = "http://localhost:3000/CreateNonCodingQuestion";


  }

  async function handleSubmit() {

    console.log(timeUnit)
    if(timeUnit === 'min')
    {
        durationInMin = duration;
    }
    else if (timeUnit === 'hour(s)')
    {
        durationInMin = duration * 60;
    }
    else if( timeUnit === 'day(s)')
    {
        durationInMin = duration * 60*24;
    }
    isSet = 1;

    if(localStorage.getItem('interviewID')<  0)
    {
        localStorage.setItem('interviewID', interviewId);

    }
    console.log(interviewId);
    window.location.href = "http://localhost:3000/AddParticipants";

}
async function handleCreate() {
  if(timeUnit === 'min')
    {
        durationInMin = duration;
    }
    else if (timeUnit === 'hour(s)')
    {
        durationInMin = duration * 60;
    }
    else if( timeUnit === 'day(s)')
    {
        durationInMin = duration * 60*24;
    }

    //alert(durationInMin);
    const newInterview = {
        user_id: localStorage.getItem('userId'),
        interview_id: -1,
        duration: durationInMin,
        position: position
      }

      if(newInterview.duration == "" || position== "" ) {
        toast.error("Fill the blank areas");
      }
      interviewId = await createNewInterview.createInterview(newInterview);
      localStorage.setItem('interviewID', interviewId);
      localStorage.setItem('position', position);
      localStorage.setItem('duration', duration);
      localStorage.setItem('timeUnit', timeUnit);
      console.log(interviewId);
      setDisable(true);



}

    return(
        <div>
           
            <div id="create_interview_container">
            <div id="creat_interview" >
            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
            >
                <Grid item>
                <Typography variant="h3" component="h3">
                    Create new interview
                </Typography>
                </Grid>
            </Grid>
            
            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
            >
                
            <FormControl onSubmit = {handleSubmit}>
                <Box
                component="form"
                sx={{
                    '& .MuiTextField-root': { m: 1, width: '25ch' }
                }}
                noValidate
                autoComplete="off"
            
                >
                <div>
                <TextField
                    disabled={disable}
                    id="outlined-multiline-flexible"
                    label="Position"
                    onChange={(e) => setPosition(e.target.value)}
                    margin="normal"
                    fullWidth
                    variant="outlined"
                    value={position}
                />
                </div>
                <Grid container spacing={2}>
                <Grid item xs={6}>
                    <div>
                        <TextField
                        disabled={disable}
                        id="outlined-multiline-flexible"
                        label="Duration"
                        onChange={(e) => setDuration(e.target.value)}
                        margin="normal"
                        variant="outlined"
                        type="number"
                        value={duration}
                        />
                    </div>
                </Grid>
                <Grid item xs={6}>
                <div>
                    <TextField
                       disabled={disable}
                        id="outlined-select-currency"
                        select
                        margin="normal"
                        value={timeUnit}
                        onChange={(e) => setTimeUnit(e.target.value)}
                        >
                        {timeUnits.map((option) => (
                            <MenuItem key={option.value} value={option.value}>
                            {option.value}
                            </MenuItem>
                        ))}
                        </TextField>
                    </div>
                </Grid>
                </Grid>
                <div>
                </div>
                </Box>
                 <Button 
                 disabled={disable}
                 variant="contained"
                        color="primary"
                        onClick={handleCreate}>Create</Button>
                </FormControl>
            </Grid>
          </div>
        </div>
          <div >
            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
                sx={{ mt: 8 , mb: 8}}
            >
                <Grid item xs={6} >
                    <Grid container  direction="row" justifyContent="center" alignItems="center">
                    <Button
                       disabled={!disable}
                        variant="contained"
                        color="primary"
                        onClick={addCodingQuestions}
                        > Add Coding Question
                    </Button>
                    </Grid>
                </Grid>
                <Grid item xs={6}>
                <Grid container  direction="row" justifyContent="center" alignItems="center">
                    <Button
                        variant="contained"
                        color="primary"
                        disabled={!disable}
                        onClick={addNonCodingQuestions}
                        > Add Non Coding Question
                    </Button>
                    </Grid>
                </Grid>
            </Grid>
            </div>
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
                    <TableCell>Type</TableCell>
                    <TableCell align="right">Title</TableCell>
                </TableRow>
                </TableHead>
                <TableBody>
                {nonCoding.map((row) => (
                    <TableRow
                    key={row.non_challenge_id}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell component="th" scope="row">
                        Non Coding Question
                    </TableCell>
                    <TableCell align="right">{row.title}</TableCell>
  
                    </TableRow>
                ))}
                {coding.map((row) => (
                    <TableRow
                    key={row.challenge_id}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell component="th" scope="row">
                        Coding question
                    </TableCell>
                    <TableCell align="right">{row.title}</TableCell>

                    </TableRow>
                ))}
                </TableBody>
            </Table>
        </TableContainer>
        </Grid>
        </Grid>
            <div >
            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
                sx={{ mt: 8 }}
            >
                <Grid item xs={6} >
                    <Grid container  direction="row" justifyContent="center" alignItems="center">
                    <Button
                        variant="contained"
                        color="primary"
                        > Cancel
                    </Button>
                    </Grid>
                </Grid>
                <Grid item xs={6}>
                <Grid container  direction="row" justifyContent="center" alignItems="center">
                    <Button
                        variant="contained"
                        color="primary"
                        disabled={!disable}
                        onClick={handleSubmit}
                        > Submit
                    </Button>
                    </Grid>
                </Grid>
            </Grid>
            </div>
            <ToastContainer />
        </div>
    );
    
}

export default CreateInterview;
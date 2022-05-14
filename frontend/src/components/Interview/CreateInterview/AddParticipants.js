import React, {useState, useEffect} from "react";
import PropTypes from 'prop-types';
import NavBar from "../../NavBar/NavBar";
import Autocomplete from '@mui/material/Autocomplete';
import Stack from '@mui/material/Stack';
import { Box, FormControl, TextField, MenuItem, Typography, Button} from "@material-ui/core";
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';



import Grid from '@mui/material/Grid';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {CreateNewInterviewAPI} from './CreateInterviewAPI'

  

function AddParticipants(props) {

  let interviewId;
  interviewId = localStorage.getItem('interviewID');
  const [username, setUsername] = React.useState('');
  const [startTime, setStartTime] = React.useState(null);
 const [endTime, setEndTime] = React.useState(null);


  const[rows, setRows] = useState([]);
    const createInterviewAPI = new CreateNewInterviewAPI();

    function fetchParticipants() {
        createInterviewAPI.getParticipants(interviewId).then(data => {
            setRows(data)});;
    }
    
    useEffect(() => {
        fetchParticipants();
    },[]);

  async function handleSubmit() {

    window.location.href = "http://localhost:3000/home";

}


    async function addParticipanttt() {
    
       // alert('hii');
        let coderId =0;
        await createInterviewAPI.getUserToAdd(username).then(data => {
            coderId = data});

        alert(new Date(endTime).toJSON());
        alert(new Date(startTime).toJSON());
        const newParticipant = {
            interviewId: localStorage.getItem('interviewID'),
            coderId: coderId,
            companyId: localStorage.getItem('userId'),
            startTime: new Date(startTime).toJSON(),
            endTime: new Date(endTime).toJSON(),
            interviewResult: "not yet out",
            invitationCode: "code"
          }

          let attendId = await createInterviewAPI.addParticipant(newParticipant);
          console.log(attendId);
 
    }

    return(
        <div>
            <NavBar></NavBar>
            <div id="create_interview_container">
            <div id="creat_interview" >
            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
                sx={{m:3}}
            >
                <Grid item>
                <Typography variant="h3" component="h3">
                   Add Participants
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
                sx={{m:3}}
            
                >
                <div>
                <TextField
                    id="outlined-multiline-flexible"
                    label="Username"
                    onChange={(e) => setUsername(e.target.value)}
                    margin="normal"
                    fullWidth
                    variant="outlined"
                    value={username}
                />
                </div>
                <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
                sx={{m:3}}
                >
                     <Grid item >
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                            <DateTimePicker
                                renderInput={(props) => <TextField {...props} />}
                                label="Start time"
                                value={startTime}
                                onChange={(newValue) => {
                                setStartTime(newValue);
                                }}
                            />
                        </LocalizationProvider>
                    </Grid>
                </Grid>
                <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
                sx={{m:3}}
                >
                     <Grid item >
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                            <DateTimePicker
                                renderInput={(props) => <TextField {...props} />}
                                label="End time"
                                value={endTime}
                                onChange={(newValue) => {
                                setEndTime(newValue);
                                }}
                            />
                        </LocalizationProvider>
                    </Grid>
                </Grid>
                <Grid container  direction="row" justifyContent="center" alignItems="center">
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={addParticipanttt}
                        > Add Participant
                    </Button>
                    </Grid>
          
                </Box>
                </FormControl>
            </Grid>
          </div>
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
                    <TableCell>Username</TableCell>
                    <TableCell align="right">Name</TableCell>
                </TableRow>
                </TableHead>
                <TableBody>
                {rows.map((row) => (
                    <TableRow
                    key={row.username}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell component="th" scope="row">
                        {row.userName}
                    </TableCell>
                    <TableCell align="right">{row.name}</TableCell> 
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
                        onClick={handleSubmit}
                        > Submit
                    </Button>
                    </Grid>
                </Grid>
            </Grid>
            </div>
        </div>
    );
    
}

export default AddParticipants;
import React, {useState, useEffect} from "react";
import { Typography, Button, Select, TextField} from "@material-ui/core";
import Grid from '@mui/material/Grid';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {InterviewAPI} from './InterviewAPI'
import { DateConverter } from "../Common/Message";


function startInterview(invivtaionCode, id)
{
    localStorage.setItem('startInterview',id);
    window.location.href = "http://localhost:3000/StartInterview";

}

function NewInterviewListForCoder(props) {
    const[rows, setRows] = useState([]);
    const interviewAPI = new InterviewAPI();
    const converter = new DateConverter();

    const [startTime, setStartTime] = React.useState(null);
    const [endTime, setEndTime] = React.useState(null);
    const [startDate, setStartDate] = React.useState(null);
    const [endDate, setEndDate] = React.useState(null);

    function fetchNewInterviews() {
        interviewAPI.getNewInterviewList().then(data => {
            setRows(data)});;

    }
    
    useEffect(() => {
        fetchNewInterviews();
    },[]);

    function search()
    {
        let start = startDate + " " + startTime+ ":00";
        let end = endDate + " " + endTime + ":00";
        //alert(start);
        console.log(start);
        interviewAPI.getInterviewsInRange(localStorage.getItem('userId'), start,end).then(data => {
            console.log("here")
            console.log(data);
            setRows(data)});
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
                    New Interviews
                </Typography>
                </Grid>
            </Grid>
            
            
            <Grid container
                direction="row"
                justifyContent="center"
                alignItems="center"
                sx={{ mt: 8 , mb: 8, ml: 3, mr:3}}>
                <Grid item xs={8} >
                <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
                sx={{m:3}}
                >
                     <Grid item xs={4}>
                     <TextField
                            label="Start Date"
                            type="date"
                            value={startDate}
                            onChange={(e)=> setStartDate(e.target.value)}
                            InputLabelProps={{ shrink: true }}
                        />
                    </Grid>
                    <Grid item xs={4}>
                     <TextField
                            label="End Date"
                            type="date"
                            value={endDate}
                            onChange={(e)=> setEndDate(e.target.value)}
                            InputLabelProps={{ shrink: true }}
                        />
                    </Grid>
                    <Grid item xs={4}>
                    <Button
                                variant="contained"
                                color="primary"
                               onClick={search}
                                > Search
                            </Button>
                    </Grid>
                    </Grid>
                <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
                sx={{m:3}}
                >
                    <Grid item xs={4}>
                     <TextField
                            label="Start Time"
                            type="time"
                            value={startTime}
                            onChange={(e)=> setStartTime(e.target.value)}
                            InputLabelProps={{ shrink: true }}
                        />
                    </Grid>
                    
                    <Grid item xs={4}>
                     <TextField
                            label="End Time"
                            type="time"
                            value={endTime}
                            onChange={(e)=> setEndTime(e.target.value)}
                            InputLabelProps={{ shrink: true }}
                        />
                    </Grid>
                    <Grid item xs={4}>
                    <Button
                                variant="contained"
                                color="primary"
                                href="/home"
                                > Clear
                            </Button>
                    </Grid>
                </Grid>
                </Grid>
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
                            <TableCell align="center">Link</TableCell>
                        </TableRow>
                        </TableHead>
                        <TableBody>
                        {
                        rows.map((row) => (
                        
                            <TableRow
                            key={row.interviewId}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                            >
                            <TableCell align="left">{row.companyName}</TableCell>
                            <TableCell align="center">{row.duration}</TableCell>
                            <TableCell align="center">{row.position}</TableCell>
                            <TableCell align="center">{converter.convert(row.startTime)}</TableCell>
                            <TableCell align="center">{converter.convert(row.endTime)}</TableCell>
                            <TableCell align="right">
                                { (new Date()) >= (new Date(row.startTime) ) &&
                                    <Button
                                variant="contained"
                                color="default"
                                onClick={() => startInterview(row.username, row.interviewId)}
                                > Start
                            </Button>
                                }
                                { (new Date()) < (new Date(row.startTime) ) && 
                                <div> Not started yet</div>
                                }
                            
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

export default NewInterviewListForCoder;
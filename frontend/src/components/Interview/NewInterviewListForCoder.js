import React, {useState, useEffect} from "react";
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


function startInterview(invivtaionCode, id)
{
    localStorage.setItem('startInterview',id);
    window.location.href = "http://localhost:3000/StartInterview";

}

function NewInterviewListForCoder(props) {
    const[rows, setRows] = useState([]);
    const interviewAPI = new InterviewAPI();

    function fetchNewInterviews() {
        interviewAPI.getNewInterviewList().then(data => {
            setRows(data)});;

    }
    
    useEffect(() => {
        fetchNewInterviews();
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
                            <TableCell align="center">{row.startTime}</TableCell>
                            <TableCell align="center">{row.endTime}</TableCell>
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
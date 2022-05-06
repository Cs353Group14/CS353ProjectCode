import React, {useState} from "react";
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


  
  async function handleSubmit() {

    window.location.href = "http://localhost:3000/home";

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
                    <Grid container  direction="row" justifyContent="fle" alignItems="center">
                    <Countdown date={Date.now() + 20000} />
                    </Grid>
                </Grid>
                <Grid item xs={6}>
                        <Grid container justifyContent="flex-end">
                        <Box  m={2}>
                        <Button  variant="contained"
                                color="primary" >Finish
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
                {rows.map((row) => (
                    <TableRow
                    key={row.title}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell component="th" scope="row">

                                <Typography variant="h5" component="div">
                                {row.title}
                                </Typography>
                                <Typography variant="body2">
                                {row.type}
                                </Typography>
                                <Button size="small">Solve</Button>
                    </TableCell>
                    <TableCell align="center">
                        Solved
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
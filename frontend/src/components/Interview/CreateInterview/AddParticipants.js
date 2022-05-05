import React, {useState} from "react";
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

function createData(username, name) {
    return { username, name };
  }
  
  const rows = [
    createData('Username1', 'Name1'),
    createData('Username2', 'Name2'),
    createData('Username3', 'Name3'),
    createData('Username4', 'Name4'),
    createData('Username5', 'Name5'),
    createData('Username6', 'Name6'),
    createData('Username7', 'Name7')
  ];

function AddParticipants(props) {

  let interviewId;
  let isSet = localStorage.getItem('interviewID');
  const [username, setUsername] = React.useState('');

  async function handleSubmit() {

    window.location.href = "http://localhost:3000/home";

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
            
                >
                <div>
                <TextField
                    id="outlined-multiline-flexible"
                    label="Position"
                    onChange={(e) => setUsername(e.target.value)}
                    margin="normal"
                    fullWidth
                    variant="outlined"
                    value={username}
                />
                </div>
            
                    <Grid container  direction="row" justifyContent="center" alignItems="center">
                    <Button
                        variant="contained"
                        color="primary"
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
                    <TableCell align="right">See details</TableCell>
                    <TableCell align="right">Remove</TableCell>
                </TableRow>
                </TableHead>
                <TableBody>
                {rows.map((row) => (
                    <TableRow
                    key={row.username}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                    <TableCell component="th" scope="row">
                        {row.username}
                    </TableCell>
                    <TableCell align="right">{row.name}</TableCell>
                    <TableCell align="right">
                    <Button
                        variant="contained"
                        color="default"
                        > See Details
                    </Button>
                    </TableCell>
                    <TableCell align="right">
                    <Button
                        variant="contained"
                        color="secondary"
                        > Remove
                    </Button>
                    </TableCell>
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
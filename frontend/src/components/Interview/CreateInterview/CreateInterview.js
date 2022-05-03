import React, {useState} from "react";
import PropTypes from 'prop-types';
import NavBar from "../../NavBar/NavBar";
import Autocomplete from '@mui/material/Autocomplete';
import Stack from '@mui/material/Stack';
import { Box, FormControl, TextField, MenuItem, Typography, Button, Select} from "@material-ui/core";
import Grid from '@mui/material/Grid';
import {CreateNewInterview} from './CreateInterviewAPI'


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

function CreateInterview(props) {

  const [position, setPosition] = React.useState('');
  const [timeUnit, setTimeUnit] = useState('min');
  const [duration, setDuration] = useState(timeUnit[0].value);
  const createNewInterview = new CreateNewInterview();
  let durationInMin = 0;
  let interviewId;
  let isSet = localStorage.getItem('interviewID');
  let interview 
  if(isSet)
  {
    interview = createNewInterview.getInterview();
  
  }
  console.log(interview);

  if(interview)
  {
      setTimeUnit('min')
      setPosition(interview.position)
      setDuration(interview.duration)
  }


  function addCodingQuestions()
  {
    localStorage.setItem('interviewID', interviewId);
    window.location.href = "http://localhost:3000/CreateCodingChallenge";
  }

  function addNonCodingQuestions()
  {
    localStorage.setItem('interviewID', interviewId);
    window.location.href = "http://localhost:3000/CreateNonCodingQuestion";
    //props.makesVisible(4);
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

    console.log(durationInMin);
    const newInterview = {
        user_id: localStorage.getItem('userId'),
        interview_id: -1,
        duration: durationInMin,
        position: position
      }
      interviewId = await createNewInterview.createInterview(newInterview);
        localStorage.setItem('position', position);
        localStorage.setItem('interviewDuration', durationInMin)
        localStorage.setItem('timeUnit', timeUnit);
   
      setTimeout(function() {
    document.getElementById("creat_interview").remove();
    document.getElementById("interview").style.display ="block";
    console.log(interviewId);
}, 1000);

}

    return(
        <div>
        { !isSet &&
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
                <Grid container spacing={12} >
                <Grid item  justify="flex-end">
                    <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    color="primary"
                    > Cancel</Button>
                </Grid>
                <Grid item >
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        onClick={handleSubmit}
                        > Create</Button>
                </Grid>
                </Grid>
                </FormControl>
            </Grid>
          </div>
        </div>
        }
          <div >
              <div style={{display:"none"}} id="interview">
              <Grid container
                direction="row"
                justifyContent="center"
                alignItems="center"
                sx={{ mt: 8 }}>
                     <Grid item xs={6}>  
                    <Grid
                            container
                            direction="row"
                            justifyContent="center"
                            alignItems="center"
                
                        >
                            <Grid item>  
                                <Typography variant="h6" > {position}</Typography>
                            </Grid>  
                    </Grid> 
                  </Grid>
                 <Grid item xs={6}>  
                    <Grid
                            container
                            direction="row"
                            justifyContent="center"
                            alignItems="center"
                
                        >
                            <Grid item >  
                                <Typography variant="h6" > {duration}  {timeUnit}</Typography>
                            </Grid>  
                    </Grid> 
                  </Grid>          
              </Grid>
          
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
                        onClick={addNonCodingQuestions}
                        > Add Non Coding Question
                    </Button>
                    </Grid>
                </Grid>
            </Grid>
            </div>
        </div>
        </div>
    );
    
}

export default CreateInterview;
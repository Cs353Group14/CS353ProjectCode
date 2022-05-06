import React, {useState} from "react";
import { Box, FormControl, TextField, MenuItem, Typography, Button, Select, Form} from "@material-ui/core";
import Grid from '@mui/material/Grid';
import {ContestApi} from '../ContestApi'
import CodingChallengeTable from "./CodingChallengeTable";


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

export default function CreateContest(props) {

  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [difficulty,setDifficulty] = useState(null);
  const [timeUnit, setTimeUnit] = useState('min');
  const [duration, setDuration] = useState(timeUnit[0].value);
  const [startDate,setStartDate] = useState(new Date('2014-08-18'));
  const [deadline,setDeadline] = useState(null);
  const [startTime,setStartTime] = useState(null);
  const [endTime,setEndTime] = useState(null);

  const contestApi = new ContestApi();

  let durationInMin = 0;

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
    console.log(startDate + startTime);
    const contest = {
        contest_id: -1,
        title: title,
        description: description,
        start_time: startDate + "T03:" + startTime,
        deadline: deadline + "T03:" + endTime,
        duration: durationInMin,
        difficulty: difficulty
      }
      const contestId = await contestApi.createContest(contest);

      localStorage.setItem('contestId',contestId);

      window.location.href = "http://localhost:3000/AddContestChallenges";

}

    return(
        <div>
          <div>

          <Grid
            container
            direction="row"
            justifyContent="center"
            alignItems="center"
          >
            <Grid item>
              <Typography variant="h3" component="h3">
                Create New Contest
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
                label="Title"
                onChange={(e) => setTitle(e.target.value)}
                margin="normal"
                fullWidth
                variant="outlined"
                value={title}
              />
            </div>
            <div>
              <TextField
                id="outlined-textarea"
                label="Description"
                onChange={(e) => setDescription(e.target.value)}
                placeholder="Placeholder"
                multiline
                fullWidth
                margin="normal"
                variant="outlined"
                value={description}
              />
            </div>
            <div>
            <TextField
                      id="outlined-multiline-flexible"
                      label="Difficulty"
                      onChange={(e) => setDifficulty(e.target.value)}
                      margin="normal"
                      variant="outlined"
                      type="number"
                      value={difficulty}
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
               <div>
              <br/>
              <br/>
              
                        <TextField
                            label="Start Date"
                            type="date"
                            InputLabelProps={{
                                shrink: true,
                            }}
                            onChange={(e)=> setStartDate(e.target.value)}
                        />
                    
                        <TextField
                            label=" "
                            type="time"
                            InputLabelProps={{
                                shrink: true,
                            }}
                            onChange={(e)=> setStartTime(e.target.value)}
                        />
                   
                    <br/>
                    <br/>
                  
                        <TextField
                            label="Deadline"
                            type="date"
                            InputLabelProps={{
                                shrink: true,
                            }}
                            onChange={(e)=> setDeadline(e.target.value)}
                        />

                          <TextField
                            label=" "
                            type="time"
                            InputLabelProps={{
                                shrink: true,
                            }}
                            onChange={(e)=> setEndTime(e.target.value)}
                        />
               
                    <br/>
                    <br/>

               </div>
              </Box>
              <Grid container spacing={12} >
              
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
    );
    
}

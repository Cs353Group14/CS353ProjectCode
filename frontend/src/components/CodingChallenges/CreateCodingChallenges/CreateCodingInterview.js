import React, {useState} from "react";
import PropTypes from 'prop-types';
import NavBar from "../../NavBar/NavBar";
import Autocomplete from '@mui/material/Autocomplete';
import Stack from '@mui/material/Stack';
import { Box, FormControl, TextField, MenuItem, Typography, Button} from "@material-ui/core";
import Grid from '@mui/material/Grid';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';
import {CreateCodingChallengeAPI} from './CreateCodingChallengeAPI'
import {CreateNewInterviewAPI} from '../../Interview/CreateInterview/CreateInterviewAPI'
import { ToastContainer,toast } from 'react-toastify';
import { MessageType } from "../../Common/Message";



const difficulties = [
  {
    value: 'Easy'
  },
  {
    value: 'Medium'
  },
  {
    value: 'Hard'
  }
]

const timeUnits = [
  {
    value: 'min'
  },
  {
    value: 'hour(s)'
  }
]

const categories =
[
  {value: "DP"},
  {value: "Array"},
  {value: "Linked list"},
  {value: "Stack"},
  {value: "Graph"},
  {value: "DFS"},
  {value: "BFS"}
]


function CreateCodingChallenge(props) {

  const parseLines = (value) => value.replace(/(\\n)/g, "\n");
  const [difficulty, setdifficulty] = React.useState('Easy');
  const [title, setTitle] = useState("");
  const [points, setPoints] = useState("");
  const [solution, setSolution] = useState("");
  const [question, setQuestion] = useState("");
  const [category, setCatefories] = useState("");
  const [timeUnit, setTimeUnit] = useState('min');
  const [duration, setDuration] = useState(timeUnit[0].value);
  const [input, setInput] = useState("");
  const [output, setOutput] = useState("");
  const createCodingChallengeAPI = new CreateCodingChallengeAPI();
  const createNewInterviewQuestion = new CreateNewInterviewAPI()
  let publicity = 0

  let listOfCategories = [];
  let showDuration = localStorage.getItem('interviewID');

  async function handleSubmit() {

   // console.log(listOfCategories);
    console.log(category);
    console.log("here");

    if(localStorage.getItem('interviewID'))
    {
      publicity = 0;
    }
    else
    {
      publicity = 1;
    }

    if(difficulty == "" || title == "" || points == "" || solution == "" || question == "" || input == "" || output == "") {

      toast.error("Please fill the blank areas");
    } else  {

      const newQuestion = {
        difficulty: difficulty,
        title: title,
        points: points,
       solution: solution,
       question: question,
       solvedNumber: 0,
       attempt_number: 0,
       publicity: publicity 
      }
  
      let challengeId;
      if(publicity == 1)
      {
          challengeId = await createCodingChallengeAPI.createCoding(newQuestion);
          localStorage.setItem('challengeId', challengeId);
        let categoryArray =[];
    
        category.forEach(category => {
          categoryArray.push(category.value);
        });
    
        if(challengeId == -1) {
          toast.error("Something went wrong. Please try again");
          return;
        }

          let inputsOutputs = [];
          inputsOutputs[0] = input;
          inputsOutputs[1] = output;
          //console.log(listOfCategories);
          //console.log(categoryArray);
          const response1 = await createCodingChallengeAPI.addCategory(challengeId, categoryArray);
          if (response1.messageType === MessageType.ERROR) {
            toast.error(response1.message);
            return;
        }
      const response2 = await createCodingChallengeAPI.addTestCase(challengeId, inputsOutputs);

          if (response2.messageType === MessageType.ERROR) {
            toast.error(response2.message);
            return;
        }
        if (challengeId.messageType === MessageType.ERROR) {
          toast.error(challengeId.message);
        } else {
          toast.success("Coding Challege is created");
              setTimeout(function() {
                  window.location.href = "http://localhost:3000/home";
              }, 1500)
        }
          //window.location.href = "http://localhost:3000/CreateInterview";
      }
      else
      {
       // alert("here");
        let interviewID = localStorage.getItem('interviewID');
        let companyId = localStorage.getItem('userId');
        challengeId = await createCodingChallengeAPI.createCodingChallengesForInteriew(interviewID, companyId, duration, newQuestion);
        localStorage.setItem('challengeId', challengeId);
      let categoryArray =[];
  
      category.forEach(category => {
        categoryArray.push(category.value);
      });
  
      if(challengeId == -1) {
        toast.error("Something went wrong. Please try again");
        return;
      }

      let inputsOutputs = [];
      inputsOutputs[0] = input;
      inputsOutputs[1] = output;
      //console.log(listOfCategories);
      //console.log(categoryArray);
      const response1 = await createCodingChallengeAPI.addCategory(challengeId, categoryArray);
      if (response1.messageType === MessageType.ERROR) {
        toast.error(response1.message);
        return;
    }
      const response2 = await createCodingChallengeAPI.addTestCase(challengeId, inputsOutputs);

      if (response2.messageType === MessageType.ERROR) {
        toast.error(response2.message);
        return;
    }

    if (challengeId.messageType === MessageType.ERROR) {
      toast.error(challengeId.message);
    } else {
      toast.success("Coding Challege is created and added to interview");
          setTimeout(function() {
              window.location.href = "http://localhost:3000/home";
          }, 1000)
    }
      //window.location.href = "http://localhost:3000/CreateInterview";
  }
  
      }
  
      /*
  
      if(publicity === 0) //Then need to add this question to interview also
      {
        localStorage.setItem('challengeId', challengeId);
        localStorage.setItem('duration', duration);
        const response3 = await createNewInterviewQuestion.addCodingQuestionToInterview();
        if (response3.messageType === MessageType.ERROR) {
          toast.error(response3.message);
        } else {
          toast.success("Coding Challege is created and added to interview");
              setTimeout(function() {
                  window.location.href = "http://localhost:3000/CreateInterview";
              }, 1000)
        }
          window.location.href = "http://localhost:3000/CreateInterview";
      }

      toast.success("Coding Challenge is created");
 
    }
    */

    

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
                Create new coding challenge
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
                label="Question"
                onChange={(e) => setQuestion(e.target.value)}
                placeholder="Placeholder"
                multiline
                fullWidth
                margin="normal"
                variant="outlined"
                value={question}
              />
            </div>
      
            <Grid container spacing={2}>
              <Grid item xs={6}>
                <div>
                    <TextField
                      id="outlined-multiline-flexible"
                      label="Points"
                      onChange={(e) => setPoints(e.target.value)}
                      margin="normal"
                      variant="outlined"
                      type="number"
                      value={points}
                    />
                </div>
              </Grid>
              <Grid item xs={6}>
                <div>
                  <TextField
                      id="outlined-select-currency"
                      select
                      margin="normal"
                      value={difficulty}
                      onChange={(e) => setdifficulty(e.target.value)}
                    >
                      {difficulties.map((option) => (
                        <MenuItem key={option.value} value={option.value}>
                          {option.value}
                        </MenuItem>
                      ))}
                      </TextField>
                  </div>
                </Grid>
              </Grid>
              <div>
                <TextField
                  id="outlined-textarea"
                  label="Solution"
                  fullWidth
                  placeholder="Placeholder"
                  multiline
                  margin="normal"
                  variant="outlined"
                  onChange={(e) => setSolution(e.target.value)}
                  value= {solution}

                />
               </div>
              <Stack spacing={3} sx={{ width: 500 }}>
                <Autocomplete
                    multiple
                    id="tags-outlined"
                    options={categories}
                    getOptionLabel={(option) => option.value}
                    filterSelectedOptions
                    margin="normal"
                    onChange={(event, options) => {
                      setCatefories(options);
                      listOfCategories = options;
                      //console.log(listOfCategories);
                    }}
                    renderInput={(params) => (
                      <TextField
                        {...params}
                        label="Catogories"
                        placeholder="Category"
                      />
                    )}
                  />
                </Stack>
              </Box>
              { showDuration &&
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
                </Grid> }
                <div>
                <TextField
                  id="outlined-textarea"
                  label="Input testcase"
                  fullWidth
                  placeholder="Placeholder"
                  multiline
                  margin="normal"
                  variant="outlined"
                  onChange={(e) => setInput(e.target.value)}
                  value={parseLines(input)}
                />
               </div>
               <div>
               <TextField
                  id="outlined-textarea"
                  label="Output testcase"
                  fullWidth
                  placeholder="Placeholder"
                  multiline
                  margin="normal"
                  variant="outlined"
                  onChange={(e) => setOutput(e.target.value)}
                  value={parseLines(output)}
                />
               </div>
              <Grid container spacing={12} style= {{marginTop: "1rem"}} >
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
                    > Save</Button>
              </Grid>
            </Grid>
            
            </FormControl>
          </Grid>

          <ToastContainer />
        </div>
    );
    
}

export default CreateCodingChallenge;
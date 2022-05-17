import React, {useState} from "react";
import PropTypes from 'prop-types';
import NavBar from "../../NavBar/NavBar";
import Autocomplete from '@mui/material/Autocomplete';
import Stack from '@mui/material/Stack';
import { Box, FormControl, TextField, MenuItem, Typography, Button} from "@material-ui/core";
import Grid from '@mui/material/Grid';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';
import {CreateNonCodingQuestionAPI} from './CreateNonCodingQuestionAPI'
import {CreateNewInterviewAPI} from '../../Interview/CreateInterview/CreateInterviewAPI';
import { toast, ToastContainer } from "react-toastify";
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


function CreateNonCodingQuestion(props) {

  const [difficulty, setdifficulty] = React.useState('Easy');
  const [title, setTitle] = useState("");
  const [points, setPoints] = useState("");
  const [question, setQuestion] = useState("");
  const [category, setCategories] = useState("");
  const createNonCodingQuestionAPI = new CreateNonCodingQuestionAPI();
  const createNewInterviewQuestion = new CreateNewInterviewAPI();
  let publicity = 0;

  let listOfCategories = [];

  async function handleSubmit() {
    console.log(listOfCategories);

    if(localStorage.getItem('interviewID'))
    {
      publicity = 0;
    }
    else
    {
      publicity = 1;
    }

    if(difficulty == "" || title == "" || points == ""  || question == "") {

      toast.error("Please fill the blank areas");
      return;
    } 

    const newQuestion = {
      non_challenge_id: -1,
      difficulty: difficulty,
      title: title,
      question: question,
      publicity: 1
    }

    let challengeId;
    if(publicity == 0)
    {
      let interviewID = localStorage.getItem('interviewID');
      let companyId = localStorage.getItem('userId');
      challengeId = await createNonCodingQuestionAPI.createNonCodingQuestionForInterview(interviewID,companyId, newQuestion);
    }
    else
    {
      challengeId = await createNonCodingQuestionAPI.createNonCoding(newQuestion);
    }

    let categoryArray =[];

    listOfCategories.forEach(category => {
      categoryArray.push(category.value);
    });

    if(challengeId == -1) {
      toast.error("Something went wrong. Please try again");
      return;
    }

    const response = await createNonCodingQuestionAPI.addCategory(challengeId, categoryArray);
    if (response.messageType === MessageType.ERROR) {
      toast.error(response.message);
      return;
    } 

    if(publicity === 0) //Then need to add this question to interview also
    {
      localStorage.setItem('challengeId', challengeId);
      const response2 = await createNewInterviewQuestion.addNonCodingQuestionToInterview();
      if (response2.messageType === MessageType.ERROR) {
        toast.error(response2.message);
      } else {
        toast.success("Non Coding Quesiton is created and added to interview");
            setTimeout(function() {
                window.location.href = "http://localhost:3000/CreateInterview";
            }, 1000)
      }
      window.location.href = "http://localhost:3000/CreateInterview";
    }

    toast.success("Non Coding Quiestion is created");


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
                Create new question
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
                      listOfCategories = options;
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

export default CreateNonCodingQuestion;
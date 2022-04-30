import React, {useState} from "react";
import PropTypes from 'prop-types';
import NavBar from "../../NavBar/NavBar";
import Autocomplete from '@mui/material/Autocomplete';
import Stack from '@mui/material/Stack';
import { Box, FormControl, TextField, MenuItem, Typography, Button} from "@material-ui/core";
import Grid from '@mui/material/Grid';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';


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


function CreateCodingChallenge(props) {

  const [difficulty, setdifficulty] = React.useState('Easy');
  const [title, setTitle] = useState("");
  const [points, setPoints] = useState("");
  const [solution, setSolution] = useState("");
  const [question, setQuestion] = useState("");
  const [category, setCatefories] = useState("");

  let handleSubmit = async (e) => {
    e.preventDefault();
    try {
      let res = await fetch("http://localhost:8080/createCodingChallenge/1", {
        method: "POST",
        body: JSON.stringify({
          difficulty: difficulty,
           title: title,
          points: points,
          solution: solution,
          question: question,
          category: category,
          solvedNumber: 0,
          attempt_number: 0,
          publicity: false

        }),
      });

      let resJson = await res.json();
      if (res.status === 200) {
        alert("Submited successfully")
      } else {
        alert("Some error occured");
      }
    } catch (err) {
      console.log(err);
    }
  };


  const handleChange = (event) => {
    
  };

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
                    onChange={(e) => setCatefories(e.target.value)}
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
        </div>
    );
    
}

export default CreateCodingChallenge;
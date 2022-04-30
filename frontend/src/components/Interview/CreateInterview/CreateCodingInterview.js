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

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
}));

function CreateCodingChallenge(props) {

  const [value, setValue] = React.useState('Controlled');
  const [difficulty, setdifficulty] = React.useState('Easy');


  const handleChange = (event) => {
    setValue(event.target.value);
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
            
          <FormControl onSubmit = "">
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
                onChange={handleChange}
                margin="normal"
                fullWidth
                variant="outlined"
              />
            </div>
            <div>
              <TextField
                id="outlined-textarea"
                label="Question"
                placeholder="Placeholder"
                multiline
                fullWidth
                margin="normal"
                variant="outlined"
              />
            </div>
      
            <Grid container spacing={2}>
              <Grid item xs={6}>
                <div>
                    <TextField
                      id="outlined-multiline-flexible"
                      label="Points"
                      onChange={handleChange}
                      margin="normal"
                      variant="outlined"
                      type="number"
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
                      onChange={handleChange}
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
                    > Save</Button>
              </Grid>
            </Grid>
            </FormControl>
          </Grid>
        </div>
    );
    
}

export default CreateCodingChallenge;
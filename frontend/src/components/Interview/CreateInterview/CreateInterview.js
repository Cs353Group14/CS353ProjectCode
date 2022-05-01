import React, {useState} from "react";
import PropTypes from 'prop-types';
import NavBar from "../../NavBar/NavBar";
import Autocomplete from '@mui/material/Autocomplete';
import Stack from '@mui/material/Stack';
import { Box, FormControl, TextField, MenuItem, Typography, Button, Select} from "@material-ui/core";
import Grid from '@mui/material/Grid';


const timeUnit = [
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
  //const createNonCodingQuestionAPI = new CreateNonCodingQuestionAPI();

  async function handleSubmit() {



}

    return(
        <div>
        <NavBar></NavBar>
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
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={timeUnit}
                    label="Unit"
                    onChange={(e) => handleSubmit(e)}
                >
                    <MenuItem value={10}>Ten</MenuItem>
                    <MenuItem value={20}>Twenty</MenuItem>
                    <MenuItem value={30}>Thirty</MenuItem>
                </Select>
              </Grid>
              </Grid>
              <div>
               </div>
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
                    > Create</Button>
              </Grid>
            </Grid>
            </FormControl>
          </Grid>
        </div>
    );
    
}

export default CreateInterview;
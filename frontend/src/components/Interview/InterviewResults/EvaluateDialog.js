import React  , {useState} from "react";
import DialogTitle from '@mui/material/DialogTitle';
import Dialog from '@mui/material/Dialog'; 
import {Button, DialogActions, DialogContent, FormControl, Radio, FormControlLabel, FormLabel, RadioGroup} from "@material-ui/core";
import { getThemeProps } from "@material-ui/styles";



function EvaluateDialog({open, handleSubmit, handleClose}) {
  const [result, setResult] = React.useState('');

  const handleChange = (event) => {
    setResult(event.target.value)
  }

  return (
    <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogContent>
            <FormControl>
        <FormLabel id="demo-row-radio-buttons-group-label">Gender</FormLabel>
        <RadioGroup
            row
            aria-labelledby="demo-row-radio-buttons-group-label"
            name="row-radio-buttons-group"
        >
            <FormControlLabel value="Strong No Hire" control={<Radio />} label="Strong No Hire"   onChange={handleChange} />
            <FormControlLabel value="No Hire" control={<Radio />} label="No Hire" />
            <FormControlLabel value="Weak No Hire" control={<Radio />} label="Weak No Hire"  onChange={handleChange} />
            <FormControlLabel value="Weak Hire" control={<Radio />} label="Weak Hire"   onChange={handleChange}/>
            <FormControlLabel value="Hire" control={<Radio />} label="Hire" />
            <FormControlLabel value="Strong Hire" control={<Radio />} label="Strong Hire"   onChange={handleChange}/>
        </RadioGroup>
        </FormControl>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={()=>handleSubmit(result)}>Submit</Button>
        </DialogActions>
      </Dialog>
  );
}
export default EvaluateDialog;
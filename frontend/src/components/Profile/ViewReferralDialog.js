import React from "react";
import DialogTitle from '@mui/material/DialogTitle';
import Dialog from '@mui/material/Dialog'; 
import {Button, DialogActions, DialogContent, DialogContentText, TextField, Box} from "@material-ui/core";



function ViewReferarlDialog({ open, handleClose }) {
  const [description, setDescription] = React.useState();

  const handleCreate = (e) => {
    e.preventDefault();
    console.log(description);
    handleClose();
  
  };

  return (
    <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Use Google's location service?"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
             Please write a descriotion
          </DialogContentText>
          <form fullWidth onSubmit={handleCreate} id="myform">
            <Box
              sx={{
                width: 500,
                maxWidth: '100%',
              }}
            >
               <TextField fullWidth variant="outlined" id="fullWidth" multiline
          minRows={4} onChange={(e) => setDescription(e.target.value)} />
            </Box>
          </form>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button variant="contained" type="submit" form="myform" autoFocus>
            Submit
          </Button>
        </DialogActions>
      </Dialog>
  );
}
export default ViewReferarlDialog;
import React from "react";
import DialogTitle from '@mui/material/DialogTitle';
import Dialog from '@mui/material/Dialog'; 
import {Button, DialogActions, DialogContent, DialogContentText, TextField, Box} from "@material-ui/core";
import {ProfileAPI} from './ProfileAPI';


function GiveReferralDialog({ open, handleClose }) {
  const [description, setDescription] = React.useState();
   
  const profileAPI = new ProfileAPI();

  async function handleCreate(){
    const refer = {
      reason: description
    }
    console.log(description);
     await profileAPI.referCoder(localStorage.getItem('userId'), localStorage.getItem('referredId'), refer);

    handleClose();
  
  };



  return (
    <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
        fullWidth
        maxWidth="md"
      >
        <DialogTitle id="alert-dialog-title">
          {'Give referral'}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
             Please write a description
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
export default GiveReferralDialog;
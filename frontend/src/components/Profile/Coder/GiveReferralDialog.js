import React from "react";
import DialogTitle from '@mui/material/DialogTitle';
import Dialog from '@mui/material/Dialog'; 
import {Button, DialogActions, DialogContent, DialogContentText, TextField, Box} from "@material-ui/core";
import {ProfileAPI} from './ProfileAPI';
import { MessageType } from "../../Common/Message";
import { ToastContainer,toast } from 'react-toastify';


function GiveReferralDialog({ open, handleClose, submitReferral }) {
  
  const [description, setDescription] = React.useState("");
   

  const profileAPI = new ProfileAPI();

  async function handleCreate(){
    submitReferral(description);
    /*
    const refer = {
      reason: description
    }

    if(refer.reason.trim() == "") {
      toast.error("You should wrtie your reasoning to be able ti give referral");
      setTimeout(function() {
        handleClose();
      }, 5000)
    }
    console.log(description);
     const response = await profileAPI.referCoder(localStorage.getItem('userId'), localStorage.getItem('referredId'), refer);

     if (response.messageType === MessageType.ERROR) {
      toast.error(response.message);
      setTimeout(function() {
        handleClose();
      }, 5000)
    } else {
        toast.success(response.message);
        setTimeout(function() {
          handleClose();
        }, 1000)
    }
    */

    
  
  };



  return (
    <div>

    
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
             Please write your reson to give referral
          </DialogContentText>
          <form fullWidth id="myform">
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
          <Button variant="contained"  autoFocus onClick={handleCreate}>
            Submit
          </Button>
        </DialogActions>
      </Dialog>
      <ToastContainer />
      </div>
  );
}
export default GiveReferralDialog;
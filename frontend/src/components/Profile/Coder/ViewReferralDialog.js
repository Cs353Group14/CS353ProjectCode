import React from "react";
import DialogTitle from '@mui/material/DialogTitle';
import Dialog from '@mui/material/Dialog'; 
import {Button, DialogActions, DialogContent, DialogContentText, Typography} from "@material-ui/core";
import { getThemeProps } from "@material-ui/styles";



function ViewReferralDialog({ open, handleClose,name, position, email, description }) {
    /*
  const [description, setDescription] = React.useState();

  const handleCreate = (e) => {
    e.preventDefault();
    console.log(description);
    handleClose();
  
  };
  */

  return (
    <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            <Typography gutterBottom variant="h4" component="div">{name}</Typography>
            <Typography gutterBottom variant="h6" component="div">{position}</Typography>
            <Typography gutterBottom variant="h6" component="div">{email}</Typography>
            <Typography gutterBottom variant="subtitle1" component="div">{description}</Typography>
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Close</Button>
        </DialogActions>
      </Dialog>
  );
}
export default ViewReferralDialog;
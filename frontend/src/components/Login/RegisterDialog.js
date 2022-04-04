import * as React from 'react';
import Button from "@material-ui/core/Button";
import {Dialog, DialogActions, DialogContent, DialogTitle} from "@material-ui/core";
import Fab from "@material-ui/core/Fab";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 200,
    },
}));


function RegisterDialog() {

    const classes = useStyles();
    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleCoder = () => {

    }

    return(
        <div className={"register-dialog"} >
            <div className = {"register-button"}>
                
                    <Button onClick={handleClickOpen} color="inherit"> Register </Button>
               
            </div>
            <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title" color='primary'>Register as: </DialogTitle>                    
                <DialogActions>
                    <Button variant="outlined" size="large"  href="/register-coder" color="primary" >
                        Coder
                    </Button>
                    <Button variant="outlined" size="large"  href="/register-editor" color="primary" >
                        Editor
                    </Button>
                    <Button variant="outlined" size="large"  href="/register-company" color="primary" >
                        Company
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default RegisterDialog;

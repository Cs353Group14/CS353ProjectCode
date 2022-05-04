import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import Menu from '../Home/Menu';
import NotificationsNoneIcon from '@mui/icons-material/NotificationsNone';


const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        flexGrow: 1,
    },
}));

export default function NavBar(props) {
    const classes = useStyles();

    function handleLogout() {
        localStorage.clear();

    }



    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    <Menu makesVisible = {props.makesVisible}/>
                    <Typography variant="h6" className={classes.title}>
                        Welcome
                    </Typography>
                    <Button  color="inherit" startIcon={<NotificationsNoneIcon />}></Button>
                    <Button color="inherit" href="/" onClick={handleLogout}>Logout</Button>
                
                </Toolbar>
            </AppBar>
        </div>
    );
}

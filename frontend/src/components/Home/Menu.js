import React from 'react';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';
import MenuIcon from "@material-ui/icons/Menu";
import IconButton from "@material-ui/core/IconButton";

const useStyles = makeStyles( (theme) => ( {
    list: {
        width: 250,
    },
    fullList: {
        width: 'auto',
    },
    menuButton: {
        marginRight: theme.spacing(2),
    }
}));

export default function Menu(props) {

    const classes = useStyles();
    const [state, setState] = React.useState({
        left: false,
    });

    const toggleDrawer = (anchor, open) => (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }

        setState({ ...state, [anchor]: open });
    };

    const list = (anchor) => (
        <div
            className={clsx(classes.list, {
                [classes.fullList]: anchor === 'top' || anchor === 'bottom',
            })}
            role="presentation"
            onClick={toggleDrawer(anchor, false)}
            onKeyDown={toggleDrawer(anchor, false)}
        >

            <div>
                <Button color="inherit" href="/" onClick={props.handleLogout}>Logout</Button>
            </div>
            <br/>
            <Divider />
            <div>
                <Button color="inherit"  onClick={openAllCodingChallenges}>Coding Challenges</Button>
            </div>
            <br/>
            <Divider />
            <div  >
                <Button color="inherit"  onClick={openAllNoncodingChallenges}>Non Coding Challenges</Button>
            </div>
            <br/>
            <Divider />
            <div  >
                <Button color="inherit"  onClick={openCreateCodingChallenge}>Create Coding Challenge</Button>
            </div>
            <br/>
            <Divider />
            <div  >
                <Button color="inherit"  onClick={openCreateNonCodingChallenge}>Create Non-Coding Challenge</Button>
            </div>
            <br/>
            <Divider />
            <div  >
                <Button color="inherit"  onClick={openCreateInterview}>Create Interview</Button>
            </div>

        </div>
    );

    const openAllCodingChallenges = () => {

        localStorage.setItem('menuId', 1);
        window.location.href = "http://localhost:3000/home";
        //props.makesVisible(1);
        //props.fetchActivities();
    }

    const openAllNoncodingChallenges = () => {

        localStorage.setItem('menuId', 2);
        window.location.href = "http://localhost:3000/home";
        //props.makesVisible(2);

        //props.fetchActivities();
    }

    const openCreateCodingChallenge = () => {
        localStorage.setItem('menuId', 3);
        window.location.href = "http://localhost:3000/home";
        //props.makesVisible(3);
    }

    const openCreateNonCodingChallenge = () => {
        localStorage.setItem('menuId', 4);
        window.location.href = "http://localhost:3000/home";
        //props.makesVisible(4);
    }

    const openCreateInterview = () => {
        localStorage.setItem('menuId', 5);
        window.location.href = "http://localhost:3000/home";
    }


    return (
        <div>
            {['left'].map((anchor) => (
                <React.Fragment key={anchor}>
                    <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu" onClick={toggleDrawer(anchor, true)}>
                        <MenuIcon />
                    </IconButton>
                    <Drawer anchor={anchor} open={state[anchor]} onClose={toggleDrawer(anchor, false)}>
                        {list(anchor)}
                    </Drawer>
                </React.Fragment>
            ))}
        </div>
    );
}

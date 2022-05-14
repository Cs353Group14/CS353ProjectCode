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

    function isUser(user) {
        return(user == localStorage.getItem('usertype'));
    }
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

            <br/>
            <div>
                <Button color="inherit"  onClick={openAllCodingChallenges}>Coding Challenges</Button>
                <br/>
                <Divider />
            </div>
            <div  >
                <Button color="inherit"  onClick={openAllNoncodingChallenges}>Non Coding Challenges</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(3)}   >
                <Button color="inherit" onClick={openCreateCodingChallenge}>Create Coding Challenge</Button>
                <br/>
                <Divider />
            </div>
            <div  hidden= {!isUser(3)}>
                <Button color="inherit" onClick={openCreateNonCodingChallenge}>Create Non-Coding Challenge</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(2)} >
                <Button color="inherit" onClick={openCreateInterview}>Create Interview</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(3)} >
                <Button color="inherit"  onClick={openCreateContest}>Create Contest</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(1)} >
                <Button color="inherit" onClick={openNonRegisteredContests}>New Contests</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(1)} >
                <Button color="inherit" onClick={openRegisteredContests}>Registered Contests</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(1)} >
                <Button color="inherit" onClick={openContinuesContests}>Started Contests</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(1)} >
                <Button color="inherit" onClick={openFinishedContests}>Finished Contests</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {isUser(1)} >
                <Button color="inherit" onClick={openAllContests}>All Contests</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(2)} >
                <Button color="inherit" onClick={openContests}>Sponsored Contests</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(3)} >
                <Button color="inherit" onClick={openContests}>My Contests</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(1)} >
                <Button color="inherit" onClick={openPastInterviews}>Past Interviews</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(1)} >
                <Button color="inherit" onClick={openNewInterviews}>New Interviews</Button>
                <br/>
                <Divider />
            </div>
            <div hidden= {!isUser(2)} >
                <Button color="inherit" onClick={openListOfInterviews}>List of Interviews</Button>
                <br/>
                <Divider />
            </div>
            <div  >
                <Button color="inherit" onClick={openCoders}>Coders</Button>
                <br/>
                <Divider />
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

    const openCreateContest = () => {
        localStorage.setItem('menuId', 6);
        window.location.href = "http://localhost:3000/home";
    }

    const openNonRegisteredContests = () => {
        localStorage.setItem('menuId', 7);
        window.location.href = "http://localhost:3000/home";
    }

    const openRegisteredContests = () => {
        localStorage.setItem('menuId', 8);
        window.location.href = "http://localhost:3000/home";
    }

    const openContinuesContests = () => {
        localStorage.setItem('menuId', 9);
        window.location.href = "http://localhost:3000/home";
    }

    const openFinishedContests = () => {
        localStorage.setItem('menuId', 10);
        window.location.href = "http://localhost:3000/home";
    }
    const openPastInterviews = () => {
        localStorage.setItem('menuId', 11);
        window.location.href = "http://localhost:3000/home";
    }

    const openNewInterviews = () => {
        localStorage.setItem('menuId', 12);
        window.location.href = "http://localhost:3000/home";
    }
    const openListOfInterviews = () => {
        localStorage.setItem('menuId', 13);
        window.location.href = "http://localhost:3000/home";
    }

    const openAllContests = () => {
        localStorage.setItem('menuId', 14);
        window.location.href = "http://localhost:3000/home";
    }

    const openContests = () => {
        localStorage.setItem('menuId', 15);
        window.location.href = "http://localhost:3000/home";
    }

    const openCoders = () => {
        localStorage.setItem('menuId', 16);
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

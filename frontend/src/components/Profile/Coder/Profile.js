import React, { useState, useEffect } from "react";
import { Avatar, Card, CardMedia, CardContent, Typography, Grid, Button, Box } from "@material-ui/core";
import './Profile.css'
import ProfileCard from "./ProfileCard";
import ViewReferral from "../../../View/ViewReferral";
import ViewBadge from "../../../View/ViewBadge";

import NavBar from "../../NavBar/NavBar";
import GiveReferralDialog from "./GiveReferralDialog";
import { ProfileAPI } from "./ProfileAPI";
import { MessageType } from "../../Common/Message";
import { ToastContainer,toast } from 'react-toastify';



//Dummy data for now

var profileInfoBasic = {
    name: "Name",
    surname: "Surname",
    position: "POSITION"
}

var profileInfo =
{
    ranking: "2",
    contestAttended: "10",
    solvedQuestions: "346"
}

var awards = [
    {
        contestName: "Contest1",
        place: "1st"
    },
    {
        contestName: "Contest2",
        place: "1st"
    },
    {
        contestName: "Contest3",
        place: "2nd"
    },
    {
        contestName: "Contest4",
        place: "2nd"
    }
]

var referrals =
    [
        {
            img: 'img.png',
            name: "A coder",
            position: "Google engineer",
            email: "x@com",
            description: "X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder!"
        },
        {
            img: 'img.png',
            name: "Y editor",
            position: "Google engineer",
            email: "x@com",
            description: "X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder!"

        },
        {
            img: 'img.png',
            name: "Z coder",
            position: "Facebook engineer",
            email: "x@com",
            description: "X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder!"

        },
        {
            img: 'img.png',
            name: "M editor",
            position: "Facebook engineer",
            email: "x@com",
            description: "X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder!"
        }
    ]


//The componenet
function Profile() {

    //Handle dialogs
    const [openDialogName, setOpenDialog] = React.useState(null);
    

    const openReferDialog = () => {
        setOpenDialog(true);
    };

    const closeDialog = () => {
        setOpenDialog(false);
    };

    const [info, setInfo] = useState([]);
    const [numberOfContest, setNumberOfContests] = useState(0);
    const [numberOfQuestions, setNumberOfQuestions] = useState(0);
    const [contest, setContests] = useState([]);
    const [referrals, setReferrals] = useState([]);
    const profileAPI = new ProfileAPI();

    let coderId;

    if(localStorage.getItem('viewer') == 'false') {
        coderId = localStorage.getItem('userId');
    } else {
        coderId = localStorage.getItem('referredId');
    }
    
    function fetchProfileInfo() {
        profileAPI.getProfileInfo(coderId).then(data => {
            setInfo(data)
        });;
    }

    function fetchNumberOfContests() {
        profileAPI.getNumberOfContests(coderId).then(data => {
            setNumberOfContests(data)
        });;
    }

    function fetchNumberOfQuestions() {
        profileAPI.getNumberOfContests(coderId).then(data => {
            setNumberOfQuestions(data)
        });;
    }

    function fetchContests() {
        profileAPI.getContestOfCoder(coderId).then(data => {
            setContests(data)
            console.log(data);
        });;
    }

    function fetchReferrals() {
        profileAPI.getReferrals(coderId).then(data => {
            setReferrals(data)
        });;
    }

    useEffect(() => {
        fetchProfileInfo();
        fetchNumberOfContests();
        fetchNumberOfQuestions();
        fetchReferrals();
        fetchContests();
    }, [contest]);

    async function submitReferral(description) {
        const refer = {
            reason: description
          }
      
          if(refer.reason.trim() == "") {
            toast.error("You should wrtie your reasoning to be able ti give referral");
                return;
          }
          console.log(description);
           const response = await profileAPI.referCoder(localStorage.getItem('userId'), localStorage.getItem('referredId'), refer);
      
           if (response.messageType === MessageType.ERROR) {
            toast.error(response.message);
                setOpenDialog(false);
          } else {
              toast.success(response.message);
              setOpenDialog(false);

          }
    }

    return (
        <div>
            <Grid container spacing={1}>
                <Grid item xs={4}>
                    <Card variant="outlined" style={{ display: "inline-block", padding: "50px", margin: "10px" }} >
                        <CardMedia >
                            <Avatar
                                src="/img.png"
                                style={{
                                    margin: "10px",
                                    width: "300px",
                                    height: "300px",
                                    align: "center"
                                }}
                            />
                        </CardMedia>
                        <CardContent>
                            <Typography align='center' gutterBottom variant="h5" component="div">
                                {info.name}
                            </Typography>
                            <Typography align='center' gutterBottom variant="subtitle1" component="div">
                                {info.position}
                            </Typography>
                            { localStorage.getItem('viewer') == 'true' &&
                            <Box sx={{ m: 2 }} >
                                <Grid container justifyContent="center">
                                    <Button sx={{ mt: 6 }} align='center' variant="contained" color="primary" onClick={openReferDialog}>Give referral</Button>
                                </Grid>
                            </Box>}
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={2} direction="column" container justifyContent="center" style={{ marginRight: "20px", marginLeft: "20px" }}>
                    <ProfileCard data2={info.rating} data1='Ranking'></ProfileCard>                </Grid>
                <Grid item xs={2} direction="column" container justifyContent="center" style={{ marginRight: "20px", marginLeft: "20px" }}>
                    <ProfileCard data2={numberOfContest} data1='Contest'></ProfileCard>
                </Grid>
                <Grid direction="column" container justifyContent="center" item xs={2} style={{ marginRight: "20px", marginLeft: "20px" }}>
                    <ProfileCard data2={numberOfQuestions} data1='Questions'></ProfileCard>
                </Grid>
            </Grid>
            <Grid container spacing={1}>
                <Grid item xs={4}>
                    <Typography gutterBottom variant="h4" component="div" style={{ marginLeft: "20px" }}>
                        Awards
                    </Typography>
                    <ViewBadge content={contest}></ViewBadge>
                </Grid>
                <Grid item xs={7} >
                    <Typography gutterBottom variant="h4" component="div">
                        Referrals
                    </Typography>
                    <ViewReferral content={referrals} ></ViewReferral>
                </Grid>
            </Grid>
            <GiveReferralDialog open={openDialogName === true} handleClose={closeDialog} submitReferral= {submitReferral}></GiveReferralDialog>
            <ToastContainer />
        </div>
    );

}

export default Profile;
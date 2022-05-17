import React, { useState, useEffect } from "react";
import { Avatar, Card, CardMedia, CardContent, Typography, Grid, Button, Box } from "@material-ui/core";
import './Profile.css'
import ProfileCard from "../Coder/ProfileCard";
import ViewBadge from "../../../View/ViewBadge";

import NavBar from "../../NavBar/NavBar";
import { ProfileAPI } from "../Coder/ProfileAPI";

//The componenet
function CompanyProfile() {

    const [info, setInfo] = useState([]);
    const [numberOfContest, setNumberOfContests] = useState(0);
    const [numberOfQuestions, setNumberOfQuestions] = useState(0);
    const [contest, setContests] = useState([]);
    const [referrals, setReferrals] = useState([]);
    const profileAPI = new ProfileAPI();

    const coderId = localStorage.getItem('userId');
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
    }, []);

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
                            { localStorage.getItem('viewer') &&
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
            <GiveReferralDialog open={openDialogName === true} handleClose={closeDialog}></GiveReferralDialog>
        </div>
    );

}

export default CompanyProfile;
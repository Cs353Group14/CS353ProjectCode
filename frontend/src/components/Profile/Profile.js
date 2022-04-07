import React, {useState} from "react";
import {Avatar, Card, CardMedia, CardContent, Typography, Grid, Button, Box} from "@material-ui/core";
import './Profile.css'
import ProfileCard from "./ProfileCard";
import ViewReferral from "../../View/ViewReferral";
import ViewBadge from "../../View/ViewBadge";
import NavBar from "../NavBar/NavBar";
import ViewReferarlDialog from "./ViewReferralDialog";



//Dummy data for now

var profileInfoBasic = {
    name: "Name",
    surname: "Surname",
    position: "POSITION"
}

var profileInfo = 
{
    ranking: "2",
    contestAttended:  "10",
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

    return (
        <div>
            <NavBar></NavBar>
            <Grid container spacing={1}>
                <Grid item xs={4}>
                    <Card  variant="outlined" style={{display: "inline-block", padding: "50px", margin: "10px"}} >
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
                            <Typography  align='center' gutterBottom variant="h5" component="div">
                                {profileInfoBasic.name} {profileInfoBasic.surname}
                            </Typography>
                            <Typography  align='center' gutterBottom variant="subtitle1" component="div">
                                {profileInfoBasic.position} 
                            </Typography>
                            <Box sx={{m: 2}} >
                            <Grid container justifyContent="center">
                                <Button sx={{mt: 6}} align='center' variant="contained" color="primary" onClick={openReferDialog}>Give referral</Button>
                            </Grid>
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={2} direction="column" container justifyContent = "center" style={{ marginRight:"20px", marginLeft: "20px"}}>
                    <ProfileCard data2={profileInfo.ranking} data1='Ranking'></ProfileCard>                </Grid>
                <Grid item xs={2} direction="column" container justifyContent = "center"  style={{ marginRight:"20px", marginLeft: "20px"}}>
                    <ProfileCard data2={profileInfo.contestAttended} data1='Contest'></ProfileCard>
                </Grid>
                <Grid direction="column" container justifyContent = "center"  item xs={2} style={{ marginRight:"20px", marginLeft: "20px"}}>
                    <ProfileCard data2={profileInfo.solvedQuestions} data1='Questions'></ProfileCard>
                </Grid>
            </Grid>
            <Grid container spacing={1}>
                <Grid item xs={4}>
                <Typography  gutterBottom variant="h4" component="div" style={{ marginLeft: "20px"}}>
                        Awards
                    </Typography>
                    <ViewBadge content={awards}></ViewBadge>
                </Grid>
                <Grid item xs={7} >
                    <Typography  gutterBottom variant="h4" component="div">
                        Referrals
                    </Typography>
                    <ViewReferral content={referrals} ></ViewReferral>
                </Grid>
            </Grid>
        <ViewReferarlDialog open={openDialogName === true} handleClose={closeDialog}></ViewReferarlDialog>
     </div>
    );

}

export default Profile;
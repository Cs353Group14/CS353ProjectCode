import React, {useState} from "react";
import {Avatar, Card, CardMedia, CardContent, Typography, Grid, Button, Box} from "@material-ui/core";
import ProfileCard from "../Coder/ProfileCard";
import ViewReferral from "../../../View/ViewReferral";
import ViewBadge from "../../../View/ViewBadge";
import NavBar from "../../NavBar/NavBar";




//Dummy data for now

var profileInfoBasic = {
    name: "Name",
    surname: "Surname",
    position: "POSITION"
}

var profileInfo = 
{
    coding: "234",
    nonCoding:  "1023",
    contests: "34"
}

var contests = [
    {
        contestName: "Contest1"
    
    },
    {
        contestName: "Contest2"
    },
    {
        contestName: "Contest3"
    },
    {
        contestName: "Contest4"
    }
]

var topQuestions = 
[
    {
        name: "X question",
        difficulty: "Hard",
        description: "Hard question! Hard question! Hard question! Hard question! Hard question! Hard question! Hard question!"
    },
    {
        name: "A question",
        difficulty: "Hard",
        description: "Hard question! Hard question! Hard question! Hard question! Hard question! Hard question! Hard question!"
    },
    {
        name: "B question",
        difficulty: "Hard",
        description: "Hard question! Hard question! Hard question! Hard question! Hard question! Hard question! Hard question!"
    },
    {
        name: "C question",
        difficulty: "Hard",
        description: "Hard question! Hard question! Hard question! Hard question! Hard question! Hard question! Hard question!"
    }
]


//The componenet
function EditorProfile() {
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
                                <Button sx={{mt: 6}} align='center' variant="contained" color="primary">Ask for referral</Button>
                            </Grid>
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={2} direction="column" container justifyContent = "center" style={{ marginRight:"20px", marginLeft: "20px"}}>
                    <ProfileCard data2={profileInfo.coding} data1='Coding Questions'></ProfileCard>                </Grid>
                <Grid item xs={2} direction="column" container justifyContent = "center"  style={{ marginRight:"20px", marginLeft: "20px"}}>
                    <ProfileCard data2={profileInfo.noncoding} data1='Non Coding Questions'></ProfileCard>
                </Grid>
                <Grid direction="column" container justifyContent = "center"  item xs={2} style={{ marginRight:"20px", marginLeft: "20px"}}>
                    <ProfileCard data2={profileInfo.contests} data1='Contests'></ProfileCard>
                </Grid>
            </Grid>
            <Grid container spacing={1}>
                <Grid item xs={4}>
                <Typography  gutterBottom variant="h4" component="div" style={{ marginLeft: "20px"}}>
                        Created Contests
                    </Typography>
                    <ViewBadge content={contests}></ViewBadge>
                </Grid>
                <Grid item xs={7} >
                    <Typography  gutterBottom variant="h4" component="div">
                        Top Questions
                    </Typography>
                    <ViewReferral content={topQuestions} ></ViewReferral>
                </Grid>
            </Grid>
     </div>
    );
}

export default EditorProfile;
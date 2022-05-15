import React, {useState} from "react";
import {Avatar, Card, CardMedia, CardContent, Typography, Grid, Button, Box} from "@material-ui/core";
import ProfileCard from "../Coder/ProfileCard";
import ViewReferral from "../../../View/ViewReferral";
import ViewBadge from "../../../View/ViewBadge";
import NavBar from "../../NavBar/NavBar";
import ViewQuestions from "../../../View/ViewQuestions";




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
        contestName: "Contest1",
        date: "12/12/2022"
    
    },
    {
        contestName: "Contest2",
        date: "12/12/2022"

    },
    {
        contestName: "Contest3",
        date: "12/12/2022"

    },
    {
        contestName: "Contest4",
        date: "12/12/2022"

    }
]

var topQuestions = 
[
    {
        id: 10,
        title: "Coin Change",
        category: "Array Dynamic-Programming Breadth-FirstSearch",
        difficulity: "Medium",
        point: 35,
        attemptNo:  102,
        acceptedNo: 63,
    },
    {
        id: 1,
        title: "Max sum",
        category: "Array Hash-Table",
        difficulity: "Easy",
        point: 20,
        attemptNo:  54,
        acceptedNo: 43
    },
    {
        id: 2,
        title: "Max product",
        category: "Array Hash-Table Prefix-Sum",
        difficulity: "Easy",
        point: 20,
        attemptNo:  41,
        acceptedNo: 35
    },
    {
        id: 3,
        title: "Scheduling",
        category: "Heap",
        difficulity: "Easy",
        point: 25,
        attemptNo:  66,
        acceptedNo: 57
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
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={2} direction="column" container justifyContent = "center" style={{ marginRight:"20px", marginLeft: "20px"}}>
                    <ProfileCard data2={profileInfo.coding} data1='Coding Questions'></ProfileCard>                </Grid>
                <Grid item xs={2} direction="column" container justifyContent = "center"  style={{ marginRight:"20px", marginLeft: "20px"}}>
                    <ProfileCard data2={profileInfo.nonCoding} data1='Non Coding Questions'></ProfileCard>
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
                    <ViewQuestions content={topQuestions}></ViewQuestions>
                </Grid>
            </Grid>
     </div>
    );
}

export default EditorProfile;
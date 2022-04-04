import React from "react";
import {Avatar, Card, CardMedia, CardContent, Typography, Grid} from "@material-ui/core";
import './Profile.css'
import ProfileCard from "./ProfileCard";
import ViewCard from "../../View/ViewCard";
import ReferralCard from "./ReferralCard";


function Profile() {
    return (
        <div>
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
                                Name Surname
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={2} style={{ marginRight:"20px", marginLeft: "20px"}}>
                    <ProfileCard img='/img.png' data='Rating'></ProfileCard>
                </Grid>
                <Grid item xs={2} style={{ marginRight:"20px", marginLeft: "20px"}}>
                    <ProfileCard img='/img.png' data='Contest'></ProfileCard>
                </Grid>
                <Grid item xs={2} style={{ marginRight:"20px", marginLeft: "20px"}}>
                    <ProfileCard img='/img.png' data='Questions'></ProfileCard>
                </Grid>
            </Grid>
            <Grid container spacing={1}>
                <Grid item xs={4}>
                <Typography  gutterBottom variant="h4" component="div" style={{ marginLeft: "20px"}}>
                        Awards
                    </Typography>
                    <ViewCard content={<ProfileCard></ProfileCard>}></ViewCard>
                </Grid>
                <Grid item xs={7} >
                    <Typography  gutterBottom variant="h4" component="div">
                        Referrals
                    </Typography>
                    <ViewCard content={<ReferralCard></ReferralCard>} ></ViewCard>
                </Grid>
            </Grid>
        
     </div>
    );

}

export default Profile;
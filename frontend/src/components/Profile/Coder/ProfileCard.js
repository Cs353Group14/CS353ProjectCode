import React, {useState} from "react";
import {Button, Avatar, Card, CardMedia, CardContent, Typography, style} from "@material-ui/core";
import './Profile.css'

function ProfileCard(props) 
{
    return (<div>
        <Card variant="outlined" >
           <CardContent>
           <Typography  align='center' gutterBottom variant="h5" component="div"> {props.data2}</Typography>
       <Typography  align='center' gutterBottom variant="h5" component="div">
           {props.data1}
       </Typography>
   </CardContent>
     </Card>
    </div>);
}
export default ProfileCard;
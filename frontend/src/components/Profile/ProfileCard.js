import React, {useState} from "react";
import {Button, Avatar, Card, CardMedia, CardContent, Typography, style} from "@material-ui/core";
import './Profile.css'

function ProfileCard(props) 
{
    return (<div>
        <Card variant="outlined" >
            <CardMedia  align='center'>
                <Avatar align='center'
                    src={props.img}
                    style={{
                        margin: "10px",
                        width: "50px",
                        height: "50px",
                        align: "center"}} 
                />
           </CardMedia>
           <CardContent>
       <Typography  align='center' gutterBottom variant="h5" component="div">
           {props.data}
       </Typography>
   </CardContent>
     </Card>
    </div>);
}
export default ProfileCard;
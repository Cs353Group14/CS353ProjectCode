import React, {useState} from "react";
import {Button, Avatar, Card, CardMedia, CardContent, Typography, style, CardHeader} from "@material-ui/core";
import './Profile.css'

function ProfileBadge(props) 
{
    return (<Card  style={{minWidth:'100%'}}>
        <CardHeader
          avatar={
            <Avatar aria-label="recipe" style={{
                margin: "10px",
                width: "100px",
                height: "100px",
                align: "center"
            }} >
                
              :)
            </Avatar>
          }
          title="Contest 1"
          titleTypographyProps={{variant:'h5' }}
          subheader="1st place"
        />
      </Card>);
}
export default ProfileBadge;
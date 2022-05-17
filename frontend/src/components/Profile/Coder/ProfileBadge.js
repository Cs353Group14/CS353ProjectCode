import React, {useState} from "react";
import {Button, Avatar, Card, CardMedia, CardContent, Typography, style, CardHeader} from "@material-ui/core";
import './Profile.css'

function ProfileBadge(props) 
{
    return (<Card  style={{minWidth:'100%'}}>
        <CardHeader
          title={props.title}
          titleTypographyProps={{variant:'h5' }}
          subheader={props.rank}
        />
      </Card>);
}
export default ProfileBadge;
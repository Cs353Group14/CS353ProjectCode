import React, {useState} from "react";
import Card from "@material-ui/core/Card";
import CardHeader from "@material-ui/core/CardHeader";
import {Typography} from "@material-ui/core";

function NotificationCard(props) {
    return(<div>
            <Card >
            <CardHeader
            title= {props.type}
            titleTypographyProps={{variant:'h5' }}
            subheader={props.notifDate}
            subheaderTypographyProps={{variant: 'subtitle1'}}
            />
            <Typography style={{padding: "20px"}}>{props.ninfo}</Typography>
        </Card>
      </div>);
}

export default NotificationCard;
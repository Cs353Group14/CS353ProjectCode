import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardHeader from "@material-ui/core/CardHeader";
import Avatar from "@material-ui/core/Avatar";
import IconButton from "@material-ui/core/IconButton";

import {Typography} from "@material-ui/core";


const useStyles = makeStyles({ 
    root: {
      
    }
  });

function ReferralCard(props) {
    const classes = useStyles();
    return(<Card className={classes.root}>
        <CardHeader
          avatar={
            <Avatar aria-label="recipe" className={classes.avatar} style={{
                margin: "10px",
                width: "100px",
                height: "100px",
                align: "center"
            }} src={props.img}>      

            </Avatar>
          }
          title= {props.name}
          titleTypographyProps={{variant:'h5' }}
          subheader={props.position}
          subheaderTypographyProps={{variant: 'h7'}}
          action=''
        />
        <Typography style={{padding: "20px"}}>{props.description}</Typography>
      </Card>);
}

export default ReferralCard;
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

function ReferralCard() {
    const classes = useStyles();
    return(<Card className={classes.root}>
        <CardHeader
          avatar={
            <Avatar aria-label="recipe" className={classes.avatar} style={{
                margin: "10px",
                width: "100px",
                height: "100px",
                align: "center"
            }} >
                
              :)
            </Avatar>
          }
          title="Name Surnamse"
          titleTypographyProps={{variant:'h5' }}
          subheader="Google Engineer"
          subheaderTypographyProps={{variant: 'h7'}}
        />
        <Typography style={{padding: "20px"}}>X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder! X is an amazing coder!</Typography>
      </Card>);
}

export default ReferralCard;
import React, {useState} from "react";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardHeader from "@material-ui/core/CardHeader";
import Avatar from "@material-ui/core/Avatar";
import IconButton from "@material-ui/core/IconButton";
import { ButtonBase, CardActionArea } from "@material-ui/core";
import {Typography} from "@material-ui/core";

function OtherAnswerCard(props) {
    return(<div>
            <Card >
            <CardHeader
            avatar={
                <Avatar aria-label="recipe"  style={{
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
            subheaderTypographyProps={{variant: 'subtitle1'}}
            />
            <Typography style={{padding: "20px"}}>{props.answer}</Typography>
        </Card>
      </div>);
}

export default OtherAnswerCard;
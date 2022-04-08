import React, {useState, useStyle} from "react";
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';


export default function NonCodingCard(props) {

    function setActivityId() {
        localStorage.setItem('nonCodingId', props.id);
        console.log(localStorage.getItem('nonCodingId'));
    }

    return (
        
        <Card className={"coding-card"}>
            <CardContent>
                <Typography variant="h5" component="h2">
                    {props.title}
                </Typography>
                <Typography className={"coding-card-info"} color="textSecondary">
                <br />
                    Difficulity: {props.difficulity} 
                </Typography>
                <Typography className={"coding-card-info"}  variant="body2" component="p" color="textSecondary">
                    <br />
                    Category: {props.category}
                </Typography>
            </CardContent>
            <CardActions>
                <Button size="small" onClick = {setActivityId} href={("/non-coding-challenges/" + props.id )} variant= "outlined">See Challenge</Button>
            </CardActions>
        </Card>
    );
}

import React, {useState, useStyle} from "react";
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';


export default function NonCodingCard(props) {

    function setNonCodingId() {
        localStorage.setItem('nonCodingId', props.id);
        console.log(localStorage.getItem('nonCodingId'));

        if(localStorage.getItem('usertype') == 1) {
            window.location.href = "http://localhost:3000/non-coding-challenges/" + props.id;
        } else {
            window.location.href = "http://localhost:3000/non-coding-challenges-noncoder/" + props.id;
        }
    }

    return (
        
        <Card className={"coding-card"}>
            <CardContent>
                <Typography variant="h5" component="h2">
                    {props.title}
                </Typography>
                <Typography className={"coding-card-info"} color="textSecondary">
                <br />
                    Difficulty: {props.difficulty} 
                </Typography>
                <Typography className={"coding-card-info"}  variant="body2" component="p" color="textSecondary">
                    <br />
                    
                </Typography>
            </CardContent>
            <CardActions>
                <Button size="small" onClick = {setNonCodingId} variant= "outlined">See Challenge</Button>
            </CardActions>
        </Card>
    );
}

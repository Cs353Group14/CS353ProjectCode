import React, {useState} from "react";
import { Avatar, ListItem, List,ListItemAvatar, ListItemText, Grid } from "@material-ui/core";
import OtherAnswerCard from "./OtherAnswersCard";

function OtherAnswerList(props)
{
    if(props.solved == true){
        const listItems = props.content.map((d) => <ListItem key={d.username}> <OtherAnswerCard img={d.img} username={d.username} position={d.position} answer={d.answer}> </OtherAnswerCard> </ListItem> );
        return (
            <Grid item xs={12} direction="column" container justifyContent = "center" >
                <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
                {listItems}
                </List>
            </Grid>);
    }
    else {
        return(<div>
            <h3>To see others's answer, you should first submit an answer</h3>
        </div>)
    }
        
}

export default OtherAnswerList;

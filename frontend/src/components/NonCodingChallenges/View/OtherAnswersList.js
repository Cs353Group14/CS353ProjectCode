import React, {useState} from "react";
import { Avatar, ListItem, List,ListItemAvatar, ListItemText, Grid } from "@material-ui/core";
import OtherAnswerCard from "./OtherAnswersCard";

function OtherAnswerList(props)
{
    const listItems = props.content.map((d) => <ListItem key={d.username}> <OtherAnswerCard img={d.img} username={d.username} position={d.position} answer={d.answer}> </OtherAnswerCard> </ListItem> );
    return (
        <Grid item xs={12} direction="column" container justifyContent = "center" >
            <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
            {listItems}
            </List>
        </Grid>);
}

export default OtherAnswerList;

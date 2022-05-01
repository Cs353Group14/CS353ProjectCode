import React, {useState} from "react";
import { Avatar, ListItem, List,ListItemAvatar, ListItemText, Grid } from "@material-ui/core";
import OtherAnswerCard from "./OtherAnswersCard";

function OtherAnswerList(props)
{
    console.log(props[0]);
    const listItems = props.content.map((d) => <ListItem key={d.name}> <OtherAnswerCard img={d.img} name={d.name} position={d.position} answer={d.answer}> </OtherAnswerCard> </ListItem> );
    return (
        <Grid item xs={12} direction="column" container justifyContent = "center" >
            <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
            {listItems}
            </List>
        </Grid>);
}

export default OtherAnswerList;

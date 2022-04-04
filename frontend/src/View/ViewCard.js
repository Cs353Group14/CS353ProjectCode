import React, {useState} from "react";
import { Avatar, ListItem, List,ListItemAvatar, ListItemText, Grid } from "@material-ui/core";

function ViewCard(props)
{
    return (
        <Grid item xs={12} direction="column" container justify = "center" >

    <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        <ListItem>
        {props.content}
        </ListItem>
        <ListItem>
        {props.content}
        </ListItem>
        <ListItem>
        {props.content}
        </ListItem>
  </List>
  </Grid>);
}
export default ViewCard;

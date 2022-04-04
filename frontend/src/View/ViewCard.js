import React, {useState} from "react";
import { Avatar, ListItem, List,ListItemAvatar, ListItemText } from "@material-ui/core";

function ViewCard(props)
{
    return (
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
  </List>);
}
export default ViewCard;

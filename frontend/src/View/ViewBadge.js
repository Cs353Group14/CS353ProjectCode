import React, {useState} from "react";
import { Avatar, ListItem, List,ListItemAvatar, ListItemText, Grid } from "@material-ui/core";
import ProfileBadge from "../components/Profile/Coder/ProfileBadge"

function ViewBadge(props)
{
    const listItems = props.content.map((d) => <ListItem key={d.contestName}> <ProfileBadge  title={d.contestName} place={d.place}> </ProfileBadge> </ListItem> );

    return (
        <Grid item xs={12} direction="column" container justifyContent = "center" >
            <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
            {listItems}
            </List>
        </Grid>);
}

export default ViewBadge;

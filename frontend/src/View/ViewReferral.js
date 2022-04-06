import React, {useState} from "react";
import { Avatar, ListItem, List,ListItemAvatar, ListItemText, Grid } from "@material-ui/core";
import ReferralCard from "../components/Profile/ReferralCard";

function ViewCard(props)
{
    const listItems = props.content.map((d) => <ListItem> <ReferralCard img={d.img} name={d.name} position={d.position} description={d.description}> </ReferralCard> </ListItem> );

    return (
        <Grid item xs={12} direction="column" container justify = "center" >
            <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
            {listItems}
            </List>
        </Grid>);
}

export default ViewCard;

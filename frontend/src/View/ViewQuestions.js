import React, {useState} from "react";
import { Avatar, ListItem, List,ListItemAvatar, ListItemText, Grid } from "@material-ui/core";
import CodingCard from "../components/CodingChallenges/CodingCard/CodingCard";

function ViewReferral(props)
{
    const listItems = props.content.map((question) => <ListItem key={question.id}> <CodingCard key={question.id}
    id={question.id}
    title = {question.title}
    category = {question.category}
    difficulity = {question.difficulity}
    point = {question.point}
    attemptNo = {question.attemptNo}
    acceptedNo = {question.acceptedNo}> </CodingCard> </ListItem> );
    return (
        <Grid item xs={12} direction="column" container justifyContent = "center" >
            <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
            {listItems}
            </List>
        </Grid>);
}

export default ViewReferral;

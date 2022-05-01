import { Divider } from "@material-ui/core";
import React, { useEffect, useState } from "react";
import { CodingChallengeApi } from "../CodingChallengeApi";

function CodingChallengeInformation(props) {

    console.log(props.info.categories);
    return(
        <div className="coding-challenge-info-root">
            <Divider/>
            <h4>Title</h4>
            {props.title}

            <Divider/>
            <h4>Author</h4>
            {props.info.authorName}

            <Divider/>
            <h4>Categories</h4>
            
            {props.info.categories.map(category => {
                return <li>{category}</li>
            })}

            <Divider/>
            <h4>Difficulty</h4>
            {props.difficulty}

            <Divider/>
            <h4>Point</h4>
            {props.point}

            <Divider/>
            <h4>Number of Attempts</h4>
            {props.attemptNo}

            <Divider/>
            <h4>Number of Accepted Answers</h4>
            {props.acceptedNo}
            <Divider/>
        </div>
    );
}

export default CodingChallengeInformation;
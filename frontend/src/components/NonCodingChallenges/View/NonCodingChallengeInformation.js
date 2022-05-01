import { Divider } from "@material-ui/core";
import React from "react";

const info = {
    id: 10,
    title: "Non-coding question",
    category: "Database systems",
    difficulity: "Medium",
    point: 35,
    attemptNo:  102
}
function NonCodingChallengeInformation(props) {

    return(
        <div className="coding-challenge-info-root">
            <Divider/>
            <h4>Title</h4>
            {props.title}

            <Divider/>
            <h4>Author</h4>
            

            <Divider/>
            <h4>Categories</h4>
            

            <Divider/>
            <h4>Difficulity</h4>
            {props.difficulty}

        </div>
    );
}

export default NonCodingChallengeInformation;
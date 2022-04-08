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
function NonCodingChallengeInformation() {

    return(
        <div className="coding-challenge-info-root">
            <Divider/>
            <h4>Title</h4>
            {info.title}

            <Divider/>
            <h4>Author</h4>
            {info.author}

            <Divider/>
            <h4>Categories</h4>
            {info.category}

            <Divider/>
            <h4>Difficulity</h4>
            {info.difficulity}

            <Divider/>
            <h4>Point</h4>
            {info.point}

        </div>
    );
}

export default NonCodingChallengeInformation;
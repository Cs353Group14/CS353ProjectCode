import { Divider } from "@material-ui/core";
import React from "react";

const info = {
    id: 10,
    title: "Coin Change",
    category: "Array Dynamic-Programming Breadth-FirstSearch",
    difficulity: "Medium",
    point: 35,
    attemptNo:  102,
    acceptedNo: 63,
    author:"Hakan Yılmaz"
}
function CodingChallengeInformation() {

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

            <Divider/>
            <h4>Number of Attempts</h4>
            {info.attemptNo}

            <Divider/>
            <h4>Number of Accepted Answers</h4>
            {info.acceptedNo}
            <Divider/>
        </div>
    );
}

export default CodingChallengeInformation;
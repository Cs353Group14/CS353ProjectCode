import { Button } from "@material-ui/core";
import React from "react";
import CodingCardContainer from "../CodingChallenges/CodingCard/CodingCardContainer";
import NavBar from "../NavBar/NavBar";

export default function ContestChallenges() {

    function handleFinish() {
        localStorage.removeItem('contestId');
        localStorage.setItem('inContest', false);
        window.location.href = "http://localhost:3000/home";
    }

    return(
        <div>
            <NavBar/>
            <CodingCardContainer inContest = {true}/>
            <div text-align = "left" >
                <Button variant="contained" color = "primary" onClick={handleFinish}>Finish</Button>
            </div>
        </div>
        
    );
}
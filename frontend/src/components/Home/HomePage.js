import React, {useEffect, useState} from "react";
import CodingCardContainer from "../CodingChallenges/CodingCard/CodingCardContainer";
import CreateCodingChallenge from "../CodingChallenges/CreateCodingChallenges/CreateCodingInterview";
import NavBar from "../NavBar/NavBar";
import NonCodingCardContainer from "../NonCodingChallenges/NonCodingCard/NonCodingCardContainer";

export default function HomePage() {

    const [codingChallengesHidden, setCodingChallengesHidded] = useState(false);
    const [noncodingChallengesHidden, setNoncodingChallengesHidded] = useState(true);
    const [createCodingChallengeHidden, setCreateCodingChallengeHidded] = useState(true);


    function makesVisible(componentNumber) {
        if(componentNumber == 1){
            console.log("here");
            setCodingChallengesHidded(false);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
        }
        else if(componentNumber == 2){
            console.log("there");
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(false);
            setCreateCodingChallengeHidded(true);
        } else if(componentNumber == 3){
            console.log("there");
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(false);
        }
    }

    useEffect(() => {
    },[]);

    return(
        <div>
            <NavBar makesVisible = {makesVisible}/>
            <div hidden = {codingChallengesHidden} >
                <CodingCardContainer />
            </div>
            <div hidden = {noncodingChallengesHidden} >
                <NonCodingCardContainer />
            </div>
            <div hidden = {createCodingChallengeHidden} >
                <CreateCodingChallenge />
            </div>
        </div>
    );
}
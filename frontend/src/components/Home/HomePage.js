import React, {useEffect, useState} from "react";
import CodingCardContainer from "../CodingChallenges/CodingCard/CodingCardContainer";
import NavBar from "../NavBar/NavBar";
import NonCodingCardContainer from "../NonCodingChallenges/NonCodingCard/NonCodingCardContainer";

export default function HomePage() {

    const [codingChallengesHidden, setCodingChallengesHidded] = useState(false);
    const [noncodingChallengesHidden, setNoncodingChallengesHidded] = useState(true);


    function makesVisible(componentNumber) {
        if(componentNumber == 1){
            console.log("here");
            setCodingChallengesHidded(false);
            setNoncodingChallengesHidded(true);
        }
        else {
            console.log("there");
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(false);
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
        </div>
    );
}
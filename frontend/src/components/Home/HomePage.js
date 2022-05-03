import React, {useEffect, useState} from "react";
import CodingCardContainer from "../CodingChallenges/CodingCard/CodingCardContainer";
import CreateCodingChallenge from "../CodingChallenges/CreateCodingChallenges/CreateCodingInterview";
import CreateContest from "../Contest/CreateContest/CreateContest";
import CreateInterview from "../Interview/CreateInterview/CreateInterview";
import NavBar from "../NavBar/NavBar";
import CreateNonCodingQuestion from "../NonCodingChallenges/CreateNonCodingQuestions/CreateNonCodingChallenge";
import NonCodingCardContainer from "../NonCodingChallenges/NonCodingCard/NonCodingCardContainer";

export default function HomePage() {

    const [codingChallengesHidden, setCodingChallengesHidded] = useState(false);
    const [noncodingChallengesHidden, setNoncodingChallengesHidded] = useState(true);
    const [createCodingChallengeHidden, setCreateCodingChallengeHidded] = useState(true);
    const [createNonCodingChallengeHidden, setNonCreateCodingChallengeHidded] = useState(true);
    const [createInterview, setCreateInterview] = useState(true);
    const [createContest, setCreateContest] = useState(true);


    function makesVisible(componentNumber) {
        if(componentNumber == 1){
            setCodingChallengesHidded(false);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
        }
        else if(componentNumber == 2){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(false);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
        } else if(componentNumber == 3){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(false);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
        } else if(componentNumber == 4){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(false);
            setCreateInterview(true);
            setCreateContest(true);
        } else if(componentNumber == 5){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(false);
            setCreateContest(true);
        }else if(componentNumber == 6){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(false);
        }
    }

    useEffect(() => {
        makesVisible(localStorage.getItem('menuId'));
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
            <div hidden = {createNonCodingChallengeHidden} >
                <CreateNonCodingQuestion />
            </div>
            <div hidden = {createInterview} >
                <CreateInterview />
            </div>
            <div hidden = {createContest} >
                <CreateContest />
            </div>
        </div>
    );
}
import React, {useEffect, useState} from "react";
import CodingCardContainer from "../CodingChallenges/CodingCard/CodingCardContainer";
import CreateCodingChallenge from "../CodingChallenges/CreateCodingChallenges/CreateCodingInterview";
import ContinuesContests from "../Contest/ContestTables/ContinuesContests";
import FinishedContests from "../Contest/ContestTables/FinishedContests";
import NonRegisteredContests from "../Contest/ContestTables/NonRegisteredContests";
import RegisteredContests from "../Contest/ContestTables/RegisteredContests";
import CreateContest from "../Contest/CreateContest/CreateContest";
import CreateInterview from "../Interview/CreateInterview/CreateInterview";
import NavBar from "../NavBar/NavBar";
import CreateNonCodingQuestion from "../NonCodingChallenges/CreateNonCodingQuestions/CreateNonCodingChallenge";
import NonCodingCardContainer from "../NonCodingChallenges/NonCodingCard/NonCodingCardContainer";
import InterviewListForCoder from "../Interview/InterviewListForCoder";
import InterviewListForCompany from "../Interview/InterviewListForCompany";

export default function HomePage() {

    const [codingChallengesHidden, setCodingChallengesHidded] = useState(false);
    const [noncodingChallengesHidden, setNoncodingChallengesHidded] = useState(true);
    const [createCodingChallengeHidden, setCreateCodingChallengeHidded] = useState(true);
    const [createNonCodingChallengeHidden, setNonCreateCodingChallengeHidded] = useState(true);
    const [createInterview, setCreateInterview] = useState(true);
    const [createContest, setCreateContest] = useState(true);
    const [nonRegisteredContests, setNonRegisteredContests] = useState(true);
    const [registeredContests, setRegisteredContests] = useState(true);
    const [continuesContests, setContinuesContests] = useState(true);
    const [finishedContests, setFinishedContests] = useState(true);
    const [pastInterviews, setPastInterviews] = useState(true);
    const [newInterviews, setNewInterviews] = useState(true);
    const [listOfInterviews, setListOfInterviews] = useState(true);



    function makesVisible(componentNumber) {
        if(componentNumber == 1){
            setCodingChallengesHidded(false);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
            setNonRegisteredContests(true);
            setRegisteredContests(true);
            setContinuesContests(true);
            setFinishedContests(true);
            setPastInterviews(true);
            setNewInterviews(true);
            setListOfInterviews(true);
        }
        else if(componentNumber == 2){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(false);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
            setNonRegisteredContests(true);
            setRegisteredContests(true);
            setContinuesContests(true);
            setFinishedContests(true);
            setPastInterviews(true);
            setNewInterviews(true);
            setListOfInterviews(true);
        } else if(componentNumber == 3){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(false);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
            setNonRegisteredContests(true);
            setRegisteredContests(true);
            setContinuesContests(true);
            setFinishedContests(true);
            setPastInterviews(true);
            setNewInterviews(true);
            setListOfInterviews(true);
        } else if(componentNumber == 4){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(false);
            setCreateInterview(true);
            setCreateContest(true);
            setNonRegisteredContests(true);
            setRegisteredContests(true);
            setContinuesContests(true);
            setFinishedContests(true);
            setPastInterviews(true);
            setNewInterviews(true);
            setListOfInterviews(true);
        } else if(componentNumber == 5){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(false);
            setCreateContest(true);
            setNonRegisteredContests(true);
            setRegisteredContests(true);
            setContinuesContests(true);
            setFinishedContests(true);
            setPastInterviews(true);
            setNewInterviews(true);
            setListOfInterviews(true);
        }else if(componentNumber == 6){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(false);
            setNonRegisteredContests(true);
            setRegisteredContests(true);
            setContinuesContests(true);
            setFinishedContests(true);
            setPastInterviews(true);
            setNewInterviews(true);
            setListOfInterviews(true);
        }else if(componentNumber == 7){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
            setNonRegisteredContests(false);
            setRegisteredContests(true);
            setContinuesContests(true);
            setFinishedContests(true);
             setPastInterviews(true);
            setNewInterviews(true);
            setListOfInterviews(true);
        }else if(componentNumber == 8){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
            setNonRegisteredContests(true);
            setRegisteredContests(false);
            setContinuesContests(true);
            setFinishedContests(true);
            setPastInterviews(true);
            setNewInterviews(true);
            setListOfInterviews(true);
        }else if(componentNumber == 9){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
            setNonRegisteredContests(true);
            setRegisteredContests(true);
            setContinuesContests(false);
            setFinishedContests(true);
            setPastInterviews(true);
            setNewInterviews(true);
            setListOfInterviews(true);
        }else if(componentNumber == 10){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
            setNonRegisteredContests(true);
            setRegisteredContests(true);
            setContinuesContests(true);
            setFinishedContests(false);
            setPastInterviews(true);
            setNewInterviews(true);
            setListOfInterviews(true);
        }
        else if(componentNumber == 11){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
            setNonRegisteredContests(true);
            setRegisteredContests(true);
            setContinuesContests(true);
            setFinishedContests(true);
            setPastInterviews(false);
            setNewInterviews(true);
            setListOfInterviews(true);
        }
        else if(componentNumber == 12){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
            setNonRegisteredContests(true);
            setRegisteredContests(true);
            setContinuesContests(true);
            setFinishedContests(true);
            setPastInterviews(true);
            setNewInterviews(false);
            setListOfInterviews(true);
        }
        else if(componentNumber == 13){
            setCodingChallengesHidded(true);
            setNoncodingChallengesHidded(true);
            setCreateCodingChallengeHidded(true);
            setNonCreateCodingChallengeHidded(true);
            setCreateInterview(true);
            setCreateContest(true);
            setNonRegisteredContests(true);
            setRegisteredContests(true);
            setContinuesContests(true);
            setFinishedContests(true);
            setPastInterviews(true);
            setNewInterviews(true);
            setListOfInterviews(false);
        }
    }

    useEffect(() => {
        makesVisible(localStorage.getItem('menuId'));
    },[]);

    return(
        <div>
            <NavBar makesVisible = {makesVisible}/>
            <div hidden = {codingChallengesHidden} >
                <CodingCardContainer inContest = {false} />
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
            <div hidden = {nonRegisteredContests} >
                <NonRegisteredContests />
            </div>
            <div hidden = {registeredContests} >
                <RegisteredContests />
            </div>
            <div hidden = {continuesContests} >
                <ContinuesContests />
            </div>
            <div hidden = {finishedContests} >
                <FinishedContests />
            </div>
            <div hidden = {pastInterviews} >
                <InterviewListForCoder />
            </div>
            <div hidden = {newInterviews} >
                <InterviewListForCoder />
            </div>
            <div hidden = {listOfInterviews} >
                <InterviewListForCompany />
            </div>
            
        </div>
    );
}
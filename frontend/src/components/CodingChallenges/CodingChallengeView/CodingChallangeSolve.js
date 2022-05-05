import { Button, Paper, TextField } from '@material-ui/core';
import React, { useState } from 'react';
import { ContestApi } from '../../Contest/ContestApi';
import {CodingChallengeApi} from '../CodingChallengeApi'

function CodingChallengeSolve(props) {

    const title = "Coin Change";
    const [description, setDescription] = useState("You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money. \n" +

    "Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1. \n "+
    
    "You may assume that you have an infinite number of each kind of coin. \n \n" +
    "Example: \n \n" +

    "Input: coins = [1,2,5], amount = 11 \n" +
    "Output: 3 \n"+
    "Explanation: 11 = 5 + 5 + 1\n"+
 
    "\nConstraints: \n\n"+

    "1 <= coins.length <= 12\n"+
    "1 <= coins[i] <= 231 - 1\n"+
    "0 <= amount <= 104);\n");
    
    const [code, setCode] = useState("");
    const[language, setLanguage] = useState("Choose Programming Language");

    const languages = ["Java", "C", "C++", "C#", "Python", "Javascript"];

    const codingChallengeApi = new CodingChallengeApi();
    const contestApi = new ContestApi();

    function SplitParagraph() {
        return props.description.split('\n')
        .map( (text,i) => <p key = {i}>{text}</p>);
    }

    function handleNewLanguage(event) {
        setLanguage(event.target.name);
    }

    function SetLanguages(){
        return languages.map((lng, i) => <a key = {i} 
                                        name = {lng}
                                        onClick ={handleNewLanguage}>
                                        {lng}
                                        </a>);
    }

    function handleNewCode(event) {
        setCode(event.target.value);
    }

    async function handleSubmit() {

        if(localStorage.getItem('inContest') == 'true') {
            console.log("here");
            const data = await contestApi.getContestStatus(localStorage.getItem('contestId'));

            if(data == 2 ){
                alert("Time is up");
                return;
            }
        }

        const solution = {
            answer: code,
            programming_language: language,
            submission_id: -1,
            pass_result: 0,
            fail_result: 0,
            submission_time: "2022-01-01"
        }
        codingChallengeApi.submitSolution(solution);
    }

    return(
        <div className= "coding-challenge-view-body">              
                
        <div className = "coding-challenge-view-left">
            
            <h2>Descripition:</h2>

            <h3>{props.title}</h3> 

            <SplitParagraph/>

        </div>

        <div className = "coding-challenge-view-right">
        <h2>Code:</h2>
        <div className="dropdown">
  <div class="dropPpr" elevation={3}>{language}</div>
  <div class="dropdown-content">
    <SetLanguages/>
  </div>
</div>

         <TextField
         fullWidth
          id="filled-multiline-static"
          multiline
          minRows={30}
          defaultValue=""
          variant="filled"
          onChange={handleNewCode}
        />
         
         <div className='coding-submission-button'>
            <Button variant="contained"  color="primary" onClick = {handleSubmit} > Submit </Button>
         </div>
         </div>

    </div>

    );

}

export default CodingChallengeSolve
import { Button, Paper, TextField } from '@material-ui/core';
import React, { useState } from 'react';
import { NonCodingChallengeApi } from '../NonCodingChallengeApi';

function NonCodingChallengeSolve(props) {

    const title = "Non coding question";
    const [description, setDescription] = useState("What are the various types of relationships in Database? Define them." );
    
    const [answer, setAnswer] = useState("");

    const nonCodingChallengeApi = new NonCodingChallengeApi();

    function SplitParagraph() {
        return props.description.split('\n')
        .map( (text,i) => <p key = {i}>{text}</p>);
    }

    function handleNewAnswer(event) {
        setAnswer(event.target.value);
    }

    function submitAnswer() {
        const reply = {
            nonChallengeId: localStorage.getItem('nonCodingId'),
            userId: localStorage.getItem('userId'),
            answer: answer,
            theResult: "",
            replyTime: "2022-01-01"}

            nonCodingChallengeApi.submitAnswer(reply);
    }

    return(
        <div className= "coding-challenge-view-body">              
                
        <div className = "coding-challenge-view-left">
            
            <h2>Descripition:</h2>

            <h3>{props.title}</h3> 

            <SplitParagraph/>

        </div>

        <div className = "coding-challenge-view-right">
        <h2>Answer:</h2>
        <div className="dropdown">
</div>

         <TextField
         fullWidth
          id="filled-multiline-static"
          multiline
          minRows={30}
          defaultValue=""
          variant="filled"
          onChange={handleNewAnswer}
        />
         
         <div className='coding-submission-button'>
            <Button variant="contained"  color="primary" onClick={submitAnswer} > Submit </Button>
         </div>
         </div>

    </div>

    );

}

export default NonCodingChallengeSolve
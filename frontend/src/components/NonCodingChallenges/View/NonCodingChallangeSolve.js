import { Button, Divider, Paper, TextField } from '@material-ui/core';
import React, { useEffect, useState } from 'react';
import { NonCodingChallengeApi } from '../NonCodingChallengeApi';
import { DateConverter, MessageType } from "../../Common/Message";
import { ToastContainer,toast } from 'react-toastify';

function NonCodingChallengeSolve(props) {

    const title = "Non coding question";
    const [description, setDescription] = useState("What are the various types of relationships in Database? Define them." );
    
    const [answer, setAnswer] = useState("");
    const[date, setDate] = useState("");
    const[disabled, setDisabled] = useState(false);

    const nonCodingChallengeApi = new NonCodingChallengeApi();
    const converter = new DateConverter();

    function SplitParagraph() {
        return props.description.split('\n')
        .map( (text,i) => <p key = {i}>{text}</p>);
    }

    function handleNewAnswer(event) {
        setAnswer(event.target.value);
    }

    function fetchSubmission() {
        nonCodingChallengeApi.getSubmission().then(data=> {
            console.log(data);
            if(data != [] ){
                props.setSolved(true);
                setDisabled(true);
                setAnswer(data.answer);
                setDate(converter.convert(data.replyTime));
            }            
        });
    }

    useEffect(() => {
        fetchSubmission();
    },[]);

    async function submitAnswer() {
        const reply = {
            nonChallengeId: localStorage.getItem('nonCodingId'),
            userId: localStorage.getItem('userId'),
            answer: answer,
            theResult: "",
            replyTime: "2022-01-01"}

            const response = await nonCodingChallengeApi.submitAnswer(reply);
            if (response.messageType === MessageType.ERROR) {
                toast.error(response.message);
              } else {
                  toast.success(response.message);
                  setTimeout(function() {
                      window.location.href = `http://localhost:3000/non-coding-challenges/${localStorage.getItem('nonCodingId')}`;
                  }, 1000)
              }
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
        <div hidden = {!disabled} >
            Submission Date:
            {date}
            <Divider/>
        </div>

         <TextField
         fullWidth
          id="filled-multiline-static"
          multiline
          minRows={30}
          defaultValue= {answer}
          disabled = {disabled}
          variant="filled"
          onChange={handleNewAnswer}
        />
         
         <div className='coding-submission-button'>
            <Button variant="contained"  color="primary" onClick={submitAnswer} > Submit </Button>
         </div>
         </div>
         <ToastContainer />
    </div>

    );

}

export default NonCodingChallengeSolve
import { Button, Divider, Paper, TextField } from '@material-ui/core';
import React, { useEffect, useState } from 'react';
import { ContestApi } from '../../Contest/ContestApi';
import {CodingChallengeApi} from '../CodingChallengeApi'
import { MessageType } from "../../Common/Message";
import { ToastContainer,toast } from 'react-toastify';
import NavBar from '../../NavBar/NavBar';


function CodingChallengeViewNonCoder(props) {

    const codingChallengeApi = new CodingChallengeApi();

    function SplitParagraph() {
        return codingChallenge.question.split('\n')
        .map( (text,i) => <p key = {i}>{text}</p>);
    }

    const[codingChallenge, setCodingChallenge] = useState({
        challenge_id: -1,
        question: "",
        title: "",
        difficulty: -1,
        points: -1,
        attempt_number:  -1,
        solved_number: -1,
        solution: "",
        publicity: -1
      });
    
      const[info, setInfo] = useState({
        authorName: "",
        categories: []
      })
    
      function fetchCodingQuestion(){
          codingChallengeApi.getCodingChallenge().then(data => setCodingChallenge(data));
      }
    
      function fetchCodingChallengeInformation() {
          codingChallengeApi.getCodingChallengeInformation()
                            .then(data => {setInfo(data);});
    }
      useEffect(() => {
        fetchCodingQuestion();
        fetchCodingChallengeInformation();
    },[]);



    return(
        <div>
            <NavBar/>
            <div className= "coding-challenge-view-body">              
                
                <div className = "coding-challenge-view-left">
                    
                    <h2>Descripition:</h2>
        
                    <h3>{codingChallenge.title}</h3> 
        
                    <SplitParagraph/>
        
                    <h2>Solution:</h2>
        
                    {codingChallenge.solution}
        
                </div>
        
                <div className = "coding-challenge-view-right">
        
                    <Divider/>
                    <h4>Author</h4>
                    {info.authorName}
        
                    <Divider/>
                    <h4>Categories</h4>
                    
                    {info.categories.map(category => {
                        return <li>{category}</li>
                    })}
        
                    <Divider/>
                    <h4>Difficulty</h4>
                    {codingChallenge.difficulty}
        
                    <Divider/>
                    <h4>Point</h4>
                    {codingChallenge.points}
        
                    <Divider/>
                    <h4>Number of Attempts</h4>
                    {codingChallenge.attempt_number}
        
                    <Divider/>
                    <h4>Number of Accepted Answers</h4>
                    {codingChallenge.solved_number}
                    <Divider/>
                 </div>
                 <ToastContainer />
            </div>
        </div>
        

    );

}

export default CodingChallengeViewNonCoder
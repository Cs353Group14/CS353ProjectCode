import { Button, Divider, Paper, TextField } from '@material-ui/core';
import React, { useEffect, useState } from 'react';
import { NonCodingChallengeApi } from '../NonCodingChallengeApi';
import { MessageType } from "../../Common/Message";
import { ToastContainer,toast } from 'react-toastify';
import NavBar from '../../NavBar/NavBar';

function NonCodingChallengeViewNonCoder(props) {

    const[nonCodingChallenge, setNonCodingChallenge] = useState({
        non_challenge_id: -1,
        question: "",
        title: "",
        difficulity: -1,
        publicity: -1
      });

      const[info, setInfo] = useState({
        authorName: "",
        categories: []
      })
    const nonCodingChallengeApi = new NonCodingChallengeApi();

    function SplitParagraph() {
        return nonCodingChallenge.question.split('\n')
        .map( (text,i) => <p key = {i}>{text}</p>);
    }

    function fetchNonCodingQuestion(){
        nonCodingChallengeApi.getNonCodingChallenge().then(data => setNonCodingChallenge(data));
    }
  
    function fetchNonCodingChallengeInformation() {
      nonCodingChallengeApi.getNonCodingChallengeInformation()
                          .then(data => {setInfo(data);});
    }
  
    /*
    function fetchOtherAnswers() {
      nonCodingChallengeApi.getOtherAnswers().then(data=> {
        console.log(data);
        setOtherAnswers(data)});
    }
    */
  
    useEffect(() => {
      fetchNonCodingQuestion();
      fetchNonCodingChallengeInformation();
      //fetchOtherAnswers();
  },[]);


    return(
        <div>
            <NavBar/>
            <div className= "coding-challenge-view-body">              
                
                <div className = "coding-challenge-view-left">
                    
                    <h2>Descripition:</h2>
        
                    <h3>{nonCodingChallenge.title}</h3> 
        
                    <SplitParagraph/>
        
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
                    {nonCodingChallenge.difficulty}
                
                 </div>
                 <ToastContainer />
            </div>
        </div>
        

    );

}

export default NonCodingChallengeViewNonCoder
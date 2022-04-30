import { Card, IconButton, InputBase, Paper, TextField } from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
import React, {useEffect, useState} from "react";
import NavBar from "../../NavBar/NavBar";
import CodingCard from "./CodingCard";
import './CodingCard.css'
import { CodingChallengeApi } from "../CodingChallengeApi";


function CodingCardContainer(props) {

    const[category, setCategory] = useState("");
    const[codingQuestions, setCodingQuestions] = useState([]);

    function handleNewCategory(event){

    }

    const codingChallengeApi = new CodingChallengeApi();

    let codingCards=  [];

    function fetchCodingQuestions() {
        codingChallengeApi.getCodingChallenges()
        .then(data => setCodingQuestions(data));

    }

    useEffect(() => {
        fetchCodingQuestions();
    },[]);

    codingQuestions.forEach( (question) => {
        codingCards.push(<CodingCard key={question.challenge_id}
                                         id={question.challenge_id}
                                         title = {question.title}
                                         category = {question.category}
                                         difficulity = {question.difficulity}
                                         point = {question.points}
                                         attemptNo = {question.attempt_number}
                                         acceptedNo = {question.solved_number}/>);
    } )

    return(
        <div>
            <h3>Filter for Coding Challenges:</h3>

            <Paper
            component="form"
            sx={{ p: '2px 4px', p: '10px', display: 'flex', alignItems: 'center', width: 200 }}
            > 

            <IconButton type="submit" sx={{ p: '10px' }} aria-label="search">
                <SearchIcon />
            </IconButton>
            
            <InputBase fullwidth='true'
                sx={{ ml: 1, flex: 1 }}
                placeholder="Category"
                inputProps={{ 'aria-label': 'search google maps'}}
            />
            </Paper>
                
            <div className ="coding-card-container" >

                {codingCards}
                
                </div>
                </div>
    );
}

export default CodingCardContainer;
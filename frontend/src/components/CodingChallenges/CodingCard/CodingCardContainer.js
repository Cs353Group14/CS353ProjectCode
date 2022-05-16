import { Button, Card, IconButton, InputBase, Paper, TextField } from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
import React, {useEffect, useState} from "react";
import NavBar from "../../NavBar/NavBar";
import CodingCard from "./CodingCard";
import './CodingCard.css'
import { CodingChallengeApi } from "../CodingChallengeApi";
import { Autocomplete, Stack } from "@mui/material";
import { ToastContainer,toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


const categories =
[
  {value: "DP"},
  {value: "Array"},
  {value: "Linked list"},
  {value: "Stack"},
  {value: "Graph"},
  {value: "DFS"},
  {value: "BFS"}
]

function CodingCardContainer(props) {

    const[category, setCategory] = useState("");
    const[codingQuestions, setCodingQuestions] = useState([]);
    const[categoryNumbers, setCategoryNumbers] = useState([]);

    function handleNewCategory(event){

    }

    const codingChallengeApi = new CodingChallengeApi();

    let codingCards=  [];
    let categoryNumberArray = [];

    function fetchCodingQuestions() {

        if(props.inContest == true) {
            codingChallengeApi.getContestCodingQuestions()
                .then(data=> setCodingQuestions(data));
        } else {
            codingChallengeApi.getCodingChallenges()
                .then(data => setCodingQuestions(data));
            codingChallengeApi.getCategoriesAndNumbersCodingChallenge()
            .then(data => setCategoryNumbers(data));
        }

    }

    useEffect(() => {
        fetchCodingQuestions();
    },[]);

    codingQuestions.forEach( (question) => {
        codingCards.push(<CodingCard key={question.challenge_id}
                                         id={question.challenge_id}
                                         title = {question.title}
                                         category = {question.category}
                                         difficulty = {question.difficulty}
                                         point = {question.points}
                                         attemptNo = {question.attempt_number}
                                         acceptedNo = {question.solved_number}
                                         inContest = {props.inContest}/>);
    } )

    categoryNumbers.forEach((cat) => {
        categoryNumberArray.push(<Paper key={cat.category}><div className="category-paper">
            {cat.category} : {cat.number} &nbsp;
        </div>
        </Paper>);

    })
    let listOfCategories = [];


    function handleCategories() {
        let categoryArray =[];

        listOfCategories.forEach(category => {
            categoryArray.push(category.value);
        });
        console.log(categoryArray);
        codingChallengeApi.getFilteredCodingChallenges(categoryArray)
                .then(data=> {
                    console.log(data);
                    setCodingQuestions(data)
                });
    }


    return(
        <div>
            <div hidden = {props.inContest}>

            <h3>Filter for Coding Challenges:</h3>


            <div className="coding-card-container">
            <IconButton sx={{ p: '10px' }} aria-label="search" onClick={handleCategories}>
                <SearchIcon />
            </IconButton>
            
            <Stack spacing={3} sx={{ width: 500 }}>
                <Autocomplete
                    multiple
                    id="tags-outlined"
                    options={categories}
                    getOptionLabel={(option) => option.value}
                    filterSelectedOptions
                    margin="normal"
                    onChange={(event, options) => {
                      listOfCategories = options;
                    }}
                    renderInput={(params) => (
                      <TextField
                        {...params}
                        label="Catogories"
                      />
                    )}
                  />
                </Stack>
                <div className="clear-btn">
                    <Button variant="outlined" href="/home" >Clear</Button>
                </div>
            </div>

         
                <div className="coding-card-container">
                    {categoryNumberArray}
                </div>
           


            </div>
                
            <div className ="coding-card-container" >

                {codingCards}
                
                </div>
                </div>
    );
}

export default CodingCardContainer;
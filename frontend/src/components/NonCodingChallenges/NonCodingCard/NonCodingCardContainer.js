import { Button, Card, IconButton, InputBase, Paper, TextField } from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
import React, {useEffect, useState} from "react";
import NavBar from "../../NavBar/NavBar";
import NonCodingCard from "./NonCodingCard";
import './NonCodingCard.css'
import {NonCodingChallengeApi} from '../NonCodingChallengeApi'
import { Autocomplete, Stack } from "@mui/material";


const nonCodingQuestions = [
    {
        id: 1,
        title: "What to Choose for History of App",
        category: "Data-Structure",
        difficulity: "Easy",
    },
    {
        id: 2,
        title: "How to Store Database",
        category: "Database Data-Structure",
        difficulity: "Hard",
    },
    {
        id: 3,
        title: "Scheduling",
        category: "Data-Structure",
        difficulity: "Easy",
    },
    {
        id: 4,
        title: "Snap-Shot",
        category: "Databse",
        difficulity: "Medium",
    },
    {
        id: 5,
        title: "SOLID",
        category: "Engineering-Principles",
        difficulity: "Medium",
    },
    {
        id: 6,
        title: "Decorative",
        category: "Design-Pattern",
        difficulity: "Medium",
    },
    {
        id: 7,
        title: "SQL vs NoSQL",
        category: "Database",
        difficulity: "Medium",
    },
    {
        id: 8,
        title: "What is Kernel",
        category: "Operating-Systems",
        difficulity: "Medium",
    },
    {
        id: 9,
        title: "Usage for Red-Black Tree",
        category: "Data-Structure",
        difficulity: "Hard",
    },
    {
        id: 10,
        title: "Multi-Thread Systems",
        category: "Operating-Systems",
        difficulity: "Hard",
    },
    {
        id: 10,
        title: "Processes",
        category: "Operating-Systems",
        difficulity: "Medium",
    },
    {
        id: 10,
        title: "Threads and n-to-n 1-to-n Relations",
        category: "Operating-Systems",
        difficulity: "Hard",
    },
]

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

function NonCodingCardContainer(props) {

    const[category, setCategory] = useState("");
    const[nonCodingQuestions, setNonCodingQuestions] = useState([]);
    const[categoryNumbers, setCategoryNumbers] = useState([]);

    let nonCodingCards=  [];

    const nonCodingChallengeApi = new NonCodingChallengeApi();


    function fetchNonCodingQuestions() {
        nonCodingChallengeApi.getNonCodingChallenges().then(data => setNonCodingQuestions(data));
        nonCodingChallengeApi.getCategoriesAndNumbersNonCodingChallenge()
            .then(data => setCategoryNumbers(data));

    }

    useEffect(() => {
        fetchNonCodingQuestions();
    },[]);

    let listOfCategories = [];
    let categoryNumberArray = [];


    function handleCategories() {
        let categoryArray =[];

        listOfCategories.forEach(category => {
            categoryArray.push(category.value);
        });
        nonCodingChallengeApi.getFilteredNonCodingChallenges(categoryArray)
                .then(data=> {
                    setNonCodingQuestions(data)
                });
    }

    nonCodingQuestions.forEach( (question) => {
        nonCodingCards.push(<NonCodingCard key={question.non_challenge_id}
                                         id={question.non_challenge_id}
                                         title = {question.title}
                                         difficulty = {question.difficulty}
/>);
    } )

    categoryNumbers.forEach((cat) => {
        categoryNumberArray.push(<Paper key={cat.category}><div className="category-paper">
            {cat.category} : {cat.number} &nbsp;
        </div>
        </Paper>);

    })

    return(
        <div>
            <h3>Filter for Non-Coding Challenges:</h3>

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
                    <Button variant="outlined" href="/home">Clear</Button>
                </div>
            </div>
            
            <div className="coding-card-container">
                    {categoryNumberArray}
                </div>
                
            <div className ="coding-card-container" >

                {nonCodingCards}
                
                </div>
                </div>
    );
}

export default NonCodingCardContainer;
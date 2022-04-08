import { Card, IconButton, InputBase, Paper, TextField } from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
import React, {useEffect, useState} from "react";
import NavBar from "../../NavBar/NavBar";
import NonCodingCard from "./NonCodingCard";
import './NonCodingCard.css'


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


function NonCodingCardContainer(props) {

    const[category, setCategory] = useState("");

    function handleNewCategory(event){

    }

    let nonCodingCards=  [];

    nonCodingQuestions.forEach( (question) => {
        nonCodingCards.push(<NonCodingCard key={question.id}
                                         id={question.id}
                                         title = {question.title}
                                         category = {question.category}
                                         difficulity = {question.difficulity}
/>);
    } )

    return(
        <div>
            <NavBar/>
            <h3>Filter for Non-Coding Challenges:</h3>

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

                {nonCodingCards}
                
                </div>
                </div>
    );
}

export default NonCodingCardContainer;
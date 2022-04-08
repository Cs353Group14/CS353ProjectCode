import { Card, IconButton, InputBase, Paper, TextField } from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
import React, {useEffect, useState} from "react";
import NavBar from "../../NavBar/NavBar";
import CodingCard from "./CodingCard";
import './CodingCard.css'


const codingQuestions = [
    {
        id: 10,
        title: "Coin Change",
        category: "Array Dynamic-Programming Breadth-FirstSearch",
        difficulity: "Medium",
        point: 35,
        attemptNo:  102,
        acceptedNo: 63,
    },
    {
        id: 1,
        title: "Max sum",
        category: "Array Hash-Table",
        difficulity: "Easy",
        point: 20,
        attemptNo:  54,
        acceptedNo: 43
    },
    {
        id: 2,
        title: "Max product",
        category: "Array Hash-Table Prefix-Sum",
        difficulity: "Easy",
        point: 20,
        attemptNo:  41,
        acceptedNo: 35
    },
    {
        id: 3,
        title: "Scheduling",
        category: "Heap",
        difficulity: "Easy",
        point: 25,
        attemptNo:  66,
        acceptedNo: 57
    },
    {
        id: 4,
        title: "Two Sum Equals Target",
        category: "Dynamic-Programming",
        difficulity: "Medium",
        point: 30,
        attemptNo:  54,
        acceptedNo: 43
    },
    {
        id: 5,
        title: "Longest Palindromic Substring",
        category: "String Dynamic-Programming",
        difficulity: "Medium",
        point: 35,
        attemptNo:  118,
        acceptedNo: 79
    },
    {
        id: 6,
        title: "Integer to Roman",
        category: "String Hahs-Table Math",
        difficulity: "Medium",
        point: 30,
        attemptNo:  89,
        acceptedNo: 67
    },
    {
        id: 7,
        title: "Sudoku Solver",
        category: "Array Bactracking String",
        difficulity: "Hard",
        point: 50,
        attemptNo:  74,
        acceptedNo: 39
    },
    {
        id: 8,
        title: "Search Insert Position",
        category: "Array Binary-Search",
        difficulity: "Easy",
        point: 15,
        attemptNo:  125,
        acceptedNo: 107
    },
    {
        id: 9,
        title: "First Missing Positive",
        category: "Array Hash-Table",
        difficulity: "Hard",
        point: 45,
        attemptNo:  89,
        acceptedNo: 59
    }
]


function CodingCardContainer(props) {

    const[category, setCategory] = useState("");

    function handleNewCategory(event){

    }

    let codingCards=  [];

    codingQuestions.forEach( (question) => {
        codingCards.push(<CodingCard key={question.id}
                                         id={question.id}
                                         title = {question.title}
                                         category = {question.category}
                                         difficulity = {question.difficulity}
                                         point = {question.point}
                                         attemptNo = {question.attemptNo}
                                         acceptedNo = {question.acceptedNo}/>);
    } )

    return(
        <div>
            <NavBar/>
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
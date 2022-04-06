import React, {useEffect, useState} from "react";
import NavBar from "../../NavBar/NavBar";
import CodingCard from "./CodingCard";
import './CodingCard.css'


const codingQuestions = [
    {
        id: 1,
        title: "Max sum",
        category: "Array",
        difficulity: "Easy",
        point: 20,
        attemptNo:  54,
        acceptedNo: 43
    },
    {
        id: 2,
        title: "Max product",
        category: "Array",
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
        category: "Dynamic Programming",
        difficulity: "Medium",
        point: 30,
        attemptNo:  54,
        acceptedNo: 43
    },
    {
        id: 5,
        title: "Two Sum Equals Target",
        category: "Dynamic Programming",
        difficulity: "Medium",
        point: 30,
        attemptNo:  54,
        acceptedNo: 43
    },
    {
        id: 6,
        title: "Two Sum Equals Target",
        category: "Dynamic Programming",
        difficulity: "Medium",
        point: 30,
        attemptNo:  54,
        acceptedNo: 43
    }
]


function CodingCardContainer(props) {


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
            <h1>List of Coding Questions:</h1>
                
            <div className ="coding-card-container" >

                {codingCards}
                
                </div>
                </div>
    );
}

export default CodingCardContainer;
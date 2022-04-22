import React, { useState } from 'react';
import NonCodingChallengeSubmissionTable from './NonCodingChallengeSubmisisonTable';


const submissions = [
    {
        submission_id: 1,
        submission_date: "2022-03-24",
        submission_time: "13:37",
        answer: "Answer: There are 3 types of relationships in Database"+

        + "One-to-one: One table has a relationship with another table having the similar kind of column. Each primary key relates to only one or no record in the related table."+
        "One-to-many: One table has a relationship with another table that has primary and foreign key relations. The primary key table contains only one record that relates to none, one or many records in the related table."+
        "Many-to-many: Each record in both the tables can relate to many numbers of records in another table."
    }
];


function NonCodindChallengeSubmissions() {

    const[currentSubIndex, setCurrentSubIindex] = useState(null); 

    function SplitParagraph() {
        return submissions[currentSubIndex].answer.split('\n')
        .map( (text,i) => <p key = {i}>{text}</p>);
    }

    function Details() {
        if(currentSubIndex != null){
            return(
                <div>

                    <h3>Answer:</h3> 
                    
                    <SplitParagraph />
                </div>
            );
        }

        return ;
    }

    return(
        <div className= "coding-challenge-view-body">              
                
        <div className = "coding-challenge-view-left">
            
            <h2>My Submission:</h2>

            <NonCodingChallengeSubmissionTable submissions = {submissions} setCurrentSubIindex = {setCurrentSubIindex}/>

        </div>

        <div className = "coding-challenge-view-right">
        <h2>Details:</h2>

        <div>
            <Details/>
        </div>
        
         </div>

    </div>
    );
}

export default NonCodindChallengeSubmissions;
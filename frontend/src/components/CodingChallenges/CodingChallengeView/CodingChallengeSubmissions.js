import React, { useEffect, useState } from 'react';
import { CodingChallengeApi } from '../CodingChallengeApi';
import CodingChallengeSubmissionTable from './CodingChallengeSubmisisonTable';


const submissions = [
    {
        submission_id: 1,
        submission_date: "2022-03-24",
        submission_time: "13:37",
        pass_result: 7,
        fail_result: 3,
        programming_language: "Java",
        answer: "import java.util.Arrays;\n"+
        "class Solution {\n" +
        "    public int coinChange(int[] coins, int amount) {\n" +
        "        Arrays.sort(coins);\n"+
        "        return helper(coins, amount);\n "+
        "}\n"+
            
        "    public int helper(int[] coins, int amount) {\n"+
        "        if(amount == 0)\n"+
        "            return 0;\n"+
                
        "        int number = -1;\n"+
                
        "        for(int i = coins.length-1; i >= 0 ;i-- ) {\n"+
                    
        "            if(coins[i] <= amount){\n"+
        "                int newNumber = coinChange(coins, amount-coins[i]); // 6, 1\n"+
                        
        "                if(newNumber != -1)\n"+
        "                    if( newNumber < number || number == -1 )\n"+
        "                       return number = newNumber + 1;\n"+
        "            }\n"+
                    
       "         }\n"+
                
        "            return number;\n"+
                
        "    }\n }"
    },
    {
        submission_id: 2,
        submission_date: "2022-03-24",
        submission_time: "11:45",
        pass_result: 5,
        fail_result: 5,
        programming_language: "Java",
        answer:"gereksiz"
    },
    {
        submission_id: 3,
        submission_date: "2022-03-23",
        submission_time: "17:28",
        pass_result: 2,
        fail_result: 8,
        programming_language: "Java",
        answer:"gereksiz"
    },
    {
        submission_id: 4,
        submission_date: "2022-03-23",
        submission_time: "16:12",
        pass_result: 0,
        fail_result: 10,
        programming_language: "Java",
        answer:"gereksiz"
    },
    {
        submission_id: 5,
        submission_date: "2022-03-23",
        submission_time: "15:58",
        pass_result: 1,
        fail_result: 9,
        programming_language: "Java",
        answer:"gereksiz"
    }
];


function CodindChallengeSubmissions() {

    const[currentSubIndex, setCurrentSubIindex] = useState(null); 
    const[submissions, setSubmissions] = useState([]);

    const codingChallengeApi = new CodingChallengeApi();

    function fetchSubmissions() {
        codingChallengeApi.getOldSubmissions().then(data=> setSubmissions(data));
    }

    useEffect(() => {
        fetchSubmissions();
        //fetchNonCodingChallengeInformation();
    },[]);

    function SplitParagraph() {
        return submissions[currentSubIndex].answer.split('\n')
        .map( (text,i) => <p key = {i}>{text}</p>);
    }

    function Details() {
        if(currentSubIndex != null){
            return(
                <div>
                    <h3>Programming Language:</h3>
                     {submissions[currentSubIndex].programming_language}

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
            
            <h2>Submissions:</h2>

            <CodingChallengeSubmissionTable submissions = {submissions} setCurrentSubIindex = {setCurrentSubIindex}/>

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

export default CodindChallengeSubmissions;
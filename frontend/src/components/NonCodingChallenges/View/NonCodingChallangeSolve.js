import { Button, Paper, TextField } from '@material-ui/core';
import React, { useState } from 'react';

function NonCodingChallengeSolve() {

    const title = "Non coding question";
    const [description, setDescription] = useState("What are the various types of relationships in Database? Define them." );
    
    const [code, setCode] = useState("");


    function SplitParagraph() {
        return description.split('\n')
        .map( (text,i) => <p key = {i}>{text}</p>);
    }

    function handleNewCode(event) {
        setCode(event.target.value);
    }

    return(
        <div className= "coding-challenge-view-body">              
                
        <div className = "coding-challenge-view-left">
            
            <h2>Descripition:</h2>

            <h3>{title}</h3> 

            <SplitParagraph/>

        </div>

        <div className = "coding-challenge-view-right">
        <h2>Answer:</h2>
        <div className="dropdown">
</div>

         <TextField
         fullWidth
          id="filled-multiline-static"
          multiline
          minRows={30}
          defaultValue=""
          variant="filled"
          onChange={handleNewCode}
        />
         
         <div className='coding-submission-button'>
            <Button variant="contained"  color="primary"> Submit </Button>
         </div>
         </div>

    </div>

    );

}

export default NonCodingChallengeSolve
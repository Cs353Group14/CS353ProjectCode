import axios from "axios";
//import {MessageResponse, MessageType} from "../common/dto/MessageResponse";

export interface newCodingQuestionModel {
        "difficulty": string,
         "title": string,
        "points": string,
        "solution": string,
        "question": string,
        "solvedNumber": number,
        "attempt_number": number,
        "publicity": number
    
}


export class CreateCodingChallengeAPI {


    async createCoding(newQuestion: newCodingQuestionModel) {
        const response = await axios.put("/createCodingChallenge/"+localStorage.getItem('userId'), newQuestion);
        return response.data;
    }
}
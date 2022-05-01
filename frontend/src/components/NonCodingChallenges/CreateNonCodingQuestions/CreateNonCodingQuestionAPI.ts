import axios from "axios";
//import {MessageResponse, MessageType} from "../common/dto/MessageResponse";

export interface newNonCodingQuestionModel {
        "difficulty": string,
         "title": string,
        "points": string,
        "question": string,
        "solvedNumber": number,
        "attempt_number": number,
        "publicity": number
    
}


export class CreateNonCodingQuestionAPI {

    async createNonCoding(newQuestion: newNonCodingQuestionModel) {
        const response = await axios.put("/createCodingChallenge/" + localStorage.getItem('userId'), newQuestion);
        return response.data;
    }
}
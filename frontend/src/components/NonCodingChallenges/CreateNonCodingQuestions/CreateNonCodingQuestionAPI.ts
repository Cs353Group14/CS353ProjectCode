import axios from "axios";
//import {MessageResponse, MessageType} from "../common/dto/MessageResponse";

export interface newNonCodingQuestionModel {
    non_challenge_id: number;
    difficulty: string,
    title: string,
    question: string,
    publicity: number,
       
}


export class CreateNonCodingQuestionAPI {

    async createNonCoding(newQuestion: newNonCodingQuestionModel) {
        const response = await axios.put("/createNonCodingChallenge/" + localStorage.getItem('userId'), newQuestion);
        return response.data;
    }

    async addCategory(nonChallengeId: number, categories: string[]) {
        categories.forEach(category => {
            const config = { headers: {'Content-Type': 'application/json'} };
            axios.put(`/addCategoryNonCodingChallenge/${nonChallengeId}`, category, config);
        })
    }

    
}
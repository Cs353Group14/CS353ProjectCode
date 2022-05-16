import axios from "axios";
import { MessageResponse } from "../../Common/Message";
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

    async addCategory(challengeId: number, categories: string[]) :Promise<MessageResponse> {
        const response = await axios.put(`/addCategoryNonCodingChallenge/${challengeId}`, categories);
        return response.data;
    }

    //createAndAddNonQuestionToInterview
    async createNonCodingQuestionForInterview(interviewID: number, companyID: number,newQuestion: newNonCodingQuestionModel) {
        const response = await axios.put(`/createAndAddNonQuestionToInterview/${interviewID}/${companyID}`, newQuestion);
        return response.data;
    }
    
}
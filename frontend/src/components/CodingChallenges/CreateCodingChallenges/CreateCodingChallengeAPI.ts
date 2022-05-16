import axios from "axios";
import { MessageResponse } from "../../Common/Message";
//import {MessageResponse, MessageType} from "../common/dto/MessageResponse";

export interface newCodingQuestionModel {
        difficulty: string,
         title: string,
        points: number,
        solution: string,
        question: string,
        solvedNumber: number,
        attempt_number: number,
        publicity: number
  
    
}

export class CreateCodingChallengeAPI {


    async createCoding(newQuestion: newCodingQuestionModel): Promise<number> {
        const response = await axios.put("/createCodingChallenge/"+localStorage.getItem('userId'), newQuestion);
        return response.data;
    }

    async addCategory(challengeId: number, categories: string[]) :Promise<MessageResponse>{
        console.log(categories);
        const response = await axios.put(`/addCategoryCodingChallenge/${challengeId}`, categories);
        return response.data;
    }

    async addTestCase(challengeId: number, inputsOutputs: string[]) :Promise<MessageResponse>{
        const response = await axios.put(`/addTestCaseForCodingChallenge/${challengeId}`, inputsOutputs);
        return response.data;

    }
    async createCodingChallengesForInteriew(interviewId: number, companyId: number, timelimit: number, newQuestion: newCodingQuestionModel) :Promise<number>
    {
        //createAndAddQuestionToInterview
        const response = await axios.put(`/createAndAddQuestionToInterview/${interviewId}/${companyId}/${timelimit}`, newQuestion);
        return response.data;
    }
}
import axios from "axios";
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

    async addCategory(challengeId: number, categories: string[]) {
        const response = await axios.put(`/addCategoryCodingChallenge/${challengeId}`, categories);
    }

    async addTestCase(challengeId: number, inputsOutputs: string[]){
        const response = await axios.put(`/addTestCaseForCodingChallenge/${challengeId}`, inputsOutputs);
        return response.data;

    }
    async createCodingChallengesForInteriew(interviewId: number, companyId: number, timelimit: number, newQuestion: newCodingQuestionModel)
    {
        //createAndAddQuestionToInterview
        const response = await axios.put(`/createAndAddQuestionToInterview/${interviewId}/${companyId}/${timelimit}`, newQuestion);
        return response.data;
    }
}
import axios from "axios";
//import {MessageResponse, MessageType} from "../common/dto/MessageResponse";

export interface interviewModel {
    user_id: number;
    interview_id: number,
    duration: number,
    position: string     
}
export interface companyModel {
    location: string;
    webPageLink: string,
    userId: number,
    username: string,
    name: string,
    mail: string, 
    password: string, 
    userType: string, 
    information: string,
    foto: string     
}

export interface NonCodingQuestionsOfInterviewModel {
    non_challenge_id: number;
    question: string,
    difficulty: string,
    title: string,
    publicity: number 
}

export interface CodingQuestionsOfInterviewModel {
    challenge_id: number;
    question: string,
    points: number,
    difficulty: string,
    solved_number: number,
    attempt_number: number,
    title: string,
    solution: string,
    publicity: number     
}


export class AttendInterviewAPI {

    async getInterview(interviewID: number): Promise<interviewModel> {
        const response = await axios.get("/getInterview/" + interviewID);
        return response.data;
    }

    async getCompany(interviewID: number): Promise<companyModel> {
        const response = await axios.get("/getCompanyofInterview/" + interviewID);
        return response.data;
    }

    async getNonCodingQuestionsOfInterview(interviewID: number): Promise<NonCodingQuestionsOfInterviewModel[]> {
        const response = await axios.put("/createNonCodingChallenge/" + localStorage.getItem('userId'), interviewID);
        return response.data;
    }

    async getCodingQuestionsOfInterview(interviewID: number): Promise<CodingQuestionsOfInterviewModel[]> {
        const response = await axios.put("/createNonCodingChallenge/" + localStorage.getItem('userId'), interviewID);
        return response.data;
    } 
}
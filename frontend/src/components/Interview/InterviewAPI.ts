import axios from "axios";
//import {MessageResponse, MessageType} from "../common/dto/MessageResponse";

export interface interviewModelForCoder {
    user_id: number;
    interview_id: number,
    duration: number,
    position: string    
    companyName: string,
     startTime: string;
     endTime: string;
     interviewResult: string;
     invitationCode: string; 
}

export interface interviewModelForCompany {
    user_id: number;
    interview_id: number,
    duration: number,
    position: string     
}


export class InterviewAPI {

    //Get lists
    async getPastInterviewList(): Promise<interviewModelForCoder[]> {
        const response = await axios.get("/getPastInterviewsForCoder/" + localStorage.getItem('userId'));
        return response.data;
    }
    async getNewInterviewList(): Promise<interviewModelForCoder[]> {
        const response = await axios.get("/getFutureInterviewsForCoder/" + localStorage.getItem('userId'));
        return response.data;
    }

    async getInterviewListCompany(): Promise<interviewModelForCompany[]> {
        const response = await axios.get("/getInterviewsOfCompanyNew/" + localStorage.getItem('userId'));
        return response.data;
    }

    //Start interview
    

}
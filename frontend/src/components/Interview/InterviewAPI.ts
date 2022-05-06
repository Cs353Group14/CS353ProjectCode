import axios from "axios";
//import {MessageResponse, MessageType} from "../common/dto/MessageResponse";

export interface interviewModel {
    user_id: number;
    interview_id: number,
    duration: number,
    position: string     
}


export class InterviewAPI {
    async getInterviewList(): Promise<interviewModel[]> {
        const response = await axios.get("getInterviewsForCoder/" + localStorage.getItem('userId'));
        return response.data;
    }
}
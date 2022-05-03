import axios from "axios";
//import {MessageResponse, MessageType} from "../common/dto/MessageResponse";

export interface newInterview {
    user_id: number,
    interview_id: number,
    duration: number,
    position: string
       
}

export interface InterviewQueryResponse {
      companyName: string,
      duration: number,
      position: string,
      interviewId: number,
      startTime: string,
      endTime: string,
      interviewResult: string,
      invitationCode: string
       
}

export class CreateNewInterview {

    async createInterview(newQuestion: newInterview): Promise<InterviewQueryResponse> {
        const response = await axios.put("/createInterview", newQuestion);
        return response.data;
    }

    async addCodingQuestionToInterview(){
        const response = await axios.put("/addCodingQuestionToInterview/"+localStorage.getItem('interviewID')+"/"+localStorage.getItem('challengeId')+"/"+localStorage.getItem('userId')+"/"+localStorage.getItem('duration'));
        return response.data;
    }

    
}
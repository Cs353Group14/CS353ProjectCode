import axios from "axios";
import { MessageResponse } from "../../Common/Message";
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


export interface Participant {
    interviewId: number,
    coderId: number,
    companyId: number,
    startTime: string,
    endTime: string,
    interviewResult: string;
    invitationCode: string;
     
}




export class CreateNewInterviewAPI {

    async createInterview(newQuestion: newInterview): Promise<InterviewQueryResponse> {
        const response = await axios.put("/createInterview", newQuestion);
        return response.data;
    }

    async addCodingQuestionToInterview() :Promise<MessageResponse>{
        const response = await axios.put("/addCodingQuestionToInterview/"+localStorage.getItem('interviewID')+"/"+localStorage.getItem('challengeId')+"/"+localStorage.getItem('userId')+"/"+localStorage.getItem('duration'));
        return response.data;
    }

    async getInterview() :Promise<newInterview>
    {
        const response = await axios.get("/getInterview/"+ localStorage.getItem('interviewID') );
        return response.data;
    }

    async addNonCodingQuestionToInterview() :Promise<MessageResponse> {
        const response = await axios.put("/addNonCodingQuestionToInterview/"+localStorage.getItem('interviewID')+"/"+localStorage.getItem('challengeId')+"/"+localStorage.getItem('userId'));
        return response.data;
    }

    async addParticipant(participant: Participant){
        const response = await axios.put("/createAttend/", participant);
        return response.data;
    }

    async getUserToAdd(username: string){
        const response = await axios.get("/getIdUserNameandName/" + username);
        return response.data;
    }

    async getParticipants(interviewID: string){
        const response = await axios.get("/getUsersAttendingToInterview/" + interviewID);
        return response.data;
    }


 
}
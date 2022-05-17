import axios from "axios";
//import {MessageResponse, MessageType} from "../common/dto/MessageResponse";

export interface newInterview {
    user_id: number,
    interview_id: number,
    duration: number,
    position: string
       
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

export interface UpdateAttend{
    interviewId: number,
    interviewResult: string,
    userId: number
}

export interface getCandidate{
    id: number,
    userName: string,
    name: string
}



export class InterviewResultAPI {

    async getCandidiates(interviewID: number): Promise<Participant[]> {
        const response = await axios.get("/getUsersAttendingToInterview/"+ interviewID);
        return response.data;
    }

    async evaluateCandidate(updateCand: UpdateAttend){
        const response = await axios.put("/updateAttend/", updateCand);
        return response.data;
    }

    async getInterview() :Promise<newInterview>
    {
        const response = await axios.get("/getInterview/"+ localStorage.getItem('interviewID') );
        return response.data;
    }

    async getCandidate(username: string) :Promise<getCandidate>
    {
        const response = await axios.get("/getIdUserNameandName/"+ username );
        return response.data;
    }

    async checkIfFinished(interview_id: number, coder_id: number): Promise<number>
    {
        const response = await axios.get("/getInterviewStatus/"+interview_id+"/"+coder_id);
        return response.data;
    }

 
}
import axios from "axios";
//import {MessageResponse, MessageType} from "../common/dto/MessageResponse";

export interface newInterview {
    user_id: number,
    interview_id: number,
    duration: number,
    position: string
       
}


export class CreateNewInterview {

    async createInterview(newQuestion: newInterview) {
        const response = await axios.put("/createInterview", newQuestion);
        return response.data;
    }

    
}
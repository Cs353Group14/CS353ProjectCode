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

export interface referral
{
    reason: string
}

export class ProfileAPI {

    async getProfileInfo(userId: string) {
        const response = await axios.get("/getCoderProfile/" + userId);
        return response.data;
    }    
    async getNumberOfContests(coderId: number) {
        const response = await axios.get("/getNumberOfContestAttended/" + coderId);
        return response.data;
    } 

    async getNumberOfQuestions(coderId: number) {
        const response = await axios.get("/getSumSolvedNumberOfQuestion/" + coderId);
        return response.data;
    } 

    async getReferrals(coderId: number) {
        const response = await axios.get("/listReferCoder/" + coderId);
        return response.data;
    } 

    async getContestOfCoder(coderId: number) {
        const response = await axios.get("/getContestsAndOrdersOfUser/" + coderId);
        return response.data;
    } 

    async referCoder(userId: number,coderId: number, referral: referral) :Promise<MessageResponse>{
        const response = await axios.put("/giveReferCoder/"+userId+"/"+coderId, referral );
        return response.data;
    } 
}
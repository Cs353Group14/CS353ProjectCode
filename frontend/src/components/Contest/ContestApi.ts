import axios from "axios";
import { CodingChallengeQueryResponse } from "../CodingChallenges/CodingChallengeApi";

export interface ContestModel {
    contest_id: number,
    start_time: string,
    description: string,
    title: string,
    difficulty: number,
    duration: number,
    deadline: string,
}

export interface ContestResult{
    userName: string,
    point: number
}

export interface ContestDeadlineResponse{
    contest_id:number;
    title:string;
    deadlinePassed: boolean;
    editorName:string;
}

export class ContestApi {
    async createContest(contest: ContestModel): Promise<number> {
        const response = await axios.put(`/createContest/${localStorage.getItem('userId')}`, contest);
        return response.data;
    }

    async getChallengesOfEditor(): Promise<CodingChallengeQueryResponse[]> {
        const response = await axios.get(`/getChallengesOfEditor/${localStorage.getItem('userId')}`);
        return response.data;
    }

    async addQuestionsToContest(contestIds: number[]) {
        const response = await axios.put(`/addQuestionsToContest/${localStorage.getItem('contestId')}`,contestIds);
    }

    async getFutureContestsNotRegistered() : Promise<ContestModel[]> {
        console.log('in non registered future');
        const response = await axios.get(`/getFutureContestsNotRegistered/${localStorage.getItem('userId')}`);
        return response.data;
    }

    async getFutureContestsRegistered() : Promise<ContestModel[]> {
        console.log('in registered future');
        const response = await axios.get(`/getFutureContestsRegistered/${localStorage.getItem('userId')}`);
        return response.data;
    }

    async getAvailableRegisteredContests(): Promise<ContestModel[]> {
        console.log('in registered continues');
        const response = await axios.get(`/getAvailableRegisteredContests/${localStorage.getItem('userId')}`);
        return response.data;
    }

    async getOldRegisteredContests(): Promise<ContestModel[]> {
        console.log('in registered old');
        const response = await axios.get(`/getOldRegisteredContests/${localStorage.getItem('userId')}`);
        return response.data;
    }


    async addCoderToContest(contestId: number) {
        const response = await axios.put(`/addCoderToContest/${localStorage.getItem('userId')}/${contestId}`);
    }

    async participateContest(contestId: number) {
        const response = await axios.put(`/participateContest/${localStorage.getItem('userId')}/${contestId}`);
    }


    async getContestStatus(contestId: number): Promise<number> {
        const response = await axios.get(`/getContestStatus/${localStorage.getItem('userId')}/${contestId}`);
        return response.data;
    }

    async getContestDetails() : Promise<ContestModel> {
        const response = await axios.get(`/getContest/${localStorage.getItem('contestId')}`);
        return response.data;
    }

    async getContesOrder() : Promise<ContestResult[]> {
        const response = await axios.get(`/getContestOrder/${localStorage.getItem('contestId')}`);
        return response.data;
    }

    async getAllContests() : Promise<ContestDeadlineResponse[]> {
        const response = await axios.get(`/getAllContests`);
        return response.data;
    }

    async getSponsoredContests() : Promise<ContestDeadlineResponse[]> {
        const response = await axios.get(`/getSponsoredContests/${localStorage.getItem('userId')}`);
        return response.data;
    }

    async getEditorsContests() : Promise<ContestDeadlineResponse[]> {
        const response = await axios.get(`/getContestsForEditor/${localStorage.getItem('userId')}`);
        return response.data;
    }


}
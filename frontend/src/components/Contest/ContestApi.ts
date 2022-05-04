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
        const response = await axios.get(`/getFutureContestsNotRegistered/${localStorage.getItem('userId')}`);
        return response.data;
    }

    async getFutureContestsRegistered() : Promise<ContestModel[]> {
        const response = await axios.get(`/getFutureContestsRegistered/${localStorage.getItem('userId')}`);
        return response.data;
    }

    async getAvailableRegisteredContests(): Promise<ContestModel[]> {
        const response = await axios.get(`/getAvailableRegisteredContests/${localStorage.getItem('userId')}`);
        return response.data;
    }

    async getOldRegisteredContests(): Promise<ContestModel[]> {
        const response = await axios.get(`/getOldRegisteredContests/${localStorage.getItem('userId')}`);
        return response.data;
    }


    async addCoderToContest(contestId: number) {
        const response = await axios.put(`/addCoderToContest/${localStorage.getItem('userId')}/${contestId}`);
    }
}
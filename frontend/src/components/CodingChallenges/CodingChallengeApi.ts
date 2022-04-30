import axios from "axios";

export interface CodingChallengeQueryResponse {
    id: number,
    title: string,
    difficulity: string,
    points: number,
    attempt_number:  number,
    solved_number: number
}

export interface CodingChallengeModel {
    challenge_id: number,
    question: string,
    title: string,
    difficulity: string,
    points: number,
    attempt_number:  number,
    solved_number: number,
    solution: string,
    publicity: number

}

export class CodingChallengeApi {
    
    async getCodingChallenges(): Promise<CodingChallengeQueryResponse[]> {
        const response = await axios.get("/publicCodingChallenges");
        return response.data;
    }

    async getCodingChallenge(): Promise<CodingChallengeModel> {
        const response = await axios.get("/getCodingChallenge/1");
        return response.data;

    }

}
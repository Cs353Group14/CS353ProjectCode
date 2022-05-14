import axios from "axios";

export interface CodingChallengeQueryResponse {
    challenge_id: number,
    title: string,
    difficulty: string,
    points: number,
    attempt_number:  number,
    solved_number: number
}

export interface CodingChallengeModel {
    challenge_id: number,
    question: string,
    title: string,
    difficulty: string,
    points: number,
    attempt_number:  number,
    solved_number: number,
    solution: string,
    publicity: number

}

export interface Submission{
    answer: string;
    programming_language: string;
    submission_id: number;
    pass_result: number;
    fail_result: number;
    submission_time: string;
}

export interface CodingChallegeInformation {
    authorName: string;
    categories: [];
}

export interface CategoryNumberResponse {
    category:string;
    number: number;
}


export class CodingChallengeApi {
    
    async getCodingChallenges(): Promise<CodingChallengeQueryResponse[]> {
        const response = await axios.get("/publicCodingChallenges");
        return response.data;
    }

    async getFilteredCodingChallenges(categories: string[]): Promise<CodingChallengeQueryResponse[]> {
        const response = await axios.post("/publicCodingChallengesFiltered", categories);
        return response.data;
    }

    async getCodingChallenge(): Promise<CodingChallengeModel> {
        const response = await axios.get(`/getCodingChallenge/${localStorage.getItem('codingId')}`);
        return response.data;

    }

    async getCodingChallengeInformation(): Promise<CodingChallegeInformation> {
        const response = await axios.get(`/getCodingChallengeAuthorAndCategory/${localStorage.getItem('codingId')}`);
        return response.data;
    }

    async submitSolution(submission: Submission) {
        const response = await axios.put(`/submitSolution/${localStorage.getItem('userId')}/${localStorage.getItem('codingId')}`, submission)
    }

    async submitSolutionToContest(submission: Submission) {
        const response = await axios.put(`/submitSolution/${localStorage.getItem('userId')}/${localStorage.getItem('codingId')}/${localStorage.getItem('contestId')}`, submission)
    }

    async getOldSubmissions(): Promise<Submission[]> {
        const response = await axios.get(`/listOldAttempts/${localStorage.getItem('userId')}/${localStorage.getItem('codingId')}`);
        return response.data;
    }

    async getContestCodingQuestions(): Promise<CodingChallengeQueryResponse[]> {
        const response = await axios.get(`/getContestCodingQuestions/${localStorage.getItem('contestId')}`);
        return response.data;
    }

    async getCategoriesAndNumbersCodingChallenge(): Promise <CategoryNumberResponse[]> {
        const response = await axios.get(`/getCategoriesAndNumbersCodingChallenge`);
        return response.data;
    }

}
import axios from "axios";

export interface NonCodingChallengeQueryResponse {
    non_challenge_id: number,
    title: string,
    difficulty: string,
}

export interface NonCodingChallengeModel {
    non_challenge_id: number,
    question: string,
    title: string,
    difficulty: string,
    publicity: number

}

export interface Reply{
    nonChallengeId: number,
    userId: number,
    answer: string;
    theResult: string;
    replyTime: string;
}

export interface NonCodingChallegeInformation {
    authorName: string;
    categories: [];
}

export interface OthersAnswer{
    username: string;
    answer: string;
}

export interface CategoryNumberResponse {
    category:string;
    number: number;
}

export class NonCodingChallengeApi {
    
    async getNonCodingChallenges(): Promise<NonCodingChallengeQueryResponse[]> {
        const response = await axios.get("/NonCodingChallenges");
        return response.data;
    }

    async getFilteredNonCodingChallenges(categories: string[]): Promise<NonCodingChallengeQueryResponse[]> {
        const response = await axios.post("/NonCodingChallengesFiltered", categories);
        return response.data;
    }

    async getNonCodingChallenge(): Promise<NonCodingChallengeModel> {
        const response = await axios.get(`/getNonCodingChallenge/${localStorage.getItem('nonCodingId')}`);
        return response.data;

    }

    async getNonCodingChallengeInformation(): Promise<NonCodingChallegeInformation> {
        const response = await axios.get(`/getNonCodingChallengeAuthorCategory/${localStorage.getItem('nonCodingId')}`);
        return response.data;
    }

    async submitAnswer(reply: Reply) {
        const response = await axios.put(`/replyQuestion`, reply);
    }

    async getSubmission() : Promise<Reply> {
        const response = await axios.get(`/seeReply/${localStorage.getItem('userId')}/${localStorage.getItem('nonCodingId')}`);
        return response.data;
    }

    async getOtherAnswers() : Promise<OthersAnswer[]> {
        const response = await axios.get(`/seeOtherCodersAnswers/${localStorage.getItem('userId')}/${localStorage.getItem('nonCodingId')}`);
        return response.data;

    }
    async getCategoriesAndNumbersNonCodingChallenge(): Promise <CategoryNumberResponse[]> {
        const response = await axios.get(`/getCategoriesAndNumbersNonCodingChallenge`);
        return response.data;
    }
}
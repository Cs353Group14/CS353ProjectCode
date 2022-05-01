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

export class NonCodingChallengeApi {
    
    async getNonCodingChallenges(): Promise<NonCodingChallengeQueryResponse[]> {
        const response = await axios.get("/NonCodingChallenges");
        return response.data;
    }

    async getNonCodingChallenge(): Promise<NonCodingChallengeModel> {
        const response = await axios.get(`/getNonCodingChallenge/${localStorage.getItem('nonCodingId')}`);
        return response.data;

    }

    async getNonCodingChallengeInformation(): Promise<NonCodingChallegeInformation> {
        const response = await axios.get(`/getCodingChallengeAuthorAndCategory/${localStorage.getItem('nonCodingId')}`);
        return response.data;
    }

    async submitAnswer(reply: Reply) {
        const response = await axios.put(`/replyQuestion`, reply);
    }

}
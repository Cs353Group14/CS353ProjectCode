import axios from "axios";

export interface CoderResponse {
    rating: number;
    points: number;
    position: string;
    place: string;
    birthYear: number;
    userId: number;
    username: string;
    mail: string;
    name: string;
    information: string;
    foto: string;
}

export class UserApi {
    async getCoders(): Promise <CoderResponse[]> {
        const response = await axios.get(`/getCoders`);
        return response.data;
    }

    async getCodersFiltered(filter: string): Promise <CoderResponse[]> {
        const response = await axios.get(`/getCoders/${filter}`);
        return response.data;
    }
}
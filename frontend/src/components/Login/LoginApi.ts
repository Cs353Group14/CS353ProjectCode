import axios from "axios";

export interface LoginResponse {
    username: string;
    userId: number;
    userType: string;
}
export interface LoginRequest {
    username: string;
    password: string;
}
export class LoginApi{

    async login(loginRequest: LoginRequest) : Promise<LoginResponse> {
        const response = await axios.post("/login", loginRequest);
        return response.data;
    }
}
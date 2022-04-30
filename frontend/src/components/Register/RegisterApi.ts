import axios from "axios";
//import {MessageResponse, MessageType} from "../common/dto/MessageResponse";

export interface CoderModel {
    "username": string;
    "mail": string;
    "password": string;
    "userType": string;
    "name": string;
    "birthYear": number;
    "position": string;
    "place": string;
}

export interface EditorModel {
    "username": string;
    "mail": string;
    "password": string;
    "userType": string;
    "name": string;
    "position": string;
    "place": string;
}

export interface CompanyModel {
    "username": string;
    "mail": string;
    "password": string;
    "userType": string;
    "name": string;
    "location": string;
    "webPageLink": string;
}

export class RegisterApi {

    async registerCoder(coderModel: CoderModel) {
        const response = await axios.put("/register-coder", coderModel);
        return response.data;
    }

    async registerEditor(editorModel: EditorModel) {
        const response = await axios.put("/register-editor", editorModel);
        return response.data;
    }

    async registerCompany(companyModel: CompanyModel) {
        const response = await axios.put("/register-company", companyModel);
        return response.data;
    }
}
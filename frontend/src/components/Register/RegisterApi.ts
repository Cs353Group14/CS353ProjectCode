import axios from "axios";
import { MessageResponse, MessageType } from "../Common/Message";
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

    async registerCoder(coderModel: CoderModel) :Promise<MessageResponse> {

        let messageResponse;

        if(coderModel.username.trim() == "" || coderModel.mail.trim() == "" || 
            coderModel.password.trim() == "" || coderModel.name.trim() == "" ||
            coderModel.place.trim() == "" || coderModel.position.trim() == "" || 
            ( coderModel.birthYear == null || coderModel.birthYear < 1900)) {

                messageResponse = {
                    messageType: MessageType.ERROR,
                    message: "Please fill all places appropriately"
                }
                return messageResponse;
            }

        const response = await axios.put("/register-coder", coderModel);
        return response.data;
    }

    async registerEditor(editorModel: EditorModel) :Promise<MessageResponse> {

        let messageResponse;

        if(editorModel.username.trim() == "" || editorModel.mail.trim() == "" || 
        editorModel.password.trim() == "" || editorModel.name.trim() == "" ||
        editorModel.place.trim() == "" || editorModel.position.trim() == "" ) {

                messageResponse = {
                    messageType: MessageType.ERROR,
                    message: "Please fill blank places"
                }
                return messageResponse;
            }
        const response = await axios.put("/register-editor", editorModel);
        return response.data;
    }

    async registerCompany(companyModel: CompanyModel) :Promise<MessageResponse> {

        let messageResponse;

        if(companyModel.username.trim() == "" || companyModel.mail.trim() == "" || 
        companyModel.password.trim() == "" || companyModel.name.trim() == "" ||
        companyModel.location.trim() == "") {

                messageResponse = {
                    messageType: MessageType.ERROR,
                    message: "Please fill blank places"
                }
                return messageResponse;
            }

        const response = await axios.put("/register-company", companyModel);
        return response.data;
    }
}
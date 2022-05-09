import axios from "axios";

export interface NotificationModel {
    nId: number;
    nInfo: string;
    notifDate: string;
    type: string;

}

export class NotificationApi {

    async getNotifications() : Promise<NotificationModel[]> {
        const response = await axios.get(`/seeNotifications/${localStorage.getItem('userId')}`);
        return response.data;
    }
}
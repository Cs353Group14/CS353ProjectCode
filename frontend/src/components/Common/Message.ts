export interface MessageResponse{

    messageType: MessageType;
    message: string;

}

export enum MessageType {
    SUCCESS = "SUCCESS", INFO = "INFO", WARNING = "WARNING", ERROR = "ERROR"
}

export class DateConverter{
    convert(date: string) :string {
        var splitted = date.split("-", 3); 
        console.log(splitted[2]);
        var removeT = splitted[2].split("T",2);
        var times = removeT[1].split(".",2);
        var n = times[0].split(":",3);
        return "" + removeT[0] + "-" + splitted[1] + "-" + splitted[0] + "    " + n[0] + ":" + n[1];
    }
}
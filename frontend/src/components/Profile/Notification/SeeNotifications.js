import { Card, Divider, IconButton, InputBase, Paper, TextField } from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
import React, {useEffect, useState} from "react";
import NavBar from "../../NavBar/NavBar";
import { NotificationApi } from "./NotificationApi";
import NotificationCard from "./NotificationCard";


function SeeNotifications() {

    const[notifications, setNotifications] = useState([]);

    const notificationApi = new NotificationApi();

    let notificationCards=  [];

    function fetchNotifications() {
            notificationApi.getNotifications()
                .then(data =>{
                setNotifications(data);});


    }

    useEffect(() => {
        fetchNotifications();
    },[]);

    notifications.forEach( (n) => {
        notificationCards.push(<div><NotificationCard key={n.nid}
                                         nid={n.nid}
                                         type = {n.type}
                                         notifDate = {n.notifDate}
                                         ninfo = {n.ninfo}/> <Divider/> <br/></div>);
    } )

    return(
        <div>

            <NavBar/>
                <br/>
            <div >

                {notificationCards}
                
            </div>
        </div>
    );
}

export default SeeNotifications;
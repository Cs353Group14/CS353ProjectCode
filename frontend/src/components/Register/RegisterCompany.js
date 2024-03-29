import React, {ChangeEvent, useState} from "react";
import {Button, TextField} from "@material-ui/core";
import RegisterBar from "./RegisterBar";
import { RegisterApi } from "./RegisterApi";
import { ToastContainer,toast } from 'react-toastify';
import { MessageType } from "../Common/Message";



function RegisterCompany() {

    const registerApi = new RegisterApi();

    const[username, setUsername] = useState("");
    const[password,setPassword] = useState("");
    const[name, setName] = useState("");
    const[mail, setMail] = useState("");
    const[location, setLocation] = useState("");
    const[link, setLink] = useState("");

    function handleNewUsername(event) {
        setUsername(event.target.value);
    }

    function handleNewPassword(event) {
        setPassword(event.target.value);
    }

    function handleNewName(event) {
        setName(event.target.value);
    }

    function handleNewMail(event) {
        setMail(event.target.value);
    }

    function handleNewLocation(event) {
        setLocation(event.target.value);
    }

    function handleNewLink(event) {
        setLink(event.target.value);
    }

    async function handleRegisterClick() {

        console.log("Signed In");

        const coder = {
            "username": username,
            "mail": mail,
            "password": password,
            "userType": "Company",
            "name": name,
            "location": location,
            "webPageLink": link
        }

        const response = await registerApi.registerCompany(coder);

        if (response.messageType === MessageType.ERROR) {
            toast.error(response.message);
        } else {
            toast.success(response.message);
            setTimeout(function() {
                window.location.href = "http://localhost:3000";
            }, 1000)
        }


    }

    return(
        <div className={"sign-in"}>
            <RegisterBar/>
        
            <div className={"register-root"}>
            <div className="register-elements">

            <div className="register-element-left">
            <form className={"register-entry-left"} noValidate autoComplete="off">
                    <TextField fullWidth label="Username" onChange={handleNewUsername}/>
                </form>

                <form className={"register-entry-left"} noValidate autoComplete="off">
                    <TextField fullWidth label="Password" type={"password"} onChange={handleNewPassword}/>
                </form>

                <form className={"register-entry-left"} noValidate autoComplete="off">
                    <TextField fullWidth label="Company Name" onChange={handleNewName}/>
                </form>
                </div>

                <div className="register-element-right">

                <form className={"register-entry-right"} noValidate autoComplete="off">
                    <TextField fullWidth label="E-mail"  type={"email"} onChange={handleNewMail} />
                </form>

                <form className={"register-entry-right"} noValidate autoComplete="off">
                    <TextField fullWidth label="Location" onChange={handleNewLocation}/>
                </form>

                <form className={"register-entry-right"} noValidate autoComplete="off">
                    <TextField fullWidth label="Web Page Link" onChange={handleNewLink}/>
                </form>
                </div>

                </div>

                <div className={"register-button"} >
                    <Button variant="contained"  onClick={handleRegisterClick} >
                        Register
                    </Button>
                </div>
            </div>
            <ToastContainer />
        </div>

    );
}

export default RegisterCompany;
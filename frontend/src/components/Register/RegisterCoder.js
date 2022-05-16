import React, {ChangeEvent, useState} from "react";
import {Button, TextField} from "@material-ui/core";
import RegisterBar from "./RegisterBar";
import './Register.css'
import { RegisterApi } from "./RegisterApi";
import { MessageType } from "../Common/Message";
import { ToastContainer,toast } from 'react-toastify';



function RegisterCoder() {

    const registerApi = new RegisterApi();

    const[username, setUsername] = useState("");
    const[password,setPassword] = useState("");
    const[name, setName] = useState("");
    const[mail, setMail] = useState("");
    const[birthYear, setBirthYear] = useState(null);
    const[position, setPosition] = useState("");
    const[place, setPlace] = useState("");

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

    function handleNewBirthYear(event) {
        setBirthYear(event.target.value);
        console.log(birthYear);
    }

    function handleNewPosition(event) {
        setPosition(event.target.value);
    }

    function handleNewPlace(event) {
        setPlace(event.target.value);
    }

    async function handleRegisterClick() {

        console.log("Signed In");

        const coder = {
            "username": username,
            "mail": mail,
            "password": password,
            "userType": "Coder",
            "name": name,
            "birthYear": birthYear,
            "position": position,
            "place": place
        }

        const response = await registerApi.registerCoder(coder);

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
                    <TextField fullWidth label="Name" onChange={handleNewName}/>
                </form>

                <form className={"register-entry-left"} noValidate autoComplete="off">
                    <TextField fullWidth label="E-mail"  type={"email"} onChange={handleNewMail} />
                </form>
                </div>

                <div className="register-element-right">
                <form className={"register-entry-right"} noValidate autoComplete="off">
                    <TextField fullWidth label="Birth Year" type={"number"} onChange={handleNewBirthYear}/>
                </form>

                <form className={"register-entry-right"} noValidate autoComplete="off">
                    <TextField fullWidth label="Current Position" onChange={handleNewPosition}/>
                </form>

                <form className={"register-entry-right"} noValidate autoComplete="off">
                    <TextField fullWidth label="Current Company" onChange={handleNewPlace}/>
                </form>

                </div>

                </div>

                <div className={"register-button"}>
                    <Button variant="contained"  onClick={handleRegisterClick} >
                        Register
                    </Button>
                </div>
            </div>
            <ToastContainer />
        </div>

    );
}

export default RegisterCoder;
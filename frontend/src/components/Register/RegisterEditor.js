import React, {ChangeEvent, useState} from "react";
import {Button, TextField} from "@material-ui/core";
import RegisterBar from "./RegisterBar";



function RegisterEditor() {

    const[username, setUsername] = useState("");
    const[password,setPassword] = useState("");
    const[name, setName] = useState("");
    const[mail, setMail] = useState("");
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

    function handleNewPosition(event) {
        setPosition(event.target.value);
    }

    function handleNewPlace(event) {
        setPlace(event.target.value);
    }

    function handleRegisterClick() {

        console.log("Signed In");
        setTimeout(function() {
            window.location.href = "http://localhost:3000";
        }, 1000)


    }

    return(
        <div className={"sign-in"}>
            <RegisterBar/>
        
            <div className={"login-root"}>

            <form className={"login-entry"} noValidate autoComplete="off">
                    <TextField label="Username" onChange={handleNewUsername}/>
                </form>

                <form className={"login-entry"} noValidate autoComplete="off">
                    <TextField label="Password" type={"password"} onChange={handleNewPassword}/>
                </form>

                <form className={"login-entry"} noValidate autoComplete="off">
                    <TextField label="Name" onChange={handleNewName}/>
                </form>

                <form className={"login-entry"} noValidate autoComplete="off">
                    <TextField label="E-mail"  type={"email"} onChange={handleNewMail} />
                </form>

                <form className={"login-entry"} noValidate autoComplete="off">
                    <TextField label="Current Position" onChange={handleNewPosition}/>
                </form>

                <form className={"login-entry"} noValidate autoComplete="off">
                    <TextField label="Current Company" onChange={handleNewPlace}/>
                </form>

                <div className={"login-button"}>
                    <Button variant="contained"  onClick={handleRegisterClick} >
                        Register
                    </Button>
                </div>
            </div>
        </div>

    );
}

export default RegisterEditor;
import React, {useState} from "react";
import {Button, TextField} from "@material-ui/core";
import LoginBar from "./LoginBar";
import './Login.css'

function Login() {

    const[username, setUsername] = useState("");
    const[password,setPassword] = useState("");

    function handleNewUsername(event) {
        setUsername(event.target.value);
    }

    function handleNewPassword(event) {
        setPassword(event.target.value);
    }

    return(
        <div className={"login"}>

            <LoginBar/>

            <div className={"login-root"}>
                <form className={"login-entry"} noValidate autoComplete="off">
                    <TextField label="Username" onChange={handleNewUsername}/>
                </form>

                <form className={"login-entry"} noValidate autoComplete="off">
                    <TextField label="Password" type={"password"} onChange={handleNewPassword} />
                </form>

                <div className={"login-button"}>
                    <Button variant="contained" href="JavaScript:setTimeout(10000)"  >
                    Login
                    </Button>
                </div>
            </div>

        </div>
    )
}

export default Login;
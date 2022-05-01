import React, {useState} from "react";
import {Button, TextField} from "@material-ui/core";
import LoginBar from "./LoginBar";
import './Login.css'
import {LoginApi} from './LoginApi'

function Login() {

    const[username, setUsername] = useState("");
    const[password,setPassword] = useState("");

    const loginApi = new LoginApi();

    function handleNewUsername(event) {
        setUsername(event.target.value);
    }

    function handleNewPassword(event) {
        setPassword(event.target.value);
    }

    function handleLoginClick() {
        console.log("Logged In");

        const loginRequest = {
            username: username,
            password: password
        }
        loginApi.login(loginRequest).then(data => {
            if(data != null) {
                localStorage.setItem('userId', data.userId);
                localStorage.setItem('usertype', data.usertype);
                localStorage.setItem('username', data.username);
            }
        })

        setTimeout(function() {
            window.location.href = "http://localhost:3000/home";
        }, 1000)
    }

    return(
        <div className={"login"}>

            <LoginBar/>

            <div className={"login-root"}>
                <div className="login-entries">
                <form className={"login-entry"} noValidate autoComplete="off">
                    <TextField fullWidth label="Username" onChange={handleNewUsername}/>
                </form>

                <form className={"login-entry"} noValidate autoComplete="off">
                    <TextField fullWidth label="Password" type={"password"} onChange={handleNewPassword} />
                </form>

                <div className={"login-button"}>
                    <Button variant="contained" onClick={handleLoginClick}  >
                    Login
                    </Button>
                </div>

                </div>

                <div className="to-register">
                    <div className="to-register-button">
                    <Button variant="outlined" size="large"  href="/register-coder" color="primary" >
                        Register as a Coder
                    </Button>
                    </div>
                    <div className="to-register-button">
                    <Button variant="outlined" size="large"  href="/register-editor" color="primary" >
                        Register as an Editor
                    </Button>
                    </div>
                    <div className="to-register-button">
                    <Button variant="outlined" size="large"  href="/register-company" color="primary" >
                        Register as a Company
                    </Button>
                    </div>
                </div>
            </div>

        </div>
    )
}

export default Login;
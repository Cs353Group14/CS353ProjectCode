import './App.css';
import React from "react";
import {BrowserRouter, Route, Link, Routes} from "react-router-dom";
import Login from "./components/Login/Login";
import RegisterCoder from './components/Register/RegisterCoder';
import RegisterEditor from './components/Register/RegisterEditor';
import RegisterCompany from './components/Register/RegisterCompany';
import Profile from './components/Profile/Profile'

function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path = {"/"} element = {<Login/>}/>
      <Route path = {"/register-coder"} element = {<RegisterCoder/>}/>
      <Route path = {"/register-editor"} element = {<RegisterEditor/>}/>
      <Route path = {"/register-company"} element = {<RegisterCompany/>}/>
      <Route path = {"/profile"} element = {<Profile/>}/>
    </Routes>
    </BrowserRouter>
  );
}


export default App;

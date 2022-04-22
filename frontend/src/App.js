import './App.css';
import React from "react";
import {BrowserRouter, Route, Link, Routes} from "react-router-dom";
import Login from "./components/Login/Login";
import RegisterCoder from './components/Register/RegisterCoder';
import RegisterEditor from './components/Register/RegisterEditor';
import RegisterCompany from './components/Register/RegisterCompany';
import Profile from './components/Profile/Coder/Profile'
import CodingCardContainer from './components/CodingChallenges/CodingCard/CodingCardContainer';
import CodingChallengeView from './components/CodingChallenges/CodingChallengeView/CodingChallengeView';

import CodingChallengeSolve from './components/CodingChallenges/CodingChallengeView/CodingChallangeSolve';
import EditorProfile from './components/Profile/Editor/EditorProfile';
import NonCodingCardContainer from './components/NonCodingChallenges/Components/NonCodingCardContainer';
import NonCodingChallengeView from './components/NonCodingChallenges/View/NonCodingChallengeView'

function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path = {"/"} element = {<Login/>}/>
      <Route path = {"/register-coder"} element = {<RegisterCoder/>}/>
      <Route path = {"/register-editor"} element = {<RegisterEditor/>}/>
      <Route path = {"/register-company"} element = {<RegisterCompany/>}/>
      <Route path = {"/coding-challenges"} element = {<CodingCardContainer/>}/>
      <Route path = {"/non-coding-challenges"} element = {<NonCodingCardContainer/>}/>

      <Route path = {`/coding-challenges/${localStorage.getItem('codingId')}`} element = {<CodingChallengeView/>}/>
      <Route path = {`/non-coding-challenges/${localStorage.getItem('codingId')}`} element = {<NonCodingChallengeView/>}/>

      <Route path = {"/profile"} element = {<Profile/>}/>
      <Route path = {"/profileEditor"} element = {<EditorProfile/>}/>
    </Routes>
    </BrowserRouter>
  );
}


export default App;

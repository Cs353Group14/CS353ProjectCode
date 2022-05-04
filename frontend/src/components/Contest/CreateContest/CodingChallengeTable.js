import { Button } from "@material-ui/core";
import { DataGrid } from "@mui/x-data-grid";
import React, { useEffect, useState } from "react";
import NavBar from "../../NavBar/NavBar";
import { ContestApi } from "../ContestApi";

const columns = [
    { field: 'challenge_id', headerName: 'ID', width: 70 },
    { field: 'title', headerName: 'Title', width: 130 },
    {
      field: 'difficulty',
      headerName: 'Difficulty',
      width: 90,
    },
    {
        field: 'points',
        headerName: 'Point',
        type: 'number',
        width: 90,
      },
  ];

export default function CodingChallengeTable() {

    const[rows, setRows] = useState([]);
    const[selecetedRows, setSelectedRows] = useState([]);
    const contestApi = new ContestApi();

    function fetchCodingChallenges() {
        contestApi.getChallengesOfEditor().then(data => {
            setRows(data);
        });
        
    }

    useEffect(() => {
        fetchCodingChallenges();
    },[]);

    async function handleClick() {
      console.log(localStorage.getItem('contestId'));
      console.log(selecetedRows);

      await contestApi.addQuestionsToContest(selecetedRows);

      window.location.href = "http://localhost:3000/home";
    }

    return (
        <div style={{ height: 400, width: '100%' }}>
          <NavBar/>
          <DataGrid
            rows={rows}
            columns={columns}
            pageSize={5}
            rowsPerPageOptions={[5]}
            checkboxSelection
            getRowId={(row) => row.challenge_id}
            onSelectionModelChange={data => setSelectedRows(data)}
          />

          <br/>
          <br/>

          <div text-align = "right" >
          <Button variant="contained" color="primary" onClick={handleClick}>Save</Button>
          </div>
        </div>
      );
}
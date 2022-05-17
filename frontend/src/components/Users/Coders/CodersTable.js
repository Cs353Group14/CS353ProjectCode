import React, { useEffect, useState } from "react";
import { Button, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Dialog, DialogTitle, DialogContent, DialogActions, IconButton, TextField } from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
import { UserApi } from "../UserApi";
import { Stack } from "@mui/material";
import '../User.css';

const StyledTableCellHead = styled(TableCell)(({ theme }) => ({
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white
}));

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    fontSize: 14
}));


const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));

export default function CodersTable() {

    const[rows, setRows] = useState([]);
    const[currentSubIndex, setCurrentSubIndex] = useState(0);
    const[username, setUsername] = useState("");

    const userApi = new UserApi();

    function fetchCoders() {
        userApi.getCoders().then(data=> setRows(data));

    }

    

    useEffect(() => {
      if(localStorage.getItem('menuId') == 16)
        fetchCoders();
    },[]);

    async function handleSeeProfile(i) {
        //await contestApi.addCoderToContest(rows[currentSubIndex].contest_id);
        console.log(i);  
    }
    function handleUsername() {
        userApi.getCodersFiltered(username).then(data=> setRows(data));

    }

    function handleSeeProfile(i) {
      localStorage.setItem('referredId', rows[i].userId);
      localStorage.setItem('menuId', 17);
        localStorage.setItem('viewer', true);
        window.location.href = "http://localhost:3000/home";
    }

    return (
        <div>
            <br/>
            <br/>

            <div className="username-search">
            <IconButton sx={{ p: '10px' }} aria-label="search" onClick={handleUsername} >
                <SearchIcon />
            </IconButton>
            
            <Stack spacing={3} sx={{ width: 500 }}>
            <TextField id="standard-basic" label="Username" variant="standard" onChange={(e) => setUsername(e.target.value)} />
                </Stack>
                <div className="clear-btn">
                    <Button variant="outlined" href="/home" >Clear</Button>
                </div>
            </div>

        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 700 }} aria-label="customized table">
            <TableHead>
              <TableRow>
                <StyledTableCellHead align="right">Placement</StyledTableCellHead>
                <StyledTableCellHead align="right">Username</StyledTableCellHead>
                <StyledTableCellHead align="right">Name</StyledTableCellHead>
                <StyledTableCellHead align="right">Place</StyledTableCellHead>
                <StyledTableCellHead align="right">Position</StyledTableCellHead>
                <StyledTableCellHead align="right">Rating Point</StyledTableCellHead>
                <StyledTableCellHead align="right">Profile</StyledTableCellHead>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row,i) => (
                <StyledTableRow key={row.userId}>
                  <StyledTableCell align="right">
                    {i+1}
                  </StyledTableCell>
                  <StyledTableCell align="right">{row.username}</StyledTableCell>
                  <StyledTableCell align="right">{row.name}</StyledTableCell>
                  <StyledTableCell align="right">{row.place}</StyledTableCell>
                  <StyledTableCell align="right">{row.position}</StyledTableCell>
                  <StyledTableCell align="right">{row.rating}</StyledTableCell>
                  <StyledTableCell align="right">
                      <Button onClick={() => {
                          console.log(row.userId);
                      setCurrentSubIndex(i);
                      handleSeeProfile(i);
                      }}>See Profile</Button>
                  </StyledTableCell>
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

</div>
      );
}
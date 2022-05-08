import React, { useEffect, useState } from "react";
import { Button, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Dialog, DialogTitle, DialogContent, DialogActions } from "@material-ui/core";
import { ContestApi } from "../ContestApi";
import NavBar from "../../NavBar/NavBar";

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

export default function ContestView() {

    const[contest, setContest] = useState({
        contest_id: -1,
        start_time: "",
        description: "",
        title: "",
        difficulty: 0,
        duration: 0,
        deadline: "",
    });
    const[rows, setRows] = useState([]);
    const[currentSubIndex, setCurrentSubIndex] = useState(null);
    //const[startHidden, setStartHidden] = useState(true);
    //const[continueHidden, setContinueHidden] = useState(true);

    const contestApi = new ContestApi();

    function fetchContestDetails() {
        contestApi.getContestDetails().then(data => {
            setContest(data)
            console.log(data);
            console.log(contest);
        });

    }

    function fetchContestResults() {
        contestApi.getContesOrder().then(data => {setRows(data)});

    }

    useEffect(() => {
        fetchContestDetails();
        fetchContestResults();
    },[]);

    return (
        <div>
            <NavBar/>

            <div>
                <h1>{contest.title}</h1>

                <h2>Description</h2>
                {contest.description}

                <h3>Difficulity</h3> {contest.difficulty}
                <br/>
                <br/>
                <h3>Start Time</h3>{contest.start_time}
                <br/>
                <br/>
                <h3>Deadline</h3> {contest.deadline}
                <br/>
                <br/>
                <h3>Duration</h3> {contest.duration} 
                <br/>
                <br/>
                <br/>
                <br/>
            </div>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 700 }} aria-label="customized table">
            <TableHead>
              <TableRow>
                <StyledTableCellHead align="right">Username</StyledTableCellHead>
                <StyledTableCellHead align="right">Point</StyledTableCellHead>
                <StyledTableCellHead align="right">Details</StyledTableCellHead>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row,i) => (
                <StyledTableRow key={row.userName}>
                  <StyledTableCell align="right">
                    {row.userName}
                  </StyledTableCell>
                  <StyledTableCell align="right">{row.point}</StyledTableCell>
                  <StyledTableCell align="right">
                      <Button onClick={() => {
                      setCurrentSubIndex(i);
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
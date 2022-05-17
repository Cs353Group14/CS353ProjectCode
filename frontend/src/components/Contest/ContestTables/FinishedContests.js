import React, { useEffect, useState } from "react";
import { Button, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Dialog, DialogTitle, DialogContent, DialogActions } from "@material-ui/core";
import { ContestApi } from "../ContestApi";
import { DateConverter } from "../../Common/Message";

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

export default function FinishedContests() {

    const[rows, setRows] = useState([]);
    const[currentSubIndex, setCurrentSubIndex] = useState(null);

    const contestApi = new ContestApi();
    const converter = new DateConverter();

    function fetchFinishedContests() {
        contestApi.getOldRegisteredContests().then(data => {
            setRows(data)});

    }

    useEffect(() => {
      if(localStorage.getItem('menuId') == 10)
      fetchFinishedContests();
    },[]);


    async function openSeeContest(index) {
        localStorage.setItem('contestId', rows[index].contest_id);
        window.location.href = "http://localhost:3000/contest";
    }


    return (
        <div>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 700 }} aria-label="customized table">
            <TableHead>
              <TableRow>
                <StyledTableCellHead align="right">Title</StyledTableCellHead>
                <StyledTableCellHead align="right">Difficulty</StyledTableCellHead>
                <StyledTableCellHead align="right">Start Time</StyledTableCellHead>
                <StyledTableCellHead align="right">Deadline</StyledTableCellHead>
                <StyledTableCellHead align="right">Duration</StyledTableCellHead>
                <StyledTableCellHead align="right">Details</StyledTableCellHead>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row,i) => (
                <StyledTableRow key={row.contest_id}>
                  <StyledTableCell align="right">
                    {row.title}
                  </StyledTableCell>
                  <StyledTableCell align="right">{row.difficulty}</StyledTableCell>
                  <StyledTableCell align="right">{converter.convert(row.start_time)}</StyledTableCell>
                  <StyledTableCell align="right">{converter.convert(row.deadline)}</StyledTableCell>
                  <StyledTableCell align="right">{row.duration}</StyledTableCell>
                  <StyledTableCell align="right">
                      <Button onClick={() => {
                          console.log(row.contest_id);
                      openSeeContest(i);
                      }}>See Details</Button>
                  </StyledTableCell>
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

</div>
      );
}
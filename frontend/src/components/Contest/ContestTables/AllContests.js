import React, { useEffect, useState } from "react";
import { Button, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Dialog, DialogTitle, DialogContent, DialogActions } from "@material-ui/core";
import { ContestApi } from "../ContestApi";

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

export default function AllContests() {

    const[rows, setRows] = useState([]);

    const contestApi = new ContestApi();

    function fetchAllContests() {
        contestApi.getAllContests().then(data => {
            setRows(data)});

    }

    useEffect(() => {
      if(localStorage.getItem('menuId') == 14)
      fetchAllContests();
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
                <StyledTableCellHead align="right">TITLE</StyledTableCellHead>
                <StyledTableCellHead align="right">EDITOR</StyledTableCellHead>
                <StyledTableCellHead align="right">IS FINISHED</StyledTableCellHead>
                <StyledTableCellHead align="right">DETAILS</StyledTableCellHead>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row,i) => (
                <StyledTableRow key={row.contest_id}>
                  <StyledTableCell align="right">
                    {row.title}
                  </StyledTableCell>
                  <StyledTableCell align="right">{row.editorName}</StyledTableCell>
                  <StyledTableCell align="right">
                      <div hidden = {row.deadlinePassed == true}>
                          Finished
                      </div>
                      <div hidden = {row.deadlinePassed == false}>
                          Not Finished
                      </div>
                      </StyledTableCell>
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
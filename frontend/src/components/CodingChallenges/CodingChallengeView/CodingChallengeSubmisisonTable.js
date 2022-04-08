import { Button, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@material-ui/core";
import { tableCellClasses } from "@material-ui/core";
import React from "react";

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
  
  
  export default function CodingChallengeSubmissionTable(props) {

    function handleNewSub(){
  
    }
    
    return (
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCellHead align="right">Date</StyledTableCellHead>
              <StyledTableCellHead align="right">Time</StyledTableCellHead>
              <StyledTableCellHead align="right">Number of Passed Cases</StyledTableCellHead>
              <StyledTableCellHead align="right">Number of Failed Cases</StyledTableCellHead>
              <StyledTableCellHead align="right">Details</StyledTableCellHead>
            </TableRow>
          </TableHead>
          <TableBody>
            {props.submissions.map((row,i) => (
              <StyledTableRow key={row.submission_id}>
                <StyledTableCell align="right">
                  {row.submission_date}
                </StyledTableCell>
                <StyledTableCell align="right">{row.submission_time}</StyledTableCell>
                <StyledTableCell align="right">{row.pass_result}</StyledTableCell>
                <StyledTableCell align="right">{row.fail_result}</StyledTableCell>
                <StyledTableCell align="right">
                    <Button onClick={() => {
                    props.setCurrentSubIindex(i);
                    }}>Details</Button>
                </StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    );
  }
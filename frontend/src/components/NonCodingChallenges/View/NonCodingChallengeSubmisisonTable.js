import { Button, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@material-ui/core";
import { tableCellClasses } from "@material-ui/core";
import React from "react";
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
  
  
  export default function NonCodingChallengeSubmissionTable(props) {

    function handleNewSub(){
  
    }

    const converter = new DateConverter();
    
    return (
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCellHead align="left">Time</StyledTableCellHead>

              <StyledTableCellHead align="left">Details</StyledTableCellHead>
            </TableRow>
          </TableHead>
          <TableBody>
            {props.submissions.map((row,i) => (
              <StyledTableRow key={row.nonChallengeId}>
                <StyledTableCell align="left">{converter.convert(row.replyTime)}</StyledTableCell>
                <StyledTableCell align="left">
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
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

export default function ContinuesContests() {

    const[rows, setRows] = useState([]);
    const[currentSubIndex, setCurrentSubIndex] = useState(null);

    const contestApi = new ContestApi();

    function fetchContinuesContests() {
        contestApi.getAvailableRegisteredContests().then(data => {
            setRows(data)});

    }

    useEffect(() => {
        fetchContinuesContests();
    },[]);

    async function handleStart() {
        //await contestApi.addCoderToContest(rows[currentSubIndex].contest_id);
        setOpen(false);
        //fetchContinuesContests();
    }

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        console.log(currentSubIndex);        
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    function ContestDetails() {
        if(rows != []) { 
            return (<div>

            <h2> {rows[currentSubIndex].title} </h2>

            {rows[currentSubIndex].description}
            </div>);
        }
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
                  <StyledTableCell align="right">{row.start_time}</StyledTableCell>
                  <StyledTableCell align="right">{row.deadline}</StyledTableCell>
                  <StyledTableCell align="right">{row.duration}</StyledTableCell>
                  <StyledTableCell align="right">
                      <Button onClick={() => {
                          console.log(row.contest_id);
                      setCurrentSubIndex(i);
                      handleClickOpen();
                      }}>Details</Button>
                  </StyledTableCell>
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

<Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
<DialogTitle id="form-dialog-title">Contest Detail</DialogTitle>
<DialogContent>
   <ContestDetails/>
</DialogContent>
<DialogActions>
    <Button onClick={handleClose} color="primary">
        Cancel
    </Button>
    <Button onClick={handleStart} color="primary">
        Start
    </Button>
</DialogActions>
</Dialog>
</div>
      );
}
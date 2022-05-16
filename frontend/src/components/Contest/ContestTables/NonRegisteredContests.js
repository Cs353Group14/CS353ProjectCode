import React, { useEffect, useState } from "react";
import { Button, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Dialog, DialogTitle, DialogContent, DialogActions } from "@material-ui/core";
import { ContestApi } from "../ContestApi";
import { MessageType } from "../../Common/Message";
import { ToastContainer,toast } from 'react-toastify';

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

export default function NonRegisteredContests() {

    const[rows, setRows] = useState([]);
    const[currentSubIndex, setCurrentSubIndex] = useState(0);

    const contestApi = new ContestApi();

    function fetchNonRegisteredFuruteContests() {
        contestApi.getFutureContestsNotRegistered().then(data => {
          setRows(data)
          console.log(data);
          console.log(rows);
        });

    }

    useEffect(() => {
      if(localStorage.getItem('menuId') == 7)
        fetchNonRegisteredFuruteContests();
    },[]);

    async function handleRegister() {
        const response = await contestApi.addCoderToContest(rows[currentSubIndex].contest_id);
        setOpen(false);
        if (response.messageType === MessageType.ERROR) {
          toast.error(response.message);
        } else {
            toast.success(response.message);
            setTimeout(function() {
                window.location.href = "http://localhost:3000/home";
            }, 2000)
        }
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
            console.log("here");
            return (<div>

            <h2> {rows[currentSubIndex].title} </h2>

            {rows[currentSubIndex].description}
            </div>);
        }

        console.log("there");
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
        Close
    </Button>
    <Button onClick={handleRegister} color="primary">
        Register
    </Button>
</DialogActions>
</Dialog>
<ToastContainer />
</div>
      );
}
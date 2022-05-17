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

export default function ContinuesContests() {

    const[rows, setRows] = useState([]);
    const[currentSubIndex, setCurrentSubIndex] = useState(null);
    const[startHidden, setStartHidden] = useState(true);
    const[continueHidden, setContinueHidden] = useState(true);

    const contestApi = new ContestApi();

    function fetchContinuesContests() {
        contestApi.getAvailableRegisteredContests().then(data => {
            setRows(data)});

    }

    useEffect(() => {
      if(localStorage.getItem('menuId') == 9)
        fetchContinuesContests();
    },[]);

    async function handleStart() {
        const response = await contestApi.participateContest(rows[currentSubIndex].contest_id);
        setOpen(false);
        localStorage.setItem('contestId', rows[currentSubIndex].contest_id);

        if (response.messageType === MessageType.ERROR) {
          toast.error(response.message);
        } else {
            toast.success(response.message);
            setTimeout(function() {
                window.location.href = "http://localhost:3000/SolveContest";
            }, 5000)
        }
        
        //fetchContinuesContests();
    }
    
    function handleContinue() {
        setOpen(false);
        localStorage.setItem('contestId', rows[currentSubIndex].contest_id);
        window.location.href = "http://localhost:3000/SolveContest";
    }

    function handleLeaderBoard(){
        localStorage.setItem('contestId', rows[currentSubIndex].contest_id);
        window.location.href = "http://localhost:3000/contest";
    }

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = async (i) => {
      console.log(rows[i]);
         const data = await contestApi.getContestStatus(rows[i].contest_id)
                //  .then(data => {
                      if(data == 0 ){
                        setStartHidden(false);
                        setContinueHidden(true);
                      } else if (data == 1) {
                        setStartHidden(true);
                        setContinueHidden(false);
                      } else {
                        setStartHidden(true);
                        setContinueHidden(true);
                      }
                 // })
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
                <StyledTableCellHead align="right">See Details and Start</StyledTableCellHead>
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
                      setCurrentSubIndex(i);
                      handleClickOpen(i);
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
    <div>
    <Button onClick={handleLeaderBoard} color="primary">
        See LeaderBoard
    </Button>
    </div>
    <div hidden = {startHidden}>
    <Button onClick={handleStart} color="primary">
        Start
    </Button>
    </div>
    <div hidden = {continueHidden}>
    <Button onClick={handleContinue} color="primary">
        Continue
    </Button>
    </div>
</DialogActions>
</Dialog>
<ToastContainer />
</div>
      );
}
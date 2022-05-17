import React, { useEffect, useState } from "react";
import { Button, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Dialog, DialogTitle, DialogContent, DialogActions } from "@material-ui/core";
import { ContestApi } from "../ContestApi";
import NavBar from "../../NavBar/NavBar";
import { DateConverter, MessageType } from "../../Common/Message";
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

export default function ContestView() {

  const[sdate, setSdate] = useState("");
  const[edate, setEdate] = useState("");
    const[contest, setContest] = useState({
        contest_id: -1,
        start_time: "",
        description: "",
        title: "",
        difficulty: 0,
        duration: 0,
        deadline: "",
        author: "",
    });
    const[statistic,setStatistic] = useState({
      maxPoint:0,
      minPoint:0,
      avgPoint:0,
      userNumber:0
    })
    const[rows, setRows] = useState([]);
    const[sponsors, setSponsors] = useState([]);
    const[currentSubIndex, setCurrentSubIndex] = useState(null);
    //const[startHidden, setStartHidden] = useState(true);
    //const[continueHidden, setContinueHidden] = useState(true);

    const contestApi = new ContestApi();
    const converter = new DateConverter();

    function fetchContestDetails() {
        contestApi.getContestDetails().then(data => {
            setContest(data);
            setSdate(converter.convert( data.start_time));
            setEdate(converter.convert(data.deadline));
            console.log(converter.convert( data.start_time));
            console.log(contest);
        });

        contestApi.getContestSponsors().then(data => setSponsors(data) );

    }

    function fetchContestResults() {
        contestApi.getContesOrder().then(data => {setRows(data)});

    }

    function fetchContestStatistic() {
      contestApi.getContestStatistic().then(data => {setStatistic(data)});

  }

    useEffect(() => {
        fetchContestDetails();
        fetchContestResults();
        fetchContestStatistic();
    },[]);

    function handleSeeProfile(i) {
      localStorage.setItem('referredId', rows[i].id);
      localStorage.setItem('menuId', 17);
        localStorage.setItem('viewer', true);
        window.location.href = "http://localhost:3000/home";
    }

    async function handleSponsor() {
      const response = await contestApi.makeCompanySponsorToContest(contest.contest_id);
      if (response.messageType === MessageType.ERROR) {
        toast.error(response.message);
      } else {
          toast.success(response.message);
      }
    }

    return (
        <div>
            <NavBar/>

            <div className="coding-challenge-view-body">
            
              <div className="coding-challenge-view-left">
              <h1>{contest.title}</h1>

              <h3>Author</h3>
                {contest.author}

                <h3>Description</h3>
                {contest.description}

                <h3>Difficulity</h3> {contest.difficulty}
                <br/>
                <br/>
                <h3>Start Time</h3>{sdate}
                <br/>
                <br/>
                <h3>Deadline</h3> {edate}
                <br/>
                <br/>
                <h3>Duration</h3> {contest.duration} 
                <br/>
                <br/>
                <br/>
                <br/>
              </div>
               
              <div className="coding-challenge-view-right">
              <br/>
                <br/>
                <br/>
                <br/>
              <h3>Number of Participants</h3> {statistic.userNumber}
                <br/>
                <br/>
                <h3>Average Point</h3>{statistic.avgPoint}
                <br/>
                <br/>
                <h3>Max Point</h3> {statistic.maxPoint}
                <br/>
                <br/>
                <h3>Min Point</h3> {statistic.minPoint} 
                <br/>
                <br/>
                <br/>
                <br/>
                <h3>Sponsors</h3> {sponsors.map(s => <div> {s} &nbsp; </div>)} 
                <br/>
                <br/>

              </div>
                
            </div>

            <div hidden = {localStorage.getItem('usertype') != 2}>
              <Button variant="contained" color = "primary" onClick={handleSponsor}>Be Sponsor</Button>  
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
                        handleSeeProfile(i);
                      setCurrentSubIndex(i);
                      }}>See Profile</Button>
                  </StyledTableCell>
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

        <ToastContainer />

</div>
      );
}
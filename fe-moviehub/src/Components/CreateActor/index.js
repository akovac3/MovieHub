import * as React from 'react';
import PropTypes from 'prop-types';
import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableContainer from '@mui/material/TableContainer';
import TableFooter from '@mui/material/TableFooter';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import IconButton from '@mui/material/IconButton';
import FirstPageIcon from '@mui/icons-material/FirstPage';
import KeyboardArrowLeft from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRight from '@mui/icons-material/KeyboardArrowRight';
import LastPageIcon from '@mui/icons-material/LastPage';
import { getActors } from '../../Api/Movie/movie'
import { postActor } from '../../Api/Movie/movie'
import {useEffect, useRef} from 'react'
import TableHead from '@mui/material/TableHead';
import { styled } from '@mui/material/styles';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TextField from '@mui/material/TextField';
import { Button} from '@material-ui/core';
import { Typography } from '@material-ui/core';
import { Container, makeStyles } from '@material-ui/core';
import { message } from 'antd'
import Grid from '@material-ui/core/Grid'
import { orange } from '@material-ui/core/colors';

import './CreateActor.css'

const ColorButton = styled(Button)(({ theme }) => ({
    color: theme.palette.getContrastText(orange[500]),
    backgroundColor: orange[500],
    '&:hover': {
      backgroundColor: orange[800]
    },
  }));




function TablePaginationActions(props) {
  const theme = useTheme();
  const { count, page, rowsPerPage, onPageChange } = props;

  const handleFirstPageButtonClick = (event) => {
    onPageChange(event, 0);
  };

  const handleBackButtonClick = (event) => {
    onPageChange(event, page - 1);
  };

  const handleNextButtonClick = (event) => {
    onPageChange(event, page + 1);
  };

  const handleLastPageButtonClick = (event) => {
    onPageChange(event, Math.max(0, Math.ceil(count / rowsPerPage) - 1));
  };

  


  return (
    <Box sx={{ flexShrink: 0, ml: 2.5 }}>
      <IconButton
        onClick={handleFirstPageButtonClick}
        disabled={page === 0}
        aria-label="first page"
      >
        {theme.direction === 'rtl' ? <LastPageIcon /> : <FirstPageIcon />}
      </IconButton>
      <IconButton
        onClick={handleBackButtonClick}
        disabled={page === 0}
        aria-label="previous page"
      >
        {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
      </IconButton>
      <IconButton
        onClick={handleNextButtonClick}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
        aria-label="next page"
      >
        {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
      </IconButton>
      <IconButton
        onClick={handleLastPageButtonClick}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
        aria-label="last page"
      >
        {theme.direction === 'rtl' ? <FirstPageIcon /> : <LastPageIcon />}
      </IconButton>
    </Box>
  );
}

TablePaginationActions.propTypes = {
  count: PropTypes.number.isRequired,
  onPageChange: PropTypes.func.isRequired,
  page: PropTypes.number.isRequired,
  rowsPerPage: PropTypes.number.isRequired,
};


const columns = [
    { id: 'name', label: 'First name' },
    { id: 'lastName', label: 'Last name'},
    {
      id: 'image',
      label: 'Image'
    },
  ];

  const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
      backgroundColor: theme.palette.common.black,
      color: theme.palette.common.white,
      fontSize:20,
      fontWeight: 'bold',
    },
    [`&.${tableCellClasses.body}`]: {
      fontSize: 14,
      color: theme.palette.common.black,
      fontWeight: 'bold'
    },
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

  const useStyles = makeStyles({
    field: {
        marginTop: 20,
        marginBottom: 20,
        display: 'block',
        width:50
    }

  })
  

export default function CustomPaginationActionsTable() {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const [actors, setActors] = React.useState([]);
  const [fName, setFName] = React.useState('');
  const [lName, setLName] = React.useState('');
  const [img, setImg] = React.useState('');
  const [firstError, setFirstError] = React.useState(false);
  const [lastError, setLastError] = React.useState(false);
  const classes = useStyles()
  const [loading, setLoading] = React.useState(false)


  const onFinish = async (values) => {
    try {
      setLoading(true)

      const response = await postActor(values)
      message.success('Successfully saved actor')
      setLoading(false)
      window.location.reload(false);
    } catch (error) {
      console.log(error)

      setLoading(false)
      message.warning(error.response.data.message)
    }
  }

 const handleSubmit = (e) => {
    e.preventDefault()
    setFirstError(false)
    setLastError(false)

    if(fName == '') {
        setFirstError(true)
    }

    
    if(lName == '') {
        setLastError(true)
    }

    if(fName && lName){const values = {
            firstName: fName,
            lastName: lName,
            image: img,
          }
          onFinish(values)
        }

 }


  const getData = async (values) => {
    try {
      return await getActors()
    } catch (error) {
      console.log(error)
      console.warning(error.response.data.message)
    }
  }


  useEffect(() => {
    async function fetchData() {
        console.log("desi se")
        try {
          const response = await getData()
          console.log(response)
          setActors(response)
          actors.sort((a, b) => (a.firstName < b.firstName ? -1 : 1));
        } catch (e) {
          console.error(e)
        }
    }
    fetchData()
  }, [])


  // Avoid a layout jump when reaching the last page with empty rows.
  const emptyRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - actors.length) : 0;

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };
 
  return (
    <>
    <TableContainer component={Paper}>
      <Table stickyHeader sx={{ minWidth: 500 }} aria-label="custom pagination table">
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <StyledTableCell 
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </StyledTableCell >
              ))}
            </TableRow>
          </TableHead>

        <TableBody>
          {(rowsPerPage > 0
            ? actors.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
            : actors
          ).map((row) => (
            <StyledTableRow key={row.id}>
              <StyledTableCell component="th" scope="row">
                {row.firstName}
              </StyledTableCell>
              <StyledTableCell >
                {row.lastName}
              </StyledTableCell>
              <StyledTableCell style={{ 'color':'black' }}>
                {row.image}
              </StyledTableCell>
            </StyledTableRow>
          ))}

          {emptyRows > 0 && (
            <StyledTableRow style={{ height: 53 * emptyRows }}>
              <StyledTableCell colSpan={6} />
            </StyledTableRow>
          )}
        </TableBody>
        <TableFooter>
          <TableRow>
            <TablePagination
              rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
              colSpan={3}
              count={actors.length}
              rowsPerPage={rowsPerPage}
              page={page}
              SelectProps={{
                inputProps: {
                  'aria-label': 'rows per page',
                },
                native: true,
              }}
              onPageChange={handleChangePage}
              onRowsPerPageChange={handleChangeRowsPerPage}
              ActionsComponent={TablePaginationActions}
            />
          </TableRow>
        </TableFooter>
      </Table>
    </TableContainer>
    <div className='form-container' >

        <Typography
            variant = "h3"
            color='secondary'
            component='h2'
            gutterBottom
            > Create new actor</Typography>

    <form noValidate autoComplete='off' onSubmit={handleSubmit} style={{'padding':'10px'}}
>
        <TextField
          onChange={(e) => setFName(e.target.value)}
          label="First name"
          required
          fullWidth
          error={firstError}
          variant='outlined'
          style={{'marginBottom':'50px'}}
        /> 
        <TextField
               onChange={(e) => setLName(e.target.value)}
               label="Last name"
               required
               fullWidth
               error={lastError}
               variant='outlined'
               style={{'marginBottom':'50px'}}
        /> 

        <TextField
                    onChange={(e) => setImg(e.target.value)}
                    label="Image"
                    required
                    fullWidth
                    variant='outlined'
                    style={{'marginBottom':'50px'}}
        /> 
        <ColorButton type='submit' variant='contained'>Submit</ColorButton>

        </form>
    </div>
    </>
  );
}

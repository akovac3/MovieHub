import React, { useEffect, useState } from 'react'
import Box from '@material-ui/core/Box'
import Grid from '@material-ui/core/Grid'
import Container from '@mui/material/Container'
import { makeStyles } from '@material-ui/core/styles'
import TextField from '@mui/material/TextField'
import { getUser } from '../utilities/localStorage'




const useStyles = makeStyles((theme) => ({
    root: {
      height: '100vh',
      paddingBottom: '20px',
    },
    grid: {
      height: '100vh',
      marginTop: '10px',
    },
    paperLeft: {
      height: '100vh',
    },
    paperTop: {
      height: '20%',
    },
    paperMain: {
      height: '100vh',
    },
    paperRight: {},
    paperBottom: { height: '20%' },
    paper: {
      textAlign: 'center',
      color: theme.palette.text.primary,
      background: theme.palette.grey,
    },
  }))
  
  export default function UserPage() {

    const [rows, setRows] = useState([])
    const [jobs, setJobs] = useState([])
    const [searched, setSearched] = useState('')
    const [open, setOpen] = useState(false)
    const [images, setImages] = useState([])
    const [currentImg, setCurrentImg] = useState()
    const [position, setPosition] = useState(0)
    const [openReview, setOpenReview] = useState(false)
    const [value, setValue] = useState(2)
    const classes = useStyles()
    const [selectedRow, setSelectedRow] = useState()
    const [textReview, setTextReview] = useState('')
    const [deals, setDeals] = useState([])
    const klase = useStyles();
    const user = getUser()
    const [userProfile, setProfile] = useState(user)
    const [file, setFile] = useState();
    
  useEffect(() => {
    async function fetchData() {
      try {
        const user = getUser()
        console.log(user)
      
      } catch (e) {
        console.error(e)
      }
    }
    fetchData()
  }, [])


return (
    <div className={classes.root} style={{ paddingBottom: '10px' }}>
      <Container component='main' maxWidth='xs'>
<Box
component='form'
noValidate
//onSubmit={handleSubmit}
sx={{ mt: 3 }}
>
<Grid container spacing={2}>
<Grid item xs={12}>
  <div className="App"  style={{
    alignItems: 'center'}}>
</div>
  </Grid> 
  <Grid item xs={12} sm={6}>
    <TextField
      value={user.firstName}
      fullWidth
      id='firstName'
      label='First Name'
      name='firstName'
      autoComplete
    />
  </Grid>
  <Grid item xs={12} sm={6}>
    <TextField
      value={user.lastName}
      fullWidth
      id='lastName'
      label='Last Name'
      name='lastName'
      autoComplete
    />
  </Grid>
  <Grid item xs={12}>
    <TextField
      value={userProfile.email} 
      fullWidth
      id='email'
      label='Email Address'
      name='email'
    />
  </Grid>
  <Grid item xs={12}>
    <TextField
    value={userProfile.number} 
      fullWidth
      id='number'
      label='Phone Number'
      name='number'
      type='tel'
    />
  </Grid>
  <Grid item xs={12}>
    <TextField
    value={userProfile.username} 
      fullWidth
      name='username'
      label='Username'
      type='username'
      id='username'
    />
  </Grid>
  <Grid item xs={12}>
    <TextField
      value={userProfile.city} 
      fullWidth
      name='city'
      label='City'
      id='city'
    />
  </Grid> 
</Grid>  
</Box>
</Container>

</div>)

  }
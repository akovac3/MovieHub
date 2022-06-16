import { useHistory } from 'react-router-dom'
import React, { useState, useEffect } from 'react'
import Avatar from '@mui/material/Avatar'
import Button from '@mui/material/Button'
import CssBaseline from '@mui/material/CssBaseline'
import TextField from '@mui/material/TextField'
import { signUp } from '../../Api/User/auth'
import { message } from 'antd'
import Link from '@mui/material/Link'
import Grid from '@mui/material/Grid'
import Box from '@mui/material/Box'
import LockOutlinedIcon from '@mui/icons-material/LockOutlined'
import Typography from '@mui/material/Typography'
import Container from '@mui/material/Container'
import { createTheme, ThemeProvider } from '@mui/material/styles'
import { loginUrl, homeUrl } from '../../utilities/appUrls'
import { setSession } from '../../utilities/localStorage'
import { useUserContext } from '../../AppContext'
import axios from 'axios';
import { orange } from '@mui/material/colors';
import { styled } from '@mui/material/styles';



const ColorButton = styled(Button)(({ theme }) => ({
  color: theme.palette.getContrastText(orange[500]),
  backgroundColor: orange[500],
  '&:hover': {
    backgroundColor: orange[800]
  },
}));

function Copyright(props) {
  return (
    <Typography
      variant='body2'
      color='text.secondary'
      align='center'
      {...props}
    >
      {'Copyright © '}
      <Link color='inherit' href={homeUrl}>
        MovieHub{' '}
      </Link>
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  )
}

const theme = createTheme()

export default function Register() {
  const history = useHistory()
  const { setLoggedIn, setRole } = useUserContext()
  const [loading, setLoading] = useState(false)
  const [status, setStatus] = useState(null)

  useEffect(() => {
  }, [])

  const onFinish = async (values) => {
    try {
      setLoading(true)
      const response = await signUp(values)
      message.success('Successfully registered')
      setLoading(false)
      setSession(response)
      setRole(response.roles[0].name)
     setLoggedIn(true)
     history.goBack()
    } catch (error) {
      setLoading(false)
      console.log(error)
      message.warning(error.response.data.message)
    }
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    const data = new FormData(event.currentTarget)
    const values = {
      username: data.get('username'),
      password: data.get('password'),
      firstName: data.get('firstName'),
      lastName: data.get('lastName'),
      email: data.get('email'),
    }
    onFinish(values)
  }


  return (
    <ThemeProvider theme={theme}>
      <Container component='main' maxWidth='xs'>
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: orange[500]}}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component='h1' variant='h5'>
            Sign up
          </Typography>
          <Box
            component='form'
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}
          >
            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <TextField
                  autoComplete='given-name'
                  name='firstName'
                  required
                  fullWidth
                  id='firstName'
                  label='First Name'
                  autoFocus
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  fullWidth
                  id='lastName'
                  label='Last Name'
                  name='lastName'
                  autoComplete
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id='email'
                  label='Email Address'
                  name='email'
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name='username'
                  label='Username'
                  type='text'
                  id='username'
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name='password'
                  label='Password'
                  type='password'
                  id='password'
                />
              </Grid>
            </Grid>
            <ColorButton
              type='submit'
              fullWidth
              variant='contained'
              loading={loading}
              sx={{ mt: 3, mb: 2 }}
              style={{ 'marginTop':'20px', 'padding':'10px'}}
            >
              Sign Up
            </ColorButton>
            <Grid container justifyContent='center'>
              <Grid item>
                <Link href={loginUrl} variant='body2'>
                  Already have an account? Sign in
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt: 5 }} />
      </Container>
    </ThemeProvider>
  )
}

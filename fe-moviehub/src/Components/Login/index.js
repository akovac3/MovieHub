import React, { useState } from 'react'
import { useHistory } from 'react-router-dom'
import { message } from 'antd'
import Avatar from '@mui/material/Avatar'
import Button from '@mui/material/Button'
import CssBaseline from '@mui/material/CssBaseline'
import TextField from '@mui/material/TextField'
import FormControlLabel from '@mui/material/FormControlLabel'
import Checkbox from '@mui/material/Checkbox'
import Link from '@mui/material/Link'
import Grid from '@mui/material/Grid'
import Box from '@mui/material/Box'
import LockOutlinedIcon from '@mui/icons-material/LockOutlined'
import Typography from '@mui/material/Typography'
import Container from '@mui/material/Container'
import { createTheme, ThemeProvider } from '@mui/material/styles'
import { registerUrl, homeUrl} from "../../utilities/appUrls";
import { login } from "../../Api/User/auth";
import { getRememberInfo, removeRememberInfo, setRememberInfo, setSession } from "../../utilities/localStorage";
import { useUserContext } from "../../AppContext";
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

export default function SignIn() {
  const history = useHistory()
  const { setLoggedIn, setRole } = useUserContext()
  const rememberInfo = getRememberInfo()
  const [loading, setLoading] = useState(false)
  const [isTrue, setIsTrue] = React.useState(false)

  const onFinish = async (values) => {
    try {
      setLoading(true)

      const response = await login(values)
      setRole(response.roles[0].name)
      message.success('Successfully logged in')
      setLoading(false)
      setSession(response)
      if (isTrue) {
        setRememberInfo(values.username, values.password)
      } else {
        removeRememberInfo()
      }
      let session = JSON.stringify(response)
      console.log(JSON.parse(session).jwt)
      history.goBack()
      setLoggedIn(true)
    } catch (error) {
      console.log(error)

      setLoading(false)
      message.warning(error.response.data.message)
    }
  }
  const handleSubmit = async (event) => {
    event.preventDefault()
    const data = new FormData(event.currentTarget)
    const values = {
      username: data.get('username'),
      password: data.get('password'),
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
          <Avatar sx={{ m: 1, bgcolor: orange[500] }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component='h1' variant='h5'>
            Sign in
          </Typography>
          <Box
            component='form'
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              margin='normal'
              required
              fullWidth
              id='username'
              label='Username'
              name='username'
              autoComplete='username'
              value={rememberInfo?.username}
              autoFocus
            />
            <TextField
              margin='normal'
              required
              fullWidth
              name='password'
              label='Password'
              type='password'
              id='password'
              value={rememberInfo?.password}
              autoComplete='password'
            />
            <FormControlLabel
              control={
                <Checkbox
                  value='remember'
                  valuePropName='checked'
                  color='primary'
                  checked={isTrue}
                  onChange={(e) => {
                    setIsTrue(e.target.checked)
                  }}
                />
              }
              label='Remember me'
            />
            <ColorButton
              loading={loading}
              type='submit'
              fullWidth
              variant='contained'
              sx={{ mt: 3, mb: 2 }}
              style={{ 'marginTop':'20px', 'padding':'10px'}}
            >
              Log In
            </ColorButton>
            <Grid container justifyContent='center'>
              <Grid item>
                <Link href={registerUrl}>
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt: 8, mb: 4 }} />
      </Container>
    </ThemeProvider>
  )
}
import React from 'react'
import {
  AppBar,
  Toolbar,
  CssBaseline,
  makeStyles,
  useTheme,
  useMediaQuery,
} from '@material-ui/core'
import { Link } from 'react-router-dom'
import DrawerComponent from './Drawer'
import {ReactComponent as ReactLogo} from '../../../images/moviehub.svg'
import { removeSession } from '../../../utilities/localStorage'
import { useUserContext } from '../../../AppContext'
import { withTheme } from '@emotion/react'

const useStyles = makeStyles((theme) => ({
  navlinks: {
    marginLeft: theme.spacing(5),
    
  },
  logo: {
    flexGrow: '1',
    cursor: 'pointer',
  },
  icon: {
    width: '300px',
    height: '50px',
    marginRight: '2px',
    marginTop: '5px',
  },
  link: {
    textDecoration: 'none',
    color: 'white',
    fontSize: '20px',
    marginLeft: theme.spacing(20),
    '&:hover': {
      color: 'gray',
      borderBottom: '1px solid white',
    },
  },
}))

function Navbar() {
  const classes = useStyles()
  const theme = useTheme()
  const isMobile = useMediaQuery(theme.breakpoints.down('md'))
  const { loggedIn } = useUserContext()
  const { setLoggedIn, role } = useUserContext()
  const handleLogout = () => {
    setLoggedIn(false)
    removeSession()
  }
  return (
    <AppBar position='static' style={{'height':'50px'}}>
      <CssBaseline />
      <Toolbar  style={{'height':'50px'}}>
        {isMobile ? (
          <>
            <DrawerComponent />
            <ReactLogo className={classes.icon} />
          </>
        ) : (
          <>
            <ReactLogo className={classes.icon} />
            <div className={classes.navlinks}>
              <Link to='/' className={classes.link}>
                Home
              </Link>

              {!loggedIn ? (
                <Link to='/login' className={classes.link}>
                  Login
                </Link>
              ) : (
                <>
                  {role === 'ROLE_ADMIN' && (
                    <>
                      <Link to='/users' className={classes.link}>
                        Users
                      </Link>
                      <Link to='/support' className={classes.link}>
                        Support
                      </Link>
                    </>
                  )}

                  {role === 'ROLE_WORKER' && (
                    <>
                      <Link to='/workerpage' className={classes.link}>
                        My jobs
                      </Link>
                    </>
                  )}

                  {role === 'ROLE_USER' && (
                    <>
                      <Link to='/userpage' className={classes.link}>
                        Jobs
                      </Link>
                    </>
                  )}
                  <Link to='/' onClick={handleLogout} className={classes.link}>
                    Signout
                  </Link>
                </>
              )}
            </div>
          </>
        )}
      </Toolbar>
    </AppBar>
  )
}
export default Navbar

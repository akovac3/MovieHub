import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import BottomNavigation from "@material-ui/core/BottomNavigation";
import BottomNavigationAction from "@material-ui/core/BottomNavigationAction";
import TvIcon from "@material-ui/icons/Tv";
import MovieIcon from "@material-ui/icons/Movie";
import SearchIcon from "@material-ui/icons/Search";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import ViewListIcon from '@mui/icons-material/ViewList';
import { useHistory } from "react-router-dom";
import { useUserContext } from "../AppContext";
import LogoutIcon from '@mui/icons-material/Logout';
import LoginIcon from '@mui/icons-material/Login';
import { removeSession, removeRememberInfo, getUser } from '../utilities/localStorage'
import PersonOutlineIcon from '@mui/icons-material/PersonOutline';
import AddReactionOutlinedIcon from '@mui/icons-material/AddReactionOutlined';
import { userRole } from "../utilities/common";
import axios from "axios";


const useStyles = makeStyles({
  root: {
    width: "100%",
    position: "fixed",
    bottom: 0,
    backgroundColor: "#2d313a",
    zIndex: 100,
  },
});

export default function SimpleBottomNavigation() {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);
  const history = useHistory();
  const { loggedIn } = useUserContext()
  const { setLoggedIn, role } = useUserContext()
  const user = getUser()
  const [watchlistId, setWatchlistId] = useState('');

      const getWatchlistId = () => {
        return axios.get("http://localhost:8089/user/api/user/"+user.id+ "/watchlist");
        }

  const handleLogout = () => {
    setLoggedIn(false)
    removeSession()
    removeRememberInfo()
    window.location.reload(false);
  }

  useEffect(() => {
    async function fetchData() {
      try {
        const watchlistResponse = await getWatchlistId()
        setWatchlistId(watchlistResponse.data)
        console.log(watchlistId)
      } catch (e) {
        console.error(e)
      }
  }
  fetchData()
    console.log(role)
    if (value === 0) {
      history.push("/");
    } else if (role==="ROLE_ADMIN" && value === 1) {
      history.push("/create-movie");
    } else if (role==="ROLE_USER" && value === 2){
      history.push({pathname:'/watchlist', search: '?id='+watchlistId,
      state: { detail: watchlistId }
});

    }
     else if ( role==="ROLE_ADMIN" && value === 3) {
      history.push("/actors");
    } else if ( role==="ROLE_ADMIN" && value === 4) {
      history.push("/genres");
    } else if (role!=="" && value === 5) {
      history.push("/my-account");
    }
     else if ((role==="ROLE_ADMIN" || role==="ROLE_USER") && value===6){
        handleLogout()
     }

    else if(role!=="ROLE_ADMIN" && role!="ROLE_USER" && value===7){
      history.push('/register')
    }
    else if(role!=="ROLE_ADMIN" && role!="ROLE_USER" && value===8){
      history.push('/login')
    }
  }, [value, history]);

  

  return (
    <BottomNavigation
      value={value}
      onChange={(event, newValue) => {
        setValue(newValue);
      }}
      showLabels
      className={classes.root}
    >
      <BottomNavigationAction
        style={{ color: "white" }}
        label="Movies"
        icon={<MovieIcon />}
      />
      {role === 'ROLE_ADMIN' && (
      <BottomNavigationAction
        style={{ color: "white" }}
        label="Create movie"
        icon={<TvIcon />}
      />)}

{role === 'ROLE_USER' && (
      <BottomNavigationAction
        style={{ color: "white" }}
        label="Watchlist"
        icon={<ViewListIcon />}
      />)}

{role === 'ROLE_ADMIN' && (
      <BottomNavigationAction
        style={{ color: "white" }}
        label="Actors"
        icon={<PersonOutlineIcon />}
      />)}

{role === 'ROLE_ADMIN' && (
      <BottomNavigationAction
        style={{ color: "white" }}
        label="Genres"
        icon={<AddReactionOutlinedIcon />}
      />)}
      
{role !== '' && (
      <BottomNavigationAction
      style={{ color: "white" }}
      label="My account"
      icon={<AccountCircleIcon />}
    />)}

{role !== '' && (
    <BottomNavigationAction
      style={{ color: "white" }}
      label="Log out"
      icon={<LogoutIcon />}
    />)}

{role === '' && (
<BottomNavigationAction
      style={{ color: "white" }}
      label="Sign up"
      icon={<LoginIcon />}
    />)}
    {role === '' && (
<BottomNavigationAction
      style={{ color: "white" }}
      label="Log in"
      icon={<LoginIcon />}
    />)}

    </BottomNavigation>
  ); 
}
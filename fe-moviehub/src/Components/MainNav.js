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
  const { setLoggedIn } = useUserContext()
  const [role, setRole]= useState('');

  useEffect(() => {
    setRole("ROLE_USER")
    console.log(role)
    if (value === 0) {
      history.push("/");
    } else if (role==="ROLE_ADMIN" && value === 1) {
      history.push("/create-movie");
    } else if (role==="ROLE_USER" && value === 2){
        history.push("/watchlist");
    }
     else if (value === 3) {
      history.push("/search");
    } else if (value === 4) {
      history.push("/my-account");
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

      <BottomNavigationAction
        style={{ color: "white" }}
        label="Search"
        icon={<SearchIcon />}
      />

      <BottomNavigationAction
      style={{ color: "white" }}
      label="My account"
      icon={<AccountCircleIcon />}
    />
    </BottomNavigation>
  ); 
}
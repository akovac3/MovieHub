import * as React from 'react';
import { useState, useEffect } from 'react';
import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import CameraIcon from '@mui/icons-material/PhotoCamera';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Link from '@mui/material/Link';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { getAllMovies } from '../../Api/Movie/movie';
import axios from 'axios';
import { Table } from 'antd';
import Column from 'antd/lib/table/Column';
import {getAllMoviesFromWatchlist} from '../../Api/Watchlist/watchlist'
import ContentWatchlist from "../ContentWatchlist/ContentWatchlist";
import './watchlist.css'
import { useLocation, useHistory} from "react-router-dom";


function Copyright() {
  return (
    <Typography variant="body2" color="text.secondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        MovieHub
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}


export default function Watchlist(){
    const [movies, setMovies] = useState(null);
    const location = useLocation();
    const history = useHistory();

  useEffect(() => {
    
    const search = location.search;
    const id = location.state.detail;

    
    const getAllMovies = () => {
      return axios.get('http://localhost:8089/main/api/watchlist/'+id)
      }

    async function fetchData() {
        try {
          const response = await getAllMovies()
          setMovies(response.data.movies)
        } catch (e) {
          console.error(e)
        }
    }
    fetchData()
  }, [])

  return (
  
    <> <h1 className='pageTitle'>Movies</h1>
    <div className="trending">
         {movies &&
           movies.map((c) => (
             <ContentWatchlist
               key={c.id}
               id={c.id}
               poster={c.image}
               title={c.title || c.name}
               date={c.year|| c.release_date}
               media_type="movie"
               vote_average={c.grade}
             />
           ))}
       </div>
   </>
  );
}
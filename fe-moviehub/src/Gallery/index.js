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
import { getAllMovies } from '../Api/Movie/movie';
import axios from 'axios';
import { Table } from 'antd';
import Column from 'antd/lib/table/Column';
import SingleContent from "../Components/SingleContent/SingleContent";
import { removeSession } from '../utilities/localStorage'
import { useUserContext } from '../AppContext'


import './gallery.css'

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

const cards = [1, 2, 3, 4, 5, 6, 7, 8, 9];

const theme = createTheme();

export default function Gallery() {
    const [movies, setMovies] = useState(null);
    const [genres, setGenres] = useState([]);
    const [selectedGenres, setSelectedGenres] = useState([]);
    const [page, setPage] = useState(1);
    const [content, setContent] = useState([]);
    const [numOfPages, setNumOfPages] = useState();

    const getAllMovies = () => {
        return axios.get("http://localhost:8089/movie/api/movie/");
        }

  useEffect(() => {
    window.scroll(0, 0);
    async function fetchData() {
        try {
          const response = await getAllMovies()
          console.log(response.data)
          setMovies(response.data)
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
            <SingleContent
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
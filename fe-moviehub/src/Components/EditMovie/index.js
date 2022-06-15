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

export default function EditMovie() {
    const [movies, setMovies] = useState(null);

  useEffect(() => {
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
  <> <h1 style={{'color':'white'}}>Naslov</h1>
  </>
  );
}
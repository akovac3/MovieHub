import * as React from 'react';
import { useState, useEffect } from 'react';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';
import SingleContent from "../Components/SingleContent/SingleContent";
import './gallery.css'
import { getUser, setUser } from '../utilities/localStorage';
import { userRole } from '../utilities/common';

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

const theme = createTheme();

export default function Gallery() {
    const [movies, setMovies] = useState(null);    
    const user = getUser()
    const [watchlistId, setWatchlistId] = useState('');
    const getAllMovies = () => {
        return axios.get("http://localhost:8089/movie/api/movie/");
        }

     const getWatchlistId = () => {
            return axios.get("http://localhost:8089/user/api/user/"+user.id+ "/watchlist");
            }


  useEffect(() => {
    window.scroll(0, 0);
    async function fetchData() {
        console.log('movies')
        try {
          const response = await getAllMovies()
          const watchlistResponse = await getWatchlistId()

          console.log(response.data)
          setMovies(response.data)
          console.log(watchlistResponse)
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
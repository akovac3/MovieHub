import Multiselect from "multiselect-react-dropdown";
import React, { useState, useEffect } from "react";
import { getAllGenres } from '../../Api/Movie/movie'
import { getActors } from '../../Api/Movie/movie'
import { MenuProps, useStyles, options } from "../utils";
import Checkbox from "@material-ui/core/Checkbox";
import InputLabel from "@material-ui/core/InputLabel";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import { message } from "antd";
import './EditMovie.css'
import { postMovie } from "../../Api/Movie/movie";
import { orange } from '@material-ui/core/colors';
import { styled } from '@mui/material/styles';
import { Button } from "@material-ui/core";
import { Typography } from '@material-ui/core';
import { useLocation } from "react-router-dom";
import axios from "axios";
import { putMovie } from "../../Api/Movie/movie";



const ColorButton = styled(Button)(({ theme }) => ({
    color: theme.palette.getContrastText(orange[500]),
    backgroundColor: orange[500],
    '&:hover': {
      backgroundColor: orange[800]
    },
  }));


export default function EditMovie() {

   const [title, setTitle] = useState('');
   const [description, setDescription] = useState('');
   const [year, setYear] = useState('');
   const [grade, setGrade] = useState('');
   const [image, setImage] = useState('');
    const [submitted, setSubmitted] = useState(false);
    const [valid, setValid] = useState(false);
    const [actors, setActors] = useState(["Christian Bale", "Brad Pitt", "Morgan Freeman"]);
    const [genres, setGenres] = useState([]);
    const [selectedGenres, setSelectedGenres] = useState([]);
   const classes = useStyles();
  const [selected, setSelected] = useState([]);
  const location = useLocation();
  const [id, setId] = useState('');
  const [movie, setMovie] = useState(null);

  const [loading, setLoading] = React.useState(false)

  const isAllSelected =
    actors.length > 0 && selected.length === actors.length;
    const isAllSelectedGenres =
    genres.length > 0 && selectedGenres.length === genres.length;

  const handleChange = (event) => {
    const value = event.target.value;
    if (value[value.length - 1] === "all") {
      setSelected(selected.length === actors.length ? [] : actors.map(actor => actor.id));
      return;
    }
    setSelected(value);
  };

  const handleChangeGenres = (event)  => {
    const value = event.target.value;
    if (value[value.length - 1] === "all") {
      setSelectedGenres(selectedGenres.length === genres.length ? [] : genres.map(genre => genre.id));
      return;
    }
    setSelectedGenres(value);
  };

  function handleSubimiting() {
    setTitle('')
    setYear('')
    setDescription('')
    setSelected([])
    setSelectedGenres([])
    setImage('')
    setGrade('')
    setSubmitted(false)
  }

  
  const onFinish = async (values) => {
    try {
      setLoading(true)

      const response = await axios.put('http://localhost:8089/movie/api/movie/'+id, values)
      message.success('Successfully saved movie')
      setLoading(false)
      handleSubimiting()
    } catch (error) {
      console.log(error)

      setLoading(false)
      message.warning(error.response.data.message)
    }
  }

    const handleSubmit = (event) => {
        event.preventDefault();
        setSubmitted(true)
        if(title && year && description ) {
            setValid(true);
            const values = {
              title: title,
              grade: grade,
              description: description,
              year: year,
              image: image,
              actors: selected,
              genres: selectedGenres
            }
            onFinish(values)
            
        }
    }

    
  const getData = async () => {
    try {
      return await getAllGenres()
    } catch (error) {
      console.log(error)
      console.warning(error.response.data.message)
    }
  }

  
  const getActor = async () => {
    try {
      return await getActors()
    } catch (error) {
      console.log(error)
      console.warning(error.response.data.message)
    }
  }

  useEffect(() => {
    const id = location.state.detail;
    setId(id);
        
    const getMovie = () => {
      return axios.get('http://localhost:8089/movie/api/movie/'+id)
      }

    async function fetchData() {
        try {
          const response = await getData()
          console.log(response)
          setGenres(response)
          const glumci = await getActor()
          setActors(glumci)
          const film = await getMovie()
          setMovie(film.data)
          setTitle(film.data.title);
          setDescription(film.data.description);
          setYear(film.data.year);
          setImage(film.data.image);
          setGrade(film.data.grade);
          setSelected(film.data.actors.map(actor => actor.id))
          setSelectedGenres(film.data.genres.map(genre => genre.id))
          console.log(movie)
          console.log(year)
        } catch (e) {
          console.error(e)
        }
    }
    fetchData()
  }, [])


    return (
      <div className='form-container' >

      <Typography
          variant = "h3"
          color='secondary'
          component='h2'
          gutterBottom
          > Edit movie</Typography>

        <form  onSubmit={handleSubmit} style={{'padding':'10px'}}
>
            {/* Uncomment the next line to show the success message */}
            <input
                onChange={(e) => {setTitle(e.target.value)}}
                value={title}
                id="movie-title"
                class="form-field"
                type="text"
                placeholder="Title"
                name="title"
            />
            {/* Uncomment the next line to show the error message */}
            {submitted && !title ? <span id="title-error">Please enter a movie title</span> : null}
            <input
                onChange={(e) => {setYear(e.target.value)}}
                value={year}
                id="movie-year"
                class="form-field"
                type="text"
                placeholder="Year"
                name="year"
            />
            {/* Uncomment the next line to show the error message */}
            {submitted && !year ? <span id="year-error">Please enter a movie year</span> : null}

            <input
                onChange={(e) => {setGrade(e.target.value)}}
                value={grade}
                id="movie-grade"
                class="form-field"
                type="text"
                placeholder="Grade"
                name="grade"
            />
            {/* Uncomment the next line to show the error message */}
            {submitted && !grade ? <span id="grade-error">Please enter a movie grade</span> : null}

            <input
                onChange={(e) => {setDescription(e.target.value)}}
                value={description}
                id="movie-description"
                class="form-field"
                type="text"
                placeholder="Description"
                name="description"
            />
              {submitted && !description ? <span id="year-error">Please enter a movie description</span> : null}

              <input
                onChange={(e) => {setImage(e.target.value)}}
                value={image}
                id="movie-image"
                class="form-field"
                type="text"
                placeholder="Image"
                name="image"
            />

            <FormControl className={classes.formControl}>
      <InputLabel id="mutiple-select-label">Select actors</InputLabel>
      <Select
        labelId="mutiple-select-label"
        multiple
        value={selected}
        onChange={handleChange}
        renderValue={(selected) => selected.join(", ")}
        MenuProps={MenuProps}
      >
        <MenuItem
          value="all"
          classes={{
            root: isAllSelected ? classes.selectedAll : ""
          }}
        >
          <ListItemIcon>
            <Checkbox
              classes={{ indeterminate: classes.indeterminateColor }}
              checked={isAllSelected}
              indeterminate={
                selected.length > 0 && selected.length < actors.length
              }
            />
          </ListItemIcon>
          <ListItemText
            classes={{ primary: classes.selectAllText }}
            primary="Select All"
          />
        </MenuItem>
        {actors.map((option) => (
          <MenuItem key={option.id} value={option.id}>
            <ListItemIcon>
              <Checkbox checked={selected.indexOf(option.id) > -1} />
            </ListItemIcon>
            <ListItemText primary={option.firstName + ' ' + option.lastName} />
          </MenuItem>
        ))}
      </Select>
    </FormControl>

    <FormControl className={classes.formControl}>
      <InputLabel id="mutiple-select-genres">Select genres</InputLabel>
      <Select
        labelId="mutiple-select-genres"
        multiple
        value={selectedGenres}
        onChange={handleChangeGenres}
        renderValue={(selectedGenres) => selectedGenres.join(", ")}
        MenuProps={MenuProps}
      >
        <MenuItem
          value="all"
          classes={{
            root: isAllSelectedGenres ? classes.selectedAll : ""
          }}
        >
          <ListItemIcon>
            <Checkbox
              classes={{ indeterminate: classes.indeterminateColor }}
              checked={isAllSelectedGenres}
              indeterminate={
                selectedGenres.length > 0 && selectedGenres.length < genres.length
              }
            />
          </ListItemIcon>
          <ListItemText
            classes={{ primary: classes.selectAllText }}
            primary="Select All"
          />
        </MenuItem>
        {genres.map((option) => (
          <MenuItem key={option.id} value={option.id}>
            <ListItemIcon>
              <Checkbox checked={selectedGenres.indexOf(option.id) > -1} />
            </ListItemIcon>
            <ListItemText primary={option.name} />
          </MenuItem>
        ))}
      </Select>
    </FormControl>
            <ColorButton type="submit" variant='contained'>
            Submit
            </ColorButton>
        </form>
        </div>
    );
}
import Multiselect from "multiselect-react-dropdown";
import React, { useState, useEffect } from "react";
import { getAllGenres } from '../Api/Movie/movie'
import { getActors } from '../Api/Movie/movie'

export default function CreateMovie() {

    const [values, setValues] = useState({
        title: "",
        year: "",
        description: "",
        actors: ""
    });
    const [submitted, setSubmitted] = useState(false);
    const [valid, setValid] = useState(false);
    const [actors, setActors] = useState(["Christian Bale", "Brad Pitt", "Morgan Freeman"]);
    const [genre, setGenre] = useState(["Action", "Comedy", "Thriller"]);
    const [genres, setGenres] = useState([]);
    const [selectedActors, setSelectedActors] = useState([]);
    const [selectedGenres, setSelectedGenres] = useState([]);

    const handleMovieTitleInputChange = (event) => {
        setValues({...values, title: event.target.value})
    }

    const handleMovieYearInputChange = (event) => {
        setValues({...values, year: event.target.value})
    }

    const handleMovieDescriptionInputChange = (event) => {
        setValues({...values, description: event.target.value})
    }

    const handleMovieActorsInputChange = (event) => {
        setValues({...values, actors: event.target.value})
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        if(values.title && values.year && values.description && values.actors) {
            setValid(true);
        }
        setSubmitted(true);
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
    async function fetchData() {
        try {
          const response = await getData()
          console.log(response)
          setGenres(response)
          const glumci = await getActor()
          setActors(glumci)
        } catch (e) {
          console.error(e)
        }
    }
    fetchData()
  }, [])


    return (
        <div class="form-container">
        <h1>Create new Movie</h1>
        <form class="register-form" onSubmit={handleSubmit}>
            {/* Uncomment the next line to show the success message */}
            {submitted && valid ? <div class="success-message">Success! Thank you for adding new movie</div> : null}
            <input
                onChange={handleMovieTitleInputChange}
                value={values.title}
                id="movie-title"
                class="form-field"
                type="text"
                placeholder="Title"
                name="title"
            />
            {/* Uncomment the next line to show the error message */}
            {submitted && !values.title ? <span id="title-error">Please enter a movie title</span> : null}
            <input
                onChange={handleMovieYearInputChange}
                value={values.year}
                id="movie-year"
                class="form-field"
                type="text"
                placeholder="Year"
                name="year"
            />
            {/* Uncomment the next line to show the error message */}
            {submitted && !values.year ? <span id="year-error">Please enter a movie year</span> : null}
            <input
                onChange={handleMovieDescriptionInputChange}
                value={values.description}
                id="movie-description"
                class="form-field"
                type="text"
                placeholder="Description"
                name="description"
            />
            {/* Uncomment the next line to show the error message */}
            {submitted && !values.description ? <span id="description-error">Please enter a movie description</span> : null}
            <input
                onChange={handleMovieActorsInputChange}
                value={values.actors}
                id="movie-actors"
                class="form-field"
                type="text"
                placeholder="Actors"
                name="actors"
            />
            {/* Uncomment the next line to show the error message */}
            {submitted && !values.actors ? <span id="actors-error">Please enter a movie actors</span> : null}
            <div>
            <p>Select actors</p>
            <Multiselect
                isObject={false}
                onRemove={function noRefCheck() {}}
                onSelect={function noRefCheck() {}}
                options={actors.map((actor) => (actor.firstName + " " + actor.lastName
                    ))}
            />
            </div>
            <div>
            <p>Select genres</p>
            <Multiselect
                isObject={false}
                onRemove={function noRefCheck() {}}
                onSelect={function noRefCheck() {}}
                options= {genres.map((genre) => (genre.name
                  ))}
            />
            </div>
            <button class="form-field" type="submit">
            Add Movie
            </button>
        </form>
        </div>
    );
}
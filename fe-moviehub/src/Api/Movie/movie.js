import {
    basicGet,
    basicPost,
    basicGetGallery,
    basicPostGallery,
  } from '../ApiClient'
  
  const movieApi = '/movie/api/movie/'
  const actorsApi = '/movie/api/actor/'
  const genresApi = '/movie/api/genre/'
  
  export const getAllMovies = async () => {
    return await basicGet(movieApi)
  }
  
  export const addMovie = async (data) => {
    return await basicPost(movieApi, data)
  }
  
  export const getMovie = async (id) => {
    return await basicGet(movieApi+id)
  }
  
  export const getActors = async () => {
    return await basicGet(actorsApi)
  }

  export const postActor = async (data) => {
    return await basicPost(actorsApi, data)
  }

  export const getAllGenres = async () => {
    return await basicGet(genresApi)
  }

  
  export const postGenre = async (data) => {
    return await basicPost(genresApi, data)
  }
  

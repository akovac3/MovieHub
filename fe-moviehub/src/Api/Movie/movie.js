import {
    basicGet,
    basicPost,
    basicGetGallery,
    basicPostGallery,
  } from '../ApiClient'
  
  const movieApi = '/movie/api/movie/'
  
  export const getAllMovies = async () => {
    return await basicGet(movieApi)
  }
  
  export const addMovie = async (data) => {
    return await basicPost(movieApi, data)
  }
  
  export const getMovie = async (id) => {
    return await basicGet(movieApi+id)
  }
  
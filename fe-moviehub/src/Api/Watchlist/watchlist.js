import {
    basicGet,
    basicPost,
  } from '../ApiClient'
  
  const watchlistApi = '/main/api/watchlist/'
  
  export const getWatchlist = async (id) => {
    return await basicGet(watchlistApi+id)
  }

  export const postToWatchlist = async (id, movieId) => {
    return await basicPost(watchlistApi+id+"/movie/"+movieId)
  }

  export const getAllMoviesFromWatchlist = async (id) => {
    return await basicGet(watchlistApi+id+"/movies")
  }

  
  

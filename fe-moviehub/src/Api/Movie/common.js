import { basicGet, basicPut, basicPost } from '../ApiClient'

const movieUrl = '/movie'

export const get = async (url) => {
  return await basicGet(movieUrl + url)
}

export const post = async (url, data) => {
  return await basicPost(movieUrl + url, data)
}

export const put = async (url, data) => {
  return await basicPut(movieUrl + url, data)
}

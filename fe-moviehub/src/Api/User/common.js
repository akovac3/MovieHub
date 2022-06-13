import { basicGet, basicPut, basicPost } from '../ApiClient'

const userUrl = ''

export const get = async (url) => {
  return await basicGet(userUrl + url)
}

export const post = async (url, data) => {
  return await basicPost(userUrl + url, data)
}

export const put = async (url, data) => {
  return await basicPut(userUrl + url, data)
}

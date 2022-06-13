import { basicGet, basicPut } from '../ApiClient'

const userUrl = '/user/'

export const getAllUsers = async () => {
  return await basicGet(userUrl + '/users/all')
}

export const updateUser = async (body) => {
  return await basicPut(userUrl + '/users', body)
}

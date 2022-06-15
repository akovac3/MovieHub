import React from 'react'
import { Route, Redirect, Navigate } from 'react-router-dom'
import { homeUrl, loginUrl } from '../utilities/appUrls'
import { moviesUrl } from '../utilities/appUrls'
import { validToken } from '../utilities/common'

// handle the private routes
const PrivateRoute = ({ component: Component, path: Path, ...rest }) => {
  return (
    <Route
      path={Path}
      render={() =>
        validToken() ? <Component {...rest} /> : <Redirect push to={homeUrl} />
      }
    />
  )
}

export default PrivateRoute

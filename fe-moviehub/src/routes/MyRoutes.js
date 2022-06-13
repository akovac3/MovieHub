import React from 'react'
import { Route, Switch } from 'react-router-dom'
import GuestRoute from './GuestRoute'
import Login from '../Components/Login'
import Register from '../Components/Register'


import Users from '../Users'
import LandingPage from '../LandingPage'
import PageNotFound from '../PageNotFound'
import PrivateRoute from './PrivateRoute'
import UserPage from '../UserPage'
import Gallery from'../Gallery'
import CreateMovie from '../CreateMovie'

const MyRoutes = () => {
    return (
      <Switch>
        <Route exact path='/' component={Gallery} />
        <GuestRoute path='/login' component={Login} />
        <GuestRoute path='/register' component={Register} />
        <PrivateRoute path='/userpage' component={UserPage} />
        <PrivateRoute path='/users' component={Users} />
        <Route path='/create-movie' component={CreateMovie} />
        <Route component={PageNotFound} />
      </Switch>
    )
  }
  
  export default MyRoutes
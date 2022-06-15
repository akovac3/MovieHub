import React, { createFactory } from 'react'
import { Route, Switch } from 'react-router-dom'
import GuestRoute from './GuestRoute'
import Login from '../Components/Login'
import Register from '../Components/Register'


import Users from '../Users'
import LandingPage from '../Components/LandingPage/LandingPageComponent'
import PageNotFound from '../PageNotFound'
import PrivateRoute from './PrivateRoute'
import UserPage from '../UserPage'
import Gallery from'../Gallery'
import CreateMovie from '../CreateMovie'
import Watchlists from '../Components/Watchlists'
import CreateActor from '../Components/CreateActor'
import EditMovie from '../Components/EditMovie'
import Genres from '../Components/Genres'

const MyRoutes = () => {
    return (
      <Switch>
        <Route exact path='/' component={LandingPage} />
        <GuestRoute path='/login' component={Login} />
        <GuestRoute path='/register' component={Register} />
        <PrivateRoute path='/userpage' component={UserPage} />
        <PrivateRoute path='/users' component={Users} />
        <PrivateRoute path='/movies' component={Gallery} />
        <PrivateRoute path='/actors' component={CreateActor} />
        <PrivateRoute path='/genres' component={Genres} />
        <PrivateRoute path='/edit-movie' component={EditMovie} />
        <Route path='/create-movie' component={CreateMovie} />
        <Route path='/my-account' component={UserPage} />
        <Route path='/watchlist' component={Watchlists} />
        <Route component={PageNotFound} />
      </Switch>
    )
  }
  
  export default MyRoutes
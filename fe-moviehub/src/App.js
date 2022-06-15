import "./App.css";
import Footer from "./Components/Sections/Footer";
import Navbar from './Components/shared/Header'
import Header from "./Components/Header";
import SimpleBottomNavigation from "./Components/MainNav"

import React from 'react'
import { BrowserRouter as Router, withRouter } from 'react-router-dom'

//import Header from 'shared/Header'
import MyRoutes from './routes/MyRoutes'

function App() {
  return (
    <div className='app-container'>
      <Router>
        <Header />
        <div className='main-container'>
          <MyRoutes />
        </div>
        <SimpleBottomNavigation />
      </Router>
    </div>
  )
}

export default App;

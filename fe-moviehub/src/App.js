import "./App.css";
import Footer from "./Components/Sections/Footer";
import Navbar from './Components/shared/Header'

import React from 'react'
import { BrowserRouter as Router } from 'react-router-dom'

//import Header from 'shared/Header'
import MyRoutes from './routes/MyRoutes'

function App() {
  return (
    <div className='app-container'>
      <Router>
        <Navbar />
        <div className='main-container'>
          <MyRoutes />
        </div>
        <Footer />
      </Router>
    </div>
  )
}

export default App

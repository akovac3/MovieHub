import React, { Component } from "react";
import ParticlesBg from "particles-bg";
import Fade from "react-reveal";



class Header extends Component {
  render() {
    if (!this.props.data) return null;

    const project = this.props.data.project;
    const github = this.props.data.github;
    const name = this.props.data.name;
    const description = this.props.data.description;

    return (
      <header id="home">
        {/* <ParticlesBg type="circle" bg={true} /> */}

        <nav id="nav-wrap">
          <a className="mobile-btn" href="#nav-wrap" title="Show navigation">
            Show navigation
          </a>
          <a className="mobile-btn" href="#home" title="Hide navigation">
            Hide navigation
          </a>

          <ul id="nav" className="nav">
            <li className="current">
              <a className="smoothscroll" href="#home">
                Home
              </a>
            </li>

            <li>
              <a className="smoothscroll" href="#about">
                About Us
              </a>
            </li>

            <li>
              <a className="smoothscroll" href="#resume">
                Watchlists
              </a>
            </li>

            <li>
              <a className="smoothscroll" href="#portfolio">
                New Movies
              </a>
            </li>
          </ul>
        </nav>

        <div className="row banner">
          <div className="banner-text">
            <Fade bottom>
              <h1 className="responsive-headline">MovieHub</h1>
            </Fade>
            <Fade bottom duration={1200}>
              <h3>Website with all newest movies and tv shows</h3>
            </Fade>
            <hr />
            <Fade bottom duration={2000}>
              <ul className="social">
                <a href={project} className="button btn">
                  <i></i>New Movies
                </a>
                <a href={github} className="button btn">
                  <i></i>Your Watchlists
                </a>
              </ul>
            </Fade>
          </div>
        </div>

        <p className="scrolldown">
          <a className="smoothscroll" href="#about">
            <i className="icon-down-circle"></i>
          </a>
        </p>
      </header>
    );
  }
}

export default Header;

import React, { Component } from "react";
import Fade from "react-reveal";
import { GithubFilled } from '@ant-design/icons';
import { Col } from 'antd';
import { Button } from 'antd';

class About extends Component {
  render() {
    if (!this.props.data) return null;

    return (
      <section id="about">
        <Fade duration={1000}>
          <div className="row">
            <div className="three columns">
            </div>
            <div className="nine columns main-col">
              <h2>About Us</h2>

              <p>Moviehub WebApplication</p>
              <div className="row">
                <div className="columns contact-details">
                  <h2>Created by:</h2>
                  <p className="address">
                    <span>Adna Fejzić</span>
                    <br />
                    <span>Allen Kovačević</span>
                    <br />
                    <span>Rijad Handžić</span>
                    <br />
                  </p>
                  <p>
                    <span>© MovieHub</span>
                    <br />
                    <span>NWT 2022</span>
                    <br />
                  </p>
                </div>
              </div>
            </div>
          </div>
        </Fade>
      </section>
    );
  }
}

export default About;

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

              <p>Moviehub aplikacija</p>
              <div className="row">
                <div className="columns contact-details">
                  <h2>Contact Details</h2>
                  <p className="address">
                    <span>Adna Fejzić</span>
                    <br />
                    <span>Allen Kovačević</span>
                    <br />
                    <span>Rijad Handžić</span>
                    <br />
                  </p>
                </div>
              </div>
            </div>
          </div>

          <Col span={8}>
                © QuizHub
                <Button type="text" href={"https://github.com/rhandzic1/NWT-projekat"}>
                    <GithubFilled className="footer-icon"/>
                </Button>
            </Col>

            <Col span={8}>
                NWT 2022
            </Col>
        </Fade>
      </section>
    );
  }
}

export default About;

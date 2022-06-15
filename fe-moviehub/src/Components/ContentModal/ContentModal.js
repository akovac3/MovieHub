import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import Fade from "@material-ui/core/Fade";
import axios from "axios";
import {
  img_500,
  unavailable,
  unavailableLandscape,
} from "../../config/config";
import "./ContentModal.css";
import { Button } from "@material-ui/core";
import YouTubeIcon from "@material-ui/icons/YouTube";
import Carousel from "../Carousel/Carousel";
import { Chip } from "@material-ui/core";
import { getUser } from "../../utilities/localStorage";


const useStyles = makeStyles((theme) => ({
  modal: {
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
  paper: {
    width: "90%",
    height: "80%",
    backgroundColor: "#39445a",
    border: "1px solid #282c34",
    borderRadius: 10,
    color: "white",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(1, 1, 3),
  },
}));

export default function ContentModal({ children, id }) {
  const classes = useStyles();
  const [open, setOpen] = useState(false);
  const [content, setContent] = useState();

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const fetchData = async () => {
    const { data } = await axios.get("http://localhost:8089/movie/api/movie/"+id);

    setContent(data);
    // console.log(data);
  };


  useEffect(() => {
    fetchData();
    // eslint-disable-next-line
  }, []);

  return (
    <>
      <div
        className="media"
        style={{ cursor: "pointer" }}
        color="inherit"
        onClick={handleOpen}
      >
        {children}
      </div>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        className={classes.modal}
        open={open}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
          {content && (
            <div className={classes.paper}>
              <div className="ContentModal">
                <img
                   style={{'width':'450px', 'height':'600px', 'alignItems':'center'}}
                   src={content.image ? `${content.image}` : unavailable}
                  alt={content.name || content.title}
                  className="ContentModal__portrait"
                />
                <img
                  src={
                    content.image
                      ? `${content.image}`
                      : unavailableLandscape
                  }
                  alt={content.name || content.title}
                  className="ContentModal__landscape"
                />
                <div className="ContentModal__about">
                  <span className="ContentModal__title">
                    {content.name || content.title} (
                    {(
                      content.year ||
                      content.release_date ||
                      "-----"
                    )}
                    )
                  </span>
                  <span className="ContentModal__description">
                    {content.description}
                  </span>
                  
                  <div>
                  <p style={{'color':'white', 'fontFamily':'Montserrat, sans-serif'}}>Actors</p>
                    <Carousel credits={content.actors} />
                  </div>

                  <div style={{ padding: "20px 0"}}>
                    <p style={{'marginBottom':'20px', 'color':'white', 'fontFamily':'Montserrat, sans-serif'}}>Genres</p>

                  {content.genres.map((genre) => (
        <Chip
          style={{ margin: 2 }}
          label={genre.name}
          key={genre.id}
        />
      ))}
                      </div>
                 
                </div>
              </div>
            </div>
          )}
        </Fade>
      </Modal>
    </>
  );
}
import { Badge } from "@material-ui/core";
import { img_300, unavailable } from "../../config/config";
import "./SingleContent.css";
import ContentModal from "../ContentModal/ContentModal";
import { Button } from "@material-ui/core";
import { getUser } from "../../utilities/localStorage";
import { userRole } from "../../utilities/common";
import { useHistory } from "react-router-dom";

const SingleContent = ({
  id,
  poster,
  title,
  date,
  media_type,
  vote_average,
}) => {
  const user = getUser();
  const role = userRole();
  const history = useHistory()


  function handleEdit() {
    history.push('/edit-movie')
  }

  function handleAddToWatchlist(){

  }
  return (
    <ContentModal media_type={media_type} id={id}>
<Badge
        badgeContent={vote_average}
        color={vote_average > 6 ? "primary" : "secondary"}
      />
      <img
        className="poster"
        style={{'width':'250px', 'height':'450px'}}
        src={poster ? `${poster}` : unavailable}
        //src={poster ? `${img_300}${poster}` : unavailable}
        alt={title}
      />
      <b className="title">{title}</b>
      <span className="subTitle">
        {media_type === "tv" ? "TV Series" : "Movie"}
        <span className="subTitle">{date}</span>
      </span>
      {(user && <span className="subTitle">
        {(role==="ROLE_ADMIN" && <Button variant="outlined" color="secondary" onClick={handleEdit}>Edit</Button>)}
        {(role==="ROLE_USER" && <Button onClick={handleAddToWatchlist}>Add to watchlist</Button>)}
      </span>)}
      </ContentModal>
  );
};

export default SingleContent;
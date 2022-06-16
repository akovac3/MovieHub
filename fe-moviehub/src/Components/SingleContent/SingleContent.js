import { Badge } from "@material-ui/core";
import { img_300, unavailable } from "../../config/config";
import "./SingleContent.css";
import ContentModal from "../ContentModal/ContentModal";
import { Button } from "@material-ui/core";
import { getUser } from "../../utilities/localStorage";
import { userRole } from "../../utilities/common";
import { useHistory } from "react-router-dom";
import { styled } from '@mui/material/styles';
import { orange } from '@mui/material/colors';
import { postToWatchlist } from "../../Api/Watchlist/watchlist";
import { message } from 'antd'


const ColorButton = styled(Button)(({ theme }) => ({
  color: theme.palette.getContrastText(orange[500]),
  backgroundColor: orange[500],
  '&:hover': {
    backgroundColor: orange[800]
  },
}));


const SingleContent = ({
  id,
  poster,
  title,
  date,
  media_type,
  vote_average,
  watchlistId,
}) => {
  const user = getUser();
  const role = userRole();
  const history = useHistory()


  function handleEdit() {
    history.push('/edit-movie')
  }

  async function handleAddToWatchlist(movieId){
    console.log(user)
    try{
      await postToWatchlist(watchlistId, movieId)
      message.success('Successfully added to watchlist')
    } catch(error){
      console.error(error)
    }
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
        {(role==="ROLE_ADMIN" && <ColorButton variant="outlined" color="secondary" onClick={() =>handleEdit}>Edit</ColorButton>)}
        {(role==="ROLE_USER" && <ColorButton onClick={() => {handleAddToWatchlist(id)}}>Add to watchlist</ColorButton>)}
      </span>)}
      </ContentModal>
      
  );
};

export default SingleContent;
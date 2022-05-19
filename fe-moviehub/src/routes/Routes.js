import react from "react";
import { Routes as RouteCollection, Route } from "react-router-dom";
import Header from "../Components/Header";
import LandingPage from "../Components/LandingPage/LandingPageComponent";
import Login from "../Components/Login/LoginComponent";

const Routes = () => {
    return (
        <RouteCollection>
            <Route exact path="/" component={LandingPage}/>
            <Route path="/login" component={Login}></Route>

        </RouteCollection>
    )
}

export default Routes;
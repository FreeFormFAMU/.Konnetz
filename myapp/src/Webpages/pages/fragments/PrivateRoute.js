import React, {useContext} from 'react';
import  {Route, Redirect} from "react-router-dom";
import {AuthContext} from "../context/AuthContext";

function PrivateRoute({component: Component, ...rest}) {
    const { currentUser } = useContext(AuthContext);
    if(currentUser!= null && currentUser.length > 0)
        localStorage.setItem("user", JSON.stringify(currentUser));
    return (
        <Route {...rest} render={ props =>  currentUser ? <Component {...props} /> : <Redirect to="/login" /> } />
    );
}

export default PrivateRoute;
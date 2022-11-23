import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Route, Switch} from "react-router-dom";
import HomeView from "./views/HomeView";
import AddCategory from "./views/CategoryView/AddCategory";
import CategoryView from "./views/CategoryView";
import ErrorView from "./views/ErrorView";
// @ts-ignore
import SockJsClient from 'react-stomp';


function App() {
  return (
      // pass stompClient to the components that need it
      <div>
          <Switch>
            <Route exact path="/" component={HomeView} />
            <Route path={"/categories/insert"} component={AddCategory}/>
            <Route path={"/categories/:id"} component={CategoryView}/>
            <Route path={"/error"} component={ErrorView}/>
          </Switch>
      </div>
  );
}

export default App;

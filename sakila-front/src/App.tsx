import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Route, Switch} from "react-router-dom";
import HomeView from "./views/HomeView";
import AddCategory from "./views/CategoryView/AddCategory";
import CategoryView from "./views/CategoryView";
import ErrorView from "./views/ErrorView";
// @ts-ignore
import SockJS from 'sockjs-client';
// @ts-ignore
import {Client, Frame, Message, over} from 'stompjs';


function App() {
  return (
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

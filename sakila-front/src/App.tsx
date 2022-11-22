import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import HomeView from "./views/HomeView";
import {Switch, Route} from "react-router-dom";
import ErrorView from "./views/ErrorView";
import CategoryView from "./views/CategoryView";
import AddCategory from "./views/CategoryView/AddCategory";

const App: React.FC = () => {
  return (
      <div>
        <Switch>
          <Route exact path="/" component={HomeView}/>
          <Route path={"/categories/insert"} component={AddCategory}/>
          <Route path={"/categories/:id"} component={CategoryView}/>
          <Route path={"/error"} component={ErrorView}/>
        </Switch>
      </div>
  )
}

export default App;

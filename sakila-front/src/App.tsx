import React from 'react';
import './App.css';
import HomeView from "./views/HomeView";
// @ts-ignore
import {Switch, Route} from "react-router-dom";

const App: React.FC = () => {
  return (
      <div>
        <Switch>
          <Route exact path="/" component={HomeView}/>
        </Switch>
      </div>
  )
}

export default App;

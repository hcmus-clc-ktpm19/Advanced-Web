import React, {createContext} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Route, Switch} from "react-router-dom";
import HomeView from "./views/HomeView";
import AddCategory from "./views/CategoryView/AddCategory";
import CategoryView from "./views/CategoryView";
import ErrorView from "./views/ErrorView";
import {WebSocketService} from "./services/WebSocketService";
// @ts-ignore
import {Client} from 'stompjs';


async function connect() {
  return await WebSocketService.connect();
}
let stompClient: Client = null;

(async () => {
  stompClient = await connect();
  console.log('controller', stompClient);
})()

export const Context = createContext(stompClient);

function App() {
  return (
      // pass stompClient to the components that need it
      <div>
        <Context.Provider value={stompClient}>
          <Switch>
            <Route exact path="/" component={HomeView} />
            <Route path={"/categories/insert"} component={AddCategory}/>
            <Route path={"/categories/:id"} component={CategoryView}/>
            <Route path={"/error"} component={ErrorView}/>
          </Switch>
        </Context.Provider>
      </div>
  );
}

export default App;

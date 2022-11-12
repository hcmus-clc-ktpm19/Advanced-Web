import HomeView from './views/HomeView/index';
import { Route, Switch } from 'react-router-dom';
import ErrorView from './views/ErrorView';
import LoginController from './controllers/LoginController';

function App() {
  return (
    <div>
      <Switch>
        <Route exact path="/" component={HomeView} />
        <Route path="/login" component={LoginController} />
        <Route path="/error" component={ErrorView} />
      </Switch>
    </div>
  );
}

export default App;

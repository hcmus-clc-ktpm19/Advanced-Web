import HomeView from './views/HomeView/index';
import { Route, Switch } from 'react-router-dom';
import ErrorView from './views/ErrorView';
import LoginController from './controllers/LoginController';
import RouteGuard from './components/RouteGuard';

function App() {
  return (
    <div>
      <Switch>
        <RouteGuard exact path="/" component={HomeView} />
        <Route path="/login" component={LoginController} />
        <Route path="/error" component={ErrorView} />
      </Switch>
    </div>
  );
}

export default App;

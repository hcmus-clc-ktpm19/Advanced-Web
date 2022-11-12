import LoginView from './views/LoginView';
import HomeView from './views';
import { Route, Switch } from 'react-router-dom';
import ErrorView from './views/ErrorView';

function App() {
  return (
    <div>
      <Switch>
        <Route exact path="/" component={HomeView} />
        <Route path="/login" component={LoginView} />
        <Route path="/error" component={ErrorView} />
      </Switch>
    </div>
  );
}

export default App;

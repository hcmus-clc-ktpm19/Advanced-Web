import React from 'react';
import { Redirect, Route } from 'react-router-dom';

interface Props {
  component: any;
  exact?: boolean;
  path: string;
}

const RouteGuard: React.FC<Props> = (props: Props) => {
  const { component, exact, path } = props;
  const Component = component;

  const isAuthenticated = (): boolean => {
    return !!localStorage.getItem('token');
  };

  return (
    <Route
      exact={exact}
      path={path}
      render={(props) =>
        isAuthenticated() ? <Component {...props} /> : <Redirect to={{ pathname: '/login' }} />
      }
    />
  );
};

export default RouteGuard;

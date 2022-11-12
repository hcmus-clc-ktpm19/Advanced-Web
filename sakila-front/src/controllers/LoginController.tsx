import { useHistory } from 'react-router-dom';
import { UserDto } from '../models/model';
import LoginView from '../views/LoginView';
import { AuthService as authService } from '../services/AuthService';
import { useState } from 'react';

const LoginController = () => {
  const history = useHistory();
  const [errorMessage, setErrorMessage] = useState<string>('');

  const handleOnSubmit = async (user: UserDto) => {
    const { username, password } = user;

    try {
      await authService.login(username, password);
      history.push('/');
    } catch (e) {
      setErrorMessage('Login Failed');
    }
  };

  return <LoginView onSubmit={handleOnSubmit} errorMessage={errorMessage} />;
};

export default LoginController;

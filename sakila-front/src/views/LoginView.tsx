import React, { FormEvent, FormEventHandler, useRef, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button, Form } from 'react-bootstrap';
import axios, { AxiosResponse } from 'axios';
import { useHistory } from 'react-router-dom';

const LoginView = (): JSX.Element => {
  const onSubmit: FormEventHandler<HTMLFormElement> = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log('submit');
    console.log(usernameRef.current?.value, passwordRef.current?.value);

    await onLogin();
  };

  const onLogin = async () => {
    try {
      const res: AxiosResponse<string> = await axios.post<string>(
        'http://localhost:8080/api/v1/auth/login',
        {
          username: usernameRef.current?.value,
          password: passwordRef.current?.value
        }
      );

      console.log(res.data);
      history.push('/');
    } catch (e) {
      setErrorMessage('Login Failed');
    }
  };

  const usernameRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);
  const history = useHistory();
  const [errorMessage, setErrorMessage] = useState<string>('');

  return (
    <div className="vh-100">
      <div className="container py-5 h-100">
        <div className="row d-flex justify-content-center align-items-center h-100">
          <div className="col-5">
            <div className="card shadow-lg" style={{ borderRadius: '1rem' }}>
              <div className="card-body p-5 text-center" style={{ maxHeight: '330px' }}>
                <h3 className="text-uppercase mb-3">Login</h3>

                <Form className="p-2 d-flex flex-column mb-2" onSubmit={onSubmit}>
                  <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Control type="text" placeholder="Username" ref={usernameRef} />
                  </Form.Group>

                  <Form.Group className="mb-4" controlId="formBasicPassword">
                    <Form.Control type="password" placeholder="Password" ref={passwordRef} />
                  </Form.Group>

                  <Button className="shadow mt-1" variant="primary" type="submit">
                    Submit
                  </Button>
                </Form>
                {<p className="text-danger fw-bold visible">{errorMessage}</p>}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginView;

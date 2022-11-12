import React, { FormEvent, FormEventHandler, useRef } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button, Form } from 'react-bootstrap';
import { UserDto } from '../../models/model';

interface Props {
  onSubmit: (user: UserDto) => void;
  errorMessage?: string;
}

const LoginView = (props: Props): JSX.Element => {
  const { onSubmit, errorMessage } = props;

  const usernameRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);

  const handleOnSubmit: FormEventHandler<HTMLFormElement> = async (
    event: FormEvent<HTMLFormElement>
  ) => {
    event.preventDefault();
    onSubmit({
      username: usernameRef.current?.value as string,
      password: passwordRef.current?.value as string
    });
  };

  return (
    <div className="vh-100">
      <div className="container py-5 h-100">
        <div className="row d-flex justify-content-center align-items-center h-100">
          <div className="col-5">
            <div className="card shadow-lg" style={{ borderRadius: '1rem' }}>
              <div className="card-body p-5 text-center" style={{ maxHeight: '330px' }}>
                <h3 className="text-uppercase mb-3">Login</h3>

                <Form className="p-2 d-flex flex-column mb-2" onSubmit={handleOnSubmit}>
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

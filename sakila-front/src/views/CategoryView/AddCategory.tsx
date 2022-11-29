import { Button, Form } from 'react-bootstrap';
import React, { useState } from 'react';
import { CategoryDto, OutputMessageDto } from '../../models/model';
import { CategoryService } from '../../services/CategoryService';
import { AxiosError } from 'axios';
import { useHistory } from 'react-router-dom';
import { useStompClient } from 'react-stomp-hooks';
import { Client } from '@stomp/stompjs';

const AddCategory: React.FC = () => {
  const [category, setCategory] = useState<CategoryDto>({
    name: '',
    categoryId: 0,
    lastUpdate: new Date()
  });

  const history = useHistory();

  const stompClient: Client | undefined = useStompClient();

  const editCategoryNameHandler = (event: React.ChangeEvent<any>): void => {
    setCategory((prevState) => {
      return {
        ...prevState,
        name: event.target.value
      };
    });
  };

  const submitBtnHandler = (event: React.FormEvent<HTMLButtonElement>): void => {
    event.preventDefault();
    console.log(category);
    CategoryService.createCategory(category)
      .then((response) => {
        console.log(response);
        const message: OutputMessageDto = {
          message: `New category was created`,
          time: new Date().toString()
        };
        sendMessage(message);
        history.push('/');
      })
      .catch((error: any | AxiosError) => {
        console.log(error);
      });
  };

  const sendMessage = (messageToSent: OutputMessageDto) => {
    stompClient?.publish({
      destination: '/app/reset',
      body: JSON.stringify(messageToSent)
    });
  };

  return (
    <div style={{ textAlign: 'left' }}>
      <h1>Insert Category</h1>
      <Form>
        <Form.Group className="mb-3" controlId="formEditCategory">
          <Form.Label>Category Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter category name"
            value={category.name}
            onChange={(event) => editCategoryNameHandler(event)}
          />
        </Form.Group>
        <Button variant="primary" type="submit" onClick={submitBtnHandler}>
          Submit
        </Button>
      </Form>
    </div>
  );
};
export default AddCategory;

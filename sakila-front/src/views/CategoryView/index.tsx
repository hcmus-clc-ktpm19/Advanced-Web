import React, { useEffect, useState } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import { Button, Form } from 'react-bootstrap';
import { CategoryDto, OutputMessageDto } from '@models/model';
import { CategoryService } from '@services/CategoryService';
import { AxiosError } from 'axios';
import { Client } from '@stomp/stompjs';
import { useStompClient } from 'react-stomp-hooks';

const CategoryView: React.FC = () => {
  const { id } = useParams<{ id: string }>();

  const [category, setCategory] = useState<CategoryDto>({
    categoryId: +id,
    lastUpdate: new Date(),
    name: ''
  });

  const history = useHistory();

  const stompClient: Client | undefined = useStompClient();

  useEffect(() => {
    CategoryService.getCategoryById(+id)
      .then((response) => {
        setCategory(response);
      })
      .catch((error: any | AxiosError) => {
        console.log(error);
      });
  }, []);

  const editCategoryNameHandler = (event: React.ChangeEvent<any>): void => {
    setCategory((prevState) => {
      return {
        ...prevState,
        name: event.target.value
      };
    });
  };

  const sendMessage = (messageToSent: OutputMessageDto) => {
    stompClient?.publish({
      destination: '/app/reset',
      body: JSON.stringify(messageToSent)
    });
  };

  const submitBtnHandler = (event: React.FormEvent<HTMLButtonElement>): void => {
    event.preventDefault();
    console.log(category);
    CategoryService.updateCategory(category)
      .then((response) => {
        console.log(response);
        const message: OutputMessageDto = {
          message: `Category with id ${id} was updated`,
          time: new Date().toString()
        };
        sendMessage(message);
        history.push('/');
      })
      .catch((error: any | AxiosError) => {
        console.log(error);
      });
  };

  return (
    <div style={{ textAlign: 'left' }}>
      <h1>Edit Category: {category.name}</h1>
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
export default CategoryView;

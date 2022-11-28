import { Button, Container, Modal, Table, Toast, ToastContainer } from 'react-bootstrap';
import { CategoryDto, OutputMessageDto } from '../../models/model';
import React, { useEffect, useState } from 'react';
import { CategoryService } from '../../services/CategoryService';
import { AxiosError } from 'axios';
import { Link } from 'react-router-dom';
import { Message } from 'stompjs';
import { useStompClient, useSubscription } from 'react-stomp-hooks';
import { Client } from '@stomp/stompjs';

const HomeView = (): JSX.Element => {
  const [categories, setCategories] = useState<CategoryDto[]>([]);
  const [showModal, setShowModal] = useState<boolean>(false);
  const [idToDelete, setIdToDelete] = useState<number>(-1);
  const [showNotification, setShowNotification] = useState<boolean>(false);
  const [receivedMessage, setReceivedMessage] = useState<OutputMessageDto>({
    message: '',
    time: ''
  });
  const stompClient: Client | undefined = useStompClient();

  useEffect(() => {
    CategoryService.getCategories()
      .then((response) => {
        setCategories(response);
      })
      .catch((error: any | AxiosError) => {
        console.error(error);
      });
  }, []);

  const onMessageReceived = (payload: Message) => {
    const payloadData = JSON.parse(payload.body);
    setReceivedMessage(payloadData);
    setShowNotification(true);
  };

  const sendMessage = (messageToSent: OutputMessageDto) => {
    if (stompClient) {
      stompClient.publish({
        destination: '/app/reset',
        body: JSON.stringify(messageToSent)
      });
    }
  };

  const deleteCategoryHandler = (): void => {
    CategoryService.deleteCategoryById(idToDelete)
      .then((response) => {
        hideModalHandler();
        const message: OutputMessageDto = {
          message: `Category with id ${idToDelete} was deleted`,
          time: new Date().toString()
        };
        sendMessage(message);
        setCategories(categories.filter((category) => category.categoryId !== idToDelete));
      })
      .catch((error: any | AxiosError) => {
        console.error(error);
      });
  };

  const setUpModalHandler = (idToDelete: number): void => {
    setIdToDelete(idToDelete);
    setShowModal(true);
  };

  const hideModalHandler = (): void => {
    setShowModal(false);
  };
  const handleCloseNotification = () => {
    CategoryService.getCategories()
      .then((response) => {
        setCategories(response);
      })
      .catch((error: any | AxiosError) => {
        console.error(error);
      });
    setShowNotification(false);
  };

  useSubscription('/topic/messages', onMessageReceived);

  return (
    <Container className="px-4 py-5">
      <h2 className="pb-2 border-bottom fw-bold">Home</h2>
      <Link to={'/categories/insert'}>
        <Button variant="primary" className="mt-3 mb-3">
          Add New Category
        </Button>
      </Link>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Category ID</th>
            <th>Name</th>
            <th>Last Update</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {categories.map((category) => {
            return (
              <tr key={category.categoryId}>
                <td>{category.categoryId}</td>
                <td>{category.name}</td>
                <td>{category.lastUpdate.toString()}</td>
                <td>
                  <Link to={`/categories/${category.categoryId}`}>
                    <Button variant="primary">Edit</Button>
                  </Link>
                  <div className={'more-space'} />
                  <Button
                    variant={'danger'}
                    className={'float-end'}
                    onClick={() => setUpModalHandler(category.categoryId)}
                  >
                    Delete
                  </Button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </Table>
      <Modal show={showModal} onHide={hideModalHandler}>
        <Modal.Header closeButton>
          <Modal.Title>Confirmation</Modal.Title>
        </Modal.Header>
        <Modal.Body>Are you sure you want to delete category?</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={deleteCategoryHandler}>
            Yes
          </Button>
          <Button variant="primary" onClick={hideModalHandler}>
            No
          </Button>
        </Modal.Footer>
      </Modal>
      <ToastContainer position="bottom-end" className="p-3">
        <Toast onClose={handleCloseNotification} show={showNotification}>
          <Toast.Header>
            <strong className="me-auto">New Message</strong>
            <small>just now</small>
          </Toast.Header>
          <Toast.Body style={{ textAlign: 'left' }}>{receivedMessage.message}</Toast.Body>
        </Toast>
      </ToastContainer>
    </Container>
  );
};
export default HomeView;

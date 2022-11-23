import {Button, Form} from "react-bootstrap";
import React, {useState} from "react";
import {CategoryDto, OutputMessageDto} from "../../models/model";
import {CategoryService} from "../../services/CategoryService";
import {AxiosError} from "axios";
import {useHistory} from "react-router-dom";
// @ts-ignore
import SockJS from 'sockjs-client';
// @ts-ignore
import {Client, Frame, Message, over} from 'stompjs';

var stompClient: Client = null;
const AddCategory = (): JSX.Element => {
  const [category, setCategory] = useState<CategoryDto>({
    name: "",
    categoryId: 0,
    lastUpdate: new Date(),
  });
  const history = useHistory();

  const editCategoryNameHandler = (event: React.ChangeEvent<any>): void => {
    setCategory(prevState => {
      return {
        ...prevState,
        name: event.target.value,
      }
    });
  }

  const submitBtnHandler = (event: React.FormEvent<HTMLButtonElement>): void => {
    event.preventDefault();
    console.log(category);
    CategoryService.createCategory(category).then((response) => {
      console.log(response);
      const message: OutputMessageDto = {
        message: `New category was created`,
        time: new Date().toString(),
      }
      sendMessage(message);
      history.push("/");
    }).catch((error: any | AxiosError) => {
      console.log(error);
    });
  }

  const onError = (err: any) => {
    console.log(err);
  }
  const onMessageReceived = (payload: Message) => {
    const payloadData = JSON.parse(payload.body);
    console.log(payloadData);
  }
  const onConnected = () => {
    stompClient.subscribe('/topic/messages', onMessageReceived);
  }

  const connect = () => {
    let Sock = new SockJS('http://localhost:8080/reset');
    stompClient = over(Sock);
    stompClient.connect({}, onConnected, onError);
  }
  connect();

  const sendMessage = (messageToSent: OutputMessageDto) => {
    if (stompClient) {
      console.log(messageToSent);
      stompClient.send("/app/reset", {}, JSON.stringify(messageToSent));
    }
  }

  return (
      <div style={{textAlign: "left"}}>
        <h1>Insert Category</h1>
        <Form>
          <Form.Group className="mb-3" controlId="formEditCategory">
            <Form.Label>Category Name</Form.Label>
            <Form.Control type="text"
                          placeholder="Enter category name"
                          value={category.name}
                          onChange={event => editCategoryNameHandler(event)}
            />
          </Form.Group>
          <Button variant="primary"
                  type="submit"
                  onClick={submitBtnHandler}>
            Submit
          </Button>
        </Form>
      </div>
  )
}
export default AddCategory;
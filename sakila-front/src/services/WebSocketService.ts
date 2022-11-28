import SockJS from 'sockjs-client';
import { Client, Frame, Message, over } from 'stompjs';
import { OutputMessageDto } from '../models/model';

let stompClient: Client;

const onConnect = (frame?: Frame): any => {
  console.log('Connected: ' + frame);
  stompClient.subscribe('/topic/messages', function (messageOutput: Message): any {
    console.log(JSON.parse(messageOutput.body));
  });
};

const onError = (error: Frame | string): any => {
  console.error(error);
};

export const WebSocketService = {
  connect: (): Client => {
    const socket = new SockJS('http://localhost:8080/reset');
    stompClient = over(socket);
    stompClient.connect({}, onConnect, onError);
    return stompClient;
  },

  sendMessage: (message: OutputMessageDto): void => {
    stompClient.send('/app/reset', {}, JSON.stringify(message));
  }
};

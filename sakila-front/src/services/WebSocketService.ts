// @ts-ignore
import SockJS from 'sockjs-client';
// @ts-ignore
import {Client, Frame, Message, over} from 'stompjs';
import {OutputMessageDto} from "../models/model";


var stompClient: Client;
export const WebSocketService = {
  getStompClient: (): Client => {
    // wait for the connection to be established
    if (!stompClient) {
      throw new Error('Stomp client is not initialized yet');
    }
    return stompClient;
  },
  connect: (): Client => {
    const socket = new SockJS('http://localhost:8080/reset');
    console.log(socket);
    stompClient = over(socket);
    // wait the stomp client to connect
    stompClient.connect({}, function (frame: any): any {
      console.log('Connected: ' + frame);
      // // @ts-ignore
      // stompClient.subscribe('/topic/messages', (messageOutput: Message) => {
      //   const message = JSON.parse(messageOutput.body);
      //   console.log(message);
      // });
    }, function (error: any) {
      console.log(error);
    });
    return new Promise((resolve) => {
      console.log('From connect = ', stompClient);
      resolve(stompClient);
    });
  },
  sendMessage: (message: OutputMessageDto): void => {
    stompClient.send("/app/reset", {}, JSON.stringify(message));
  },
  disconnect: (): void => {
    if (stompClient !== null) {
      stompClient.disconnect();
    }
    console.log("Disconnected");
  },
}
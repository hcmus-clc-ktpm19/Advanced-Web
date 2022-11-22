import SockJS from 'sockjs-client';
import {Client, Frame, Message, over} from 'stompjs';
import {OutputMessageDto} from "../models/model";

var stompClient: Client | null = null;
export const WebSocketService = {
  stompClient: null,
  connect: (): void => {
    const socket = new SockJS('http://localhost:8080/reset');
    console.log(socket);
    stompClient = over(socket);
    console.log(stompClient);
    stompClient.connect({}, function(frame: any): any {
      console.log('Connected: ' + frame);
      // @ts-ignore
      stompClient.subscribe('/topic/messages', function(messageOutput: Message): any {
        console.log(JSON.parse(messageOutput.body));
      });
    }, function(error: any) {
      console.log(error);
    });
  },
  sendMessage: (message: OutputMessageDto): void => {
    // @ts-ignore
    stompClient.send("/app/reset", {}, JSON.stringify(message));
  }
}
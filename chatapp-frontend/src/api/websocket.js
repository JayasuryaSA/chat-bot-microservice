import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

let stompClient = null;

export function connect(roomId, onMessageReceived, onLoadHistory) {
  
  const socket = new SockJS('http://localhost:8765/chat-service/chat');


  stompClient = new Client({
    webSocketFactory: () => socket,
    reconnectDelay: 5000,
    debug: str => console.log('websocket.js:', str),
    onConnect: () => {
      console.log('WebSocket connected');
      if (onLoadHistory) {
        onLoadHistory(roomId);
      }
      stompClient.subscribe(`/topic/room/${roomId}`, message => {
        const receivedMessage = JSON.parse(message.body);
        onMessageReceived(receivedMessage);
      });
    },
    onStompError: frame => {
      console.error('Broker error: ' + frame.headers['message']);
      console.error('Details:', frame.body);
    },
    onDisconnect: () => {
      console.log('WebSocket disconnected');
    },
  });
  stompClient.activate();
}

export function disconnect() {
  if (stompClient) {
    stompClient.deactivate();
    console.log('WebSocket disconnected');
  }
}

export function sendMessage(roomId, message) {
  if (stompClient && stompClient.connected) {
    stompClient.publish({
      destination: `/app/sendMessage/${roomId}`,
      body: JSON.stringify(message),
    });
  } else {
    console.warn('WebSocket not connected');
  }
}

import React, { useState, useEffect } from 'react';
import { connect, disconnect, sendMessage } from '../../api/websocket';
import styles from './ChatRoom.module.css';
import MessageList from './MessageList';
import MessageInput from './MessageInput';

const BASE_URL = 'http://localhost:8765/room-service/api/v1/rooms';

const ChatRoom = ({ roomId, sender, onEndChat }) => {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');

  useEffect(() => {
    const loadHistory = async () => {
      try {
        const response = await fetch(`${BASE_URL}/${roomId}/messages`);
        if (response.ok) {
          const text = await response.text();
          if (text) {
            const history = JSON.parse(text);
            setMessages(history);
          } else {
            setMessages([]);
          }
        } else {
          console.error('Failed to load message history, status:', response.status);
        }
      } catch (err) {
        console.error('Failed to load message history', err);
      }
    };

    loadHistory();
    connect(roomId, msg => setMessages(prev => [...prev, msg]));

    return () => disconnect();
  }, [roomId]);

  const handleSend = () => {
    if (input.trim()) {
      sendMessage(roomId, { sender, content: input, roomId });
      setInput('');
    }
  };

  const handleEndChat = () => {
    disconnect();
    if (onEndChat) onEndChat();
  };

  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <span>Room: {roomId}</span>
        <button className={styles.endChatButton} onClick={handleEndChat} title="End Chat">
          End Chat
        </button>
      </div>

      <div className={styles.messageWrapper}>
        <MessageList messages={messages} />
        <MessageInput input={input} setInput={setInput} onSend={handleSend} />
      </div>
    </div>
  );
};

export default ChatRoom;

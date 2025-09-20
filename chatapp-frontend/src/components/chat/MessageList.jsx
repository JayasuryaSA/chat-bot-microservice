import React from 'react';
import styles from './ChatRoom.module.css';

const MessageList = ({ messages }) => (
  <div className={styles.messageList}>
    {messages.map((msg, i) => (
      <div key={i} className={styles.message}>
        <span className={styles.messageSender}>{msg.sender}</span>
        <span className={styles.messageContent}>: {msg.content}</span>
        <span className={styles.messageTimestamp}>
          {msg.timeStamp ? new Date(msg.timeStamp).toLocaleString() : ''}
        </span>
      </div>
    ))}
  </div>
);

export default MessageList;

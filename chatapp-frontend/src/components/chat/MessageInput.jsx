import React from 'react';
import styles from './ChatRoom.module.css';

const MessageInput = ({ input, setInput, onSend }) => (
  <div className={styles.inputContainer}>
    <input
      className={styles.input}
      type="text"
      placeholder="Type your message..."
      value={input}
      onChange={e => setInput(e.target.value)}
      onKeyDown={e => e.key === 'Enter' && onSend()}
    />
    <button className={styles.sendButton} onClick={onSend}>
      Send
    </button>
  </div>
);

export default MessageInput;

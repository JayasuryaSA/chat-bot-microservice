import React from 'react';
import styles from './JoinCreateForm.module.css';

const JoinCreateForm = ({ sender, setSender, roomId, setRoomId, onJoin, onCreate }) => (
  <div className={styles.container}>
    <h1>Join or Create Chat Room</h1>
    <form className={styles.form} onSubmit={e => e.preventDefault()}>
      <div className={styles.inputs}>
        <input
          placeholder="Your name"
          value={sender}
          onChange={e => setSender(e.target.value)}
        />
        <input
          placeholder="Room ID"
          value={roomId}
          onChange={e => setRoomId(e.target.value)}
        />
      </div>
      <div className={styles.buttons}>
        <button type="button" onClick={onJoin}>Join Room</button>
        <button type="button" onClick={onCreate}>Create Room</button>
      </div>
    </form>
  </div>
);

export default JoinCreateForm;

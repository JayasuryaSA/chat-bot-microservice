import React, { useState } from 'react';
import ChatRoom from './components/chat/ChatRoom';
import JoinCreateForm from './components/joinCreate/JoinCreateForm';

const BACKEND_BASE_URL = 'http://localhost:8765/room-service/api/v1/rooms';

const App = () => {
  const [roomId, setRoomId] = useState('');
  const [sender, setSender] = useState('');
  const [joined, setJoined] = useState(false);

  const joinRoom = async () => {
    if (!sender.trim() || !roomId.trim()) {
      alert('Enter your name and room ID');
      return;
    }
    try {
      const response = await fetch(`${BACKEND_BASE_URL}/${roomId.trim()}`);
      if (!response.ok) {
        alert('Room does not exist. Please create it first.');
        return;
      }
      setJoined(true);
    } catch (error) {
      alert('Network or server error: ' + error.message);
    }
  };

  const createRoom = async () => {
    if (!sender.trim() || !roomId.trim()) {
      alert('Enter your name and desired room ID');
      return;
    }

    try {
      const res = await fetch(`${BACKEND_BASE_URL}/createRoom`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ roomId: roomId.trim() }),
      });

      if (res.ok) {
        alert('Room created! Joining...');
        setJoined(true);
      } else {
        const errorText = await res.text();
        alert(`Failed to create room: ${errorText}`);
      }
    } catch (error) {
      alert('Network or server error: ' + error.message);
    }
  };

  const leaveChat = () => {
    setJoined(false);
    setRoomId('');
    setSender('');
  };

  if (!joined)
    return (
      <JoinCreateForm
        sender={sender}
        setSender={setSender}
        roomId={roomId}
        setRoomId={setRoomId}
        onJoin={joinRoom}
        onCreate={createRoom}
      />
    );

  return <ChatRoom roomId={roomId} sender={sender} onEndChat={leaveChat} />;
};

export default App;

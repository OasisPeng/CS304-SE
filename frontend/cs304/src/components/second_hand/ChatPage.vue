<template>
  <v-container>
    <v-app-bar app color="white" flat>
      <v-btn icon @click="goBack">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-toolbar-title>{{ chatPartnerName }}</v-toolbar-title>
    </v-app-bar>
    <v-row class="chat-container">
      <v-col cols="12">
        <div class="chat-bubble left" v-for="(msg, index) in chatMessages" :key="index">
          <v-avatar left size="32">
            <v-img
                v-if="msg.avatar && !msg.avatarError"
                :src="msg.avatar"
                @error="msg.avatarError = true"
            ></v-img>
            <v-icon v-else>mdi-account-circle</v-icon>
          </v-avatar>
          <div class="chat-text">{{ msg.text }}</div>
        </div>
        <div class="chat-bubble right" v-for="(msg, index) in myMessages" :key="index">
          <div class="chat-text">{{ msg.text }}</div>
          <v-avatar right size="32">
            <v-img
                v-if="myAvatar && !msg.avatarError"
                :src="myAvatar"
                @error="msg.avatarError = true"
            ></v-img>
            <v-icon v-else>mdi-account-circle-outline</v-icon>
          </v-avatar>
        </div>
      </v-col>
    </v-row>
    <v-row class="message-input-container">
      <v-col cols="12" class="d-flex align-center">
        <v-text-field v-model="newMessage" placeholder="输入消息..." hide-details></v-text-field>
        <v-btn icon @click="sendMessage">
          <v-icon>mdi-send</v-icon>
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      chatPartnerName: '',
      chatMessages: [],
      myMessages: [],
      newMessage: '',
      websocket: null,
      myAvatar: 'path/to/my-avatar.jpg', // Replace with your actual avatar path
      retryInterval: 500 // Retry interval in milliseconds
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    async fetchChatMessages() {
      const from = this.$route.params.message.from;
      const to = this.$route.params.message.to;

      try {
        const response = await axios.get(`${this.$httpUrl}/message/getByTwoUser/${from}/${to}`, {
          headers: {
            'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
          }
        });
        const messages = response.data.data;

        this.chatMessages = messages
            .filter(msg => msg.from === to)
            .map(msg => ({
              text: msg.text,
              avatarError: false
            }));

        this.myMessages = messages
            .filter(msg => msg.from === from)
            .map(msg => ({
              text: msg.text,
              avatarError: false
            }));

        // Sort messages by timestamp
        this.chatMessages.sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp));
        this.myMessages.sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp));
      } catch (error) {
        console.error('Error fetching chat messages:', error);
      }
    },
    sendMessage() {
      if (this.newMessage.trim() !== '') {
        const from = this.$route.params.message.from;
        const to = this.$route.params.message.to;

        const message = {
          from: from,
          to: to,
          text: this.newMessage,
          time: new Date()
        };

        const sendMsg = () => {
          if (this.websocket.readyState === WebSocket.OPEN) {
            this.websocket.send(JSON.stringify(message));
            this.myMessages.push({ text: this.newMessage, avatarError: false });
            this.newMessage = '';
          } else if (this.websocket.readyState === WebSocket.CONNECTING) {
            setTimeout(sendMsg, this.retryInterval);
          } else {
            console.error('WebSocket connection is not open');
          }
        };

        sendMsg();
      }
    },
    initWebSocket() {
      const from = this.$route.params.message.from;
      const wsProtocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
      const wsUrl = `${wsProtocol}//${window.location.host}/chatroom/${from}`;

      this.websocket = new WebSocket(wsUrl);

      this.websocket.onopen = () => {
        console.log('WebSocket connection established');
      };

      this.websocket.onmessage = (event) => {
        const message = JSON.parse(event.data);
        if (message.from === this.$route.params.message.to) {
          this.chatMessages.push({ text: message.text, avatarError: false });
        }
      };

      this.websocket.onclose = () => {
        console.log('WebSocket connection closed');
      };

      this.websocket.onerror = (error) => {
        console.error('WebSocket error:', error);
      };
    }
  },
  created() {
    this.chatPartnerName = this.$route.params.message.name;
    this.fetchChatMessages();
    this.initWebSocket();
  }
};
</script>

<style scoped>
.v-app-bar {
  justify-content: space-between;
}

.chat-container {
  padding-top: 16px;
  padding-bottom: 60px;
}

.chat-bubble {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.chat-bubble.left .chat-text {
  background-color: #f1f1f1;
  border-radius: 10px;
  padding: 10px;
  margin-left: 10px;
}

.chat-bubble.right {
  justify-content: flex-end;
}

.chat-bubble.right .chat-text {
  background-color: #4caf50;
  color: white;
  border-radius: 10px;
  padding: 10px;
  margin-right: 10px;
}

.message-input-container {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: white;
  padding: 10px;
  box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
}
</style>

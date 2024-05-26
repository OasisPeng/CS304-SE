<template>
  <v-container>
    <v-app-bar app color="white" flat>
      <v-btn icon @click="goBack">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-toolbar-title>{{ chatMessage.name }}</v-toolbar-title>
    </v-app-bar>
    <v-row class="chat-container">
      <v-col cols="12">
        <div class="chat-bubble left" v-for="(msg, index) in chatMessages" :key="index">
          <v-avatar left size="32">
            <v-img
                v-if="chatMessage.avatar && !msg.avatarError"
                :src="chatMessage.avatar"
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
export default {
  data() {
    return {
      chatMessage: this.$route.params.message,
      chatMessages: [
        { text: '想聊一下二手鞋，可以吗?', avatarError: false },
        { text: 'I can\'t wait to talk with you,immediately.', avatarError: false }
      ],
      myMessages: [
        { text: '可以啊，我这儿不少好鞋!', avatarError: false }
      ],
      newMessage: '',
      myAvatar: 'path/to/my-avatar.jpg' // Replace with your actual avatar path
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    sendMessage() {
      if (this.newMessage.trim() !== '') {
        this.myMessages.push({ text: this.newMessage, avatarError: false });
        this.newMessage = '';
      }
    }
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

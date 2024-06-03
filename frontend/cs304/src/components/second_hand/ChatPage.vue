<template>
  <v-container>
    <v-app-bar app color="white" flat>
      <v-btn icon @click="goBack">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-toolbar-title>{{ chatPartnerName }}</v-toolbar-title>
    </v-app-bar>
    <v-row class="product-display">
      <v-col cols="12" class="d-flex align-center">
        <v-img :src="product.image" class="goods-image"></v-img>
        <div class="product-info">
          <div class="product-title">{{ product.name }}</div>
          <div class="product-price">{{ product.price }}</div>
        </div>
      </v-col>
    </v-row>
    <v-row class="chat-container">
      <v-col cols="12">
        <div
            v-for="(msg, index) in filteredMessages"
            :key="index"
            :class="['chat-bubble', isCurrentUser(msg.from) ? 'right' : 'left']"
        >
          <div :class="isCurrentUser(msg.from) ? 'right-avatar' : 'left-avatar'">
            <v-avatar size="32">
              <v-icon>{{ getIconType(msg.from) }}</v-icon>
            </v-avatar>
          </div>
          <div class="chat-text">{{ msg.text }}</div>
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
    const storedInfo = JSON.parse(localStorage.getItem('info')) || {};
    return {
      chatPartnerName: '',
      chatMessages: [],
      filteredMessages: [],
      newMessage: '',
      currentUser: storedInfo.username || '',
      chatPartner: '',
      goodsId: null,
      product: {}
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    async fetchChatMessages() {
      const from = this.currentUser;
      const to = this.chatPartner;
      this.goodsId = this.$route.params.message.goodsId;

      try {
        const response = await axios.get(`${this.$httpUrl}/message/getByTwoUser/${from}/${to}`, {
          headers: {
            'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
          }
        });
        const messages = response.data.data;

        this.chatMessages = messages.map(msg => ({
          ...msg,
          avatarError: false
        }));

        this.filterMessagesByGoodsId();
      } catch (error) {
        console.error('Error fetching chat messages:', error);
      }
    },
    async fetchProductDetails() {
      try {
        const response = await axios.get(`${this.$httpUrl}/goods/${this.goodsId}`, {
          headers: {
            'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
          }
        });
        this.product = response.data.data;
      } catch (error) {
        console.error('Error fetching product details:', error);
      }
    },
    filterMessagesByGoodsId() {
      this.filteredMessages = this.chatMessages
          .filter(msg => msg.goodsId === this.goodsId)
          .sort((a, b) => new Date(a.time) - new Date(b.time));

      console.log(this.filteredMessages);
    },
    async sendMessage() {
      if (this.newMessage.trim() !== '') {
        const from = this.currentUser;
        const to = this.chatPartner;

        const message = {
          from: from,
          to: to,
          text: this.newMessage,
          time: new Date(),
          goodsId: this.goodsId
        };

        try {
          await axios.post(`${this.$httpUrl}/message/sendMessage`, message, {
            headers: {
              'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
            }
          });
          this.chatMessages.push({ ...message, avatarError: false });
          this.filterMessagesByGoodsId();
          this.newMessage = '';
        } catch (error) {
          console.error('Error sending message:', error);
        }
      }
    },
    async prepare() {
      const storedInfoRaw = localStorage.getItem('info');
      if (!storedInfoRaw) {
        console.error('No user info found in localStorage');
        return;
      }

      let storedInfo;
      try {
        storedInfo = JSON.parse(storedInfoRaw);
      } catch (error) {
        console.error('Error parsing user info from localStorage', error);
        return;
      }

      if (!storedInfo || !storedInfo.username) {
        console.error('Invalid user info in localStorage');
        return;
      }

      this.currentUser = storedInfo.username;

      const from = this.$route.params.message.from || "";
      const to = this.$route.params.message.to || "";

      console.log("from", from);
      console.log("to", to);

      if (from === this.currentUser) {
        this.chatPartner = to;
      } else {
        this.chatPartner = from;
      }
      this.chatPartnerName = this.chatPartner;
    },
    isCurrentUser(user) {
      console.log("当前用户:", this.currentUser, "类型:", typeof this.currentUser);
      console.log("消息发送者:", user, "类型:", typeof user);

      if (this.currentUser === null || this.currentUser === undefined || this.currentUser === '') {
        console.error("当前用户未定义或为空");
        return false;
      }

      if (user === null || user === undefined || user === '') {
        console.error("消息发送者未定义或为空");
        return false;
      }

      // 将两者都转换为字符串进行比较
      const result = String(user) === String(this.currentUser);
      console.log("比较结果:", result);
      return result;
    },

    getIconType(user) {
      console.log("当前用户:", this.currentUser, "类型:", typeof this.currentUser);
      console.log("消息发送者:", user, "类型:", typeof user);

      if (this.currentUser === null || this.currentUser === undefined || this.currentUser === '') {
        console.error("当前用户未定义或为空");
        return 'mdi-account-circle'; // 返回默认值
      }

      if (user === null || user === undefined || user === '') {
        console.error("消息发送者未定义或为空");
        return 'mdi-account-circle'; // 返回默认值
      }

      // 将两者都转换为字符串进行比较
      const result = String(user) !== String(this.currentUser) ? 'mdi-account-circle' : 'mdi-account-circle-outline';
      console.log("图标类型:", result);
      return result;
    },
  },
  mounted() {
    this.prepare();
    this.fetchChatMessages();
    this.fetchProductDetails();
    console.log("当前是谁", this.currentUser)
  }
};
</script>

<style scoped>
.v-app-bar {
  justify-content: space-between;
}

.product-display {
  padding: 16px 0;
  border-bottom: 1px solid #e0e0e0;
}

.goods-image {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 10px;
  margin-right: 10px;
}

.product-info {
  flex-grow: 1;
}

.product-title {
  font-weight: bold;
  font-size: 18px;
}

.product-price {
  color: #ff5722;
  font-size: 16px;
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

.chat-bubble.left {
  justify-content: flex-start;
}

.chat-bubble.left .chat-text {
  background-color: #f1f1f1;
  border-radius: 10px;
  padding: 10px;
  margin-left: 10px;
}

.chat-bubble.right {
  justify-content: flex-end;
  flex-direction: row-reverse;
}

.chat-bubble.right .chat-text {
  background-color: #4caf50;
  color: white;
  border-radius: 10px;
  padding: 10px;
  margin-right: 10px;
}

.left-avatar {
  margin-right: 10px;
}

.right-avatar {
  margin-left: 10px;
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

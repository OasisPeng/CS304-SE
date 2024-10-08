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
        <v-btn
            :disabled="isBuyer"
            color="green"
            @click="confirmPurchase"
        >
          确认成交
        </v-btn>
      </v-col>
    </v-row>
    <v-row class="chat-container">
      <v-col cols="12">
        <div
            v-for="(msg, index) in filteredMessages"
            :key="index"
            :class="['chat-bubble', String(msg.from) === String(currentUser) ? 'right' : 'left']"
        >
          <template v-if="String(msg.from) === String(currentUser)">
            <div class="chat-text">{{ msg.text }}</div>
            <v-avatar
                :left="false"
                :right="true"
                size="32"
            >
              <v-icon>mdi-account-circle-outline</v-icon>
            </v-avatar>
          </template>
          <template v-else>
            <v-avatar
                :left="true"
                :right="false"
                size="32"
            >
              <v-icon>mdi-account-circle</v-icon>
            </v-avatar>
            <div class="chat-text">{{ msg.text }}</div>
          </template>
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

    <!-- 交易成功提示弹窗 -->
    <v-dialog v-model="successDialog" max-width="300">
      <v-card>
        <v-card-title class="headline">提示</v-card-title>
        <v-card-text>交易成功</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="closeSuccessDialog">确定</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>
<script>
import axios from 'axios';

export default {
  data() {
    return {
      chatPartnerName: '',
      chatMessages: [],
      filteredMessages: [],
      newMessage: '',
      currentUser: JSON.parse(localStorage.getItem('info')).username,
      chatPartner: '', // 保存聊天对象的ID
      goodsId: null,
      product: {},
      from: '',
      to: '',
      tab: null,
      isBuyer: false,
      successDialog: false
    };
  },
  methods: {
    goBack() {
      this.$router.push('/MessagesPage');
    },
    async fetchChatMessages() {
      const from = this.currentUser;
      const to = this.chatPartner;
      this.from = from;
      this.to = to;
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

      let buyerId;
      const owner = this.product.sellerId;
      console.log("this.product: ", this.product);
      console.log("Owner: ", owner);
      if (String(owner) === String(this.currentUser)) {
        buyerId = this.chatPartner;
      } else {
        buyerId = this.currentUser;
      }

      // 设置是否为买家
      this.isBuyer = String(this.currentUser) === String(buyerId);

      console.log("buyerId:", buyerId);
      console.log("isBuyer:", this.isBuyer);
    },
    filterMessagesByGoodsId() {
      this.filteredMessages = this.chatMessages
          .filter(msg => msg.goodsId === this.goodsId)
          .sort((a, b) => new Date(a.time) - new Date(b.time));
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
    async confirmPurchase() {
      let buyerId;
      const owner = this.product.sellerId;
      if (String(owner) === String(this.currentUser)) {
        buyerId = this.chatPartner;
      } else {
        buyerId = this.currentUser;
      }

      const updatedProduct = {
        ...this.product,
        buyerId
      };
      console.log("buyerId: ", buyerId);

      try {
        const response = await axios.post(`${this.$httpUrl}/goods/update`, updatedProduct, {
          headers: {
            'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
          }
        });

        if (response.data.msg === '成功') {
          console.log('购买确认成功');
          this.successDialog = true;
        } else {
          console.log('购买确认失败');
        }
      } catch (error) {
        console.error('Error confirming purchase:', error);
        console.log('购买确认失败');
      }
    },
    closeSuccessDialog() {
      this.successDialog = false;
      this.$router.push('/MessagesPage');
    }
  },
  created() {
    const from = this.$route.params.message.from;
    const to = this.$route.params.message.to;
    const tab = this.$route.params.tab;

    console.log("this.$route.params.message:", this.$route.params.message);
    console.log("from:", from, "type:", typeof from);
    console.log("this.currentUser:", this.currentUser, "type:", typeof this.currentUser);

    console.log("tab: ", tab);
    this.tab = tab;

    // 确定聊天对象
    if (String(from) === String(this.currentUser)) {
      this.chatPartner = to;
    } else {
      this.chatPartner = from;
    }
    console.log("currentUser:", this.currentUser);
    console.log("chatPartner:", this.chatPartner);

    this.fetchChatMessages();
    this.fetchProductDetails();
    // 设置聊天对象的名称
    this.chatPartnerName = this.chatPartner;
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

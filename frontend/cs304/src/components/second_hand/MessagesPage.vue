<template>
  <v-container>
    <v-app-bar app color="white" flat>
      <v-toolbar-title>消息中心</v-toolbar-title>
    </v-app-bar>
    <v-tabs v-model="tab" background-color="white" grow color="teal">
      <v-tab>互动消息</v-tab>
      <v-tab>我想要的</v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab">
      <v-tab-item>
        <v-list>
          <v-list-item
              v-for="(message, index) in interactionMessages"
              :key="index"
              @click="goToChat(message)"
          >
            <v-list-item-avatar>
              <v-img
                  v-if="message.avatar && !message.avatarError"
                  :src="message.avatar"
                  @error="message.avatarError = true"
              ></v-img>
              <v-icon v-else>mdi-account-circle</v-icon>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>{{ message.from }}</v-list-item-title>
              <v-list-item-subtitle>{{ message.text }}</v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-action>
              <v-list-item-action-text>{{ formatTime(message.time) }}</v-list-item-action-text>
            </v-list-item-action>
            <v-img
                v-if="message.goodsImage"
                :src="message.goodsImage"
                class="goods-image"
            ></v-img>
            <v-icon
                v-if="message.old !== 0"
                color="red"
                class="red-dot"
            >mdi-circle</v-icon>
          </v-list-item>
        </v-list>
      </v-tab-item>
      <v-tab-item>
        <v-list>
          <v-list-item
              v-for="(message, index) in orderMessages"
              :key="index"
              @click="goToChat(message)"
          >
            <v-list-item-avatar>
              <v-img
                  v-if="message.avatar && !message.avatarError"
                  :src="message.avatar"
                  @error="message.avatarError = true"
              ></v-img>
              <v-icon v-else>mdi-account-circle</v-icon>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>{{ message.to }}</v-list-item-title>
              <v-list-item-subtitle>{{ message.text }}</v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-action>
              <v-list-item-action-text>{{ formatTime(message.time) }}</v-list-item-action-text>
            </v-list-item-action>
            <v-img
                v-if="message.goodsImage"
                :src="message.goodsImage"
                class="goods-image"
            ></v-img>
            <v-icon
                v-if="message.old !== 0"
                color="red"
                class="red-dot"
            >mdi-circle</v-icon>
          </v-list-item>
        </v-list>
      </v-tab-item>
    </v-tabs-items>
    <div class="fixed-bottom">
      <BottomNavigation :value="selectedPage" />
    </div>
  </v-container>
</template>
<script>
import BottomNavigation from "@/components/second_hand/BottomNavigation";
import moment from "moment";

export default {
  components: {
    BottomNavigation
  },
  data() {
    return {
      selectedPage: 'MessagesPage',
      tab: 0,
      interactionMessages: [],
      orderMessages: []
    };
  },
  methods: {
    async fetchInteractionMessages() {
      const data = localStorage.getItem('info');
      const userInfo = JSON.parse(data);
      const id = userInfo.username;

      try {
        const response = await this.$axios.get(this.$httpUrl + `/message/getByOneUserTo/` + id, {
          withCredentials: false,
          headers: {
            'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
          },
        });

        const messages = response.data.data;
        const uniqueMessages = {};

        console.log(messages)
        for (let message of messages) {
          const key = `${message.from}-${message.goodsId}`;
          if (!uniqueMessages[key] || new Date(uniqueMessages[key].time) < new Date(message.time)) {
            // Fetch product info
            const productResponse = await this.$axios.get(this.$httpUrl + `/goods/${message.goodsId}`, {
              withCredentials: false,
              headers: {
                'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
              }
            });
            message.goodsImage = productResponse.data.data.image;
            uniqueMessages[key] = message;
          }
        }

        this.interactionMessages = Object.values(uniqueMessages);

      } catch (error) {
        console.error('Error fetching interaction messages:', error);
      }
    },
    async fetchOrderMessages() {
      const data = localStorage.getItem('info');
      const userInfo = JSON.parse(data);
      const id = userInfo.username;

      try {
        const response = await this.$axios.get(this.$httpUrl + `/message/getByOneUserFrom/` + id, {
          withCredentials: false,
          headers: {
            'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
          },
        });

        const messages = response.data.data;
        const uniqueMessages = {};


        console.log(messages)
        for (let message of messages) {
          const key = `${message.to}-${message.goodsId}`;
          if (!uniqueMessages[key] || new Date(uniqueMessages[key].time) < new Date(message.time)) {
            // Fetch product info
            const productResponse = await this.$axios.get(this.$httpUrl + `/goods/${message.goodsId}`, {
              withCredentials: false,
              headers: {
                'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
              }
            });
            message.goodsImage = productResponse.data.data.image;
            uniqueMessages[key] = message;
          }
        }

        this.orderMessages = Object.values(uniqueMessages);

      } catch (error) {
        console.error('Error fetching order messages:', error);
      }
    },
    goToChat(message) {
      this.$router.push({ name: 'ChatPage', params: { message, tab} });
    },
    formatTime(time) {
      return moment(time).format('YYYY-MM-DD HH:mm');
    }
  },
  watch: {
    tab(newVal) {
      if (newVal === 0) {
        this.fetchInteractionMessages();
      } else if (newVal === 1) {
        this.fetchOrderMessages();
      }
    }
  },
  created() {
    this.fetchInteractionMessages(); // 默认加载互动消息
  }
};
</script>
<style scoped>
.v-app-bar {
  justify-content: center;
}

.fixed-bottom {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: white;
  padding: 10px 0;
  box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
}

.red-dot {
  position: absolute;
  top: 10px;
  left: 10px;
  font-size: 5px;
}

.goods-image {
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 50%;
  margin-left: 10px;
}
</style>

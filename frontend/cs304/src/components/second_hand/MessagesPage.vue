<template>
  <v-container>
    <v-app-bar app color="white" flat>
      <v-toolbar-title>消息中心</v-toolbar-title>
    </v-app-bar>
    <v-tabs v-model="tab" background-color="white" grow>
      <v-tab>订单消息</v-tab>
      <v-tab>互动消息</v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab">
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
              <v-list-item-title>{{ message.name }}</v-list-item-title>
              <v-list-item-subtitle>{{ message.text }}</v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-action>
              <v-list-item-action-text>{{ message.time }}</v-list-item-action-text>
            </v-list-item-action>
          </v-list-item>
        </v-list>
      </v-tab-item>
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
              <v-list-item-title>{{ message.name }}</v-list-item-title>
              <v-list-item-subtitle>{{ message.text }}</v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-action>
              <v-list-item-action-text>{{ message.time }}</v-list-item-action-text>
            </v-list-item-action>
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
// import {getAllMessages} from '../../api/index'
import BottomNavigation from "@/components/second_hand/BottomNavigation";
// import request from "@/utils/request";

export default {
  components: {
    BottomNavigation
  },
  data() {
    return {
      selectedPage: 'MessagesPage',
      tab: 0,
      orderMessages: [],
      interactionMessages: []
    };
  },
  methods: {
    async fetchMessages() {
      const data=localStorage.getItem('info')
      const id=JSON.parse(data).username
      try {
        const response = await this.$axios.get(this.$httpUrl + `/message/${id}`, {
          withCredentials: false,
          headers: {
            'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
          },
        })
        console.log(id)
        console.log(response)
        this.orderMessages = response.data.map(message => ({
          ...message,
          avatarError: false
        }));
      } catch (error) {
        console.error('Error fetching messages:', error);
      }
    },
    goToChat(message) {
      this.$router.push({ name: 'ChatPage', params: { message } });
    }
  },
  created() {
    this.fetchMessages();
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
</style>

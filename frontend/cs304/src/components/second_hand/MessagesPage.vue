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
import BottomNavigation from "@/components/second_hand/BottomNavigation";

export default {
  components: {
    BottomNavigation
  },
  data() {
    return {
      selectedPage:'MessagesPage',
      tab: 0,
      orderMessages: [
        { name: 'Jun_', text: '你好，电脑还卖吗?', time: '12.14', avatar: 'path/to/avatar1.jpg', avatarError: false },
        { name: '虎扑体育', text: '想聊一下二手鞋，可以吗?', time: '昨天', avatar: 'path/to/avatar2.jpg', avatarError: false }
      ],
      interactionMessages: [
        { name: 'Alex', text: '你好，新书还有吗?', time: '10:30', avatar: '', avatarError: false }
      ]
    };
  },
  methods: {
    goToChat(message) {
      this.$router.push({ name: 'ChatPage', params: { message } });
    }
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

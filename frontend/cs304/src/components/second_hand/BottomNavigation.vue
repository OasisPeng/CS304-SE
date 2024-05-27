<template>
  <v-bottom-navigation v-model="selectedPage" color="teal" grow>
    <v-btn value="home" @click="navigateTo('FirstPage')">
      <span>首页</span>
      <v-icon>mdi-home-heart</v-icon>
    </v-btn>

    <v-btn value="MarketPage" @click="navigateTo('MarketPage')">
      <span>市场</span>
      <v-icon>mdi-storefront-outline</v-icon>
    </v-btn>

    <v-btn value="publish" @click="navigateTo('ReleasePage')" class="publish-btn">
      <span class="publish-text">发布商品</span>
      <v-icon class="publish-icon">mdi-plus-circle</v-icon>
    </v-btn>

    <v-btn value="MessagesPage" @click="navigateTo('MessagesPage')">
      <span>消息</span>
      <v-icon>mdi-chat-outline</v-icon>
    </v-btn>

    <v-btn value="Person" @click="navigateTo('Person')">
      <span>我的</span>
      <v-icon>mdi-account-outline</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  props: {
    value: {
      type: String,
      required: true
    }
  },
  computed: {
    selectedPage: {
      get() {
        return this.value;
      },
      set(val) {
        this.$emit('input', val);
      }
    }
  },
  methods: {
    navigateTo(page) {
      if (this.$route.name !== page) {
        this.$emit('input', page);
        this.$router.push({ name: page });
      }
    }
  }
};
</script>
<style scoped>
.publish-btn {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.publish-text {
  font-weight: bold;
  font-size: 14px; /* 可以根据需要调整 */
}

.publish-icon {
  font-size: 36px; /* 增大图标 */
}
</style>

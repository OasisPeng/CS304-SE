<template>
  <v-bottom-navigation v-model="selectedPage" color="teal" grow>
    <v-btn value="home" @click="navigateTo('home')">
      <span>首页</span>
      <v-icon>mdi-home-heart</v-icon>
    </v-btn>

    <v-btn value="market" @click="navigateTo('market')">
      <span>市场</span>
      <v-icon>mdi-storefront-outline</v-icon>
    </v-btn>

    <v-btn value="messages" @click="navigateTo('messages')">
      <span>消息</span>
      <v-icon>mdi-chat-outline</v-icon>
    </v-btn>
    <v-btn value="Person" @click="navigateTo('Person')">
      <span>我的</span>
      <v-icon>mdi-account</v-icon>
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
      this.$emit('input', page);
      this.$router.push({ name: page });
    }
  }
};
</script>

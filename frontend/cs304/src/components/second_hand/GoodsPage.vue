<template>
  <v-container>
    <v-app-bar app color="white" flat>
      <v-btn icon @click="goBack">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-toolbar-title>商品详情</v-toolbar-title>
    </v-app-bar>

    <v-card class="mx-auto my-5" max-width="600">
      <v-img :src="product.image" aspect-ratio="1.7"></v-img>
      <v-card-title>{{ product.name }}</v-card-title>
      <v-card-subtitle class="pb-0">分类: {{ product.category }}</v-card-subtitle>
      <v-card-subtitle>发布时间: {{ formatDate(product.publishDate) }}</v-card-subtitle>
      <v-card-text>
        <div class="text-h5 font-weight-bold mb-2">¥{{ product.price }}</div>
        <div class="mb-3">{{ product.description }}</div>
        <v-divider></v-divider>
        <div class="mt-3">卖家ID: {{ product.sellerId }}</div>
        <div>买家ID: {{ product.buyerId }}</div>
      </v-card-text>
      <v-card-actions>
        <v-btn color="green" @click="buyProduct">购买</v-btn>
        <v-btn text @click="contactSeller">联系卖家</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      product: {
        id: 1,
        name: '示例商品',
        price: 299.99,
        image: 'https://via.placeholder.com/600',
        sellerId: 'seller123',
        buyerId: 'buyer456',
        description: '这是一件非常好的商品，功能齐全，品质优良。',
        category: '电子产品',
        publishDate: new Date()
      },
      websocket: null
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    buyProduct() {
    },
    contactSeller() {
      const sellerId = this.product.sellerId;
      this.websocket = new WebSocket(this.$httpUrl + `/chatroom/${sellerId}`);
      this.websocket.onopen = () => {
        console.log('WebSocket connection established');
      };
      this.websocket.onmessage = (event) => {
        const message = JSON.parse(event.data);
        console.log('Received message:', message);
      };
      this.websocket.onclose = () => {
        console.log('WebSocket connection closed');
      };
      this.websocket.onerror = (error) => {
        console.error('WebSocket error:', error);
      };
    },
    formatDate(date) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(date).toLocaleDateString(undefined, options);
    }
  }
};
</script>

<style scoped>
.v-container {
  padding-top: 64px;
}

.v-card-title {
  font-size: 24px;
  font-weight: bold;
}

.v-card-subtitle {
  font-size: 16px;
  color: gray;
}

.v-card-text {
  font-size: 14px;
}

.v-btn {
  margin-right: 10px;
}
</style>

<template>
  <v-container>
    <v-app-bar app color="white" flat>
      <v-btn icon @click="goBack">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-toolbar-title>商品详情</v-toolbar-title>
    </v-app-bar>

    <v-card class="mx-auto my-5" max-width="600">
      <v-img :src="product.image"></v-img>
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
      <v-btn
          v-if="isSeller"
          color="red"
          fab
          bottom
          right
          @click="removeProduct"
          class="fixed-bottom-right"
      >
        <v-icon>mdi-delete</v-icon>
      </v-btn>
      <div v-if="isSeller" class="remove-text">下架该商品</div>
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
        image: '',
        sellerId: 'Aaa',
        buyerId: 'Bbb',
        description: '好东西',
        category: 'book',
        publishDate: new Date()
      },
      websocket: null,
      currentUser: JSON.parse(localStorage.getItem('info')).username,
      isSeller: false
    };
  },
  async created() {
    const productId = this.$route.params.id;
    try {
      const response = await this.$axios.get(this.$httpUrl + '/goods/' + productId, {
        withCredentials: false,
        headers: {
          'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
        },
      });
      const productData = response.data.data;
      this.product = {
        id: productData.id,
        name: productData.name,
        price: productData.price,
        image: productData.image,
        sellerId: productData.sellerId,
        buyerId: productData.buyerId,
        description: productData.description,
        category: productData.category,
        publishDate: productData.publishDate
      };
      // 检查当前用户是否是卖家
      this.isSeller = this.product.sellerId === this.currentUser;
    } catch (error) {
      console.error('Error fetching product details:', error);
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    buyProduct() {
      // 购买逻辑
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
    async removeProduct() {
      try {
        console.log(this.product)
        const response = await this.$axios.post(this.$httpUrl + '/goods/del', this.product, {
          headers: {
            'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
          }
        });
        if (response.data.msg === '成功') {
          this.$router.push('/FirstPage');
        } else {
          console.error('商品下架失败');
        }
      } catch (error) {
        console.error('Error removing product:', error);
      }
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
  color: rgb(128, 128, 128);
}

.v-card-text {
  font-size: 14px;
}

.v-btn {
  margin-right: 10px;
}

.fixed-bottom-right {
  position: fixed;
  bottom: 35px;
  right: 20px;
  z-index: 10;
}

.remove-text {
  position: fixed;
  bottom: 15px; /* Adjust the position as needed */
  right: 20px; /* Adjust the position as needed */
  font-size: 12px;
  color: red;
  text-align: center;
}

</style>

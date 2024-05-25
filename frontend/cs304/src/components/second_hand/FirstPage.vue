<template>
  <v-container class="custom-tabs-container" style="height: 100vh !important;">
    <v-form>
      <v-container class="custom-container">
        <v-row style="padding-bottom: 0 !important">
          <v-col cols="12" style="padding-bottom: 0 !important">
            <v-text-field
                v-model="message"
                outlined
                clearable
                label="🔍搜索相关商品"
                type="text"
            >
            </v-text-field>
          </v-col>
        </v-row>
        <v-carousel hide-delimiters style="height: 150px; border-radius: 20px; padding-top: 0">
          <v-carousel-item
              v-for="(item, i) in items"
              :key="i"
              :src="item.src"
              cover
          ></v-carousel-item>
        </v-carousel>
      </v-container>

      <v-container>
        <v-row justify="center" align="center" class="button-row">
          <v-col v-for="(button, index) in buttons" :key="index" class="button-col">
            <v-btn icon @click="button.action" color="green" class="button">
              <v-icon large>{{ button.icon }}</v-icon>
            </v-btn>
            <div class="button-label">{{ button.label }}</div>
          </v-col>
        </v-row>
      </v-container>

      <v-card style="padding-top: 0 !important">
        <v-tabs color="green" centered>
          <v-tab>电子产品</v-tab>
          <v-tab>书籍</v-tab>
          <v-tab>食物</v-tab>
          <v-tab>其它</v-tab>
          <v-tab-item v-for="n in 4" :key="n">
            <v-container fluid>
              <div class="product-list-container">
                <v-row>
                  <v-col cols="12" sm="6" md="4" lg="3" v-for="(product, index) in product" :key="index">
                    <v-card class="product-card" outlined>
                      <v-img :src="product.image" aspect-ratio="1.5">
                        <template v-slot:placeholder>
                          <v-row class="fill-height ma-0" align="center" justify="center">
                            <v-progress-circular indeterminate color="green"></v-progress-circular>
                          </v-row>
                        </template>
                      </v-img>
                      <v-card-text>
                        <div class="product-info">
                          <div>
                            <div class="product-name">{{ product.name }}</div>
                            <div class="product-price">{{ product.price }}</div>
                          </div>
                          <div class="product-seller-info">
                            <div class="product-seller">{{ product.seller }}</div>
                            <div v-if="product.soldOut" class="sold-out-label">已售出</div>
                          </div>
                        </div>
                      </v-card-text>
                      <v-card-actions>
                        <v-btn icon @click="toggleFavorite(product)" class="position">
                          <v-icon :color="product.isFavorite ? 'green' : 'white'">mdi-heart</v-icon>
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-col>
                </v-row>
              </div>
            </v-container>
          </v-tab-item>
        </v-tabs>
      </v-card>
      <BottomNavigation :value="selectedPage" />
    </v-form>
  </v-container>
</template>

<script setup>
import BottomNavigation from '@/components/second_hand/BottomNavigation.vue';

const items = [
  {
    src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
  },
  {
    src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
  },
  {
    src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
  },
  {
    src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
  },
];

// const user = {
//   initials: 'JD',
//   fullName: 'John Doe',
//   email: 'john.doe@doe.com',
// };

// const editAccount = () => {
//   console.log('Edit Account clicked');
// };
//
// const disconnect = () => {
//   console.log('Disconnect clicked');
// };

const toggleFavorite = (product) => {
  product.isFavorite = !product.isFavorite;
};
</script>

<script>
export default {
  components: {
    BottomNavigation
  },
  data() {
    return {
      selectedPage: 'home',
      items: [
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
        },
      ],
      buttons: [
        {
          icon: 'mdi-book-open-page-variant',
          label: '购买书籍',
          action: this.buy_book,
        },
        {
          icon: 'mdi-monitor-screenshot',
          label: '购买设备',
          action: this.buy_electric,
        },
        {
          icon: 'mdi-cart-variant',
          label: '购买其他',
          action: this.buy_others,
        },
        {
          icon: 'mdi-point-of-sale',
          label: '出售物品',
          action: this.sale,
        },
      ],
      product: [
        { image: 'https://via.placeholder.com/150', name: '商品1', price: '$10', seller: '卖家1', soldOut: false, isFavorite: false },
        { image: 'https://via.placeholder.com/150', name: '商品2', price: '$20', seller: '卖家2', soldOut: true, isFavorite: true },
        { image: 'https://via.placeholder.com/150', name: '商品3', price: '$15', seller: '卖家3', soldOut: false, isFavorite: false },
        { image: 'https://via.placeholder.com/150', name: '商品4', price: '$25', seller: '卖家4', soldOut: true, isFavorite: true },
      ]
    }
  },
  methods: {
    buy_book() {
      // Handle buy_book action
    },
    buy_electric() {
      // Handle buy_electric action
    },
    buy_others() {
      // Handle buy_others action
    },
    sale() {
      // Handle sale action
    },
    toggleFavorite(product) {
      product.isFavorite = !product.isFavorite;
    },
  },
}
</script>

<style scoped>
.button-row {
  text-align: center;
  padding-bottom: 0 !important;
}

.button-col {
  display: inline-block;
  margin: 0 5px; /* 设置图标之间的间距 */
}
.button-col {
  text-align: center;
  margin-bottom: 2px; /* 控制按钮和文字之间的间隔 */
}

.button {
  margin-bottom: 5px; /* 控制按钮和文字之间的间隔 */
}
.button-label {
  font-size: 14px;
  font-weight: bold; /* 将文字加粗 */
}

.product-list-container {
  max-height: 390px; /* 固定窗口高度 */
  overflow-y: auto; /* 允许垂直滚动 */
}

.product-card {
  margin-bottom: 20px;
  position: relative;
}

.product-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.product-name {
  font-weight: bold;
}

.product-price {
  color: green;
}

.product-seller-info {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.product-seller {
  color: grey;
}

.sold-out-label {
  color: red;
  font-weight: bold;
}
.position {
  position: absolute;
  top: 5px;
  right: 5px;
}

</style>

<style lang="scss">
.custom-container {
  padding-top: 0 !important; /* 上边距 */
  padding-bottom: 0 !important; /* 下边距 */
}
.v-bottom-navigation {
  position: fixed;
  bottom: 0;
  width: 100%;
}
</style>

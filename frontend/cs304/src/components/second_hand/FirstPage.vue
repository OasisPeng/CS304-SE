<template>
  <v-container class="custom-tabs-container" style="height: 100vh !important;">
    <v-form>
      <v-container class="custom-container">
        <v-row style="padding-bottom: 0 !important">
          <v-col cols="12" style="padding-bottom: 0 !important">
            <v-text-field
                v-model="searchText"
                outlined
                clearable
                label="🔍搜索相关商品"
                type="text"
            >
              <template v-slot:append>
                <v-btn icon @click="searchAndNavigateToMarketPage" color="green" text outlined style="margin-top: -5px">
                  <v-icon>mdi-magnify</v-icon>
                </v-btn>
              </template>
            </v-text-field>
          </v-col>
        </v-row>
        <v-carousel hide-delimiters style="height: 150px; border-radius: 20px; padding-top: 0">
          <v-carousel-item
              v-for="(item, i) in items"
              :key="i"
          >
            <v-img :src="item.src" contain>
              <template v-slot:placeholder>
                <v-row class="fill-height ma-0" align="center" justify="center">
                  <v-progress-circular indeterminate color="green"></v-progress-circular>
                </v-row>
              </template>
            </v-img>
          </v-carousel-item>
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
          <v-tab v-for="(category, index) in categories" :key="index">{{ category.label }}</v-tab>
          <v-tab-item v-for="(category, index) in categories" :key="index">
            <v-container fluid>
              <div class="product-list-container">
                <v-row>
                  <v-col cols="12" sm="6" md="4" lg="3" v-for="(product, index) in category.products" :key="index">
                    <v-card class="product-card" outlined @click="goToProductDetail(product.id)">
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
<!--                          <v-icon :color="product.isFavorite ? 'green' : 'white'">mdi-heart</v-icon>-->
                          <v-icon :color="green">mdi-heart</v-icon>
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

    <!-- Dialog for displaying message -->
    <v-snackbar
        v-model="snackbar"
        vertical
    >
      <div class="text-subtitle-1 pb-2">行为准则</div>
      <p>诚信交易</p>
      <v-btn
          color="indigo"
          text
          @click="snackbar = false"
      >
        Close
      </v-btn>
    </v-snackbar>

  </v-container>
</template>

<script setup>
import BottomNavigation from '@/components/second_hand/BottomNavigation.vue';
import router from "@/router";
import { ref } from "vue";
import bookImage from "@/assets/book.jpeg";
import computerImage from "@/assets/computer.jpeg";
import foodImage from "@/assets/food.jpeg";

const searchText = ref('');

const searchAndNavigateToMarketPage = () => {
  console.log('Searching for:', searchText.value);
  router.push({ name: 'MarketPage', query: { search: searchText.value } });
};

const toggleFavorite = (product) => {
  product.isFavorite = !product.isFavorite;
};

const items = [
  { src: bookImage },
  { src: computerImage },
  { src: foodImage },
];

const dialog = ref(false);

const buy_book = () => {
  dialog.value = true;
};
</script>

<script>
export default {
  components: {
    BottomNavigation
  },
  data() {
    return {
      selectedPage: 'FirstPage',
      snackbar: false,
      buttons: [
        {
          icon: 'mdi-book-open-page-variant',
          label: '交易需知',
          action: this.buy_book,
        },
        {
          icon: 'mdi-monitor-screenshot',
          label: '购买列表',
          action: this.buy_electric,
        },
        {
          icon: 'mdi-cart-variant',
          label: '交易列表',
          action: this.buy_others,
        },
        {
          icon: 'mdi-point-of-sale',
          label: '出售物品',
          action: this.sale,
        },
      ],
      categories: [
        { label: '书籍', products: [] },
        { label: '电子产品', products: [] },
        { label: '食物', products: [] },
        { label: '其他', products: [] }
      ]
    }
  },
  methods: {
    goToProductDetail(productId) {
      this.$router.push({ name: 'GoodsPage', params: { id: productId } });
    },
    buy_book() {
      this.snackbar = true; // Show the dialog
    },
    buy_electric() {
      this.$router.push('/BuyPage')
    },
    buy_others() {
      this.$router.push('/MessagesPage')
    },
    sale() {
      this.$router.push('/ReleasePage')
    },
    toggleFavorite(product) {
      product.isFavorite = !product.isFavorite;
    },
    async fetchCategory(categoryIndex, endpoint) {
      try {
        const response = await this.$axios.get(this.$httpUrl + endpoint, {
          withCredentials: false,
          headers: {
            'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
          },
        });
        const products = response.data.data.map(evo => {
          return {
            id: evo.id || "",
            name: evo.name || "",
            price: evo.price || "",
            image: evo.image || "",
            seller: evo.sellerId || "",
            buyerId: evo.buyerId || "",
            description: evo.description || "",
            category: evo.category || "",
            publishDate: evo.publishDate || "",
            soldOut: evo.buyerId !== ""
          };
        });
        this.categories[categoryIndex].products = products;
      } catch (error) {
        console.error('Error querying category:', error);
      }
    },
    async fetchData() {
      await Promise.all([
        this.fetchCategory(0, '/goods/category/book'),
        this.fetchCategory(1, '/goods/category/device'),
        this.fetchCategory(2, '/goods/category/food'),
        this.fetchCategory(3, '/goods/category/other')
      ]);
      console.log("Categories:", this.categories);
    }
  },
  mounted() {
    this.fetchData();
  }
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
  height: 385px; /* 固定窗口高度 */
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

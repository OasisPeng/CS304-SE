<script >
export default {
  data() {
    return {
      selectedPage: 'Person',
      product: []
    };
  },
  methods :{
    goToProductDetail(productId) {
      this.$router.push({ name: 'GoodsPage', params: { id: productId } });
    },
    async fetchCategory() {
      try {
        const response = await this.$axios.get(this.$httpUrl + '/goods/buyer/'+JSON.parse(localStorage.getItem('info')).username, {
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
        this.product = products;
      } catch (error) {
        console.error('Error querying category:', error);
      }
    },
  },
  mounted() {
    this.fetchCategory();
  }
}

</script>

<script setup>
import BottomNavigation from "@/components/second_hand/BottomNavigation.vue";
import router from "@/router";

const toggleFavorite = (product) => {
  product.isFavorite = !product.isFavorite;
};
const goBack = () => {
  router.push('/person');
};
</script>
<template>

  <v-container class="custom-tabs-container" style="height: 100vh !important;">
    <v-form>

      <v-row >
        <v-col cols="12" >
          <v-btn icon @click="goBack" class="position" x-large >
            <v-icon color="black">mdi-arrow-left</v-icon>
          </v-btn>
        </v-col>
      </v-row>

      <v-card style="padding-top: 0 !important; margin-top: 50px;"> <!-- 添加了 margin-top -->
        <v-tabs color="green" centered>
          <v-tab>我购买的物品</v-tab>
          <v-tab-item>
            <v-container fluid>
              <div class="product-list-container">
                <v-row>
                  <v-col cols="12" v-if="product.length === 0">
                    <!-- 占位符，保持容器的宽度 -->
                  </v-col>
                  <v-col cols="12" v-for="(item, index) in product" :key="index">
                    <v-card class="product-card" outlined @click="goToProductDetail(product.id)">
                      <v-img :src="item.image" aspect-ratio="1.5">
                        <template v-slot:placeholder>
                          <v-row class="fill-height ma-0" align="center" justify="center">
                            <v-progress-circular indeterminate color="green"></v-progress-circular>
                          </v-row>
                        </template>
                      </v-img>
                      <v-card-text>
                        <div class="product-info">
                          <div>
                            <div class="product-name">{{ item.name }}</div>
                            <div class="product-price">{{ item.price }}</div>
                          </div>
                          <div class="product-seller-info">
                            <div class="product-seller">{{ item.seller }}</div>
                            <div v-if="item.soldOut" class="sold-out-label">已售出</div>
                          </div>
                        </div>
                      </v-card-text>
                      <v-card-actions>
                        <v-btn icon @click="toggleFavorite(item)">
                          <v-icon color="green">mdi-heart</v-icon>

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
      <BottomNavigation v-model="selectedPage" />
    </v-form>
  </v-container>

</template>

<style scoped>


.product-list-container {
  height: 650px; /* 固定窗口高度 */
  overflow-y: auto; /* 允许垂直滚动 */
  width: 400px;
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
  top: 10px;
  left: 5px;

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
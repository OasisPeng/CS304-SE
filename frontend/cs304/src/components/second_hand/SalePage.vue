<script >
export default {
  data() {
    return {
      selectedPage: 'Person',
      product: [
        { image: 'https://via.placeholder.com/150', name: '商品1', price: '$10', seller: '卖家1', soldOut: false, isFavorite: false },
        { image: 'https://via.placeholder.com/150', name: '商品2', price: '$20', seller: '卖家2', soldOut: true, isFavorite: true },
        { image: 'https://via.placeholder.com/150', name: '商品3', price: '$15', seller: '卖家3', soldOut: false, isFavorite: false },
        { image: 'https://via.placeholder.com/150', name: '商品4', price: '$25', seller: '卖家4', soldOut: true, isFavorite: true },
      ]
    };
  },

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
          <v-tab>我卖出的物品</v-tab>
          <v-tab-item>
            <div class="product-list-container">
              <v-container fluid>
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
              </v-container>
            </div>
          </v-tab-item>
        </v-tabs>
      </v-card>
      <BottomNavigation v-model="selectedPage" />
    </v-form>
  </v-container>

</template>

<style scoped>


.product-list-container {
  max-height: 650px; /* 固定窗口高度 */
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
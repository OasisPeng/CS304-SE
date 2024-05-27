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

      </v-container>

      <v-container>

        <v-select
            v-model="selectedItem"
            :items="items"
            item-text="state"
            item-value="state"
            label="筛选栏"
            return-object
            class="select"
        ></v-select>
      </v-container>

      <v-card style="padding-top: 0 !important">
        <v-tabs color="green" centered>
          <v-tab>电子产品</v-tab>
          <v-tab>书籍</v-tab>
          <v-tab>食物</v-tab>
          <v-tab>所有</v-tab>
          <v-tab-item v-for="n in 4" :key="n">
            <v-container fluid>
              <div class="product-list-container">
                <v-row>
                  <v-col cols="12" sm="6" md="4" lg="3" v-for="(product, index) in displayedProducts" :key="index">
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

      <v-pagination
          v-model="page"
          :length="totalPages"
          color="green"
          circle
      ></v-pagination>
      <BottomNavigation :value="selectedPage" />
    </v-form>
  </v-container>
</template>

<script>
import BottomNavigation from "@/components/second_hand/BottomNavigation.vue";

export default {
  components: {BottomNavigation},
  data() {
    return {
      searchText: '',
      selectedPage: 'MarketPage',
      selectedItem: null,
      items: [
        { state: 'Price' },
        { state: 'Favourite' },
        { state: 'Unsale' },
      ],
      product: [
        { image: 'https://via.placeholder.com/150', name: '商品1', price: '$10', seller: '卖家1', soldOut: false, isFavorite: false },
        { image: 'https://via.placeholder.com/150', name: '商品2', price: '$20', seller: '卖家2', soldOut: true, isFavorite: true },
        { image: 'https://via.placeholder.com/150', name: '商品3', price: '$15', seller: '卖家3', soldOut: false, isFavorite: false },
        { image: 'https://via.placeholder.com/150', name: '商品4', price: '$25', seller: '卖家4', soldOut: true, isFavorite: true },
        // Add more products as needed
      ],
      page: 1, // Current page
      itemsPerPage: 10, // Number of items per page
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.product.length / this.itemsPerPage);
    },
    displayedProducts() {
      const startIndex = (this.page - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.product.slice(startIndex, endIndex);
    },
  },
  watch: {
    $route(to) {
      if (to.query.search) {
        this.searchText = to.query.search;
        // Perform search logic here
        console.log('Searching for:', this.searchText);
      }
    }
  },
  methods: {
    searchAndNavigateToMarketPage() {
      this.$router.push({ name: 'MarketPage', query: { search: this.searchText } });
    },
    toggleFavorite(product) {
      product.isFavorite = !product.isFavorite;
    },
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.query.search) {
        vm.searchText = to.query.search;
        // Perform search logic here
        console.log('Searching for:', vm.searchText);
      }
    });
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
  margin: 0 5px;
}

.button-col {
  text-align: center;
  margin-bottom: 2px;
}

.button {
  margin-bottom: 5px;
}

.button-label {
  font-size: 14px;
  font-weight: bold;
}

.product-list-container {
  max-height: 500px;
  overflow-y: auto;
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
.select{
  margin-top:-20px !important;
}
</style>

<style lang="scss">
.custom-container {
  padding-top: 0 !important;
  padding-bottom: 0 !important;
}

.v-bottom-navigation {
  position: fixed;
  bottom: 0;
  width: 100%;
}

</style>

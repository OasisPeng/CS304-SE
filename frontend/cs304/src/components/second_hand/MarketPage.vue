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
            @change="applyFilter"
        ></v-select>
      </v-container>

      <v-card style="padding-top: 0 !important">
        <v-tabs v-model="activeCategoryIndex" color="green" centered>
          <v-tab v-for="(category, index) in categories" :key="index">{{ category.label }}</v-tab>
          <v-tab-item v-for="(category, index) in categories" :key="index">
            <v-container fluid>
              <div class="product-list-container">
                <v-row>
                  <v-col cols="12" sm="6" md="4" lg="3" v-for="(product, productIndex) in displayedProducts(category.products, index)" :key="productIndex">
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

      <v-pagination
          v-model="page"
          :length="totalPages(categories[activeCategoryIndex].products)"
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
  components: { BottomNavigation },
  data() {
    return {
      searchText: '',
      selectedPage: 'MarketPage',
      selectedItem: null,
      items: [
        { state: 'Price' },
        { state: 'Unsale' },
      ],
      categories: [
        { label: '书籍', products: [] },
        { label: '电子产品', products: [] },
        { label: '食物', products: [] },
        { label: '所有', products: [] }
      ],
      filter: [
        { label: '书籍', products: [] },
        { label: '电子产品', products: [] },
        { label: '食物', products: [] },
        { label: '所有', products: [] }
      ],
      page: 1, // Current page
      itemsPerPage: 10, // Number of items per page
      activeCategoryIndex: 0, // The index of the active category
    };

  },

  computed: {
    totalPages() {
      return (products) => Math.ceil(products.length / this.itemsPerPage);
    },
    displayedProducts() {
      return (products, index) => {
        if (index === this.activeCategoryIndex) {
          const startIndex = (this.page - 1) * this.itemsPerPage;
          const endIndex = startIndex + this.itemsPerPage;
          return products.slice(startIndex, endIndex);
        }
        return [];
      };
    }
  },
  watch: {
    $route(to) {
      if (to.query.search) {
        this.searchText = to.query.search;
        // Perform search logic here
        console.log('Searching for:', this.searchText);
      }
    },
    activeCategoryIndex() {
      this.page = 1; // Reset to the first page when the category changes
    }
  },
  methods: {
    goToProductDetail(productId) {
      this.$router.push({ name: 'GoodsPage', params: { id: productId } });
    },
    async searchAndNavigateToMarketPage() {
      if (this.$route.name !== 'MarketPage' || this.$route.query.search !== this.searchText) {
        await this.$router.push({name: 'MarketPage', query: {search: this.searchText}});
      }
      await this.search_products();
      this.activeCategoryIndex = 3; // Automatically switch to the "所有" tab
    },
    toggleFavorite(product) {
      product.isFavorite = !product.isFavorite;
    },
    async search_products() {
      try {
        const response = await this.$axios.get(this.$httpUrl + '/goods/search', {
          params: {
            keyword: this.searchText
          },
          withCredentials: false,
          headers: {
            'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
          }
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
            soldOut: evo.buyerId !== null
          };
        });

        console.log("sb", products);
        this.categories[3].products = products;
      } catch (error) {
        console.error('Error querying category:', error);
      }
    },
    applyFilter() {
      // Create a deep copy of this.filter to ensure it doesn't get mutated
      this.categories = JSON.parse(JSON.stringify(this.filter));

      switch (this.selectedItem.state) {
        case 'Price':
          this.categories.forEach(category => {
            category.products.sort((a, b) => a.price - b.price);
          });
          break;
        case 'Unsale':
          this.categories.forEach(category => {
            category.products = category.products.filter(product => !product.soldOut);
          });
          break;
        default:
          // Do nothing for other filters
          break;
      }
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
            soldOut: evo.buyerId !== null
          };
        });
        this.categories[categoryIndex].products = products;
        this.filter[categoryIndex].products = products;
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
      console.log("Categories:", this.product);
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
  height: 500px;
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

.select {
  margin-top: -20px !important;
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

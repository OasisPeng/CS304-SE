<template>
  <v-container fluid class="d-flex flex-column fill-height">
    <!-- 其他内容 -->
    <v-card class="mx-auto" max-width="100%">
      <v-img
          class="align-end text-white"
          height="200"
          src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
          cover
      >
        <v-row justify="left" class="touxiang">
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon v-bind="attrs" v-on="on">
                <v-avatar>
                  <v-icon color="white" size="60px">mdi-account-circle</v-icon>
                </v-avatar>
              </v-btn>
            </template>
            <v-card>
              <v-card-text>
                <div class="mx-auto text-center">
                  <v-avatar color="brown">
                    <span class="text-h5">{{ user.initials }}</span>
                  </v-avatar>
                  <h3>{{ user.fullName }}</h3>
                  <p class="text-caption mt-1">{{ user.email }}</p>
                  <v-divider class="my-3"></v-divider>
                  <v-btn variant="text" rounded @click="editAccount">
                    Edit Account
                  </v-btn>
                  <v-divider class="my-3"></v-divider>
                  <v-btn variant="text" rounded @click="disconnect">
                    Disconnect
                  </v-btn>
                </div>
              </v-card-text>
            </v-card>
          </v-menu>
          <v-col>
            <h3 class="name">{{ user.fullName }}</h3>
          </v-col>
        </v-row>
      </v-img>

      <v-card-subtitle class="pt-4">
        个人简介
      </v-card-subtitle>

      <v-card-text>
        <div>{{ user.bio }}</div>
      </v-card-text>

      <v-card-actions>
        <v-btn color="orange" text>Share</v-btn>
        <v-btn color="orange" text>Explore</v-btn>
      </v-card-actions>
    </v-card>

    <v-form>
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
    </v-form>

    <v-card style="padding-top: 0 !important">
      <v-tabs color="green" centered>
        <v-tab>我上传的物品</v-tab>
        <v-tab-item>
          <div class="product-list-container">
            <v-container fluid>
              <v-row>
                <v-col cols="12" sm="6" md="4" lg="3" v-for="(product, index) in product" :key="index">
                  <v-card class="product-card" outlined>
                    <v-img :src="product.image" aspect-ratio="1.5"></v-img>
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
                      <v-btn icon @click="toggleFavorite(product)">
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

    <!-- 底部导航栏 -->
    <BottomNavigation v-model="selectedPage" />

    <!-- 编辑账户信息的对话框 -->
    <v-dialog v-model="editDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">编辑账户信息</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6">
                <v-text-field v-model="editedUser.initials" label="Initials"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field v-model="editedUser.fullName" label="Full Name"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field v-model="editedUser.email" label="Email"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-textarea v-model="editedUser.bio" label="个人简介"></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeEditDialog">取消</v-btn>
          <v-btn color="blue darken-1" text @click="saveEdit">保存</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script setup>
import BottomNavigation from "@/components/second_hand/BottomNavigation.vue";
import { ref } from 'vue';

const user = ref({
  initials: 'JD',
  fullName: 'John Doe',
  email: 'john.doe@doe.com',
  bio: '我绝对不是黄牛',
});

const editDialog = ref(false);

const editedUser = ref({ ...user.value });

const editAccount = () => {
  editedUser.value = { ...user.value };
  editDialog.value = true;
};

const closeEditDialog = () => {
  editDialog.value = false;
};

const saveEdit = () => {
  user.value = { ...editedUser.value };
  editDialog.value = false;
};

const disconnect = () => {
  console.log('Disconnect clicked');
};

const toggleFavorite = (product) => {
  product.isFavorite = !product.isFavorite;
};
</script>

<script>


export default {
  data() {
    return {
      selectedPage: 'Person',
      buttons: [
        {
          icon: 'mdi-cart-percent',
          label: '购买',
          action: () => { this.$router.push('/BuyPage'); },
        },

        {
          icon: 'mdi-monitor-screenshot',
          label: '卖出',
          action: () => { this.$router.push('/SalePage');},
        },
        {
          icon: 'mdi-heart',
          label: '收藏',
          action: () => { this.$router.push('/FavouritePage'); },
        },
        {
          icon: 'mdi-message-badge-outline',
          label: '聊天',
          action: () => { console.log('聊天 clicked') },
        },
      ],
      product: [
        { image: 'https://via.placeholder.com/150', name: '商品1', price: '$10', seller: '卖家1' },
        { image: 'https://via.placeholder.com/150', name: '商品2', price: '$20', seller: '卖家2' },
        { image: 'https://via.placeholder.com/150', name: '商品3', price: '$15', seller: '卖家3' },
        { image: 'https://via.placeholder.com/150', name: '商品4', price: '$25', seller: '卖家4' },
      ]
    };
  },
}
</script>

<style scoped>
.fill-height {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.flex-grow-1 {
  flex-grow: 1;
}

.v-bottom-navigation {
  position: fixed;
  bottom: 0;
  width: 100%;
}

.touxiang {
  padding-top: 20px !important;
  margin-left: 10px !important;
  position: absolute;
  top: 10px;
  left: 10px;
}

.name {
  padding-left: 10px !important;
  margin-top: -10px !important;
  font-size: 25px;
  color: #e9ecef;
}

.v-card-title {
  margin-left: 80px !important;
  margin-top: 60px !important;
}

.button-row {
  text-align: center;
  padding-bottom: 0 !important;
}

.button-col {
  display: inline-block;
  margin: 0 15px; /* 设置图标之间的间距 */
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
  max-height: 400px; /* 固定窗口高度 */
  overflow-y: auto; /* 允许垂直滚动 */
}

.product-card {
  margin-bottom: 20px;
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
  text-align: right;
}

.product-seller {
  color: grey;
}

.sold-out-label {
  color: red;
}
</style>

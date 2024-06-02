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
        <v-row justify="start" class="touxiang">
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
      <v-container fluid>
        <v-row  align="center" class="button-row">
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
          <v-container fluid>
            <div class="product-list-container">
              <v-row>
                <v-col cols="12" v-if="product.length === 0">
                  <!-- 占位符，保持容器的宽度 -->
                </v-col>
                <v-col cols="12" v-for="(item, index) in product" :key="index">
                  <v-card class="product-card" outlined>
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

    <!-- 底部导航栏 -->


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
    <BottomNavigation v-model="selectedPage" />
  </v-container>
</template>

<script setup>
import BottomNavigation from "@/components/second_hand/BottomNavigation.vue";
import { ref } from 'vue';

const user = ref({
  initials: 'St',
  fullName: JSON.parse(localStorage.getItem('info')).username,
  email: JSON.parse(localStorage.getItem('info')).username+'@sustech.edu.cn',
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



const toggleFavorite = (product) => {
  product.isFavorite = !product.isFavorite;
};
</script>

<script>


import router from "@/router";

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
          label: '想要',
          action: () => { this.$router.push('/FavouritePage'); },
        },
        {
          icon: 'mdi-message-badge-outline',
          label: '聊天',
          action: () => { this.$router.push('/MessagesPage'); },
        },
      ],
      product: []
    };
  },
  methods :{
    disconnect() {
      this.$router.push('/');
    },
    async fetchCategory() {
      try {
        const response = await this.$axios.get(this.$httpUrl + '/goods/seller/'+JSON.parse(localStorage.getItem('info')).username, {
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
    this.interval = setInterval(() => {
      this.fetchCategory();
    }, 10000); // 调用fetchCategory方法每10秒更新一次
  },
  beforeDestroy() {
    clearInterval(this.interval); // 清除定时器
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
  height: 400px;
  overflow-y: auto;
  width: 400px;
}


.product-card {
  margin-bottom: 20px;
  position: relative;


}

.product-info {
  display: flex;
  align-items: center;
  justify-content: space-between; /* 让内容在行内两端对齐 */

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
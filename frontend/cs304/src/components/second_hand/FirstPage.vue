<script setup>
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
]
</script>

<script>
export default {
  data () {
    return {
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
      value: 'recents',
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
        { image: 'https://via.placeholder.com/150', name: '商品1', price: '$10', seller: '卖家1' },
        { image: 'https://via.placeholder.com/150', name: '商品2', price: '$20', seller: '卖家2' },
        { image: 'https://via.placeholder.com/150', name: '商品3', price: '$15', seller: '卖家3' },
        { image: 'https://via.placeholder.com/150', name: '商品4', price: '$25', seller: '卖家4' },
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
  },
}
</script>
<template>
  <v-container class="custom-tabs-container" style="height: 100vh !important; ">
  <v-form>
    <v-container class="custom-container">
      <v-row  style="padding-bottom: 0 !important">
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
      <v-carousel hide-delimiters style="height: 150px; border-radius: 20px; padding-top: 0 ">
        <v-carousel-item
            v-for="(item, i) in items"
            :key="i"
            :src="item.src"
            cover
        ></v-carousel-item>
      </v-carousel>
    </v-container>

    <v-form>
      <v-container>
        <v-row justify="center" align="center" class="button-row" >
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
      <v-tabs
          color="green"
          centered
      >
        <v-tab>电子产品</v-tab>
        <v-tab>书籍</v-tab>
        <v-tab>食物</v-tab>
        <v-tab>其它</v-tab>
        <v-tab-item v-for="n in 4" :key="n">
          <v-container fluid>
            <v-virtual-scroll

                :items="items"
                :item-height="400"
                :height="400"
                :loading="false"
            >

            <v-row>
              <v-col cols="12" sm="6" md="4" lg="3" v-for="(product, index) in product" :key="index">
                <v-card class="product-card" outlined>
                  <v-img :src="product.image" aspect-ratio="1.5">

                  </v-img>
                  <v-card-text>
                    <div class="product-name">{{ product.name }}</div>
                    <div class="product-price">{{ product.price }}</div>
                    <div class="product-seller">{{ product.seller }}</div>
                  </v-card-text>
                </v-card>
              </v-col>
            </v-row>

            </v-virtual-scroll>
          </v-container>
        </v-tab-item>

      </v-tabs>
    </v-card>
    <v-bottom-navigation :value="value" color="teal" grow>
      <v-btn>
        <span>首页</span>
        <v-icon>mdi-home-heart</v-icon>
      </v-btn>

      <v-btn>
        <span>市场</span>
        <v-icon>mdi-storefront-outline</v-icon>
      </v-btn>

      <v-btn>
        <span>消息</span>
        <v-icon>mdi-chat-outline</v-icon>
      </v-btn>
      <v-btn>
        <span>我的</span>
        <v-icon>mdi-account</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </v-form>
  </v-container>



</template>



<style scoped>
.button-row {
  text-align: center;
  padding-bottom: 0 !important
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
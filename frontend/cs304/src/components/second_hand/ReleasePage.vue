<template>
  <v-container>
    <v-app-bar app color="white" flat>
      <v-btn icon @click="goBack">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-toolbar-title>发布</v-toolbar-title>
    </v-app-bar>

    <v-row>
      <v-col cols="12">
        <v-text-field
            label="添加宝贝标题"
            outlined
            class="title-input"
            color="green"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <v-textarea
            label="描述型号，入手渠道，转手原因..."
            outlined
            rows="3"
            no-resize
            class="description-input"
            color="green"
        ></v-textarea>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" class="d-flex align-start">
        <div class="image-upload-section">
          <v-btn
              outlined
              class="image-upload-btn"
              @click="uploadImage"
          >
            <v-icon>mdi-plus</v-icon>
          </v-btn>
          <div class="image-upload-text">添加图片</div>
        </div>
        <div class="image-grid">
          <v-img
              v-for="(image, index) in images"
              :key="index"
              :src="image"
              class="image-thumbnail"
          ></v-img>
        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <v-expansion-panels>
          <v-expansion-panel>
            <v-expansion-panel-header>物品状况</v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-radio-group class="custom-radio-group" color="green">
                <v-radio label="全新" value="new"></v-radio>
                <v-radio label="二手" value="used"></v-radio>
              </v-radio-group>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <v-text-field label="价格" outlined color="green"></v-text-field>
      </v-col>
    </v-row>
    <v-row class="fixed-bottom">
      <v-col cols="12" class="text-center">
        <v-btn color="green" large>确认发布</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'ReleasePage',
  data() {
    return {
      images: []
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    uploadImage() {
      // 这里添加你的图片上传逻辑
      // 假设图片上传成功后，添加图片到 images 数组
      const newImage = 'path/to/new/image.jpg';
      this.images.push(newImage);
    }
  }
};
</script>

<style scoped>
.title-input .v-input__control {
  font-weight: bold;
  font-size: 20px; /* 调整字体大小 */
}

.description-input .v-input__control {
  border: none;
}

.image-upload-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-right: 10px;
}

.image-upload-btn {
  margin-bottom: 4px;
}

.image-upload-text {
  font-size: 12px;
  color: gray;
}

.image-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.image-thumbnail {
  width: 70px;
  height: 70px;
  object-fit: cover;
  border: 1px solid #ccc;
}

.fixed-bottom {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: white;
  padding: 10px 0;
  box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
}

/* 确保所有选中状态颜色为绿色 */
.custom-radio-group .v-radio .v-input--selection-controls__ripple--active {
  background-color: green !important;
}
.custom-radio-group .v-radio .v-input--selection-controls__input:checked + .v-input--selection-controls__ripple::before {
  border-color: green !important;
}
.custom-radio-group .v-radio .v-input--selection-controls__input:checked + .v-input--selection-controls__ripple::after {
  background-color: green !important;
}
</style>

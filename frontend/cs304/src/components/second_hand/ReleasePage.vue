<template>
  <v-container>
    <v-app-bar app color="white" flat>
      <v-btn icon @click="goBack">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-toolbar-title>发布</v-toolbar-title>
    </v-app-bar>

    <v-form ref="form" v-model="valid" lazy-validation>
      <v-row>
        <v-col cols="12">
          <v-text-field
              v-model="title"
              label="添加宝贝标题"
              outlined
              class="title-input"
              color="green"
              :rules="[rules.required]"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-textarea
              v-model="description"
              label="描述型号，入手渠道，转手原因..."
              outlined
              rows="3"
              no-resize
              class="description-input"
              color="green"
              :rules="[rules.required]"
          ></v-textarea>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-expansion-panels>
            <v-expansion-panel>
              <v-expansion-panel-header>物品种类</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-radio-group
                    v-model="condition"
                    class="custom-radio-group"
                    color="green"
                    :rules="[rules.required]"
                >
                  <v-radio label="书籍" value="book"></v-radio>
                  <v-radio label="电子产品" value="device"></v-radio>
                  <v-radio label="食物" value="food"></v-radio>
                  <v-radio label="其他" value="other"></v-radio>
                </v-radio-group>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-text-field
              v-model="price"
              label="价格"
              outlined
              color="green"
              :rules="[rules.required, rules.isNumber]"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" class="d-flex align-start">
          <div class="image-upload-section">
            <v-btn
                v-if="!image"
                outlined
                class="image-upload-btn"
                @click="triggerFileInput"
            >
              <v-icon>mdi-plus</v-icon>
            </v-btn>
            <div class="image-upload-text" v-if="!image">添加图片</div>
            <input
                ref="fileInput"
                type="file"
                @change="uploadImage"
                style="display: none"
            />
          </div>
          <div v-if="image" class="image-container">
            <v-img
                :src="image"
                class="image-thumbnail"
            >
              <template v-slot:placeholder>
                <v-row class="fill-height ma-0" align="center" justify="center">
                  <v-progress-circular indeterminate color="green"></v-progress-circular>
                </v-row>
              </template>
            </v-img>
            <v-btn icon small class="remove-image-btn" @click="removeImage">
              <v-icon color="red">mdi-close</v-icon>
            </v-btn>
          </div>
        </v-col>
      </v-row>
      <v-row class="fixed-bottom">
        <v-col cols="12" class="text-center">
          <v-btn
              color="green"
              large
              @click="submit"
              :disabled="!valid"
          >确认发布</v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<script>
export default {
  name: 'ReleasePage',
  data() {
    return {
      valid: false,
      title: '',
      description: '',
      condition: '',
      price: '',
      image: null,
      rules: {
        required: value => !!value || '此项为必填项',
        isNumber: value => !isNaN(value) || '请输入一个有效的数字'
      }
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    triggerFileInput() {
      this.$refs.fileInput.click();
    },
    async uploadImage(event) {
      const file = event.target.files[0];
      const formData = new FormData();
      formData.append('file', file);
      try {
        const response = await this.$axios.post(this.$httpUrl + '/goods/upload', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
            'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
          }
        });
        if (response.data.msg === '成功') {
          this.image = response.data.data;
        } else {
          console.error('图片上传失败');
        }
      } catch (error) {
        console.error('Error uploading image:', error);
      }
    },
    removeImage() {
      this.image = null;
    },
    async submit() {
      if (this.$refs.form.validate()) {
        const product = {
          name: this.title,
          description: this.description,
          category: this.condition === 'other' ? this.condition : this.condition,
          price: this.price,
          image: this.image,
          sellerId: JSON.parse(localStorage.getItem('info')).username,
          publishDate: new Date()
        };
        try {
          const response = await this.$axios.post(this.$httpUrl + '/goods/add', product, {
            headers: {
              'Authorization': `Bearer ${JSON.parse(localStorage.getItem('info')).token}`
            }
          });
          if (response.data.msg === '成功') {
            this.$router.push('/FirstPage');
          } else {
            console.error('商品发布失败');
          }
        } catch (error) {
          console.error('Error submitting product:', error);
        }
      }
    }
  }
};
</script>

<style scoped>
.title-input .v-input__control {
  font-weight: bold;
  font-size: 20px;
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

.image-container {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
}

.image-thumbnail {
  width: 100%;
  max-width: 300px;
  height: auto;
  border: 1px solid #ccc;
}

.remove-image-btn {
  position: absolute;
  top: 10px;
  right: 10px;
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

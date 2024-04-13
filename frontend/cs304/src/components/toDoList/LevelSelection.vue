<template>
  <v-app>
    <v-main>
      <v-container class="pa-3" fluid>
          
        <v-row class="mb-5" align="center" justify="center">
          <v-col cols="12" class="text-center">
            <v-icon large>mdi-alert-octagon-outline</v-icon>
            <h1>请选择紧急程度</h1>
          </v-col>
        </v-row>

        <v-row class="mb-5" align="center" justify="space-around">
          <v-col
            cols="12"
            md="6"
            v-for="level in levels"
            :key="level.name"
          >
            <v-btn
              block
              large
              :class="{ 'is-selected': selectedLevel === level.name }"
              :color="selectedLevel === level.name ? 'orange darken-2' : ''"
              class="level-btn"
              @click="selectLevel(level.name)"
            >
              {{ level.name }}
            </v-btn>
          </v-col>
        </v-row>

        <v-row align="end" justify="center">
          <v-col cols="12" class="text-center">
            <v-btn color="blue-grey" dark @click="goBack">退回上一步</v-btn>
            <v-btn color="orange" @click="confirm">确定</v-btn>
          </v-col>
        </v-row>

        <v-dialog v-model="dialog" max-width="300">
          <v-card>
            <v-card-title class="headline">啊喔</v-card-title>
            <v-card-text>
              请选择一个紧急程度。
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="orange" text @click="dialog = false">关闭</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      levels: [
        { name: "重要紧急" },
        { name: "重要不紧急" },
        { name: "不重要紧急" },
        { name: "不重要不紧急" },
      ],
      selectedLevel: null,
      dialog: false
    };
  },
  methods: {
    selectLevel(level) {
      this.selectedLevel = level;
    },
    confirm() {
      if (this.selectedLevel) {
        localStorage.setItem("level", this.selectedLevel);
        this.$router.push("/TodoEdit");
      } else {
        this.dialog = true;
      }
    },
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.level-btn {
  transition: transform 0.5s ease, opacity 0.3s ease, filter 0.3s ease;
  opacity: 0.9;
}

.level-btn:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 6px rgba(0,0,0,0.2);
  opacity: 1;
  filter: blur(0px);
}

.level-btn.is-selected {
  border: 2px solid currentColor;
  /* background-color: transparent; */
  background-color: primary;
  color: inherit;
  opacity: 0.7;
}

/* tab键导航时会触发 */
.level-btn:active,
.level-btn:focus{
  opacity: 0.9;
  filter: blur(0.5px);
}

@media (max-width: 600px) {
  .category-btn {
    margin-bottom: 20px;
  }
}

.text-center{
  opacity: 0.7;
}

.pa-3 {
  max-width: 500px;
  max-height: 1111px;
  background-image: url('../../assets/bg3.jpeg');
  background-size: cover;
}
</style>

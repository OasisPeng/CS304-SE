<template>
  <v-app>
    <v-main>
      <v-container class="pa-3" fluid>
          
        <v-row class="mb-5" align="center" justify="center">
          <v-col cols="12" class="text-center">
            <v-icon large>mdi-emoticon-happy-outline</v-icon>
            <h1>请选择您的心情</h1>
          </v-col>
        </v-row>

        <v-row class="mb-5" align="center" justify="space-around">
          <v-col
            cols="12"
            md="4"
            v-for="emotion in emotions"
            :key="emotion.name"
          >
            <v-btn
              block
              large
              :class="{ 'is-selected': selectedEmotion === emotion.name }"
              :color="selectedEmotion === emotion.name ? 'light-blue' : ''"
              class="emotion-btn"
              @click="selectEmotion(emotion.name)"
            >
              <v-icon left>{{ emotion.icon }}</v-icon>
              {{ emotion.name }}
            </v-btn>
          </v-col>
        </v-row>

        <v-row align="end" justify="center">
          <v-col cols="12" class="text-center">
            <v-btn color="blue-grey" dark @click="goBack">退回上一步</v-btn>
            <v-btn color="light-blue" @click="confirm">确定</v-btn>
          </v-col>
        </v-row>

        <v-dialog v-model="dialog" max-width="300">
          <v-card>
            <v-card-title class="headline">啊喔</v-card-title>
            <v-card-text>
              请选择一个心情。
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="light-blue" text @click="dialog = false">关闭</v-btn>
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
      emotions: [
        { name: "开心😀", icon: "mdi-emoticon-excited-outline" },
        { name: "平静😐", icon: "mdi-emoticon-neutral-outline" },
        { name: "孤独🤡", icon: "mdi-emoticon-sad-outline" },
        { name: "疲惫😵‍💫", icon: "mdi-emoticon-sick-outline" },
        { name: "难过😞", icon: "mdi-emoticon-cry-outline" },
      ],
      selectedEmotion: null,
      dialog: false
    };
  },
  methods: {
    selectEmotion(emotion) {
      this.selectedEmotion = emotion;
    },
    confirm() {
      if (this.selectedEmotion) {
        localStorage.setItem("emotion", this.selectedEmotion);
        this.$router.push("/LevelSelection");
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
.emotion-btn {
  transition: transform 0.5s ease, opacity 0.3s ease, filter 0.3s ease;
  opacity: 0.9;
}

.emotion-btn:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 6px rgba(0,0,0,0.2);
  opacity: 1;
  filter: blur(0px);
}

.emotion-btn.is-selected {
  border: 2px solid currentColor;
  /* background-color: transparent; */
  background-color: primary;
  color: inherit;
  opacity: 0.7;
}

/* tab键导航时会触发 */
.emotion-btn:active,
.emotion-btn:focus{
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
  width: 500px;
  height: 1111px;
  background-image: url('../../assets/bg3.jpeg');
  background-size: cover;
}
</style>

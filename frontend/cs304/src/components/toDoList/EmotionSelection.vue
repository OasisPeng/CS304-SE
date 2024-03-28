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
                color="light-blue"
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
            </v-col>
          </v-row>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script>
  export default {
    data() {
      return {
        emotions: [
          { name: "开心", icon: "mdi-emoticon-excited-outline" },
          { name: "平静", icon: "mdi-emoticon-neutral-outline" },
          { name: "孤独", icon: "mdi-emoticon-sad-outline" },
          { name: "疲惫", icon: "mdi-emoticon-sick-outline" },
          { name: "难过", icon: "mdi-emoticon-cry-outline" },
        ],
      };
    },
    methods: {
      selectEmotion(emotion) {
        localStorage.setItem("emotion", emotion);
        this.$router.push("/LevelSelection");
      },
      goBack() {
        this.$router.go(-1);
      },
    },
  };
  </script>
  
  <style scoped>
  .emotion-btn {
    transition: transform 0.3s ease;
    margin-bottom: 20px; /* 保证按钮之间的间距 */
  }
  
  .emotion-btn:hover {
    transform: translateY(-5px);
    box-shadow: 0 4px 6px rgba(0,0,0,0.2);
  }
  
  @media (max-width: 600px) {
    .emotion-btn {
      margin-bottom: 10px;
    }
  }
  </style>
  
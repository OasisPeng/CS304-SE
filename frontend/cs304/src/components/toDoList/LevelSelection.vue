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
                color="orange darken-2"
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
        levels: [
          { name: "重要紧急" },
          { name: "重要不紧急" },
          { name: "不重要紧急" },
          { name: "不重要不紧急" },
        ],
      };
    },
    methods: {
      selectLevel(level) {
        localStorage.setItem("level", level);
        this.$router.push("/TodoEdit");
      },
      goBack() {
        this.$router.go(-1);
      },
    },
  };
  </script>
  
  <style scoped>
  .level-btn {
    transition: transform 0.3s ease;
    margin-bottom: 20px; /* 保证按钮之间的间距 */
  }
  
  .level-btn:hover {
    transform: translateY(-5px);
    box-shadow: 0 4px 6px rgba(0,0,0,0.2);
  }
  
  @media (max-width: 600px) {
    .level-btn {
      margin-bottom: 10px;
    }
  }
  </style>
  
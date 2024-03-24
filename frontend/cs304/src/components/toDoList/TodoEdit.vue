<template>
    <v-app>
      <v-main>
        <v-container class="pa-3" fluid>
            
          <v-row class="mb-3">
            <v-col cols="12">
              <v-text-field
                label="代办事项标题"
                v-model="todo.title"
                prepend-icon="mdi-format-title"
                outlined
              ></v-text-field>
            </v-col>
          </v-row>
  
          
          <v-row class="mb-5">
            <v-col cols="12">
              <v-textarea
                label="代办事项正文"
                v-model="todo.text"
                rows="5"
                auto-grow
                prepend-icon="mdi-text"
                outlined
              ></v-textarea>
            </v-col>
          </v-row>
  
          
          <v-row justify="end">
            <v-col cols="12" class="text-right">
              <v-btn color="green" dark @click="saveTodo">保存</v-btn>
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
        todo: {
          title: '',
          text: '',
        },
      };
    },
    methods: {
      saveTodo() {
        const category = localStorage.getItem("category");
        const emotion = localStorage.getItem("emotion");
        const level = localStorage.getItem("level");
        const todoData = {
          ...this.todo,
          category,
          emotion,
          level,
        };
        console.log("Saving todo:", todoData);
      },
    },
  };
  </script>
  
  <style scoped>
  </style>
  
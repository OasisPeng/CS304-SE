<template>
  <v-app>
    <v-main>
      <v-container class="pa-3" fluid>
        <div style="padding-top: 25px;">
          <v-row class="mb-3">
            <v-col cols="7">
              <v-text-field
                label="代办事项标题"
                v-model="todo.title"
                prepend-icon="mdi-format-title"
                outlined
              ></v-text-field>
            </v-col>
          </v-row>
          
          <v-row>
            <v-col>
              <v-chip v-if="category" outlined>{{ category }}</v-chip>
              <v-chip v-if="emotion" outlined>{{ emotion }}</v-chip>
              <v-chip v-if="level" outlined>{{ level }}</v-chip>
            </v-col>
          </v-row>
          
          <v-row class="mb-5">
            <v-col cols="12">
              <v-textarea
                label="代办事项正文"
                v-model="todo.text"
                rows="20"
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
        </div>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { saveEvent } from '../../api/index';
export default {
  data() {
    return {
      todo: {
        title: '',
        text: '',
      },
      category: '',
      emotion: '',
      level: '',
    };
  },
  mounted() {
    this.category = localStorage.getItem("category");
    this.emotion = localStorage.getItem("emotion");
    this.level = localStorage.getItem("level");
  },
  methods: {
    saveTodo() {
      const todoData = {
        ...this.todo,
        category: this.category,
        emotion: this.emotion,
        level: this.level,
      };
      console.log("Saving todo:", todoData);
      saveEvent(todoData)
    },
  },
};
</script>

<style scoped>
</style>

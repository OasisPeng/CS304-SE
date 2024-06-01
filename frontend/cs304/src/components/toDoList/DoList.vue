<template>
  <v-row justify="center">
    <v-col cols="12">
      <v-card max-width="600" class="max-height-card" style="background-color: palevioletred!important">
        <v-img class="max-height-card" height="200" :src="ToDoListBackground" cover :style="{ backgroundColor: 'palevioletred !important'}">
          <v-card-text class="text-center">
            <v-row justify="space-between" align="center">
              <div class="title">{{ currentMonth }}</div>
              <v-btn variant="text" class="btn-margin" color="rgba(0, 0, 0, 0)" theme="dark">
                <v-icon color="cyan">mdi-magnify</v-icon>
              </v-btn>
              <v-btn variant="text" color="rgba(0, 0, 0, 0)" theme="dark" @click="newEvent()">
                <v-icon color="cyan">mdi-pencil-plus-outline</v-icon>
              </v-btn>
              <div class="week-selector">
                <v-btn icon @click="prevWeek">
                  <v-icon large>mdi-chevron-left</v-icon>
                </v-btn>
                <div class="week-number">
                  Week <span class="week"> {{ currentWeek }}</span>
                </div>
                <v-btn icon @click="nextWeek">
                  <v-icon large>mdi-chevron-right</v-icon>
                </v-btn>
              </div>
            </v-row>
            <v-row justify="center">
              <v-col v-for="(day, index) in daysInWeek" :key="index" cols="auto">
                <div class="day-item" @click="selectDate(day)">
                  <div :class="{ 'day': true, 'selected1': isCurrentDate(day.date) }">{{ day.date }}</div>
                  <div :class="{ 'weekday': true, 'selected': isCurrentDate(day.date) }">{{ day.weekday }}</div>
                </div>
              </v-col>
            </v-row>
          </v-card-text>
        </v-img>
        <v-card max-width="500" class="max-height-card1">
          <v-img class="max-height-card1" height="800" cover style="background-color: lavender!important">
            <v-list dense>
              <v-list-item v-for="(task, taskIndex) in selectedDateTasks" :key="taskIndex" :class="getTaskRowClass(task.level)" @click="handleTaskClick(task)">
                <v-list-item-icon class="box">
                  <v-checkbox v-model="task.finish" color="primary" @change="updateTaskFinishStatus(task)"></v-checkbox>
                </v-list-item-icon>
                <v-list-item-content>
                  <div class="task-container">
                    <v-list-item-title :class="{ 'completed': task.finish, 'larger-font': true }">
                      {{ task.title }}
                    </v-list-item-title>
                    <div class="task-buttons">
                      <v-btn variant="text" class="btn-margin" color="rgba(0, 0, 0, 0)" theme="dark" @click="editEvent(task)">
                        <v-icon color="blue">mdi-receipt-text-edit-outline</v-icon>
                      </v-btn>
                      <v-btn variant="text" class="btn-margin2" color="rgba(0, 0, 0, 0)" theme="dark">
                        <v-icon color="blue" @click="sheet = true">mdi-dots-vertical</v-icon>
                      </v-btn>
                      <template>
                        <v-bottom-sheet v-model="sheet">
                          <template>
                            <v-card class="mx-auto" max-width="344">
                              <v-img height="200px" :src="detailBackground" cover></v-img>
                              <v-card-title>
                                {{ selectedTaskDetails.title }}
                                <div class="emotion-indicator">{{ selectedTaskDetails.emotion }}</div>
                              </v-card-title>
                              <v-card-subtitle>{{ selectedTaskDetails.category }}</v-card-subtitle>
                              <v-card-actions>
                                <v-spacer></v-spacer>
                              </v-card-actions>
                              <v-expand-transition>
                                <div>
                                  <v-divider></v-divider>
                                  <v-card-text>{{ selectedTaskDetails.text }}</v-card-text>
                                </div>
                              </v-expand-transition>
                            </v-card>
                          </template>
                        </v-bottom-sheet>
                      </template>
                    </div>
                  </div>
                </v-list-item-content>
              </v-list-item>
            </v-list>
            <v-divider></v-divider>
          </v-img>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>

<script setup>
</script>
<script>
import ToDoListBackground from '@/components/toDoList/toDoListBackground.jpeg';
import detailBackground from '@/components/toDoList/pexels-photo-9916558.jpeg';
import Background from '@/assets/pink1.jpeg';

export default {
  data() {
    return {
      currentDate: new Date(),
      daysInWeek: [],
      currentMonth: '',
      currentWeek: '',
      Background: Background,
      list: [],
      show: false,
      sheet: false,
      selectedTaskDetails: {},
    };
  },
  mounted() {
    this.updateWeek();
    this.updateMonth();
    this.fetchCategory();
  },
  computed: {
    selectedDateTasks() {
      const selectedDateDay = this.currentDate.getDay();
      return this.list.filter(task => task.xq === selectedDateDay);
    },
  },
  methods: {
    getTasksByDay(xq) {
      return this.list.filter(task => task.xq === xq);
    },
    getTaskRowClass(level) {
      return {
        'normal-row': level === 'normal',
        'important-row': level === 'important',
        'unimportant-row': level === 'unimportant',
      };
    },
    handleTaskClick(task) {
      this.selectedTaskDetails = task;
      this.show = !this.show;
    },
    newEvent() {
      this.$router.push('/ToDoCreate');
    },
    editEvent(task) {
      localStorage.setItem('id', task.id);
      localStorage.setItem('category', task.category);
      localStorage.setItem('emotion', task.emotion);
      localStorage.setItem('level', task.level);
      localStorage.setItem('title', task.title);
      localStorage.setItem('text', task.text);
      if (task.finish === 'no') task.finish = 0;
      else task.finish = 1;
      localStorage.setItem('finish', task.finish);
      localStorage.setItem('owner', task.owner);

      const taskDate = new Date(task.data);
      const getWeekNumber = date => {
        const firstDayOfYear = new Date(date.getFullYear(), 0, 1);
        const pastDaysOfYear = (date - firstDayOfYear) / 86400000;
        return Math.ceil((pastDaysOfYear + firstDayOfYear.getDay() + 1) / 7);
      };
      const getDayOfWeek = date => date.getDay();
      task.week = getWeekNumber(taskDate);
      task.xq = getDayOfWeek(taskDate);

      localStorage.setItem('week', task.week);
      localStorage.setItem('xq', task.xq);
      this.$router.push('/ToDoEdit');
    },
    updateWeek() {
      // 确保更新周的逻辑正确
      const startOfWeek = new Date(this.currentDate);
      startOfWeek.setDate(startOfWeek.getDate() - startOfWeek.getDay());

      this.daysInWeek = [...Array(7).keys()].map(index => {
        const date = new Date(startOfWeek);
        date.setDate(date.getDate() + index);
        return {
          date: date.getDate(),
          month: date.getMonth(),
          year: date.getFullYear(),
          weekday: this.getWeekdayName(date.getDay()),
          xq: date.getDay(),
        };
      });

      this.currentWeek = this.getWeekNumber(startOfWeek);
    },
    updateMonth() {
      // 确保更新月的逻辑正确
      const monthNames = [
        'January', 'February', 'March', 'April', 'May', 'June', 'July',
        'August', 'September', 'October', 'November', 'December',
      ];
      const monthIndex = this.currentDate.getMonth();
      this.currentMonth = monthNames[monthIndex] + ' ' + this.currentDate.getFullYear();
    },
    prevWeek() {
      this.currentDate.setDate(this.currentDate.getDate() - 7);
      this.updateWeek();
      this.updateMonth();
    },
    nextWeek() {
      this.currentDate.setDate(this.currentDate.getDate() + 7);
      this.updateWeek();
      this.updateMonth();
    },
    getWeekdayName(dayIndex) {
      const weekdays = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
      return weekdays[dayIndex];
    },
    getWeekNumber(date) {
      const firstDayOfYear = new Date(date.getFullYear(), 0, 1);
      const pastDaysOfYear = (date - firstDayOfYear) / 86400000;
      return Math.ceil((pastDaysOfYear + firstDayOfYear.getDay() + 1) / 7);
    },
    isCurrentDate(day) {
      const currentDay = this.currentDate.getDate();
      const currentMonth = this.currentDate.getMonth();
      const currentYear = this.currentDate.getFullYear();
      const dayDate = new Date(this.currentDate);
      dayDate.setDate(day);

      return (
          dayDate.getDate() === currentDay &&
          dayDate.getMonth() === currentMonth &&
          dayDate.getFullYear() === currentYear
      );
    },
    selectDate(day) {
      // 设置选择的日期

      const selectedDate = new Date();
      selectedDate.setFullYear(day.year);
      selectedDate.setMonth(day.month);
      selectedDate.setDate(day.date);



      // 更新当前日期
      this.currentDate = selectedDate;
      this.updateWeek();
      this.updateMonth();
      // 日志输出用于调试
    },


    async fetchCategory() {
      try {
        const response = await this.$axios.get(this.$httpUrl + '/event/queryByOwner', {
          params: {
            owner: JSON.parse(localStorage.getItem('info')).username,
          },
          withCredentials: false,
          headers: {
            Authorization: `Bearer ${JSON.parse(localStorage.getItem('info')).token}`,
          },
        });
        const products = response.data.data.map(evo => {
          return {
            id: evo.id || '',
            title: evo.title || '',
            owner: evo.owner || '',
            week: evo.week || '',
            xq: evo.xq || '',
            level: evo.level || '',
            finish: evo.finish || '',
            category: evo.category || '',
            emotion: evo.emotion || '',
            text: evo.text !== '',
          };
        });
        this.list = products;
        console.log('fetchCategory:', this.daysInWeek);
      } catch (error) {
        console.error('Error querying category:', error);
      }
    },
    async updateTaskFinishStatus(task) {
      console.log("任务",task)
      const updatedTask = { ...task, finish: task.finish ? 1 : 0, id: parseInt(task.id, 10) };
      console.log("任务",updatedTask)
      try {
        // 发送更新请求
        const response = await this.$axios.post(this.$httpUrl + '/event/update', updatedTask, {
          withCredentials: false,
          headers: {
            Authorization: `Bearer ${JSON.parse(localStorage.getItem('info')).token}`,
          },
        });
        console.log('消息:', response);
      } catch (error) {
        console.error('更新任务状态时出错:', error);
        // 如果发生错误，可以考虑将状态恢复原样
        task.finish = !task.finish;
      }
    },
  },
};
</script>

<style scoped>
.v-input--selection-controls__input {
  color: inherit;
  display: inline-flex;
  flex: 10 10 auto;
  height: 14px;
  position: relative;
  transition: 0.3s cubic-bezier(0.25, 0.8, 0.5, 1);
  transition-property: transform;
  width: 24px;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}
.normal-row {
  background-color: transparent;
}

.important-row {
  background-color: lightcoral;
}

.unimportant-row {
  background-color: #b6f7e8;
}
.task-container {
  display: flex;
  justify-content: space-between;
}

.task-buttons {
  display: flex;
}

.btn-margin2 {
  margin-left: 2px;
}
.emotion-indicator {
  margin-left: auto;
}
.title {
  margin-right: 30px !important;
  color: #e9ecef;
}

.week-selector {
  display: flex;
  align-items: center;
}

.week-number {
  font-size: 1rem;
  margin: 0 10px;
  color: #e9ecef;
}

.day-item {
  text-align: center;
  width: 30px;
  height: 50px;
}

.day {
  font-weight: bold;
  color: #e9ecef;
}
.selected1 {
  color: palevioletred;
  box-shadow: 0 0 3px 6px palevioletred;
  border-radius: 40%;
}
.selected {
  color: palevioletred !important;
}
.week {
  color: black;
  font-size: 20px;
}
.weekday {
  font-size: smaller;
  color: #e9ecef;
}
.max-height-card {
  max-height: 140px !important;
}
.max-height-card1 {
  max-height: 1800px !important;
}
.box {
  margin-bottom: 45px !important;
}
</style>


<template >
  <v-row justify="center" >
    <v-col cols="12">
      <v-card
          max-width="500"
          class="max-height-card"
          style="background-color: palevioletred!important"

      >
        <v-img
            class="max-height-card"
            height="200"
            :src="ToDoListBackground"
            cover
            :style="{ backgroundColor: 'palevioletred !important'}"
        >
          <v-card-text class="text-center">
            <v-row justify="space-between" align="center">
              <div class="title">{{ currentMonth }}</div>
              <v-btn  variant="text" class="btn-margin"
                      color="rgba(0, 0, 0, 0)"
                      theme="dark"
              >
                <v-icon color="cyan">mdi-magnify</v-icon>
              </v-btn>
              <v-btn  variant="text"
                      color="rgba(0, 0, 0, 0)"
                      theme="dark"
                      @click=newEvent()
              >
                <v-icon color="cyan">mdi-pencil-plus-outline</v-icon>
              </v-btn>
              <div class="week-selector">
                <v-btn icon @click="prevWeek">
                  <v-icon
                      large
                  >mdi-chevron-left
                  </v-icon>
                </v-btn>
                <div class="week-number">Week <span class="week"> {{ currentWeek }}</span></div>
                <v-btn icon @click="nextWeek">
                  <v-icon
                      large
                  >mdi-chevron-right</v-icon>
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
        <!-- 按星期分组的任务列表 -->
        <v-card
            max-width="500"
            class="max-height-card1"
        >
          <v-img
              class="max-height-card1"
              height="800"
              cover
              style="background-color: lavender!important"
          >


          <!-- 星期任务列表 -->
          <v-list dense>
            <!-- 每个任务 -->
            <v-list-item v-for="(task, taskIndex) in selectedDateTasks" :key="taskIndex"
                         :class="getTaskRowClass(task.level)"
                         @click="handleTaskClick(task)">
              <!-- 任务完成状态 -->
              <v-list-item-icon class="v-size--small">
                <v-checkbox v-model="task.finish" color="primary"></v-checkbox>
              </v-list-item-icon>

              <!-- 任务内容 -->
              <v-list-item-content>
                <div class="task-container">
                  <!-- 任务标题 -->
                  <v-list-item-title :class="{ 'completed': task.finish, 'larger-font': true }">{{ task.title }}</v-list-item-title>

                  <!-- 任务按钮容器 -->
                  <div class="task-buttons">
                    <!-- 第一个按钮 -->
                    <v-btn variant="text" class="btn-margin"
                           color="rgba(0, 0, 0, 0)"
                           theme="dark"
                    >
                      <v-icon color="blue">mdi-receipt-text-edit-outline</v-icon>
                    </v-btn>
                    <!-- 第二个按钮 -->

                    <v-btn variant="text" class="btn-margin2"
                           color="rgba(0, 0, 0, 0)"
                           theme="dark"
                    >
                      <v-icon color="blue"
                              @click="sheet = true"

                      >mdi-dots-vertical</v-icon>
                    </v-btn>
                    <template>
                      <!-- 你的其他代码 -->
                      <template>
                        <v-bottom-sheet v-model="sheet">
                          <template>
                            <v-card
                                class="mx-auto"
                                max-width="344"
                            >
                              <v-img
                                  height="200px"
                                  :src="detailBackground"
                                  cover
                              >
                              </v-img>

                              <v-card-title>
                                {{ selectedTaskDetails.title }}
                                <div class="emotion-indicator">
                                  {{ selectedTaskDetails.emotion }}
                                </div>
                              </v-card-title>
                              <v-card-subtitle>
                                {{ selectedTaskDetails.category }}
                              </v-card-subtitle>

                              <v-card-actions>
                                <v-spacer></v-spacer>
                              </v-card-actions>
                              <v-expand-transition>
                                <div >
                                  <v-divider></v-divider>
                                  <v-card-text>
                                    {{ selectedTaskDetails.text }}
                                  </v-card-text>
                                </div>
                              </v-expand-transition>
                            </v-card>
                          </template>
                        </v-bottom-sheet>
                      </template>
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
      list:[
        {
          id:"1",
          title:"Play LOL",
          owner:"yangyu" ,
          week:"1",
          data:"4/15/2024",
          level:"important",
          finish:"no",
          category:"life",
          emotion:"😀",
          text:"I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape."
        },
        {
          title:"Play Valorant",
          owner:"yangyu" ,
          data:"4/16/2024",
          level:"important",
          finish:"no",
          category:"",
          emotion:"",
          text:""
        },
        {
          title:"Watch Movie",
          owner:"yangyu" ,
          data:"4/16/2024",
          level:"normal",
          finish:"no",
          category:"",
          emotion:"",
          text:""
        },
        {
          title:"Sleep all the day",
          owner:"yangyu" ,
          data:"4/17/2024",
          level:"unimportant",
          finish:"no",
          category:"",
          emotion:"",
          text:""
        },

      ],
      show: false,
      sheet:false,
      selectedTaskDetails: {},
    }

  },
  mounted() {
    this.updateWeek();
    this.updateMonth();
  },
  computed: {
    selectedDate() {
      return this.currentDate.toLocaleDateString('en-US', { month: 'long', day: 'numeric' });
    },
    selectedDateTasks() {
      const selectedDateString = this.currentDate.toLocaleDateString('en-US');
      return this.list.filter(task => task.data === selectedDateString);
    }


  },
  methods: {
    getTasksByDay (xq) {
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
      // 更新 selectedTaskDetails
      this.selectedTaskDetails = task;

    },
    newEvent(){
      this.$router.push("/CategorySelection");
    },
    updateWeek() {
      const startOfWeek = new Date(this.currentDate);
      startOfWeek.setDate(startOfWeek.getDate() - startOfWeek.getDay());

      this.daysInWeek = [...Array(7).keys()].map(index => {
        const date = new Date(startOfWeek);
        date.setDate(date.getDate() + index);
        return {
          date: date.getDate(),
          weekday: this.getWeekdayName(date.getDay())
        };
      });

      // Update current week number
      this.currentWeek = this.getWeekNumber(startOfWeek);
    },
    updateMonth() {
      const monthNames = [
        'January', 'February', 'March', 'April', 'May', 'June', 'July',
        'August', 'September', 'October', 'November', 'December'
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
      return currentDay === day;
    },
    selectDate(day) {
      const selectedDate = new Date(this.currentDate);
      selectedDate.setDate(day.date);
      this.currentDate = selectedDate;
      this.updateWeek();
    }
  }
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
  background-color: transparent; /* 正常级别的背景颜色 */
}

.important-row {
  background-color: lightcoral; /* 重要级别的背景颜色 */
}

.unimportant-row {
  background-color: #b6f7e8; /* 不重要级别的背景颜色 */
}
.task-container {
  display: flex;
  justify-content: space-between!important;
}

.task-buttons {
  display: flex;
}

.btn-margin2 {
  margin-left: 2px; /* 调整第二个按钮的间距 */
}
.emotion-indicator {
  margin-left: auto; /* 将情感指示器放置到右侧 */
}
.title {
  margin-right: 30px !important; /* 调整标题向右移动的距离 */
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
  width: 30px; /* 固定日期元素的宽度 */
  height: 50px; /* 固定日期元素的高度 */
}

.day {
  font-weight: bold;
  color: #e9ecef;
}
.selected1 {
  color: palevioletred;
  box-shadow: 0 0 3px 6px palevioletred; /* 增加阴影宽度 */
  border-radius: 40%; /* 使阴影成圆形 */
}
.selected {
  color: palevioletred !important; /* 当日期被选中时改变颜色 */
}
.week {
  color: black; /* week 字体颜色为黑色 */
  font-size: 20px;
}
.weekday {
  font-size: smaller;
  color: #e9ecef;
}
.max-height-card {
  max-height: 120px !important;
}
.max-height-card1 {
  max-height: 1800px !important;
}
</style>

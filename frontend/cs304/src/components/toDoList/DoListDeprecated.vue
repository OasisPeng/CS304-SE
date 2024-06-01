<template>

    <v-card
        class="mx-auto"
        max-width="500"
        height="200"
    >
      <v-img
          class="text-white"
          height="200"
          :src="ToDoListBackground"
          cover
      >
      <v-toolbar
          color="rgba(0, 0, 0, 0)"
          theme="dark"
      >
        <v-btn  variant="text"
                color="rgba(0, 0, 0, 0)"
                theme="dark"
        >
          <v-icon color="cyan" >mdi-menu</v-icon>
        </v-btn>

        <v-spacer>
          <v-toolbar-title class="v-toolbar__title">Weekly To Do List</v-toolbar-title>
        </v-spacer>
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
      </v-toolbar>

      </v-img>
      <!-- 按星期分组的任务列表 -->
      <v-list-group v-for="(day, index) in weekDays" :key="index" no-action>
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title class="general-header">{{ day.label }}</v-list-item-title>
          </v-list-item-content>

        </template>

        <!-- 星期任务列表 -->
        <v-list dense>
          <!-- 每个任务 -->
          <v-list-item v-for="(task, taskIndex) in getTasksByDay(day.xq)" :key="taskIndex"
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
                    <v-icon color="blue"
                    @click=editEvent(task)>mdi-receipt-text-edit-outline</v-icon>
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
      </v-list-group>

    </v-card>
</template>

<script setup>




const weekDays = [
  { label: 'Monday', xq: '1' },
  { label: 'Tuesday', xq: '2' },
  { label: 'Wednesday', xq: '3' },
  { label: 'Thursday', xq: '4' },
  { label: 'Friday', xq: '5' },
  { label: 'Saturday', xq: '6' },
  { label: 'Sunday', xq: '7' },
];



</script>

<script>
import ToDoListBackground from '@/components/toDoList/toDoListBackground.jpeg';
import detailBackground from '@/components/toDoList/pexels-photo-9916558.jpeg';
export default {
  data: () => ({
    list:[
      {
        id:"1",
        title:"Play LOL",
        owner:"yangyu" ,
        week:"1",
        xq:"1",
        level:"important",
        finish:"no",
        category:"life",
        emotion:"😀",
        text:"I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape."
      },
      {
        title:"Play Valorant",
        owner:"yangyu" ,
        xq:"2",
        level:"important",
        finish:"no",
        category:"",
        emotion:"",
        text:""
      },
      {
        title:"Watch Movie",
        owner:"yangyu" ,
        xq:"3",
        level:"normal",
        finish:"no",
        category:"",
        emotion:"",
        text:""
      },
      {
        title:"Sleep all the day",
        owner:"yangyu" ,
        xq:"1",
        level:"unimportant",
        finish:"no",
        category:"",
        emotion:"",
        text:""
      },

    ],
        show: false,
        sheet:false,
     weekDays : [
      { label: 'Monday', xq: '1' },
      { label: 'Tuesday', xq: '2' },
      { label: 'Wednesday', xq: '3' },
      { label: 'Thursday', xq: '4' },
      { label: 'Friday', xq: '5' },
      { label: 'Saturday', xq: '6' },
      { label: 'Sunday', xq: '7' },
    ],
        selectedTaskDetails: {},
  }

  ),
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
    editEvent(task){
      // this.selectedTaskDetails = task;
      localStorage.setItem("category", task.category);
      localStorage.setItem("emotion", task.emotion);
      localStorage.setItem("level", task.level);
      localStorage.setItem("title", task.title);
      localStorage.setItem("text", task.text);
      localStorage.setItem("finish", task.finish);
      localStorage.setItem("owner", task.owner);
      localStorage.setItem("xq", task.xq);
      this.$router.push("/ToDoEdit");
    }
  }

}
</script>
<style scoped>
.v-toolbar__title {
  font-size: 1.5rem;
  line-height: 1.5;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

}
.v-toolbar .v-toolbar__title {
  text-align: center;
  font-size: 1.6rem; /* 调整字体大小 */
  font-weight: bold; /* 加粗 */
  color: white;
}
.btn-margin {
  margin-right: 10px; /* 调整合适的间距 */

}
.btn-margin2 {
  margin-right: 0px; /* 调整合适的间距 */
}
</style>
<style lang="scss">
.v-btn:not(.v-btn--round).v-size--default {
  height: 40px !important;
  min-width: 40px !important;
  padding: 0 !important;
}
.general-header {
  font-size: 1.3rem!important; /* 调整字体大小 */
  font-weight: bold!important; /* 加粗 */
}

.list-spacing {
  margin-top: 20px!important; /* 添加间距 */
}
.completed {
  text-decoration: line-through!important;
  opacity: 0.5!important;
}
.larger-font {
  font-size: 20px!important; /* 根据需要调整字体大小 */
}

.v-list-item--dense .v-list-item__icon, .v-list--dense .v-list-item .v-list-item__icon {
  height: 12px!important;
  margin-top: 0!important;;
  margin-bottom: 0!important;;
  margin-right: 5px!important;;

}
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
</style>
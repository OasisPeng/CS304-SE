<template>
  <v-row >
    <v-col cols="12">
      <v-sheet >
        <v-btn icon @click="selectedOpen = false">
          <v-icon>mdi-backspace-outline
          </v-icon>

        </v-btn>
        <v-calendar class="fuck"

            ref="calendar"
            v-model="today"
            :events=this.events
            color="#6fa0e9"

            type="week"
            @click:event="showEvent"
            :weekdays="weekday"
            :first-interval="8"
            :last-interval="21"
            :interval-count="13"
            :interval-height="55"
        >
          <template v-slot:day-body="{ date, week }">
            <div
                :class="{ first: date === week[0].date }"
                :style="{ top: nowY }"
                class="v-current-time"
            ></div>
          </template>
          <template #event="{ event }">
            <div class="my-event" :style="{ backgroundColor: event.color }">
              <div class="event-text">{{ event.name }}</div>
            </div>
          </template>



        </v-calendar>

        <v-menu
            v-model="selectedOpen"
            :close-on-content-click="false"
            :activator="selectedElement"
            offset-x
        >
          <v-card
              color="grey-lighten-4"
              class="mx-auto"
              flat fluid
          >
            <v-toolbar
                :color="selectedEvent.color"
                dark
            >
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-btn icon @click="selectedOpen = false">
                <v-icon>mdi-backspace-outline
                </v-icon>

              </v-btn>

            </v-toolbar>

            <div class="row"  >
              <v-card-text >
                <v-btn icon color="green">
                  <v-icon>mdi-calendar-month</v-icon>
                </v-btn>
                <span class="detail-text" v-html="selectedEvent.week"></span>
              </v-card-text>
            </div>
            <div class="row" >
              <v-card-text>
                <v-btn icon color="orange">
                  <v-icon>mdi-clock-time-five-outline</v-icon>
                </v-btn>
                <span class="detail-text" v-html="selectedEvent.table"></span>
              </v-card-text>
            </div>
            <div class="row" >
              <v-card-text>
                <v-btn icon color="blue">
                  <v-icon>mdi-account-circle-outline</v-icon>
                </v-btn>
                <span class="detail-text" v-html="selectedEvent.teacher"></span>
              </v-card-text>
            </div>
            <div class="row" >
              <v-card-text>
                <v-btn icon color="red">
                  <v-icon>mdi-office-building-outline</v-icon>
                </v-btn>
                <span class="detail-text" v-html="selectedEvent.building"></span>
              </v-card-text>
            </div>
            <div class="row" >
              <v-card-text>
                <v-btn icon color="yellow">
                  <v-icon>mdi-account-group-outline</v-icon>
                </v-btn>
                <span class="detail-text" v-html="selectedEvent.jc"></span>
              </v-card-text>
            </div>
            <div class="row" >
              <v-card-text>
                <v-btn icon color="purple">
                  <v-icon>mdi-translate</v-icon>
                </v-btn>
                <span class="detail-text" v-html="selectedEvent.language"></span>
              </v-card-text>
            </div>
          </v-card>
        </v-menu>

      </v-sheet>
    </v-col>
  </v-row>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'

const selectedEvent = ref({})
const selectedElement = ref(null)
const selectedOpen = ref(false)

// 将星期几转换为日期的辅助函数

function showEvent ({ nativeEvent, event }) {
  const open = () => {
    selectedEvent.value = event
    selectedElement.value = nativeEvent.target
    requestAnimationFrame(() => requestAnimationFrame(() => selectedOpen.value = true))
  }
  if (selectedOpen.value) {
    selectedOpen.value = false
    requestAnimationFrame(() => requestAnimationFrame(() => open()))
  } else {
    open()
  }
  nativeEvent.stopPropagation()
}



const today = ref(new Date())
const calendar = ref()

const ready = ref(false)

const cal = computed(() => {
  return ready.value ? calendar.value : null
})
const nowY = computed(() => {
  return cal.value ? cal.value.timeToY(cal.value.times.now) + 'px' : '-10px'
})

onMounted(() => {
  ready.value = true
  scrollToTime()
  updateTime()
})

function getCurrentTime () {
  return cal.value ? cal.value.times.now.hour * 60 + cal.value.times.now.minute : 0
}
function scrollToTime () {
  const time = getCurrentTime()
  const first = Math.max(0, time - (time % 30) - 30)
  cal.value.scrollToTime(first)
}
function updateTime () {
  setInterval(() => cal.value.updateTimes(), 60 * 1000)
}
</script>

<script>
// import axios from "axios";

export default {
  data: () => ({
    today: '2019-01-08',
    weekday: [1, 2, 3, 4, 5, 6, 0],
    weekdays: [
      { title: 'Mon - Sun', value: [1, 2, 3, 4, 5, 6, 0] },
    ],

    value: '',
    ready: false,
    username: '',
    password: '',
    courseList: [], // 存储课程列表的数组
     colors :['blue lighten-3', 'teal accent-2', 'purple accent-2', 'cyan', 'green', 'orange', "red accent-2",'red lighten-3','yellow','pink dark-1','pink lighten-4','blue lighten-2','light-blue lighten-2'],
     events:[]
  }),


  computed: {
    cal () {
      return this.ready ? this.$refs.calendar : null
    },
    nowY () {
      return this.cal ? this.cal.timeToY(this.cal.times.now) + 'px' : '-10px'
    },
  },
  mounted () {
    this.ready = true
    this.scrollToTime()
    this.updateTime()
    this.queryCurrentCourse();
  },

    methods: {
      async queryCurrentCourse() {
        try {
          const response = await this.$axios.post(this.$httpUrl+'/course/queryCurrentCourse', {
            username: this.username,
            password: this.password
          });
          console.log("2",response.data)
          this.courseList = response.data.data
              .map(evo => {
                return {
                  teacher: evo.teacher || "",
                  englishName: evo.englishName || "",
                  chineseName: evo.chineseName || "",
                  teachingBuilding: evo.teachingBuilding || "",
                  xq: evo.xq || "",
                  weeks:evo.weeks || "",
                  jc: evo.jc || "",
                  color: evo.color || "",
                  startTime: evo. startTime || "",
                  endTime: evo.endTime || "",
                  classes:evo.classes || "",
                  language:evo.language || "",
                };
              }); // 将响应数据赋值给courseList数组
          this.events= this.courseList.map(event => this.createEventObject(event.startTime, event.endTime, event.xq, event.chineseName,event.color,event.teachingBuilding,event.teacher,event.weeks,event.jc,event.classes,event.language));
          console.log("kebiao",this.courseList)
        } catch (error) {
          console.error('Error querying current course:', error);
        }
      },
      formatDateTime(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        return `${year}-${month}-${day} ${hours}:${minutes}`;
      },
      getDateFromDayOfWeek(dayOfWeek) {
        const today = new Date();
        const diff = dayOfWeek - today.getDay();
        return new Date(today.setDate(today.getDate() + diff));
      },

// 将字符串时间转换为日期时间
      getTimeFromString(timeString) {
        const [hours, minutes] = timeString.split(':');
        const date = new Date();
        date.setHours(parseInt(hours, 10));
        date.setMinutes(parseInt(minutes, 10));
        return date;
      },

// 将时间和日期合并为完整的事件对象
      createEventObject(start, end, dayOfWeek,name,color,building,teacher,week,jc,classes,language) {
        const date = this.getDateFromDayOfWeek(dayOfWeek);
        const startTime = this.getTimeFromString(start);
        const endTime = this.getTimeFromString(end);
        // 设置日期和时间
        const startDateTime = new Date(date.getFullYear(), date.getMonth(), date.getDate(), startTime.getHours(), startTime.getMinutes());

        const endDateTime = new Date(date.getFullYear(), date.getMonth(), date.getDate(), endTime.getHours(), endTime.getMinutes());
        return {
          name: name, // 你的事件标题
          start: this.formatDateTime(startDateTime),
          end: this.formatDateTime(endDateTime),
          color:this.colors[color],
          building:building+"-"+classes,
          teacher:teacher,
          week:week,
          jc:jc,
          table:start+"-"+end,
          language:language
        };
      },
      getCurrentTime () {
        return this.cal ? this.cal.times.now.hour * 60 + this.cal.times.now.minute : 0
      },
      scrollToTime () {
        const time = this.getCurrentTime()
        const first = Math.max(0, time - (time % 30) - 30)

        this.cal.scrollToTime(first)
      },
      updateTime () {
        setInterval(() => this.cal.updateTimes(), 60 * 1000)
      },
    },
}
</script>

<style scoped>

.row + .row {
  margin-top: -30px;
}


.v-event-timed-container{
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  margin-right: 0;
  pointer-events: none
}

.my-event {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  border-radius: 2px;


  font-size: 12px;
  padding: 3px;
  cursor: pointer;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;

  display: flex;
  align-items: center;
  justify-content: space-between;
}

.event-text {
  max-width: 100%;
  word-break: break-word;
  white-space: normal;
  text-align: center;
}
.detail-text {
  margin-left: 8px; /* 可以根据需要调整间距 */
}

</style>
<style lang="scss">
.v-current-time {
  height: 2px;
  background-color: #ea4335;
  position: absolute;
  left: -1px;
  right: 0;
  pointer-events: none;

  &.first::before {
    content: '';
    position: absolute;
    background-color: #ea4335;
    width: 12px;
    height: 12px;
    border-radius: 50%;
    margin-top: -5px;
    margin-left: -6.5px;
  }
}
.event-container {
  padding: 8px;
  border-radius: 4px;
  margin: 4px 0;
  color: white;
}

.event-name {
  font-weight: bold;
}

.event-time {
  font-size: 0.8rem;
  margin-top: 4px;
}
.v-btn--fab.v-size--default {
  height: 40px;
  width: 40px;
}
.v-application .primary {
  background-color: #6fa0e9 !important;
  border-color: #d3f7b6 !important;
}
</style>
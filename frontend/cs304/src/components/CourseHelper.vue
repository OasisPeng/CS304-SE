<template>
  <div>
    <v-alert
        v-if="config.show"
        density="compact"
        position="fixed"
        title="Alert title"
        :type="config.type"
        :text="config.text"
        :duration="config.duration"
    >{{
        config.text
      }}
    </v-alert>
    <div class="wrap">
      <v-container class="warp11">
        <v-col class="name">
          欢迎来到SUSTech-Class
        </v-col>
        <v-col class="login">
          <v-sheet class="mx-auto" >

            <v-form ref="form" >
              账号:
              <v-text-field
                  v-model="name"
                  :rules="nameRules"

                  required
              ></v-text-field>
              密码:
              <v-text-field
                  v-model="password"
                  :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                  :rules="[rules.required, rules.min]"
                  :type="show1 ? 'text' : 'password'"

                  name="input-10-1"

                  @click:append="show1 = !show1"
              ></v-text-field>
              <div class="d-flex flex-column">
                <v-btn
                    class="mt-4"
                    color="success"
                    block
                    @click="validate"
                >
                  登录
                </v-btn>

              </div>
            </v-form>
          </v-sheet>

        </v-col>
      </v-container>
    </div>
  </div>
</template>

<script>
import {Login} from '../api/index'
export default {
  name:'CourseHelper',
  data:()=>({
    name: '',
    password:'',
    show1: false,
    config:{
      show:false,
      text:'登录成功',
      type:'success',
      duration:1000
    },
    rules: {
      required: value => !!value || '填写密码',
      min: v => v.length >= 6 || '密码长度不能小于8位',
    },
    nameRules: [
      v => !!v || '请填写学号',
    ],
  }),
  methods:{
    async validate () {
      localStorage.clear()
      this.config.show=false
      if(this.name&&this.password){
        const {code,msg,data}=   await Login({
          username:this.name,
          password:this.password
        })
        this.config.text='登录'+msg
        if(code==200){
          localStorage.setItem('info',JSON.stringify(data))
          this.$router.push('/')

        }else{
          this.config.type='warning'
        }

        this.config.show=true
      }
    },
  }
}
</script>

<style  scoped>
.name{
  font-size: 30px;
  font-weight: bold;
  color: #ffffff;
  margin-top: 100px;
  text-align: center;
}
.login{
  width: 300px;
  background-color: #ffffff;
  border-radius:10px;
  box-shadow: 0px 0px 10px #ccc;
  position: absolute;
  left: 50%;
  top: 50%;

  transform: translate(-50%,-50%);
}
.wrap {
  position: relative;
  width: 100vw;
  height: 100vh;
  background: linear-gradient(to bottom,#3a6ec2 0%, #3d6ab1 40%,#84aaaa 60%, #fff 100%);
  background-size: cover;
}
.ff{
  position: absolute;
  top: 0;
  z-index: 12;
}
</style>
<template>
    <v-container>
        <v-col class="name">
            欢迎来到SUSTech-Class
        </v-col>
 <v-col class="login">
    <v-sheet class="mx-auto" width="300">

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
text:'c',
type:'success',
duration:1000
        },
        rules: {
          required: value => !!value || '填写密码',
          emailMatch: () => ('The email and password you entered don\'t match'),
        },
      nameRules: [
        v => !!v || '请填写学号',
      ],
      }),
      methods:{
        async validate () {
            localStorage.clear()
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
                    this.config.type='error'
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
    color: #000000;
    margin-top: 100px;
    text-align: center;
}
.login{
    /* margin-top: 100px; */
}
</style>
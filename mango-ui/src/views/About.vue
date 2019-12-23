<template>
  <div class="about">
    <h1>This is an about page</h1>
     <h2>About page</h2>
    <el-button type="primary" @click="testAxios()">测试Axios调用</el-button>
    <el-button type="primary" @click="getUser()">获取用户信息</el-button>
    <el-button type="primary" @click="getMenu()">获取菜单信息</el-button>
    <el-button type="primary" @click="getToken()">获取tokan</el-button>
  </div>
</template>
<script>
import axios from 'axios'
//import mock from '@/mock/mock.js'

// var instance = axios.create({
//     headers: {'content-type': 'application/x-www-form-urlencoded'}
// });



export default { 
  name: 'About',
  methods:{
    testAxios() {
      axios.get('http://localhost:8081').then(res => {alert(res.data)});
    },
    getUser() {
      axios.get('http://localhost:8081/user').then(res => {alert(JSON.stringify(res.data))})
      //instance.get('http://localhost:8081/user').then(res => {alert(JSON.stringify(res.data))});
    },
    getMenu() {
      axios.get('http://localhost:8081/menu').then(res => {alert(JSON.stringify(res.data))})
    },
    getToken() {
      axios.get('http://localhost:8081/login').then(res => {alert(JSON.stringify(res.data))})
    }
  }
}
</script>
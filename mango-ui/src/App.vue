<template>
  <div id="app">
    <!-- <div id="nav">
      <router-link to="/">{{$t('common.home')}}</router-link> |
      <router-link to="/about">{{$t('common.about')}}</router-link> |
      <router-link to="/404">{{$t('common.404')}}</router-link> |
      <router-link to="/login">{{$t('common.login')}}</router-link>
    </div> -->
    <!-- 添加 router-view 标签就可以在所有页面看到以上内容-->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

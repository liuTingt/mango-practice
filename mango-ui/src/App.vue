<template>
  <div id="app">
    <div id="nav">
      <!-- <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/404">404</router-link> |
      <router-link to="/login">login</router-link>  -->
      <router-link to="/">{{$t('common.home')}}</router-link> |
      <router-link to="/about">{{$t('common.about')}}</router-link> |
      <router-link to="/404">{{$t('common.404')}}</router-link> |
      <router-link to="/login">{{$t('common.login')}}</router-link>
    </div>
    <!-- 添加 router-view 标签就可以在所有页面看到以上内容-->
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>

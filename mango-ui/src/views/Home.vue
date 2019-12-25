<template>
  <div class="container">
    <!-- 导航菜单栏 -->
    <nav-bar></nav-bar>

    <!-- 头部区域 -->
    <head-bar></head-bar>

    <!-- 主内容区域 -->
    <main-content></main-content>
  </div>
 
</template>

<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'
import HeadBar from "./HeadBar"
import NavBar from "./NavBar"
import MainContent from "./MainContent"


export default {
  //name: 'home',
  components: {
    HeadBar,
    NavBar,
    MainContent
  }
  // methods: {
  //   changeLanguage(lang) {
  //     lang === '' ? 'zh_cn' : lang;
  //     this.$i18n.locale = lang;
  //     this.langVisible = false;
  //   }
  // }
};
</script>

<style scoped lang="scss">
  .container {
    position:absolute;
    top: 0px;
    left: 0px;
    right: 0px;
    bottom: 0px;
    // background: rgba(224, 234, 235, 0.1);
  }
</style>  

<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <li class="fa fa-home fa-lg"></li>
    {{ $t('common.doc') }}
    <el-button type="success" @click="changeLanguage('zh_cn')">简体中文</el-button>
    <el-button type="success" @click="changeLanguage('en_us')">English</el-button>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'home',
  components: {
    HelloWorld
  },
  methods: {
    changeLanguage(lang) {
      lang === '' ? 'zh_cn' : lang;
      this.$i18n.locale = lang;
      this.langVisible = false;
    }
  }
}
</script>

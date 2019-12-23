# mango-ui

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

安装第三方图标库：font-awesome
     npm install font-awesome
安装国际化组件，执行： npm install vue-i18n


问题记录：
    1、axios访问mock接口控制台总是报错404
        解决：在main.js中引入自定义的mock.js
        原因：需要在main.js中引入自定义的mock.js（import './mock/mock'），而不是在.vue组件中引入mock.js（import mock from '@/mock/mock.js'）
    
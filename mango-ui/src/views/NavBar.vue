<template>
    <div class="menu-bar-container">
        <div class="logo" :style="{'background':themeColor}" :class="collapse?'menu-bar-collapse-width':'menu-bar-width'" @click="$router.push('/')">
            <!-- <img src="@/assets/logo.png" ><div>Maongo</div> -->
            <img v-if="collapse" src="@/assets/logo.png" /><div>{{collapse?'':appName}}</div>
        </div>
    </div>
</template>
<script>
import { mapState } from 'vuex'

export default {
    computed: {
        ...mapState({
            appName: state => state.app.appName,
            collapse: state => state.app.collapse,
            themeColor: state => state.app.themeColor
        })
    },
    methods: {

    }
}
</script>

<style lang="scss" scoped>
    .menu-bar-container {
        position: fixed;
        top: 0px;
        left: 0px;
        bottom: 0px;
        z-index: 1020;
    }
    .logo {
        position: absolute;
        top: 0px;
        height: 60px;
        line-height: 60px;
        background: #545c64;
        cursor: pointer;
        img {
            width: 40px;
            height: 40px;
            border-radius: 0px;
            margin: 10px 10px 10px 10px;;
            float: left;
        }
        div {
            font-size: 25px;
            color: white;
            text-align: left;
            padding-left: 20px;
        }
    }
    .menu-bar-width {
        width: 200px;
    }
    .menu-bar-collapse-width {
        width: 65px;
    }
</style>
<template>
  <div class="">

    <mt-header  title="功能">
      <mt-button icon="back" slot="left" @click="$router.go(-1);">返回</mt-button>
      <mt-button icon="back" slot="right" @click="$router.go(-1);">返回</mt-button>
    </mt-header>
    <mt-navbar v-model="selected">
      <mt-tab-item id="1">已保存</mt-tab-item>
      <mt-tab-item id="2">已提交</mt-tab-item>
    </mt-navbar>
    <mt-tab-container v-model="selected" style="margin-top: 10px">
      <mt-tab-container-item id="1">
        <mt-cell v-for="n in 10" :title="'内容 ' + n" />
      </mt-tab-container-item>
      <mt-tab-container-item id="2">
        <mt-cell v-for="n in 4" :title="'测试 ' + n" />
      </mt-tab-container-item>
    </mt-tab-container>
  </div>
</template>
<script>
  import NavBar from './navBar'

  export default {
    data () {
      return {
        selected: '1'
      }
    },
    methods: {

    },
    components:{
      navBar:NavBar
    }
  }
</script>
<style>
</style>

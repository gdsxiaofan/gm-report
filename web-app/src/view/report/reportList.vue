<template>
  <div class="">

    <mt-header  title="功能">
      <mt-button icon="back" slot="left" @click="$router.go(-1);">返回</mt-button>
      <mt-button size="small" slot="right" @click="$router.push({path:'/reportInfo'})">新增</mt-button>
    </mt-header>
    <mt-navbar v-model="selected">
      <mt-tab-item id="1">已保存</mt-tab-item>
      <mt-tab-item id="2">已提交</mt-tab-item>
    </mt-navbar>
    <mt-tab-container v-model="selected" style="margin-top: 10px">
      <mt-tab-container-item id="1">
        <mt-cell v-for="n in 10"  @click.native="$router.push({path:'/reportInfo',query:{id:n}})" :key="n">
            <div slot="title">内容{{n}}<br><span style="font-size: 0.6rem">201444</span> </div>
          <div>asdada</div>
        </mt-cell>
      </mt-tab-container-item>
      <mt-tab-container-item id="2">
        <mt-cell v-for="n in 10"  :key="n" @click.native="$router.push({path:'/reportInfo',query:{id:n}})">
          <div slot="title">内容{{n}}<br><span style="font-size: 0.6rem">201444</span> </div>
          <div>asdada</div>
        </mt-cell>
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

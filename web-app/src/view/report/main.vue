<template>
  <div class="classify">
    <mt-header  title="功能">
      <!--<mt-button icon="back" slot="left" @click="$router.go(-1);">返回</mt-button>-->
    </mt-header>
    <mt-cell   title="日报信息" is-link to="reportList">
      <mt-badge v-if="count>0" type="error">{{count}}</mt-badge>
      <!--<img slot="icon" src="../../assets/img/1.jpg" width="24" height="24">-->
    </mt-cell>
    <mt-cell   title="日报交接"   is-link to="reportTransfer"></mt-cell>
    <mt-cell   title="文件下载"   is-link to="reportDown"></mt-cell>
    <mt-cell   title="考勤管理"     is-link to="attence"></mt-cell>
    <nav-bar></nav-bar>
  </div>
</template>
<script>
  import {
    getNoReadCount
  } from "../../global/report"
  import NavBar from './navBar'

  export default {
    data () {
      return {
        count:0,
        selected: ''
      }
    },
    methods: {
    },
    created(){
      getNoReadCount().then(res=>{
         this.count=res.data.data
      })
    },
    components:{
      navBar:NavBar
    }
  }
</script>
<style>
  .navBar .nav p {
    font-size: 0.5rem;
    color: #555;
    margin-top: 5%;
  }

  .navBar .mint-tabbar > .mint-tab-item.is-selected {
    background-color: #fafafa;
  }

  .navBar .nav i {
    font-size: 1rem;
  }

  .navBar a {
    text-decoration: none;
  }

  .navBar a.router-link-active p {
    color: #ff3600;
  }
</style>

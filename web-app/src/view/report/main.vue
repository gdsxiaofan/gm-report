<template>
  <div class="classify">
    <mt-header  title="功能">
      <!--<mt-button icon="back" slot="left" @click="$router.go(-1);">返回</mt-button>-->
    </mt-header>
    <mt-cell   title="日报信息" is-link to="reportList">
      <!--<img slot="icon" src="../../assets/img/1.jpg" width="24" height="24">-->
    </mt-cell>
    <mt-cell   title="日报交接"   is-link to="/main"></mt-cell>
    <mt-cell   title="文件下载"   is-link to="/main"></mt-cell>
    <nav-bar></nav-bar>
  </div>
</template>
<script>
  import NavBar from './navBar'

  export default {
    data () {
      return {
        selected: ''
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

<template>
  <div class="classify">
    <mt-header fixed title="全部产品分类">
      <mt-button icon="back" slot="left" @click="$router.go(-1);">返回</mt-button>
    </mt-header>
    <router-view></router-view>
    <div class="navBar">
      <mt-tabbar v-model="selected" class="nav" fixed="fixed">
        <mt-tab-item id="main">
          <router-link to="/main">
            <p><i class="fa fa-home" aria-hidden="true"></i></p>
            <p>首页</p>
          </router-link>
        </mt-tab-item>
        <mt-tab-item id="classify">
          <router-link to="/classify">
            <p><i class="fa fa-th-large" aria-hidden="true"></i></p>
            <p>分类</p>
          </router-link>
        </mt-tab-item>
        <mt-tab-item id="cart">
          <router-link to="/shopping">
            <p><i class="fa fa-shopping-cart" aria-hidden="true"></i></p>
            <p>购物车</p>
          </router-link>
        </mt-tab-item>
        <mt-tab-item id="user">
          <router-link to="/personal">
            <p><i class="fa fa-user-o" aria-hidden="true"></i></p>
            <p>我的信息</p>
          </router-link>
        </mt-tab-item>
      </mt-tabbar>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        selected: ''
      }
    },
    methods: {

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

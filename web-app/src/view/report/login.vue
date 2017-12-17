<template>
  <div class="login">
    <mt-header title="账户登录"
               style="-webkit-transform: translateZ(0);background: -webkit-gradient(linear,left top,right top,from(#0af),to(#0085ff))"
               slot="header">
      <!--<router-link slot="left" to="/home">-->
      <!--<mt-button icon="back">返回</mt-button>-->
      <!--</router-link>-->
      <!--<router-link to="/register" slot="right">-->
      <!--<mt-button>注册</mt-button>-->
      <!--</router-link>-->
    </mt-header>
    <form :model="loginForm" class="loginForm">

      <!--<mt-field label="用户名：" placeholder="请输入用户名" v-model="loginForm.username"></mt-field>-->
      <!--<mt-field label="密码：" placeholder="请输入密码" v-model="loginForm.password"></mt-field>-->
      <section>
        <div>
          <img src="../../assets/img/5.png">
        </div>
      </section>
      <section>
        <div>
          <input type="text" placeholder="工号/手机号" v-model="loginForm.username">
        </div>
      </section>
      <section>
        <div>
          <input type="password" placeholder="密码" v-model="loginForm.password">
        </div>
      </section>
      <section>
        <mt-button style="background: #26a2ff" @click="loginSubmit">登录</mt-button>
      </section>
      <!--<section class="test">-->
      <!--<p>测试：</p>-->
      <!--<p>用户名/密码:2014874236/123456</p>-->
      <!--<p>用户名/密码:2014874239/123456</p>-->
      <!--<p>用户名/密码:2014874207/654321</p>-->
      <!--</section>-->
    </form>
    <!-- 验证码 -->
    <!-- <div class="img_change_img">
            <img v-show="captchaCodeImg" :src="captchaCodeImg">
            <div class="change_img" @click="getCaptchas">
                <p>看不清</p>
                <p>换一张</p>
            </div>
        </div> -->
  </div>
</template>
<script>
  import { login } from '../../global/api'
  import { MessageBox } from 'mint-ui'

  export default {
    data () {
      return {
        loginForm: {
          username:'',
          password:''
        },
        form: '',
        // loginStatus:store.state.loginStatus
        // captchaCodeImg:''
      }
    },
    mounted: function () {
    },
    methods: {
      loginSubmit: function () {
        login(this.loginForm.username, this.loginForm.password).then(res => {
          if (res.data.code === 0) {
            MessageBox.alert(res.data.message, '提示')
            this.loginForm.username = ''
            this.loginForm.password = ''
          } else {
            let redirect = this.$route.query.redirect
            this.$router.push({path: redirect ? redirect : '/'})
            // this.loginStatus=true;
            // store.dispatch('setloginStatus', true)
            // console.log("login页面的loginStatus值",this.loginStatus);
          }
        })



      }
      // getCaptchas:function(){
      // 	this.$http.get(api.captchas).then((response) => {
      // 		console.log("图片验证码",response);
      // 	},() => {
      // 		console.log("请求失败");
      // 	})
      // }
    }
  }
</script>
<style>
  /*头部*/
  .login .mint-header {
    background-color: #1b1b20;
    color: #fff;
    font-size: 0.8rem;
    z-index: 100;
    height: 2rem;
    line-height: 2.5rem;
  }

  /*返回按钮*/
  .login button, .login button label {
    cursor: pointer;
    height: 1.7rem;
  }

  .login .mintui {
    font-size: 0.8rem;
  }

  /*登录组件*/
  .login .loginForm section {
    line-height: 2rem;
    width: 100%;
    text-align: center;
    margin-top: 1rem;
    margin-bottom: 1.2rem;
  }

  .login .loginForm section:nth-child(1) {
    margin-top: 2rem;
  }

  .login .loginForm section div {
    width: 80%;
    border: 1px solid #e0e0e0;
    display: inline-block;
    background: #fff;
  }

  .login .loginForm section div input {
    outline: none;
    border: 0;
    font-size: 0.7rem;
    width: 90%;
  }

  /*登录按钮*/
  .login .loginForm button {
    width: 80%;
    background: #ff3600;
    border-radius: 0px;
    color: #fff;
    text-align: center;
    margin-top: 1.2rem;
    height: 1.7rem;
    font-size: 0.7rem;
  }

  .login .test {
    font-size: 0.7rem;
  }
</style>

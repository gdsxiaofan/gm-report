<template>
	<div class="personal">
		<mt-header title="个人中心">
		    <mt-button icon="back" slot="left" @click="goBack">返回</mt-button>
		</mt-header>
    <mt-cell title="头像" :value="userInfo.img"></mt-cell>
    <mt-cell title="姓名" :value="userInfo.name"></mt-cell>
    <mt-cell title="工号" :value="userInfo.employeeNo"></mt-cell>
    <mt-cell title="手机号码" :value="userInfo.mobileNo"></mt-cell>
    <mt-cell title="所属班组" :value="userInfo.groupName"></mt-cell>
    <mt-cell title="级别" :value="userInfo.levelName"></mt-cell>

    <mt-button type="danger" size="large" @click="$router.push({path:'/login'})">退出登录</mt-button>
		<NavBar></NavBar>
	</div>
</template>
<script>
	// 底部导航
	import NavBar from './navBar'
	import {
    getUserInfo
  } from '../../global/api'
	import store from '../../store/index'
	export default{
		data(){
			return{
				userInfo:{
				  img:'img',
          name:'',
          employeeNo:'',
          mobileNo:'',
          groupName:'',
          levelName:'',
        }
			}
		},
		components:{
			NavBar
		},
		computed:{
		},
		mounted:function(){

		},
    created(){
      getUserInfo().then(res=>{
        this.userInfo=res.data.data
      })
    },
		methods:{
			goBack:function(){
				this.$router.go(-1);
			},
		}
	}
</script>
<style>
</style>

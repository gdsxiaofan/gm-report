<template>
  <div class="">

    <mt-header title="功能">
      <mt-button icon="back" slot="left" @click="$router.go(-1);">返回</mt-button>
      <mt-button size="small" slot="right" @click="$router.push({path:'/reportInfo'})">新增</mt-button>
    </mt-header>
    <mt-navbar v-model="reportStatus">
      <mt-tab-item :id="1">已保存</mt-tab-item>
      <mt-tab-item :id="2">已提交</mt-tab-item>
    </mt-navbar>
    <mt-loadmore :bottom-method="loadBottom" :bottom-all-loaded="allLoaded" ref="loadmore">
      <mt-cell v-for="n in 10" @click.native="$router.push({path:'/reportInfo',query:{id:n}})" :key="n">
        <div slot="title">内容{{n}}<br><span style="font-size: 0.6rem">201444</span></div>
        <div>asdada</div>
      </mt-cell>
    </mt-loadmore>
  </div>
</template>
<script>
  import NavBar from './navBar'
  import {
    getReportList,
  } from '../../global/report'

  export default {
    data () {
      return {
        reportStatus: 1,
        queryForPage: {
          total: 0,
          pageNum: 1,
          pageSize: 10

        },
        list: [],
        allLoaded: true
      }
    },
    watch: {
      reportStatus: function (val, oldVal) {
        this.queryForPage.pageNum = 1
        this.queryForPage.reportStatus = val
        this.getlist()
      }
    },
    methods: {
      getlist () {
        getReportList(this.queryForPage).then(res => {
          this.lists = res.data.data.list
          this.allLoaded = true// 若数据已全部获取完毕
        })
      },
      loadBottom () {
        this.allLoaded = false// 若数据已全部获取完毕
        // 加载更多数据
        this.getlist()
        this.$refs.loadmore.onBottomLoaded()
      }
    },
    components: {
      navBar: NavBar
    },
    created () {
      this.getlist()
    }
  }
</script>
<style>
</style>

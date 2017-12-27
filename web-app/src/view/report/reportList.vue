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
      <mt-cell v-for="n in list" @click.native="$router.push({path:'/reportInfo',query:{reportId:n.reportId,reportType:n.reportTypeCode,reportStatus:reportStatus}})" :key="n.id">
        <div slot="title">{{n.reportName }}<br><span style="font-size: 0.6rem">{{n.createTime}}</span></div>
        <div>{{n.reportTypeName}}</div>
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
        this.getlist()
      }
    },
    methods: {
      getlist () {
        this.queryForPage.reportStatus = this.reportStatus
        getReportList(this.queryForPage).then(res => {
          this.list = res.data.data.list
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

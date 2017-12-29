<template>
  <div style="">

    <mt-header title="功能">
      <mt-button icon="back" slot="left" @click="$router.push({path:'/'});">返回</mt-button>
      <mt-button size="small" slot="right" @click="$router.push({path:'/reportInfo'})">新增</mt-button>
    </mt-header>
    <mt-navbar v-model="reportStatus">
      <mt-tab-item :id="1">已保存</mt-tab-item>
      <mt-tab-item :id="2">已提交</mt-tab-item>
    </mt-navbar>
    <div ref="wrapper" :style="{ height: wrapperHeight + 'px',overflow: 'scroll' }">
    <loadmore
      :bottom-method="loadBottom"
      :bottom-all-loaded="allLoaded"
      :auto-fill="false"
      ref="loadmore">
      <mt-cell v-for="n in list"
               @click.native="$router.push({path:'/reportInfo',query:{reportId:n.reportId,reportType:n.reportTypeCode,reportStatus:reportStatus}})"
               :key="n.id">
        <div slot="title">{{n.reportName }}<br><span style="font-size: 0.6rem">{{n.createTime}}</span></div>
        <div>{{n.reportTypeName}}</div>
      </mt-cell>
    </loadmore>
    </div>
  </div>
</template>
<script>
  import NavBar from './navBar'
  import { Loadmore } from 'mint-ui'
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
          pageSize: 5

        },
        wrapperHeight: 0,
        list: [],
        allLoaded: false
      }
    },
    watch: {
      reportStatus: function (val, oldVal) {
        this.queryForPage.pageNum = 1
        this.list = []
        this.getlist()
      }
    },
    methods: {
      getlist () {
        this.allLoaded = true// 若数据已全部获取完毕
        this.queryForPage.reportStatus = this.reportStatus
        return getReportList(this.queryForPage).then(res => {
          this.list = this.list.concat(res.data.data.list)
          if(res.data.data.pages>this.queryForPage.pageNum){
            this.allLoaded = false
          }
          this.queryForPage.pageNum+=1
          return Promise.resolve(res)
        })
      },
      loadBottom () {
        // this.allLoaded = true// 若数据已全部获取完毕
        // 加载更多数据
        this.getlist().then(res => {
          this.$refs.loadmore.onBottomLoaded()
        })

      }
    },
    components: {
      navBar: NavBar,
      loadmore: Loadmore
    },
    created () {
      this.getlist()
    },
    mounted () {
      this.wrapperHeight = document.documentElement.clientHeight - this.$refs.wrapper.getBoundingClientRect().top
    }
  }
</script>
<style>


  /*.page-loadmore-wrapper {*/
  /*height: auto;*/
  /*overflow: scroll*/
  /*}*/
</style>

<template>
  <div style="">

    <mt-header title="考勤功能">
      <mt-button icon="back" slot="left" @click="$router.push({path:'/'})">返回</mt-button>
      <mt-button size="small" slot="right" @click="">筛选</mt-button>
    </mt-header>
    <div ref="wrapper" :style="{ height: wrapperHeight + 'px',overflow: 'scroll' }">
      <loadmore
        :bottom-method="loadBottom"
        :bottom-all-loaded="allLoaded"
        :auto-fill="false"
        ref="loadmore">
        <mt-cell v-for="n in list"
                 @click.native="$router.push({path:'/reportInfo',query:{reportId:n.reportId,reportType:n.reportTypeCode}})"
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
//todo  考勤相关
  export default {
    data () {
      return {
        queryForPage: {
          total: 0,
          pageNum: 1,
          pageSize: 10

        },
        wrapperHeight: 0,
        list: [],
        allLoaded: false
      }
    },
    watch: {

    },
    methods: {
      getlist () {
        this.allLoaded = true// 若数据已全部获取完毕
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

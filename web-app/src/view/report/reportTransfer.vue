<template>
  <div style="">

    <mt-header title="日报交接">
      <mt-button icon="back" slot="left" @click="$router.push({path:'/'});">返回</mt-button>
      <mt-button size="small" slot="right" @click="">编辑</mt-button>
    </mt-header>
    <mt-button type="default"
               @click="doMsg(1)"
               style="margin: 1rem;margin-left: 3rem;width: 5rem">保存
    </mt-button>
    <mt-button type="primary"
               @click="doMsg(2)"
               style="margin: 1rem;margin-right: 3rem;float: right;width: 5rem">提交
    </mt-button>
    <div ref="wrapper" :style="{ height: wrapperHeight + 'px',overflow: 'scroll' }">
    <loadmore
      :bottom-method="loadBottom"
      :bottom-all-loaded="allLoaded"
      :auto-fill="false"
      ref="loadmore">
      <mt-cell v-for="n in list"
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

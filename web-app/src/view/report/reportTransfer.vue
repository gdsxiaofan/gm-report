<template>
  <div style="">

    <mt-header title="日报交接">
      <mt-button icon="back" slot="left" @click="back">返回</mt-button>
      <mt-button size="small" slot="right" v-if="current===3" @click="transfer">确认</mt-button>
    </mt-header>

    <template v-if="current===1">
      <mt-cell>
        <div slot="title">
          <mt-button type="default"
                     @click="allCheck"
                     style="margin: 1rem;margin-left: 3rem;width: 5rem">{{isAllCheck?'取消':'全选'}}
          </mt-button>
        </div>
        <div>
          <mt-button type="primary"
                     @click="current=2"
                     style="margin: 1rem;margin-right: 3rem;float: right;width: 5rem">移交
          </mt-button>
        </div>
      </mt-cell>
      <div ref="wrapper" :style="{ height: wrapperHeight + 'px',overflow: 'scroll' }">
        <loadmore
          :bottom-method="loadBottom"
          :bottom-all-loaded="allLoaded"
          :auto-fill="false"
          ref="loadmore">
          <mt-checklist
            title="复选框列表"
            v-model="value"
            :options="options">
          </mt-checklist>
        </loadmore>

      </div>
    </template>
    <template v-if="current===2">
      <mt-cell v-for="i of groupList" :key="i.id" @click.native="getGroupInfoList(i.groupName)">
        <div slot="title">
          {{i.groupName}}
        </div>
      </mt-cell>
    </template>
    <template v-if="current===3">
      <mt-radio
        :title="groupName"
        v-model="userId"
        :options="groupInfoOptions">
      </mt-radio>
    </template>
  </div>
</template>
<script>
  import NavBar from './navBar'
  import {
    Loadmore,
    Toast
  } from 'mint-ui'
  import {
    getReportList,
    transferReport
  } from '../../global/report'
  import {
    getGroups
  } from '../../global/api'

  export default {
    data() {
      return {
        current: 1,
        value: [],
        reportStatus: 2,
        queryForPage: {
          total: 0,
          pageNum: 1,
          pageSize: 10

        },
        groupName: '',
        wrapperHeight: 0,
        list: [],
        groupList: [],
        groupInfoOptions: [{label: '1', value: '1'}],
        userId: '',
        allLoaded: false,
        isAllCheck: false
      }
    },
    computed: {
      options() {
        let options = []
        this.list.forEach(x => {
          options.push({label: x.reportName, value: x.reportId})
        })
        return options
      }
    },
    watch: {
      current: function (val, oldVal) {
        if (val === 2) {
          getGroups({pageSize: 0}).then(res => {
            this.groupList = res.data.data.list
          })
        }
      }

    },
    methods: {
      transfer() {
        if(!this.userId){
          Toast({
            message: '请选择移交人',
          });
          return
        }
        let ids = this.value.join(",")
        transferReport(this.userId, ids).then(res => {
          Toast({
            message: '操作成功',
          });
          this.$router.push({path:'/'})
        })
      },
      getGroupInfoList(groupName) {
        //todo
        this.current = 3
        this.groupName = groupName
      },
      back() {
        switch (this.current) {
          case 1:
            this.$router.push({path: '/'});
            break;
          case 2:
            this.current = 1
            break;
          case 3:
            this.current = 2
            break
        }
      },
      allCheck() {
        this.value = []
        if (!this.isAllCheck) {
          this.options.forEach(x => {
            this.value.push(x.value)
          })
        }
        this.isAllCheck = !this.isAllCheck

      },
      getlist() {
        this.allLoaded = true// 若数据已全部获取完毕
        this.queryForPage.reportStatus = this.reportStatus
        return getReportList(this.queryForPage).then(res => {
          this.list = this.list.concat(res.data.data.list)
          if (res.data.data.pages > this.queryForPage.pageNum) {
            this.allLoaded = false
          }
          this.queryForPage.pageNum += 1
          return Promise.resolve(res)
        })
      },
      loadBottom() {
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
    created() {
      this.getlist()
    },
    mounted() {
      this.wrapperHeight = document.documentElement.clientHeight - this.$refs.wrapper.getBoundingClientRect().top
    }
  }
</script>
<style>
  .icon-succes{ content: "\e602"; }

  /*.page-loadmore-wrapper {*/
  /*height: auto;*/
  /*overflow: scroll*/
  /*}*/
</style>

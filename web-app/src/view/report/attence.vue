<template>
  <div style="">

    <mt-header title="考勤功能">
      <mt-button icon="back" slot="left" @click="$router.push({path:'/'})">返回</mt-button>
      <mt-button size="small" slot="right" @click="pickerVisible=true">筛选</mt-button>
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
          <div slot="title">{{n.workDate }}<br><span style="font-size: 0.6rem">{{n.createTime}}</span></div>
          <div>{{n.reportTypeName}}</div>
        </mt-cell>
      </loadmore>
    </div>


    <popSelect :slots="slots"
               :show="pickerVisible"
               @hide-picker="pickerVisible=arguments[0]"
               @save="handleConfirm"></popSelect>
  </div>
</template>
<script>
  import NavBar from './navBar'
  import {Loadmore} from 'mint-ui'
  import popSelect from './compent/popSelect'
  import {
    getAttendances,
  } from '../../global/report'
  import {
    formatData
  } from '../../utils/common'
  //todo  考勤相关
  export default {
    data() {
      return {
        queryForPage: {
          total: 0,
          pageNum: 1,
          pageSize: 10,
          month: formatData.call(new Date(), "yyyy-MM")
        },
        wrapperHeight: 0,
        list: [],
        allLoaded: false,
        pickerVisible: false
      }
    },
    computed: {
      slots() {
        let year = this.queryForPage.month.split("-")[0]
        let month = this.queryForPage.month.split("-")[1]
        let yearOptions = []
        for(let i=new Date().getFullYear() - 5;i<new Date().getFullYear() + 5;i++){
          yearOptions.push(i+"")
        }
        let monthOptions = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12']
        let yearIndex, monthIndex
        yearOptions.filter((x, i) => {
          if (x === year) {
            yearIndex = i
          }
        })
        monthOptions.filter((x, i) => {
          if (x === month) {
            monthIndex = i
          }
        })
        return [
          {
            flex: 1,
            values: yearOptions,
            className: 'slot1',
            textAlign: 'right',
            defaultIndex: yearIndex
          }, {
            divider: true,
            content: '-',
            className: 'slot2'
          }, {
            flex: 1,
            values: monthOptions,
            className: 'slot3',
            textAlign: 'left',
            defaultIndex: monthIndex
          }
        ]
      }
    },
    watch: {},
    methods: {
      getlist() {
        this.allLoaded = true// 若数据已全部获取完毕
        return getAttendances(this.queryForPage).then(res => {
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
      },
      openPicker() {
        this.$refs.picker.open();
      },
      handleConfirm(date) {
        this.queryForPage.month = date[0] + "-" + date[1]
        this.pickerVisible = false
        this.queryForPage.pageNum=1
        this.getlist()
      }
    },
    components: {
      navBar: NavBar,
      loadmore: Loadmore,
      popSelect: popSelect
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


  /*.page-loadmore-wrapper {*/
  /*height: auto;*/
  /*overflow: scroll*/
  /*}*/
</style>

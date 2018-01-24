<template>
  <div style="">

    <mt-header :title="type===0?'考勤功能':'考勤详情'">
      <mt-button icon="back" slot="left" @click="goback">返回</mt-button>
      <mt-button v-show="type===0" size="small" slot="right" @click="pickerVisible=true">筛选</mt-button>
    </mt-header>
    <div v-show="type===0" ref="wrapper" :style="{ height: wrapperHeight + 'px',overflow: 'scroll' }">
      <loadmore
        :bottom-method="loadBottom"
        :bottom-all-loaded="allLoaded"
        :auto-fill="false"
        ref="loadmore">
        <mt-cell v-for="n in list"
                 @click.native="getDetail(n.id)"
                 :key="n.id">
          <div slot="title">{{n.workDate }}<br><span style="font-size: 0.6rem">{{n.week}}</span></div>
          <div>{{n.workHours}}小时  </div>
        </mt-cell>
      </loadmore>
    </div>
    <div v-show="type===1">
      <mt-cell title="工号" :value="detail.employeeNo"></mt-cell>
      <mt-cell title="姓名" :value="detail.userName"></mt-cell>
      <mt-cell title="上班时间" :value="detail.workStartTime"></mt-cell>
      <mt-cell title="下班时间" :value="detail.workEndTime"></mt-cell>
      <mt-cell title="工时" :value="detail.workHours"></mt-cell>

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
    getAttendanceDetail
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
        pickerVisible: false,
        type:0, //0 list 1  detail
        detail:{
          employeeNo:"",
          userName:"",
          workStartTime:"",
          workEndTime:"",
          workHours:""
        }
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
      goback(){
        switch (this.type){
          case 0 :
            $router.push({path:'/'})
            break;
          case 1:
            this.type=0
            break

        }
      },
      getDetail(id){
         this.type=1
        getAttendanceDetail(id).then(res=>{
          this.detail =res.data.data
        })
      },
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

<template>
  <div style="">

    <mt-header :title="type===0?'考勤功能':'考勤详情'">
      <mt-button icon="back" slot="left" @click="goback">返回</mt-button>
      <mt-button v-show="type===0" size="small" slot="right" @click="pickerVisible=true">筛选</mt-button>
    </mt-header>
    <div id="container" ref="asdada"></div>
    <!--<div v-show="type===0" ref="wrapper" :style="{ height: wrapperHeight + 'px',overflow: 'scroll' }">-->
      <!--<loadmore-->
        <!--:bottom-method="loadBottom"-->
        <!--:bottom-all-loaded="allLoaded"-->
        <!--:auto-fill="false"-->
        <!--ref="loadmore">-->
        <!--<mt-cell v-for="n in list"-->
                 <!--@click.native="getDetail(n.id)"-->
                 <!--:key="n.id">-->
          <!--<div slot="title">{{n.workDate }}<br><span style="font-size: 0.6rem">{{n.week}}</span></div>-->
          <!--<div>{{n.workHours}}小时</div>-->
        <!--</mt-cell>-->
      <!--</loadmore>-->
    <!--</div>-->
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
  import Calendar from 'mob-calendar';
  //todo  考勤相关
  export default {
    data() {
      return {
        queryForPage: {
          total: 0,
          pageNum: 1,
          pageSize: 0,
          month: formatData.call(new Date(), "yyyy-MM")
        },
        wrapperHeight: 0,
        list: [],
        allLoaded: false,
        pickerVisible: false,
        type: 0, //0 list 1  detail
        detail: {
          employeeNo: "",
          userName: "",
          workStartTime: "",
          workEndTime: "",
          workHours: ""
        }
      }
    },
    computed: {
      slots() {
        let year = this.queryForPage.month.split("-")[0]
        let month = this.queryForPage.month.split("-")[1]
        let yearOptions = []
        for (let i = new Date().getFullYear() - 5; i < new Date().getFullYear() + 5; i++) {
          yearOptions.push(i + "")
        }
        let monthOptions = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12']
        let yearIndex, monthIndex
        yearOptions.forEach((x, i) => {
          if (x === year) {
            yearIndex = i
          }
        })
        monthOptions.forEach((x, i) => {
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
      initClendar(year, month) {
        this.getlist().then(res => {
          let attenceArr = this.list.map(x => {
            return {
              stamp: new Date(x.workDate.replace(new RegExp("-","gm"),"/")).getTime(),
              workHours:x.workHours,
              className: 'target',
            }
          })
          console.log(attenceArr)
          // 实例化一个日历插件，具体参数意义可以看下文中的参数列表
          new Calendar({
            // clickTarget: 'target',
            container: 'container',
            angle: 10,
            isMask: false, // 是否需要弹层
            beginTime: [],//如空数组默认设置成1970年1月1日开始,数组的每一位分别是年月日。
            endTime: [],//如空数组默认设置成次年12月31日,数组的每一位分别是年月日。
            recentTime: [year, month, 1],//如空数组默认设置成当月1日,数组的每一位分别是年月日。
            isSundayFirst: true, // 周日是否要放在第一列
            isShowNeighbor: false, // 是否展示相邻月份的月尾和月头
            isToggleBtn: false, // 是否展示左右切换按钮
            isChinese: true, // 是否是中文
            monthType: 0, // 0:1月, 1:一月, 2:Jan, 3: April
            canViewDisabled: false, // 是否可以阅读不在范围内的月份
            beforeRenderArr: attenceArr,
            success: function (item, arr, cal) {
              console.log(item, arr);
              cal.hideBackground();
            },
            switchRender: function (year, month, cal) {
              console.log('计算机识别的 - 年份: ' + year + ' 月份: ' + month);
              var data = [{
                'stamp': 1507737600000,
                'className': 'able1',
              }];
              cal.renderCallbackArr(data);
            }
          });
          attenceArr.forEach(x=>{

            let day=document.getElementsByClassName("container-item-"+x.stamp+" target")[0].getElementsByTagName("i")[0].innerHTML
            if(day.indexOf("h")===-1){
              document.getElementsByClassName("container-item-"+x.stamp+" target")[0].getElementsByTagName("i")[0].innerHTML=day+"<br><span>"+x.workHours+"h</span>"
            }
          })

        })

      },
      goback() {
        switch (this.type) {
          case 0 :
            this.$router.push({path: '/'})
            break;
          case 1:
            this.type = 0
            break

        }
      },
      getDetail(id) {
        this.type = 1
        getAttendanceDetail(id).then(res => {
          this.detail = res.data.data
        })
      },
      getlist() {
        this.allLoaded = true// 若数据已全部获取完毕
        return getAttendances(this.queryForPage).then(res => {
          this.list = res.data.data.list
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
        this.queryForPage.pageNum = 1
        this.initClendar(date[0], date[1])
      }
    },
    components: {
      navBar: NavBar,
      loadmore: Loadmore,
      popSelect: popSelect
    },
    created() {
    },
    mounted() {
      // this.wrapperHeight = document.documentElement.clientHeight - this.$refs.wrapper.getBoundingClientRect().top
      let year = new Date().getFullYear()
      let month = new Date().getMonth() + 1
      this.initClendar(year, month)
    }
  }
</script>
<style>

  .calendar-box .calendar-item .calendar-item-body li {
    color: #cceeff;
    line-height :32px
  }
  .calendar-box .calendar-item .calendar-item-body li i{
    line-height:1rem!important;

  }
  .target {
    line-height:1rem!important;
    color: #0a0a0a !important;
    font-size: 0.7rem;

  }
  /*.page-loadmore-wrapper {*/
  /*height: auto;*/
  /*overflow: scroll*/
  /*}*/
</style>

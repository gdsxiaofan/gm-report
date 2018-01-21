<template>
  <div class="">
    <mt-header :title="reportTypeName">
      <mt-button icon="back" slot="left" @click="$router.go(-1);">返回</mt-button>
      <mt-button size="small" slot="right" @click="abled=true" v-if="report.id&&$route.query.reportStatus===1&&!abled">
        编辑
      </mt-button>
    </mt-header>
    <mt-field :readonly="true" v-if="!report.id" label="类别" placeholder="" @click.native="showPop(0)" :value="reportTypeName"></mt-field>
    <mt-field :disabled="!abled" :key="1" label="设备名称" placeholder="" :readonly="true" @click.native="showPop(1)"
              :value="report.deviceName"></mt-field>
    <template v-if="report.reportType===1">
      <mt-field :key="2" :disabled="!abled" label="设备故障" placeholder="" v-model="report.deviceFault"></mt-field>
      <mt-field :key="2" :disabled="!abled" label="处理方法" placeholder="" v-model="report.dealMethod"></mt-field>
      <mt-field :key="3" :disabled="!abled" label="区域" placeholder=""  v-model="report.areaName"></mt-field>
      <mt-field :key="4" :disabled="!abled" label="停线时间" placeholder="" @click.native="$refs.picker.open()"
                :value="report.stopTime"></mt-field>
    </template>


    <template v-else>
      <mt-field :key="5" :disabled="!abled" label="维修保养设备" placeholder="" v-model="report.repairComment"></mt-field>
      <mt-field :key="6" :disabled="!abled" label="更换部件" placeholder="" v-model="report.replaceParts"></mt-field>
      <mt-field :key="7" :disabled="!abled" label="验证措施" placeholder="" v-model="report.validateMethod"></mt-field>
      <mt-field :key="8" :disabled="!abled" label="标定体积" placeholder="" v-model="report.calibratedVolume"></mt-field>
      <mt-field :key="9" :disabled="!abled" label="状态确认" :readonly="true" placeholder="" @click.native="showPop(2)"
                :value="repairStatusName"></mt-field>
      <mt-field :key="10" :disabled="!abled" label="修复后首车号" placeholder="" v-model="report.repairCarNo"></mt-field>
    </template>
    <template v-if="abled">
      <mt-button type="default"
                 @click="doMsg(1)"
                 style="margin: 1rem;margin-left: 3rem;width: 5rem">保存
      </mt-button>
      <mt-button type="primary"
                 @click="doMsg(2)"
                 style="margin: 1rem;margin-right: 3rem;float: right;width: 5rem">提交
      </mt-button>
    </template>
    <popSelect :slots="slots"
               :show="show"
               @hide-picker="show=arguments[0]"
               @save="onValuesChange"></popSelect>

    <mt-datetime-picker
      ref="picker"
      type="datetime"
      month-format="{value} 月"
      date-format="{value} 日"
      hourFormat="{value} 时"
      minuteFormat="{value} 分"
      @confirm="confirm">
    </mt-datetime-picker>
  </div>
</template>
<script>
  import {
    formatData
  } from '../../utils/common'
  import NavBar from './navBar'
  import popSelect from './compent/popSelect'
  import { MessageBox } from 'mint-ui'
  import {
    getFault,
    addFault,
    updateFault,
    deleteFault,
    getRepair,
    addRepair,
    updateRepair,
    deleteRepair
  } from '../../global/report'

  export default {
    data () {
      return {
        abled: true,//true : 可编辑 flase：  不可编辑
        show: false,
        popType: 0,//select类型 0  日报类型  1 设备名称
        report: {
          id: '',
          reportType: 1,
          stopTime: formatData.call(new Date(), 'yyyy-MM-dd hh:mm:ss'),
          deviceName: '',
          deviceId: 0,
          deviceFault: '',
          dealMethod: '',
          areaName:'',
          repairComment: '',
          replaceParts: '',
          repairStatus: 1,
          validateMethod: '',
          calibratedVolume: '',
          repairCarNo: ''
        }
      }
    },
    computed: {
      reportTypeName () {
        return this.report.reportType === 1 ? '故障记录' : '修复验证'
      },
      repairStatusName () {
        return this.report.repairStatus === 1 ? '未修复' : '已修复'
      },
      slots () {
        let solt = []
        let options
        let defaultIndex
        switch (this.popType) {
          case 0:
             options=[
              {key: '故障记录', value: 1},
              {key: '修复验证', value: 2}]
             defaultIndex=0;
            solt.push({
              values: options,
              defaultIndex: defaultIndex
            })
            break
          case 1:
             options=this.$store.getters.dictionary
             defaultIndex=0
            options.filter((x,i)=>{
              if(x.value===this.report.deviceId){
                defaultIndex=i
              }
            })
            solt.push({
              values: options,
              defaultIndex:defaultIndex
            })
            break
          case 2:
             options=[
              {key: '未修复', value: 1},
              {key: '已修复', value: 2}]
             defaultIndex=0
            options.filter((x,i)=>{
              if(x.value===this.report.repairStatus){
                defaultIndex=i
              }
            })
            solt.push({
              values: options,
              defaultIndex:defaultIndex
            })
            break
        }
        return solt
      }
    },
    methods: {
      doMsg (reportStatus) {
        MessageBox.confirm('确定执行此操作?').then(action => {
          this.report.reportStatus = reportStatus
          if (this.report.id) {//update
            this.update(this.report).then(res => {
              this.$router.push({path: '/reportList'})
            })
          } else {//add
            this.add(this.report).then(res => {
              this.$router.push({path: '/reportList'})
            })
          }

        }).catch(e => {
          console.log(e)
        })
      },
      add (data) {
        switch (data.reportType) {
          case 2://修复验证
            return addRepair(data)
          case 1://故障记录
            return addFault(data)
        }
      },
      update (data) {
        switch (this.report.reportType) {
          case 2://修复验证
            return updateRepair(data)
          case 1://故障记录
            return updateFault(data)
        }
      },
      showPop (popType) {
        if (this.abled) {
          this.show = true
          this.popType = popType
        }
      },
      onValuesChange (value) {
        this.show = false
        value=value[0]
        switch (this.popType) {
          case 0:
            this.report.reportType = value.value
            break
          case 1:
            this.report.deviceName = value.key
            this.report.deviceId = value.value
            break
          case 2:
            this.report.repairStatus = value.value
            break
        }
      },
      confirm (date) {
        this.report.stopTime = formatData.call(date, 'yyyy-MM-dd hh:mm:ss')
      }
    },
    components: {
      navBar: NavBar,
      popSelect: popSelect
    },
    mounted () {
      console.log(this.$route.query.reportId)
      if (this.$route.query.reportId) {
        this.abled = false
        if (this.$route.query.reportType === 1) {//故障记录
          getFault(this.$route.query.reportId).then(res => {
            this.report = res.data.data
            this.report.reportType = this.$route.query.reportType
          })
        } else if (this.$route.query.reportType === 2) {//修复验证
          getRepair(this.$route.query.reportId).then(res => {
            this.report = res.data.data
            this.report.reportType = this.$route.query.reportType
          })
        }
      }
    }
  }
</script>
<style>
</style>

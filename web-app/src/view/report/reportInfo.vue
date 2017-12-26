<template>
  <div class="">
    <mt-header title="故障记录">
      <mt-button icon="back" slot="left" @click="$router.go(-1);">返回</mt-button>
      <mt-button size="small" slot="right" @click="">编辑</mt-button>
    </mt-header>

    <mt-field label="类别" placeholder="" @click.native="show=true;popType=0" :value="reportTypeName"></mt-field>
    <mt-field label="设备名称" placeholder="" @click.native="show=true;popType=1" :value="report.deviceName"></mt-field>
    <!--<mt-field label="邮箱" placeholder="请输入邮箱" type="email" v-model="email"></mt-field>-->
    <!--<mt-field label="密码" placeholder="请输入密码" type="password" v-model="password"></mt-field>-->
    <mt-field label="区域" placeholder="" @click.native="$refs.picker.open()" :value="report.stopTime"></mt-field>
    <mt-field label="停线时间" placeholder="" @click.native="$refs.picker.open()" :value="report.stopTime"></mt-field>
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

  export default {
    data () {
      return {
        show: false,
        popType: 0,//select类型 0  日报类型  1 设备名称
        report: {
          id: '',
          reportType: 0,
          stopTime:formatData.call(new Date(), "yyyy-MM-dd hh:mm:ss"),
          deviceName:'',
          deviceId:0
        }
      }
    },
    computed: {
      reportTypeName () {
        return this.report.reportType === 1 ? '故障记录' : '修复验证'
      },
      slots () {
        let solt = []
        switch (this.popType){
          case 0:
            solt.push({
              values: [
                {key: '故障记录', value: 0},
                {key: '修复验证', value: 1}],
            })
            break;
          case 1:
            solt.push({
              values: this.$store.getters.dictionary,
            })
            break;
        }
        return solt
      }
    },
    methods: {
      onValuesChange ( value) {
        this.show = false
        console.log(value)
        switch (this.popType){
          case 0:
            this.report.reportType=value.value
            break;
          case 1:
            this.report.deviceName=value.key
            this.report.deviceId=value.value
            break;
        }
      },
      confirm(date){
        this.report.stopTime=formatData.call(date, "yyyy-MM-dd hh:mm:ss")
      }
    },
    components: {
      navBar: NavBar,
      popSelect:popSelect
    }
  }
</script>
<style>
</style>

<template>
  <div style="">

    <mt-header :title="title">
      <mt-button icon="back" slot="left" @click="back">返回</mt-button>
    </mt-header>

    <template v-if="current===0">
      <mt-cell title="下载设备修复验证记录表" @click.native="current=1">
      </mt-cell>
      <mt-cell title="下载设备历史故障记录表" @click.native="current=2">
      </mt-cell>
      <mt-cell title="下载维修交接班跟踪表" @click.native="current=3">
      </mt-cell>
    </template>
    <template v-if="current!==0">
      <mt-field v-if="current===1" label="设备名称" placeholder="" @click.native="showPop(1)"
                :readonly="true" :value="queryForPage.deviceId"></mt-field>
      <mt-field v-if="current===2" label="区域" placeholder="" @click.native="showPop(0)"
                :readonly="true" :value="queryForPage.areaId"></mt-field>
      <mt-field :key="4" label="开始时间时间" placeholder="" @click.native="$refs.begin.open()"
                :value="queryForPage.startTime"></mt-field>
      <mt-field :key="5" label="停线时间" placeholder="" @click.native="$refs.end.open()"
                :value="queryForPage.endTime"></mt-field>
      <a
         download
         style="text-decoration:none"
         :href="'/v1/'+(current===3?'report':current===1?'repair':'fault')+'/download?startTime='+queryForPage.startTime+'&endTime='+queryForPage.endTime+'&deviceId='+queryForPage.deviceId+'&areaId='+queryForPage.areaId">
        <mt-button type="primary" size="large"
                   style="margin-top: 1rem;;"> 下载
        </mt-button>
      </a>
    </template>
    <mt-datetime-picker
      ref="begin"
      type="date"
      month-format="{value} 月"
      date-format="{value} 日"
      @confirm="queryForPage.startTime= formatData(arguments[0])">
    </mt-datetime-picker>
    <mt-datetime-picker
      ref="end"
      type="date"
      month-format="{value} 月"
      date-format="{value} 日"
      @confirm="queryForPage.endTime= formatData(arguments[0])">
    </mt-datetime-picker>
    <popSelect :slots="slots"
               :show="show"
               @hide-picker="show=arguments[0]"
               @save="onValuesChange"></popSelect>

  </div>
</template>
<script>
  import NavBar from './navBar'
  import popSelect from './compent/popSelect'
  import {
    formatData
  } from '../../utils/common'
  import {
    down
  } from '../../global/api'
  //todo
  export default {
    data () {
      return {
        popType:0,
        show: false,
        current: 0,
        queryForPage: {
          startTime: formatData.call(new Date(), 'yyyy-MM-dd'),
          endTime: formatData.call(new Date(), 'yyyy-MM-dd'),
          deviceId:1,
          areaId:1
        }
      }
    },
    computed: {
      title () {
        let title = ''
        switch (this.current) {
          case 0:
            title = '文件下载'
            break
          case 1:
            title = '设备修复验证记录表'
            break
          case 2:
            title = '设备历史故障记录表'
            break
          case 3:
            title = '维修交接班跟踪表'
            break
        }
        return title
      },
      slots () {
        //todo   0需要换成  区域
        let solt = []
        let options
        let defaultIndex
        switch (this.popType) {
          case 0:
            options = [
              {key: '故障记录', value: 1},
              {key: '修复验证', value: 2}]
            defaultIndex = 0
            solt.push({
              values: options,
              defaultIndex: defaultIndex
            })
            break
          case 1:
            options = this.$store.getters.dictionary
            defaultIndex = 0
            options.filter((x, i) => {
              if (x.value === this.queryForPage.deviceId) {
                defaultIndex = i
              }
            })
            solt.push({
              values: options,
              defaultIndex: defaultIndex
            })
            break
        }
        return solt
      }
    },
    watch: {
      current: function (val, oldVal) {
        this.queryForPage.startTime=formatData.call(new Date(), 'yyyy-MM-dd')
        this.queryForPage.endTime=formatData.call(new Date(), 'yyyy-MM-dd')
      }

    },
    methods: {
      onValuesChange (value) {
        this.show = false
        switch (this.popType) {
          case 0:
            this.queryForPage.areaId = value.value
            break
          case 1:
            this.queryForPage.deviceId = value.value
            break
        }
      },
      showPop (popType) {
        this.show = true
        this.popType = popType
      },
      back () {
        switch (this.current) {
          case 0:
            this.$router.push({path: '/'})
            break
          case 1:
            this.current = 0
            break
          case 2:
            this.current = 0
            break
          case 3:
            this.current = 0
            break
        }
      },
      formatData (date) {
        return formatData.call(date, 'yyyy-MM-dd')
      }

    },
    components: {
      navBar: NavBar,
      popSelect:popSelect
    },
    created () {
    },
    mounted () {
    },
  }
</script>
<style>
  .icon-succes {
    content: "\e602";
  }

  /*.page-loadmore-wrapper {*/
  /*height: auto;*/
  /*overflow: scroll*/
  /*}*/
</style>

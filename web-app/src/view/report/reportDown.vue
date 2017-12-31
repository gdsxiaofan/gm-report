<template>
  <div style="">

    <mt-header :title="title">
      <mt-button icon="back" slot="left" @click="back">返回</mt-button>
    </mt-header>

    <template v-if="current===0">
      <mt-cell title="设备修复验证记录表" @click.native="current=1">
      </mt-cell>
      <mt-cell title="设备历史故障记录表" @click.native="current=2">
      </mt-cell>
      <mt-cell title="维修交接班跟踪表" @click.native="current=3">
      </mt-cell>
    </template>
    <template v-if="current!==0">
      <mt-field :key="4"  label="开始时间时间" placeholder="" @click.native="$refs.begin.open()"
                :value="queryForPage.startTime"></mt-field>
      <mt-field :key="5"  label="停线时间" placeholder="" @click.native="$refs.end.open()"
                :value="queryForPage.endTime"></mt-field>
      <a  target="_blank"
          style="text-decoration:none"
          :href="'/v1/report/download?startTime='+queryForPage.startTime+'&endTime='+queryForPage.endTime">
      <mt-button type="primary" size="large"
                 style="margin-top: 1rem;;">   下载
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
  </div>
</template>
<script>
  import NavBar from './navBar'
  import {
    formatData
  } from '../../utils/common'
  import {
    down
  } from '../../global/api'
//todo
  export default {
    data() {
      return {
        current: 0,
        queryForPage:{
          startTime: formatData.call(new Date(), 'yyyy-MM-dd'),
          endTime:formatData.call(new Date(), 'yyyy-MM-dd')
      }
      }
    },
    computed: {
      title(){
        let title=''
        switch (this.current) {
          case 0:
            title='文件下载'
            break;
          case 1:
            title='设备修复验证记录表'
            break;
          case 2:
            title='设备历史故障记录表'
            break;
          case 3:
            title='维修交接班跟踪表'
            break
        }
        return title
      }
    },
    watch: {
      current: function (val, oldVal) {
      }

    },
    methods: {
      back() {
        switch (this.current) {
          case 0:
            this.$router.push({path: '/'});
            break;
          case 1:
            this.current = 0
            break;
          case 2:
            this.current = 0
            break;
          case 3:
            this.current = 0
            break
        }
      },
      formatData(date){
        return  formatData.call(date, 'yyyy-MM-dd')
      }

    },
    components: {
      navBar: NavBar,
    },
    created() {
    },
    mounted() {
    }
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

<template>
  <div>
    <Row>
      <Col :span="24" style="margin-top:0.2%">
      时间区间:
      <DatePicker type="daterange" :value="[startTime,endTime]"
                  @on-change='startTime=arguments[0][0];endTime=arguments[0][1]'
                  placeholder="选择日期和时间"
                  format="yyyy-MM-dd"
                  style="width: 300px" transfer
                  :clearable="false"></DatePicker>
      </Col>
      <Col :span="24" style="margin-top:2%">
      下载设备修复验证记录表:
      <Select v-model="target" style="width:200px">
        <Option v-for="item in dictionary" :value="item.key" :key="item.key">{{ item.value }}</Option>
      </Select>
      <a
        download
        style="text-decoration:none"
        :href="'/v1/repair/download?startTime='+startTime+'&endTime='+endTime+'&deviceId='+target">
        <Button type="primary"> 下载
        </Button>
      </a>
      </Col>
      <Col :span="24" style="margin-top:2%">
      下载设备历史故障记录表:
      <a
        download
        style="text-decoration:none"
        :href="'/v1/fault/download?startTime='+startTime+'&endTime='+endTime">
        <Button type="primary"> 下载
        </Button>
      </a>
      </Col>
      <Col :span="24" style="margin-top:2%">
      下载维修交接班跟踪表:<a
      download
      style="text-decoration:none"
      :href="'/v1/report/download?startTime='+startTime+'&endTime='+endTime">
      <Button type="primary"> 下载
      </Button>
    </a>
      </Col>
    </Row>
  </div>
</template>

<script>
  import {
    getDictionaryInfoByType
  } from '../../../src/api/api'
  import {
    formatData
  }from "../../lib/utils/common"
  export default {
    data() {
      return {
        startTime: formatData.call(new Date(),"yyyy-MM-dd"),
        endTime: formatData.call(new Date(),"yyyy-MM-dd"),
        dictionary: [],
        target: 0
      }
    },
    computed: {},
    methods: {},
    created() {
      getDictionaryInfoByType(1).then(res => {
        this.dictionary = res.data.data.map(x => {
          return {value: x.dictionaryName, key: x.id}
        })
        this.target = this.dictionary[0].key
      })
    }
  }
</script>

<style>

</style>

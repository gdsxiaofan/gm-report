<template>
  <div class="">
    <mt-header  title="故障记录">
      <mt-button icon="back" slot="left" @click="$router.go(-1);">返回</mt-button>
      <mt-button size="small" slot="right" @click="">编辑</mt-button>
    </mt-header>
    <mt-popup style="width: 100%"
      v-model="reportType"
      popup-transition="popup-fade"
      position="bottom">
      <section style="width:100%; height: 100%;">
        <div class="padding">
          <span>取消</span>
          <span  style="float: right;">完成</span>
        </div>
        <mt-picker :slots="slots" @change="onValuesChange"></mt-picker>
      </section>

    </mt-popup>

  </div>
</template>
<script>
  import NavBar from './navBar'

  export default {
    data () {
      return {
        reportType:true,
        slots: [
          {
            values: ['2015-01', '2015-02', '2015-03', '2015-04', '2015-05', '2015-06'],
          }
        ]
      }
    },
    methods: {
      onValuesChange(picker, values) {
        if (values[0] > values[1]) {
          picker.setSlotValue(1, values[0]);
        }
      }
    },
    components:{
      navBar:NavBar
    }
  }
</script>
<style>
</style>

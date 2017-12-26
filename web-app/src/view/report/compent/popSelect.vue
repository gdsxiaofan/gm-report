<template>
  <mt-popup style="width: 100%"
            :value="show"
            popup-transition="popup-fade"
            :closeOnClickModal="false"
            position="bottom">
    <section style="width:100%; height: 100%;">
      <div class="padding">
        <span @click="hidePicker">取消</span>
        <span style="float: right;" @click="save">完成</span>
      </div>
      <mt-picker :slots="slots"
                 valueKey="key"
                 @change="onValuesChange"></mt-picker>
    </section>
  </mt-popup>

</template>

<script>
  export default {
    props:['slots','show'],
    data(){
      return {
        value:''
      }
    },
    methods:{
      save() { //保存所选地区
        this.$emit('save', this.value);
      },
      onValuesChange(picker, values){
        this.value=values[0]
      },
      hidePicker(){
        this.$emit('hide-picker', false);
      }
    }
  }
</script>

<style>

</style>

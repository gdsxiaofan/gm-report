<template>
  <mt-popup style="width: 100%"
            :value="show"
            @input="hidePicker"
            popup-transition="popup-fade"
            :closeOnClickModal="true"
            position="bottom">
    <section style="width:100%; height: 100%;">
      <div class="padding">
        <span class="action" @click="hidePicker(false)">取消</span>
        <span class="action"  style="float: right;" @click="save">完成</span>
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
        let value=this.value
        this.$emit('save', value);
      },
      onValuesChange(picker, values){
        this.value=values
      },
      hidePicker(show){
        if(!show) {
          this.$emit('hide-picker', false);
        }
      }
    }
  }
</script>

<style lang="css">
  .action{
    display: inline-block;
    width: 50%;
    text-align: center;
    line-height: 40px;
    font-size: 16px;
    color: #26a2ff;
  }
</style>

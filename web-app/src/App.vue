<template>
  <div id="app">

    <router-view></router-view>
  </div>
</template>

<script>
  import {
    getDictionaryInfoByType
  } from '../src/global/api'
export default {
  name: 'app',
  created(){
    getDictionaryInfoByType(1).then(res=>{
      this.$store.dispatch('setDictionaryInfo', res.data.data.map(x=>{
        return {key:x.dictionaryName,value:x.id}
      }))
    })
  }
}
</script>

<style>
	*{
		margin: 0px;
		padding:0px;
	}
	body{
		background: #f4f4f4;
	}
</style>

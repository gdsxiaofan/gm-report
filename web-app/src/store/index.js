/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    loginForm: {
      username: '',
      password: ''
    },
    dictionary: [],
    // 登录状态
    loginStatus: false
  },
  getters: {
    userLoginStatus: state => {
      console.log('入参zzzzz：', state.loginStatus)
      return state.loginStatus
    },
    dictionary: state => state.dictionary
  },
  mutations: {
    SET_LOGINSTATUS: (state, newLoginStatus) => {
      state.loginStatus = newLoginStatus
      console.log('入参：', state.loginStatus)
    },
    setDictionaryInfo: (state, dictionary) => {
      state.dictionary = dictionary
    }
  },
  actions: {
    setloginStatus ({dispatch, commit, state}, newLoginStatus) {
      commit('SET_LOGINSTATUS', newLoginStatus)
    },
    setDictionaryInfo ({dispatch, commit, state}, dictionary) {
      commit('setDictionaryInfo', dictionary)
    },
  }
})
export default store

/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
var merge = require('webpack-merge')
var prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"'
})

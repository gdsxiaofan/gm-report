/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
var merge = require('webpack-merge')
var devEnv = require('./dev.env')

module.exports = merge(devEnv, {
  NODE_ENV: '"testing"'
})

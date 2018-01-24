<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <!--<Col :span="2" style="margin-top:0.2%">-->
        <!--角色：-->
        <!--</Col>-->
        <!--<Col :span="3">-->
        <!--<Select v-model="queryCondition.roleId" filterable not-found-text>-->
        <!--<Option :value="0" :key="0">全部</Option>-->
        <!--<Option v-for="item in RoleList" :value="item.id" :key="item.id">{{ item.roleName }}</Option>-->
        <!--</Select>-->
        <!--</Col>-->
        <Col :span="2" :offset="1" style="margin-top:0.2%">
        设备名：
        </Col>
        <Col :span="4">
        <Input type="text" v-model="queryCondition.employeeNo" placeholder="请输入..."></Input>
        </Col>
        <Col :span="2" offset="8">
        <Button type="primary" shape="circle" icon="ios-search" @click="getlist">查询</Button>
        </Col>
        <Col :span="2" offset="1">
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="add">
          新建设备
        </Button>
        </Col>
      </Row>
    </Card>
    <Table border :columns="columns" @on-selection-change="getSelection" :data="list"></Table>
    <div style="margin: 10px;overflow: hidden">
      <div style="float: right;">
        <Page :total="queryCondition.total" :current.sync="queryCondition.pageNum" @on-change="getlist" show-total
              show-elevator></Page>
      </div>
    </div>
    <Modal v-model="deviceModal.isShow"
           :title="deviceModal.title"
    >
      <Form ref="device" :model="deviceInfo" :rules="deviceRules" :label-width="80">
        <Form-item label="设备名：" prop="deviceName">
          <Input type="text" v-model="deviceInfo.deviceName" placeholder="">
          </Input>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="deviceModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="dodevice" :loading="deviceModal.isLoading">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {
    getdeviceList,
    updatedevice,
    deldevice,
    adddevice,
    isActivedevice
  } from '../../api/role/device'

  export default {
    data() {
      return {
        deviceInfo: {
          id: '',
          deviceName: '',
        },
        deviceRules: {
          deviceName: [
            {required: true, message: '请填写设备', trigger: 'blur'}
          ]
        },
        deviceModal: {
          isShow: false,
          isLoading: false,
          title: ''
        },
        selection: '',
        queryCondition: {
          pageSize: 10,
          pageNum: 1,
          deviceName: '',
          total: 0
        },
        columns: [
          {
            title: '编号',
            key: 'id'
          },
          {
            title: '设备名',
            key: 'deviceName'
          },
          {
            title: '操作',
            width: 300,
            render: (h, params) => h('div', [
              h('Button', {
                  props: {
                    type: 'info'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.$refs['device'].resetFields()
                      this.deviceInfo.id = params.row.id
                      this.deviceInfo.deviceName = params.row.deviceName
                      this.deviceModal.isShow = true
                      this.deviceModal.title = '修改设备'
                    }
                  }
                },
                '修改'
              ),
              h('Button', {
                props: {
                  type: 'error'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.$Modal.confirm({
                      title: '是否删除',
                      content: '<p>' + params.row.employeeName + '</p>',
                      loading: true,
                      onOk: () => {
                        deldevice(params.row.id).then(res => {
                          this.$Message.success(res.data.message);
                          this.$Modal.remove()
                          this.getlist()
                        })

                      }
                    });
                  }
                }
              }, '删除')]
            )
          }
        ],
        list: [],
        RoleList: []
      }
    },
    methods: {
      add() {
        this.$refs['device'].resetFields()
        this.deviceModal.title = '新增设备'
        this.deviceModal.isShow = true

      },
      getSelection(selection) {
        this.selection = selection
      },
      getlist(pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getdeviceList(this.queryCondition).then(res => {
          this.queryCondition.pageNum = res.data.data.pageNum
          this.list = res.data.data.list
          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        });
      },
      dodevice() {
        this.$refs['device'].validate((valid) => {
          if (valid) {
            this.deviceModal.isLoading = true
            if (this.deviceModal.title === '修改设备') {
              updatedevice(this.deviceInfo).then(res => {
                this.deviceModal.isLoading = false
                this.deviceModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            } else if (this.deviceModal.title === '新增设备') {
              this.deviceInfo.id = null
              adddevice(this.deviceInfo).then(res => {
                this.deviceModal.isLoading = false
                this.deviceModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            }
          }
        })
      }
    },
    mounted() {
      this.$refs['device'].resetFields()
      this.getlist()
    }
  }
</script>

<style>

</style>

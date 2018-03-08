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
        工号：
        </Col>
        <Col :span="2">
        <Input type="text" v-model="queryCondition.employeeNo" placeholder="请输入..."></Input>
        </Col>
        <Col :span="2" :offset="1" style="margin-top:0.2%">
        姓名：
        </Col>
        <Col :span="2">
        <Input type="text" v-model="queryCondition.userName" placeholder="请输入..."></Input>
        </Col>
        <Col :span="2" offset="1">
        <Button type="primary" shape="circle" icon="ios-search" @click="getlist">查询</Button>
        </Col>
        <Col :span="2" offset="1">
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="add">
          新建用户
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
    <Modal v-model="userModal.isShow"
           :title="userModal.title"
    >
      <Form ref="user" :model="userInfo" :rules="userRules" :label-width="80">
        <Form-item label="工号：" v-if="userModal.title!=='修改用户'" prop="employeeNo">
          <Input type="text" v-model="userInfo.employeeNo" placeholder="工号">
          <Icon type="ios-person-outline" slot="prepend"></Icon>
          </Input>
        </Form-item>
        <FormItem v-if="userModal.title==='修改用户'" label="密码：">
          <Input type="password" v-model="userInfo.employeePassword"
                 :placeholder="userModal.title==='修改用户'?'不填则不修改':'密码'">
          <Icon type="ios-locked-outline" slot="prepend"></Icon>
          </Input>
        </FormItem>
        <Form-item label="姓名：" prop="name">
          <Input type="text" v-model="userInfo.name" placeholder=""/>
        </Form-item>
        <!--<Form-item label="排班：" >-->
        <!--<Select v-model="userInfo.shiftsNo" style="width:200px">-->
        <!--<Option :value="0">无排班</Option>-->
        <!--<Option :value="1">早班</Option>-->
        <!--<Option :value="2">晚班</Option>-->
        <!--</Select>-->
        <!--</Form-item>-->
        <Form-item label="手机号码：" prop="mobileNo">
          <Input type="text" v-model="userInfo.mobileNo" placeholder=""/>
        </Form-item>
        <Form-item label="级别" prop="levelId">
          <Select v-model="userInfo.levelId" style="width:200px" filterable>
            <Option v-for="item in levels" :value="item.optionCode" :key="item.optionCode">{{ item.opitonName }}
            </Option>
          </Select>
        </Form-item>
        <Form-item label="班组" prop="groupId">
          <Select v-model="userInfo.groupId" style="width:200px" filterable>
            <Option v-for="item in groups" :value="item.id" :key="item.id">{{ item.groupName }}</Option>
          </Select>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="userModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doUser" :loading="userModal.isLoading">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {
    getUserList,
    updateUser,
    delUser,
    addUser,
    isActiveUser,
    checkEmployeeNo
  } from '../../api/role/showUser'
  import {
    getgroupList,
  } from '../../api/role/group'
  import {
    getLevels
  } from '../../api/api'

  export default {
    data () {
      return {
        userInfo: {
          id: '',
          mobileNo: '',
          employeeNo: '',
          name: '',
          employeePassword: '',
          levelId: '',
          groupId: ''
        },
        userRules: {
          employeeNo: [
            {required: true, message: '请填写工号', trigger: 'blur'},
            {
              validator: (rule, value, callback) => {
                checkEmployeeNo(value).then(res => {
                  if (res.data.data === false) {
                    callback(new Error('工号不可重复'))
                  } else {
                    callback()
                  }
                }).catch(e => {
                  callback(new Error('系统异常'))
                })
              }, trigger: 'blur'
            }
          ],
          name: [
            {required: true, message: '请填写姓名', trigger: 'blur'}
          ],
          mobileNo: [
            {required: true, message: '请填写手机号', trigger: 'blur'}
          ],
          employeePassword: [
            {required: true, message: '请填写密码', trigger: 'blur'}
          ],
          levelId: [
            {type: 'number', required: true, message: '请选择级别', trigger: 'change'},
          ],
          groupId: [
            {type: 'number', required: true, message: '请选择班组', trigger: 'change'},
          ]
        },
        userModal: {
          isShow: false,
          isLoading: false,
          title: ''
        },
        selection: '',
        queryCondition: {
          pageSize: 10,
          pageNum: 1,
          roleId: null,
          userName: '',
          employeeNo: '',
          total: 0
        },
        columns: [
          {
            title: '编号',
            key: 'id'
          },
          {
            title: '工号',
            key: 'employeeNo'
          },
          {
            title: '姓名',
            key: 'name'
          },
          {
            title: '手机号码',
            key: 'mobileNo'
          },
          {
            title: '级别',
            key: 'levelName'
          },
          {
            title: '班组',
            key: 'groupName'
          },
          {
            title: '操作',
            width: 300,
            render: (h, params) => h('div', [
              h('Button', {
                props: {
                  type: 'ghost'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.$Modal.confirm({
                      title: params.row.userStatus === 1 ? '是否停用' : '是否启用',
                      content: '<p>' + params.row.name + '</p>',
                      loading: true,
                      onOk: () => {
                        isActiveUser(params.row.id, params.row.userStatus === 1 ? 0 : 1).then(res => {
                          this.$Message.success(res.data.message)
                          this.$Modal.remove()
                          this.getlist()
                        })

                      }
                    })
                  }
                }
              }, params.row.userStatus === 1 ? '停用' : '启用'),
              h('Button', {
                  props: {
                    type: 'info'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      // this.$refs['user'].resetFields()
                      this.userInfo.id = params.row.id
                      this.userInfo.name = params.row.name
                      this.userInfo.employeeNo = params.row.employeeNo
                      this.userInfo.mobileNo = params.row.mobileNo
                      this.userInfo.groupId = params.row.groupId
                      this.userInfo.levelId = params.row.levelId
                      this.userModal.isShow = true
                      this.userModal.title = '修改用户'
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
                      content: '<p>' + params.row.name + '</p>',
                      loading: true,
                      onOk: () => {
                        delUser(params.row.id).then(res => {
                          this.$Message.success(res.data.message)
                          this.$Modal.remove()
                          this.getlist()
                        })

                      }
                    })
                  }
                }
              }, '删除')]
            )
          }
        ],
        list: [],
        RoleList: [],
        groups: [],
        levels: []
      }
    },
    methods: {
      add () {
        this.userModal.title = '新增用户'
        this.userInfo = {
          id: '',
          roleId: '',
          roleName: '',
          mobileNo: '',
          employeeNo: '',
          shiftsNo: 0,
          name: '',
          employeePassword: '',
          levelId: '',
          groupId: ''
        }
        this.$nextTick(()=>{
          this.$refs['user'].resetFields()
        })
        this.userModal.isShow = true

      },
      getSelection (selection) {
        this.selection = selection
      },
      getlist (pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getUserList(this.queryCondition).then(res => {
          this.queryCondition.pageNum = res.data.data.pageNum
          this.list = res.data.data.list
          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        })
      },
      doUser () {
        this.$refs['user'].validate((valid) => {
          if (valid) {
            this.userModal.isLoading = true
            if (this.userModal.title === '修改用户') {
              this.userInfo.newPassword = this.userInfo.employeePassword
              updateUser(this.userInfo).then(res => {
                this.userModal.isLoading = false
                this.userModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            } else if (this.userModal.title === '新增用户') {
              addUser(this.userInfo).then(res => {
                this.userModal.isLoading = false
                this.userModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            }
          }
        })
      }
    },
    created () {
//获取rolelist
      getgroupList({pageSize: 0}).then(res => {
        this.groups = res.data.data.list
      })
      getLevels().then(res => {
        this.levels = res.data.data
      })
      this.getlist()
    }
  }
</script>

<style>

</style>

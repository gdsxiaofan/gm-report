<template>
  <div>
    <Card>
      <p slot="title">条件查询</p>
      <Row>
        <Col :span="2" :offset="1" style="margin-top:0.2%">
        姓名：
        </Col>
        <Col :span="4">
        <Input type="text" v-model="queryCondition.userName" placeholder="请输入..."></Input>
        </Col>
        <Col :span="2" offset="8">
        <Button type="primary" shape="circle" icon="ios-search" @click="getlist">查询</Button>
        </Col>
        <Col :span="2" offset="1">
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="add">
          新建考勤
        </Button>
        </Col>
      </Row>
    </Card>
    <Table border :columns="columns" @on-selection-change="getSelection"   :data="list"></Table>
    <div style="margin: 10px;overflow: hidden">
      <div style="float: right;">
        <Page :total="queryCondition.total" :current.sync="queryCondition.pageNum" @on-change="getlist" show-total
              show-elevator></Page>
      </div>
    </div>
    <Modal v-model="attendanceModal.isShow"
           :title="attendanceModal.title"
    >
      <Form ref="attendance" :model="attendanceInfo" :rules="attendanceRules" :label-width="80">
        <Form-item label="工号-姓名：" prop="employeeNo">
          <Select type="text" :value="attendanceInfo.employeeNo" @on-change="selectUser" placeholder="">
            <Option v-for="item of userList" :value="item.employeeNo" :key="item.id">
              {{item.employeeNo}}-{{item.name}}
            </Option>
          </Select>
        </Form-item>
        <Form-item label="上班时间：" prop="workStartTime">
          <DatePicker type="datetime" :value="attendanceInfo.workStartTime"
                      @on-change='attendanceInfo.workStartTime=arguments[0]'
                      placeholder="选择日期和时间"
                      style="width: 300px"
                      :clearable="false"></DatePicker>
        </Form-item>
        <Form-item label="下班时间：" prop="workEndTime">
          <DatePicker type="datetime" :value="attendanceInfo.workEndTime"
                      @on-change='attendanceInfo.workEndTime=arguments[0]'
                      placeholder="选择日期和时间"
                      style="width: 300px"
                      :clearable="false"></DatePicker>
        </Form-item>
        <Form-item label="工作时长：" prop="workHours">
          <InputNumber :max="24" :min="0" v-model="attendanceInfo.workHours" placeholder=""></InputNumber>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="attendanceModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="doattendance" :loading="attendanceModal.isLoading">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {
    getattendanceList,
    updateattendance,
    delattendance,
    addattendance,
    isActiveattendance
  } from '../../api/role/attendance'
  import { formatData } from '../../lib/utils/common'
  import { getUserList } from '../../api/role/showUser'

  export default {
    data () {
      return {
        attendanceInfo: {
          id: '',
          employeeNo: '',
          userName: '',
          workStartTime: formatData.call(new Date(), 'yyyy-MM-dd') + ' 09:00:00',
          workEndTime: formatData.call(new Date(), 'yyyy-MM-dd') + ' 18:00:00',
          workHours: 8,
        },
        attendanceRules: {
          employeeNo: [
            {required: true, message: '请选择工号', trigger: 'change'}
          ],
          workStartTime: [
            {required: true, message: '请填写上班时间', trigger: 'change'}
          ],
          workEndTime: [
            {required: true, message: '请填写下班时间', trigger: 'change'}
          ],
          workHours: [
            { type: 'number', message: '请填写工作时长', trigger: 'blur' }
          ],
        },
        attendanceModal: {
          isShow: false,
          isLoading: false,
          title: ''
        },
        selection: '',
        queryCondition: {
          pageSize: 10,
          pageNum: 1,
          userName: '',
          total: 0
        },
        columns: [
          {
            title: '工号',
            key: 'employeeNo'
          },
          {
            title: '姓名',
            key: 'userName'
          },
          {
            title: '工作时长（时）',
            key: 'workHours'
          },
          {
            title: '上班时间',
            key: 'workStartTime'
          },
          {
            title: '下班时间',
            key: 'workEndTime'
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
                      this.$refs['attendance'].resetFields()
                      this.attendanceInfo.id = params.row.id
                      this.attendanceInfo.employeeNo = params.row.employeeNo
                      this.attendanceInfo.userName = params.row.userName
                      this.attendanceInfo.workStartTime = params.row.workStartTime
                      this.attendanceInfo.workEndTime = params.row.workEndTime
                      this.attendanceInfo.workHours = params.row.workHours
                      this.attendanceModal.isShow = true
                      this.attendanceModal.title = '修改考勤'
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
                        delattendance(params.row.id).then(res => {
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
        userList: []
      }
    },
    methods: {
      selectUser (employeeNo) {
        this.attendanceInfo.employeeNo = employeeNo
        this.attendanceInfo.userName = this.userList.filter(x => {
          return x.employeeNo === employeeNo
        })[0].name
      },
      add () {
        this.$refs['attendance'].resetFields()
        this.attendanceModal.title = '新增考勤'
        this.attendanceModal.isShow = true

      },
      getSelection (selection) {
        this.selection = selection
      },
      getlist (pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getattendanceList(this.queryCondition).then(res => {
          this.queryCondition.pageNum = res.data.data.pageNum
          this.list = res.data.data.list
          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        })
      },
      doattendance () {
        this.$refs['attendance'].validate((valid) => {
          if (valid) {
            this.attendanceModal.isLoading = true
            if (this.attendanceModal.title === '修改考勤') {
              updateattendance(this.attendanceInfo).then(res => {
                this.attendanceModal.isLoading = false
                this.attendanceModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            } else if (this.attendanceModal.title === '新增考勤') {
              this.attendanceInfo.id = null
              addattendance(this.attendanceInfo).then(res => {
                this.attendanceModal.isLoading = false
                this.attendanceModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            }
          }
        })
      }
    },
    created () {
      this.getlist()
      getUserList({pageSize: 0}).then(res => {
        this.userList = res.data.data.list
      })
    },
    mounted () {
      this.$refs['attendance'].resetFields()
    }
  }
</script>

<style>

</style>

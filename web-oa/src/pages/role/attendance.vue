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
    <Modal v-model="attendanceModal.isShow"
           :title="attendanceModal.title"
    >
      <Form ref="attendance" :model="attendanceInfo" :rules="attendanceRules" :label-width="80">
        <Form-item label="班组名：" prop="attendanceName">
          <Input type="text" v-model="attendanceInfo.attendanceName" placeholder="">
          </Input>
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

  export default {
    data() {
      return {
        attendanceInfo: {
          id: '',
          attendanceName: '',
        },
        attendanceRules: {
          attendanceName: [
            {required: true, message: '请填写班组', trigger: 'blur'}
          ]
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
                      this.attendanceInfo.attendanceName = params.row.attendanceName
                      this.attendanceModal.isShow = true
                      this.attendanceModal.title = '修改班组'
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
        this.$refs['attendance'].resetFields()
        this.attendanceModal.title = '新增班组'
        this.attendanceModal.isShow = true

      },
      getSelection(selection) {
        this.selection = selection
      },
      getlist(pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getattendanceList(this.queryCondition).then(res => {
          this.queryCondition.pageNum = res.data.data.pageNum
          this.list = res.data.data.list
          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        });
      },
      doattendance() {
        this.$refs['attendance'].validate((valid) => {
          if (valid) {
            this.attendanceModal.isLoading = true
            if (this.attendanceModal.title === '修改班组') {
              updateattendance(this.attendanceInfo).then(res => {
                this.attendanceModal.isLoading = false
                this.attendanceModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            } else if (this.attendanceModal.title === '新增班组') {
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
    mounted() {
      this.$refs['attendance'].resetFields()
      this.getlist()
    }
  }
</script>

<style>

</style>

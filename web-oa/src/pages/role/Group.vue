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
        班组名：
        </Col>
        <Col :span="4">
        <Input type="text" v-model="queryCondition.groupName" placeholder="请输入..."></Input>
        </Col>
        <Col :span="2" offset="8">
        <Button type="primary" shape="circle" icon="ios-search" @click="getlist">查询</Button>
        </Col>
        <Col :span="2" offset="1">
        <Button type="success" shape="circle" icon="ios-personadd"
                @click="add">
          新建班组
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
    <Modal v-model="groupModal.isShow"
           :title="groupModal.title"
    >
      <Form ref="group" :model="groupInfo" :rules="groupRules" :label-width="80">
        <Form-item label="班组名：" prop="groupName">
          <Input type="text" v-model="groupInfo.groupName" placeholder="">
          </Input>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="ghost" @click="groupModal.isShow=false" style="margin-left: 8px">取消</Button>
        <Button type="primary" @click="dogroup" :loading="groupModal.isLoading">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {
    getgroupList,
    updategroup,
    delgroup,
    addgroup,
  } from '../../api/role/group'

  export default {
    data() {
      return {
        groupInfo: {
          id: '',
          groupName: '',
        },
        groupRules: {
          groupName: [
            {required: true, message: '请填写班组', trigger: 'blur'}
          ]
        },
        groupModal: {
          isShow: false,
          isLoading: false,
          title: ''
        },
        selection: '',
        queryCondition: {
          pageSize: 10,
          pageNum: 1,
          groupName: '',
          total: 0
        },
        columns: [
          {
            title: '编号',
            key: 'id'
          },
          {
            title: '班组名',
            key: 'groupName'
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
                      this.$refs['group'].resetFields()
                      this.groupInfo.id = params.row.id
                      this.groupInfo.groupName = params.row.groupName
                      this.groupModal.isShow = true
                      this.groupModal.title = '修改班组'
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
                        delgroup(params.row.id).then(res => {
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
        this.$refs['group'].resetFields()
        this.groupModal.title = '新增班组'
        this.groupModal.isShow = true

      },
      getSelection(selection) {
        this.selection = selection
      },
      getlist(pageNum) {
        this.queryCondition.pageNum = !isNaN(pageNum) ? pageNum : this.queryCondition.pageNum
        getgroupList(this.queryCondition).then(res => {
          this.queryCondition.pageNum = res.data.data.pageNum
          this.list = res.data.data.list
          this.queryCondition.total = res.data.data.total
        }).catch(err => {

        });
      },
      dogroup() {
        this.$refs['group'].validate((valid) => {
          if (valid) {
            this.groupModal.isLoading = true
            if (this.groupModal.title === '修改班组') {
              updategroup(this.groupInfo).then(res => {
                this.groupModal.isLoading = false
                this.groupModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            } else if (this.groupModal.title === '新增班组') {
              this.groupInfo.id = null
              addgroup(this.groupInfo).then(res => {
                this.groupModal.isLoading = false
                this.groupModal.isShow = false
                this.$Message.success(res.data.message)
                this.getlist()
              })
            }
          }
        })
      }
    },
    mounted() {
      this.$refs['group'].resetFields()
      this.getlist()
    }
  }
</script>

<style>

</style>

<template>
  <div id="User">
    <el-form :inline="true" ref="form" :model="form"  label-width="80px" size="small" :label-position="labelPosition">
      <el-form-item label="用户编码">
        <el-input v-model="form.userCode"></el-input>
      </el-form-item>

      <el-form-item label="用户名称">
        <el-input v-model="form.userName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="userData"
      stripe
      border
      style="width: 100%">
      <el-table-column label="选择" width="60">
        <template slot-scope="scope">
          <el-checkbox
            @change="handleCheck(scope.row)"
            v-model="scope.row.check"
            :max="1">
          </el-checkbox>
        </template>
      </el-table-column>
      <el-table-column
        prop="userCode"
        label="用户编码"
        width="180">
      </el-table-column>
      <el-table-column
        prop="userName"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: "User",
    data() {
      return {
        labelPosition:'right',
        form:{
          userCode:'',
          userName:''
        },

        userData: []
      }
    },
    created() {
      axios.get('/sys_user/list',{
        params: {
          userCode: this.form.userCode,
          userName: this.form.userName
        }
      }).then(res =>{
        this.userData = res.data.userList;
      })
    },
    methods:{
      search(){
        axios.get('/sys_user/list',{
          params: {
            userName: this.form.userName
          }
        }).then(res =>{
          this.userData = res.data.userList;
        })
      },
      handleCheck(row){
        if(row.check == true){
          this.form.userCode = row.userCode
          this.form.userName = row.userName
        }else {
          this.form.userCode = ''
          this.form.userName = ''
        }

      }
    }
  }
</script>

<style scoped>

</style>

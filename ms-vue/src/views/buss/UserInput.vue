<template>
<div id="UserInput">
  <el-form :inline="true" ref="form" :model="form"  label-width="80px" size="small" :label-position="labelPosition">
      <el-form-item label="用户编码">
        <el-input v-model="form.inUserCode"></el-input>
      </el-form-item>
    <el-form-item label="用户名称">
      <el-input v-model="form.inUserName"></el-input>
    </el-form-item>
    <el-form-item label="用户密码">
      <el-input v-model="form.inUserPass"></el-input>
    </el-form-item>
    <el-form-item label="用户地址">
      <el-input v-model="form.inUserAddr"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="input">新增</el-button>
    </el-form-item>
  </el-form>

</div>
</template>

<script>
import axios from "axios";
export default {
  name: "UserInput",
  data() {
    return {
      labelPosition:'right',
      form:{
        inObjectCode:'',
        inUserCode:'',
        inUserName:'',
        inUserPass:'',
        inUserAddr:'',
        inObjectName:''
      },

      userData: []
    }
  },
  methods:{
    input(){
      alert(sessionStorage.getItem('sysName'))
      axios.get('/sys_user/input',{
        params:{
          inObjectCode:sessionStorage.getItem('sysName'),
          inUserCode: this.form.inUserCode,
          inUserName: this.form.inUserName,
          inUserPass: this.form.inUserPass,
          inUserAddr: this.form.inUserAddr
        }
      }).then(res =>{
        this.$message(res.data.result);
      }).catch()
    }
  }
}
</script>

<style scoped>

</style>

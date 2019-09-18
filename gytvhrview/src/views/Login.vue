<template>
  <div style="display: flex;justify-content: center">
    <el-card class="box-card" style="width: 450px;margin-top: 150px">
      <div slot="header" class="clearfix">
        <span>登录</span>
      </div>
      <div>
        <table>
          <tr>
            <td>
              <el-tag>用户名</el-tag>
            </td>
            <td>
              <el-input size="mini" v-model="user.username" placeholder="请输入用户名"></el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>密码</el-tag>
            </td>
            <td>
              <el-input size="mini" v-model="user.password" placeholder="请输入密码" @keydown.enter.native="doLogin"></el-input>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <el-button type="primary" @click="doLogin">登录</el-button>
            </td>
          </tr>
        </table>
      </div>
    </el-card>
  </div>
</template>

<script>

  export default {
    name: "Login",
    data() {
      return {
        user:{
          username:'admin',
          password:'123'
        }
      }
    },
    methods:{
      doLogin() {
        this.postKeyValueRequest("/doLogin", this.user).then(msg=>{
          if (msg) {
            window.sessionStorage.setItem("user",JSON.stringify(msg.obj))
            this.$router.replace("/home");
          }
        });
      }
    }
  }
</script>

<style scoped>

</style>

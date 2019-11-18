<template>
  <div  >
      <div  style="width: 600px;margin-top:20px;margin-bottom: 20px">
          <el-input
                  placeholder="默认展示部分用户，可以通过用户名搜索更多用户"
                  >
          </el-input>
      </div>
      <div style="display: flex;justify-content: space-around;flex-wrap: wrap;text-align: left">

          <el-card class="box-card" v-for="(hr,index) in hrs" :key="hr.id">
              <div slot="header" >
                  <span>{{hr.name}}</span>
                  <el-button style="float: right; padding: 3px 0;color: #ff0317" icon="el-icon-delete" @click="deleteRole(r)"
                             type="text">
                  </el-button>
              </div>
              <div style="display: flex;align-items: center;justify-content: center">
                  <img :src="hr.userface" alt="item.name" style="width: 70px;height: 70px;border-radius:70px">
              </div>
              <div >
                  <div><span class="user-info">用户名:{{hr.name}}</span></div>
                  <div><span class="user-info">手机号码:{{hr.phone}}</span></div>
                  <div><span class="user-info">电话号码:{{hr.telephone}}</span></div>
                  <div><span class="user-info">地址:{{hr.address}}</span></div>
                  <div class="user-info" style="display: flex;align-items: center;margin-bottom: 3px">
                      用户状态:
                      <el-switch
                              style="display: inline;margin-left: 5px"
                              v-model="hr.enabled"
                              active-color="#13ce66"
                              inactive-color="#aaaaaa"
                              active-text="启用"
                              :key="hr.id"
                              @change="switchChange(hr.enabled,hr.id,index)"
                              inactive-text="禁用">
                      </el-switch>
                  </div>
              </div>
          </el-card>
      </div>
  </div>
</template>

<script>
    export default {
        data(){
            return {
                hrs:[]
            }
        },
        mounted() {
            this.initHrs();
        },
        methods: {
            initHrs(){
                this.getRequest('/system/hr/').then(resp=>{
                    if (resp) {
                        this.hrs=resp;
                    }
                })
            }
        }
    }
</script>

<style>


    .item {
        margin-bottom: 18px;
    }



    .box-card {
        width: 350px;
        margin-bottom: 20px
    }
    .user-info {
        font-size: 16px;
        color: #09c0f6;
    }
</style>

<template>
  <div style="margin-top: 20px">
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="部门管理" name="DepMana"><DepMana></DepMana></el-tab-pane>
      <el-tab-pane label="职称管理" name="JobTitleMana"><JobTitleMana></JobTitleMana></el-tab-pane>
      <el-tab-pane label="职位管理" name="PosMana"><PosMana></PosMana></el-tab-pane>
      <el-tab-pane label="奖惩规则" name="ECMana"><ECMana></ECMana></el-tab-pane>
      <el-tab-pane label="权限组" name="MenuRole"><MenuRole></MenuRole></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import DepMana from './basic/DepMana'
  import ECMana from './basic/ECMana'
  import JobTitleMana from './basic/JobTitleMana'
  import MenuRole from './basic/MenuRole'
  import PosMana from './basic/PosMana'

  export default {
     name: 'SysBasic',
    data(){
       return {
         activeName:'DepMana'
       }
    },
     components:{
       DepMana,
       ECMana,
       JobTitleMana,
       MenuRole,
       PosMana
     }
  }
</script>


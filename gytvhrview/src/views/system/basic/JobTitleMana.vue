<template>
    <div>
        <el-table
                :data="jobTitles"
                border
                stripe
                style="width: 100%">
            <el-table-column
                    prop="id"
                    label="编号"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="titleLevel"
                    label="职称">
            </el-table-column>
            <el-table-column
                    prop="createDate"
                    label="创建时间">
            </el-table-column>
            <el-table-column
                    label="是否可用">
              <template slot-scope="scope">
                  <el-tag type="success" v-if="scope.row.enabled">是</el-tag>
                  <el-tag type="error" v-else>是</el-tag>
              </template>
            </el-table-column>
        </el-table>
    </div>
</template>
<script>

    export default {
        data() {
            return {
              jobTitles:[]
            }
        },
        mounted:function(){
            this.initJobLevels();
        },
        methods: {
            initJobLevels(){
                this.getRequest('/system/basic/joblevel/').then(resp => {
                    if(resp){
                      this.jobTitles=resp;
                    }
                })
            }
        }
    }
</script>

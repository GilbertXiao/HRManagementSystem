<template>

    <div>
        <div style="display: flex;margin-bottom: 10px">
            <el-button type="primary" icon="el-icon-plus" size="small" @click="showAddView"> 添加
            </el-button>
            <el-button type="danger" size="small" style="margin-left: 20px" @click="handleDeleteByIds"
                       :disabled="multipleSelection.length==0"> 批量删除
            </el-button>
        </div>
        <div>
            <el-dialog
                    :title="diaTitle"
                    :visible.sync="dialogVisible"
                    width="30%"
            >
                <div class="dialog-div">
                    <el-tag class="dialog-input">职位名称</el-tag>

                    <el-input size="small" v-model="jobTitle.name" placeholder="请输入职称名称"
                              style="display:flex; width: 40%"></el-input>
                </div>
                <div class="dialog-div">
                    <el-tag style="width: 80px;margin-right: 20px">职位级别</el-tag>
                    <el-select v-model="jobTitle.titleLevel" size="small"
                               placeholder="职称等级">
                        <el-option
                                v-for="tl in titleLevels"
                                :key="tl"
                                :label="tl"
                                :value="tl">
                        </el-option>
                    </el-select>

                </div>


                <div class="dialog-div" style="align-items: center">
                    <el-tag style="width: 80px;margin-right: 20px">是否可用</el-tag>
                    <el-switch
                            v-model="jobTitle.enabled"
                            active-text="启用"
                            inactive-text="禁用" :active-value="1" :inactive-value="0">
                    </el-switch>
                </div>


            <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="hideView">取 消</el-button>
                <el-button size="small" type="primary" @click="doHandlerJT">确 定</el-button>
            </span>
            </el-dialog>
        </div>
        <div>
            <el-table
                    :data="jobTitles"
                    border
                    stripe
                    style="width: 100%"
                    @selection-change="handleSelectionChange"
            >
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="id"
                        label="编号"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="职称名称"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="titleLevel"
                        label="职称级别">
                </el-table-column>
                <el-table-column
                        prop="createDate"
                        label="创建时间">
                </el-table-column>
                <el-table-column
                        label="是否可用" width="80px">
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="scope.row.enabled">是</el-tag>
                        <el-tag type="error" v-else>否</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="showEditView(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="handleDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>


    </div>
</template>
<script>

    export default {
        data() {
            return {
                jobTitles: [],
                multipleSelection: [],
                dialogVisible: false,
                diaTitle: '',
                jobTitle: {
                    name: '',
                    titleLevel: '',
                    enabled: 1,
                },
                titleLevels: ['正高级', '副高级', '中级', '初级', '员级'],
                opType: '',

            }
        },
        mounted: function () {
            this.initJobLevels();
        },
        methods: {
            initJobLevels() {
                this.getRequest('/system/basic/joblevel/').then(resp => {
                    if (resp) {
                        this.jobTitles = resp;
                    }
                })
            },

            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            showAddView(index, row) {
                this.diaTitle = '新增职称';
                this.opType='ADD';
                this.dialogVisible = true;

            },
            showEditView(index, row) {
                this.diaTitle = '编辑职称';
                this.opType='EDIT';
                this.dialogVisible = true;
                Object.assign(this.jobTitle, row);
            },
            handleDelete(index, row) {
                this.$confirm('此操作将永久删除【' + row.name + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest('/system/basic/joblevel/' + row.id).then(resp => {
                            if (resp) {
                                this.initJobLevels();
                            }
                        }
                    );
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            hideView() {
                this.jobTitle = {
                    name: '',
                    titleLevel: '',
                    enabled: 1,
                };
                this.dialogVisible=false;
            },
            doHandlerJT(){
                console.log(this.jobTitle);
                if(this.opType=='ADD'){
                    this.postRequest('/system/basic/joblevel/',this.jobTitle).then(resp=>{
                        if (resp){
                            this.initJobLevels();
                            this.hideView();
                        }
                    })
                }else if(this.opType=='EDIT'){
                    this.putRequest('/system/basic/joblevel/',this.jobTitle).then(resp=>{
                        if (resp){
                            this.initJobLevels();
                            this.hideView();
                        }
                    })
                }
            },
            handleDeleteByIds() {
                this.$confirm('此操作将永久删除【' + this.multipleSelection.length + '】条记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let ids='?';
                    this.multipleSelection.forEach(item=>{
                        ids+='ids='+item.id+'&';
                    })
                    this.deleteRequest('/system/basic/joblevel/' + ids).then(resp => {
                            if (resp) {
                                this.initJobLevels();
                            }
                        }
                    );
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

        }
    }
</script>

<style>
    .dialog-div {
        display: flex;
        margin-bottom: 10px
    }

    .dialog-input {

        width: 78px;
        margin-right: 20px

    }
</style>

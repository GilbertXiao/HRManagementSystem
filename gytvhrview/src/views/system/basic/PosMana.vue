<template>
    <div>
        <div style="display: flex;margin-bottom: 20px">
            <el-input
                    placeholder="添加职位..."
                    prefix-icon="el-icon-plus"
                    v-model="pos.name" size="small" style="width: 300px" @keydown.enter.native="addPos">
            </el-input>

            <el-button type="primary" icon="el-icon-plus" size="small" style="margin-left: 20px" @click="addPos"> 添加
            </el-button>
            <el-button type="danger" size="small" style="margin-left: 20px" @click="deleteByIds"
                       :disabled="multipleSelection.length==0"> 批量删除
            </el-button>
        </div>
        <div>
            <el-table size="small"
                      :data="posList"
                      border
                      stripe
                      style="width: 70%"
                      @selection-change="handleSelectionChange">
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
                        label="职位名称"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="createDate"
                        label="创建时间">
                </el-table-column>
                <el-table-column
                        label="是否启用" width="80" prop="enabled">
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="scope.row.enabled">启用</el-tag>
                        <el-tag type="error" v-else>禁用</el-tag>
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

        <div>
            <el-dialog
                    title="编辑职位"
                    :visible.sync="dialogVisible"
                    width="30%"
            >
                <div class="dialog-div">
                    <el-tag style="width: 106px;margin-right: 20px">职位名称</el-tag>

                    <el-input size="small" v-model="updatePos.name" placeholder="请输入职位名称"></el-input>
                </div>


                <div class="dialog-div" style="align-items: center">
                    <el-tag style="width: 80px;margin-right: 20px">是否启用</el-tag>
                    <el-switch
                            v-model="updatePos.enabled"
                            active-text="启用"
                            inactive-text="禁用" :active-value="1" :inactive-value="0">
                    </el-switch>
                </div>


                <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="doUpdatePos">确 定</el-button>
            </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>

    export default {
        data() {
            return {
                pos: {
                    name: ''
                },
                updatePos: {
                    name: '',
                    enabled: 0
                },
                posList: [],
                dialogVisible: false,
                multipleSelection: [],
            }
        },
        methods: {
            showEditView(index, row) {
                this.dialogVisible = true;
                Object.assign(this.updatePos, row);
            },
            handleDelete(index, row) {
                this.$confirm('此操作将永久删除【' + row.name + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest('/system/basic/pos/' + row.id).then(resp => {
                            if (resp) {
                                this.initPos();
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
            initPos() {
                this.getRequest("/system/basic/pos/").then(resp => {
                    if (resp) {
                        this.posList = resp;
                    }
                })
            },
            addPos() {
                if (this.isNotNullORBlank(this.pos.name)) {
                    this.postRequest("/system/basic/pos/", this.pos).then(resp => {
                        if (resp) {
                            this.initPos();
                            this.pos.name='';
                        }
                    })
                }
            },
            doUpdatePos() {
                if (this.isNotNullORBlank(this.updatePos)) {
                    this.putRequest("/system/basic/pos/", this.updatePos).then(resp => {
                        if (resp) {
                            this.initPos();
                            this.updatePos = {
                                name: '',
                                enabled: 0
                            };
                            this.dialogVisible = false;
                        }
                    })
                }
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            deleteByIds() {
              this.$confirm('此操作将永久删除【' + this.multipleSelection.length + '】条记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                let ids = "?";
                this.multipleSelection.forEach(item => {
                  ids += 'ids=' + item.id + '&';
                })
                this.deleteRequest('/system/basic/pos/' + ids).then(resp => {
                  if (resp) {
                    this.initPos();
                  }
                });
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                });
              });

            }

        },
        mounted() {
            this.initPos();
        }
    }

</script>

<style>
    .dialog-div {
        display: flex;
        margin-bottom: 10px
    }
</style>

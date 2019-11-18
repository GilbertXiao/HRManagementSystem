<template>
    <div>
        <div  style="width: 600px;margin-bottom: 20px">
            <el-input
                    placeholder="输入部门名称进行过滤"
                    v-model="filterText">
            </el-input>
        </div>

        <div>
            <el-tree :data="deps" :props="defaultProps"  style="width: 600px"
                     :render-content="renderContent" :expand-on-click-node="false"
                     :filter-node-method="filterNode"
                     ref="tree2" ></el-tree>
        </div>

        <div>
            <el-dialog
                    title="新增部门"
                    :visible.sync="dialogVisible"
                    width="30%"
            >
                <span></span>
                <table>
                    <tr>
                        <td style="width: 50%;padding-left: 25%"><el-tag style="width: 80px">父部门</el-tag></td>
                        <td>
                            <el-select v-model="dep.parentId" placeholder="请选择">
                                <el-option
                                        v-for="item in allDeps"
                                        :key="item.id"
                                        :label="item.name"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 50%;padding-left: 25%">
                            <el-tag style="width: 80px">部门名称</el-tag>
                        </td>
                        <td>
                            <el-input v-model="dep.name" placeholder="请输入部门名称" ></el-input>
                        </td>


                    </tr>
                </table>



                <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addDep">确 定</el-button>
            </span>
            </el-dialog>
        </div>


    </div>
</template>

<script>
    import {initMenu} from "../../../utils/utils";

    export default {
        data() {
            return {
                filterText: '',
                deps: [],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                dialogVisible:false,
                allDeps:[],
                dep:{
                    parentId:-1,
                    name:''
                }
            };
        },
        watch: {
            //监控filterText变量，当该变量发生变化时，这个方法会被调用
            filterText(val) {
                this.$refs.tree2.filter(val);
            }
        },
        mounted: function () {
            this.initDeps()
        },
        methods: {
            filterNode(value, data) {
                if (!value) return true;
                return data.name.indexOf(value) !== -1;
            },
            initDeps() {
                this.getRequest('/system/basic/dep/').then(resp => {
                        this.deps = resp;
                    }
                )
                this.getRequest('/system/basic/dep/allDeps').then(resp => {
                        this.allDeps = resp;
                    }
                )
            },
            deleteDepFromDeps(deps,data){
                for (let i=0; i<deps.length;i++ ){
                    if(deps[i].id==data.id){
                        deps.splice(i,1);
                        return;
                    }else{
                        this.deleteDepFromDeps(deps[i].children,data);
                    }
                }
            },
            removeDepartment(data) {
                this.$confirm('此操作将永久删除该部门, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    if (data.children.length>0) {
                        this.$message.error('父部门无法删除');
                    } else {
                        this.deleteRequest('/system/basic/dep/?id=' + data.id).then(resp => {
                                if (resp) {
                                    this.deleteDepFromDeps(this.deps,data);
                                    this.deleteDepFromDeps(this.allDeps,data);
                                }
                            }
                        )
                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            },
            showAddDepView(data){
                this.dep.parentId=data.id;
                this.dialogVisible=true;
            },
            addDep2Deps(deps,obj){
                for (let i=0;i<deps.length;i++){
                    let d= deps[i];
                    if (d.id==obj.parentId){
                        d.children=d.children.concat(obj);
                        return;
                    }else{
                        this.addDep2Deps(d.children,obj);
                    }
                }
            },
            addDep(){
                this.postRequest('/system/basic/dep/',this.dep).then(resp => {
                    if(resp){

                        this.addDep2Deps(this.deps,resp.obj);
                        /*this.allDeps.concat(resp.obj);*/
                    }
                    this.dialogVisible=false;
                    this.dep.name='';
                })
            },
            renderContent(h, {node, data, store}) {
                return (
                        <span class="custom-tree-node" style="display:flex;justify-content:space-between;width:100%">
                      <span>{node.label}</span>
                      <span>
                      <el-button size="middle" type="primary" style="padding: 1px"  on-click={ () => this.showAddDepView(data) }>新增部门</el-button>
              <el-button size="middle" type="danger"  style="padding: 1px" on-click={ () => this.removeDepartment(data) }>删除部门</el-button>
              </span>
              </span>);
            }
        }
    };
</script>

<style>
    .te {
        margin-bottom: 2px;
    }
</style>


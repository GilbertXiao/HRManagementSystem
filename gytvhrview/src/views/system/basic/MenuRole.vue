<template>
    <div>
        <div class="permissManaTool">
            <el-input size="small" placeholder="请输入角色英文名" v-model="role.name"></el-input>
            <el-input size="small" placeholder="请输入角色中文名" v-model="role.nameZh"></el-input>
            <el-button type="primary" size="small" icon="el-icon-plus" @click="addRole">添加角色</el-button>
        </div>
        <div class="panel">
            <el-collapse v-model="activeName" accordion @change="change" >
                <el-collapse-item :title="r.nameZh" :name="r.id" v-for="(r,index) in roles" :key="index" >
                    <el-card class="box-card" >
                        <div slot="header" class="clearfix">
                            <span>可访问的资源</span>
                            <el-button style="float: right; padding: 3px 0;color: #ff0317" icon="el-icon-delete" @click="deleteRole(r)"
                                       type="text">删除
                            </el-button>
                        </div>
                        <div >
                            <el-tree
                                    :data="menuTree"
                                    show-checkbox
                                    node-key="id"
                                    ref="menuTree"
                                    :default-checked-keys="selectMenu"
                                    :props="defaultProps">
                            </el-tree>
                        </div>
                        <div style="display: flex;justify-content: flex-end">
                            <el-button size="small" @click="cancelUpdate">取消修改</el-button>
                            <el-button size="small" type="primary" @click="updateMenuRole(index)">确认修改</el-button>
                        </div>
                    </el-card>

                </el-collapse-item>
            </el-collapse>

        </div>
    </div>
</template>

<script>

    export default {
        name: "PosMana",
        data() {
            return {
                role: {
                    name: '',
                    nameZh: ''
                },
                activeName: -1,
                roles: [],
                menuTree: [],
                menuTreeBak: [],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                selectMenu: [],




            }
        },
        methods: {
            change(rid) {

                if (rid) {
                    this.selectMenu = [];
                    this.menuTree = [];
                    Object.assign(this.menuTree, this.menuTreeBak);
                    this.initSelectedMenu(rid);
                }


            },
            initRoles() {
                this.getRequest('/system/basic/permiss/').then(resp => {
                    this.roles = resp;
                })
            },
            initMenuTree() {
                this.getRequest('/system/basic/permiss/menu').then(resp => {
                    this.menuTree = resp;
                    Object.assign(this.menuTreeBak, this.menuTree);
                })
            },
            initSelectedMenu(rid) {
                this.getRequest('/system/basic/permiss/selectedMenu/' + rid).then(resp => {
                    this.selectMenu = resp;
                })
            },
            cancelUpdate() {
                this.activeName = -1;
            },
            updateMenuRole(index) {
                let mids = this.$refs.menuTree[index].getCheckedKeys(true);
                let url='rid='+this.activeName+'&';
                mids.forEach(mid=>{
                    url+='mid='+mid+'&';
                });
                console.log(url);
                this.putRequest('/system/basic/permiss/roleMenu?'+url).then(resp=>{
                    this.activeName=-1;
                })

            },
            addRole() {
                if (this.isNotNullORBlank(this.role.nameZh) && this.isNotNullORBlank(this.role.name)) {
                    this.postRequest('/system/basic/permiss/role', this.role).then(resp => {
                        if (resp) {
                            this.role.name = '';
                            this.role.nameZh = '';
                            this.initRoles();
                        }
                    })
                }

            },
            deleteRole(role){
                this.$confirm('此操作将永久删除【' + role.nameZh + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest('/system/basic/permiss/role/' + role.id).then(resp => {
                            if (resp) {
                                this.initRoles();
                            }
                        }
                    );
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            }

        },
        mounted() {
            this.initRoles();
            this.initMenuTree();

        }
    }

</script>

<style>
    .permissManaTool {
        display: flex;
        align-items: flex-start;
        margin-bottom: 20px;
    }

    .permissManaTool div {
        width: 300px;
        margin-right: 20px;

    }

    .panel {
        width: 800px;
    }

    .clearfix {
        display: flex;
        justify-content: space-between;
    }


</style>

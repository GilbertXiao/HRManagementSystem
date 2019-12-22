<template>
    <div>
        <div style="margin-top:20px;margin-bottom: 20px; display: flex; justify-content: center ">
                <el-input
                        placeholder="请输入用户名搜索" style="width: 400px;margin-right: 10px" prefix-icon="el-icon-search" v-model="keyword" @keydown.native.enter="doSearch"
                >
                </el-input>
                <el-button icon="el-icon-search" type="primary" @click="doSearch">搜索</el-button>
        </div>
        <div style="display: flex;justify-content: space-around;flex-wrap: wrap;text-align: left">

            <el-card class="box-card" v-for="(hr,index) in hrs" :key="hr.id">
                <div slot="header">
                    <span>{{hr.name}}</span>
                    <el-button style="float: right; padding: 3px 0;color: #ff0317" icon="el-icon-delete"
                               @click="deleteRole(hr)"
                               type="text">
                    </el-button>
                </div>
                <div style="display: flex;align-items: center;justify-content: center">
                    <img :src="hr.userface" alt="item.name" style="width: 70px;height: 70px;border-radius:70px">
                </div>
                <div>
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
                                @change="switchChange(hr)"
                                inactive-text="禁用">
                        </el-switch>
                    </div>
                    <div class="user-info">
                        用户角色:
                        <el-tag type="success" class="tag-info" v-for="(role,indexj) in hr.roles">{{role.nameZh}}
                        </el-tag>
                        <el-popover
                                placement="right-start"
                                title="角色列表"
                                width="240"
                                trigger="click" @hide="updateSelRoles(hr.id)">
                            <el-select v-model="selRoles" multiple placeholder="请选择角色">
                                <el-option
                                        v-for="item in allRoles"
                                        :key="item.id"
                                        :label="item.nameZh"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                            <el-button slot="reference" icon="el-icon-more" type="text" style="margin-left: 4px"
                                       @click="initSelRoles(hr.roles)"></el-button>
                        </el-popover>

                    </div>
                    <div><span class="user-info">备注:{{hr.remark}}</span></div>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                hrs: [],
                selRoles: [],
                selRolesBak: [],
                allRoles: [],
                keyword:''
            }
        },
        mounted() {
            this.initHrs();
            this.initAllRoles();
        },

        methods: {
            deleteRole(hr){
                this.$confirm('此操作将永久删除【' + hr.name + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest('/system/hr/?id=' + hr.id).then(resp => {
                            if (resp) {
                                this.initHrs();
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
            initHrs() {
                this.getRequest('/system/hr/?keyword='+this.keyword).then(resp => {
                    if (resp) {
                        this.hrs = resp;
                    }
                })
            },
            doSearch(){
                this.initHrs();
            },
            initAllRoles() {
                this.getRequest('/system/hr/role').then(resp => {
                    if (resp) {
                        this.allRoles = resp;
                    }
                })
            },
            initSelRoles(roles) {
                this.selRoles = [];
                roles.forEach(role => {
                    this.selRoles.push(role.id);
                })
                Object.assign(this.selRolesBak, this.selRoles);
            },
            updateSelRoles(hrid) {

                let uri = '';
                uri += 'hrId=' + hrid;
                this.selRoles.forEach(item => {
                    uri += '&roleId=' + item;
                })
                if (this.hasChange(this.selRoles, this.selRolesBak)) {
                    this.putRequest('/system/hr/role/?' + uri).then(resp => {
                        this.initHrs();
                    })
                }
            },
            hasChange(obj1, obj2) {

                if (obj1.length != obj2.length) {
                    //has change
                    return true;
                } else {
                    obj1.forEach(item1 => {
                        let flag = false;
                        obj2.forEach(item2 => {
                            if (item1 === item2) {
                                //存在一样的
                                flag = true;
                            }
                        })
                        if (flag === false) {
                            //不存在一样的
                            return true;
                        }
                    })
                    return false;
                }

            },
            switchChange(hr) {
                this.putRequest('/system/hr/', hr).then(resp => {
                        this.initHrs();
                    }
                )
            }
        }
    }
</script>



<style>


    .item {
        margin-bottom: 18px;
    }


    .box-card {
        width: 400px;
        margin-bottom: 20px
    }

    .user-info {
        font-size: 16px;
        color: #09c0f6;
    }

    .tag-info {
        height: 22px;
        padding: 0 5px;
        line-height: 20px;
        margin-left: 8px;
        margin-top: 6px;
    }
</style>

<template>
    <div style="margin-top: 10px">
        <div style="display: flex;justify-content: space-between;margin-bottom: 10px">
            <div>
                <el-button type="primary" icon="el-icon-plus" size="small" @click="showAddView"> 添加
                </el-button>
                <el-button type="danger" size="small" style="margin-left: 20px" @click="handleDeleteByIds"
                           :disabled="multipleSelection.length==0"> 批量删除
                </el-button>
            </div>
            <div>
                <el-button icon="el-icon-refresh" type="success" @click="initSalaries" size="small"></el-button>
            </div>

        </div>
        <el-table
                :data="salaries"
                style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column
                    type="selection"
                    width="55" class-name="checkbox_style"></el-table-column>
            <el-table-column
                    prop="name"
                    label="帐套名称" align="center"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="basicSalary"
                    label="基本工资" align="center"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="trafficSalary"
                    label="交通补助" align="center"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="lunchSalary"
                    label="午餐补助" align="center"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="bonus"
                    label="奖金" align="center"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="createDate"
                    label="启动时间" align="center"
                    width="130">
            </el-table-column>

            <el-table-column label="养老金" align="center">
                <el-table-column
                        prop="pensionPer"
                        label="比率" align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="pensionBase"
                        label="基数" align="center"
                        width="120">
                </el-table-column>
            </el-table-column>
            <el-table-column label="医疗保险" align="center">
                <el-table-column
                        prop="medicalPer"
                        label="比率" align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="medicalBase"
                        label="基数" align="center"
                        width="120">
                </el-table-column>
            </el-table-column>
            <el-table-column label="公积金" align="center">
                <el-table-column
                        prop="accumulationFundPer"
                        label="比率" align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="accumulationFundBase"
                        label="基数" align="center"
                        width="120">
                </el-table-column>
            </el-table-column>
            <el-table-column label="操作" align="center"
                             width="240">
                <template slot-scope="scope">
                    <el-button type="primary" @click="showEditView(scope.row)">编辑</el-button>
                    <el-button type="danger" @click="handleDeleteById(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>


        <el-dialog
                :title="dialogTitle" center style="font-weight: bold;"
                :visible.sync="dialogVisible" top="15vh"
                width="40%">
            <div style="display: flex;align-items: center;justify-content: space-around;height: 400px">

                <el-steps :active="active" finish-status="success" direction="vertical">
                    <el-step v-for="(item,index) in salaryTitleItems" :title="item" :key="index"></el-step>
                </el-steps>
                <div v-for="(value,title,index) in salary" v-show="active==index" style="width: 40%;">
                    <el-input v-model.number="salary[title]" :placeholder="'请输入'+salaryTitleItems[index]+'...'"
                              :key="index"></el-input>

                </div>
            </div>
            <div slot="footer" class="dialog-footer">
                <div style="display: flex;justify-content: space-around">
                    <div style="margin-top: 12px;">
                        <el-button @click="pre" v-if="active!=0" type="primary">上一步</el-button>
                        <el-button @click="next" :type="active>9?'success':'primary'">{{active>9?'完成':'下一步'}}
                        </el-button>
                        <el-button @click="cancelDialog" type="danger">取消</el-button>
                    </div>
                </div>
            </div>
        </el-dialog>

    </div>
</template>
<script>
    export default {
        data() {
            return {
                salaries: [],
                multipleSelection: [],
                dialogVisible: false,
                dialogTitle: '',
                active: 0,
                salaryTitleItems: [
                    '帐套名称',
                    '基本工资',
                    '交通补助',
                    '午餐补助',
                    '奖金',
                    '养老金比率',
                    '养老金基数',
                    '医疗保险比率',
                    '医疗保险基数',
                    '公积金比率',
                    '公积金基数'
                ],
                salary: {
                    name: '',
                    basicSalary: '',
                    trafficSalary: '',
                    lunchSalary: '',
                    bonus: '',
                    pensionPer: '',
                    pensionBase: '',
                    medicalPer: '',
                    medicalBase: '',
                    accumulationFundPer: '',
                    accumulationFundBase: '',
                },
                rules: {
                    basicSalary: [
                        {required: true, message: '请输入工号', trigger: 'blur'},
                        {type: 'number', message: '只能输入数字', trigger: 'blur'},
                    ],
                },


            }
        },
        methods: {
            cancelDialog() {
                this.dialogVisible = false;
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            next() {
                if (this.active > 9) {
                    if (this.salary.id) {
                        this.putRequest('/salary/sob/', this.salary).then(resp => {
                            if (resp) {
                                this.initSalaries();
                            }
                        });
                        this.dialogVisible = false;
                    } else {
                        this.postRequest('/salary/sob/', this.salary).then(resp => {
                            if (resp) {
                                this.initSalaries();
                            }
                        });
                        this.dialogVisible = false;

                    }
                } else {
                    this.active++;
                }

            },
            pre() {
                this.active--;
            },
            showAddView() {
                this.active = 0;
                this.salary = {
                    name: '',
                    basicSalary: '',
                    trafficSalary: '',
                    lunchSalary: '',
                    bonus: '',
                    pensionPer: '',
                    pensionBase: '',
                    medicalPer: '',
                    medicalBase: '',
                    accumulationFundPer: '',
                    accumulationFundBase: '',
                };
                this.dialogTitle='新增工资帐套';
                this.dialogVisible = true;

            },
            showEditView(data){
                this.active = 0;
                this.salary.id=data.id;
                this.salary.name=data.name;
                this.salary.basicSalary=data.basicSalary;
                this.salary.trafficSalary=data.trafficSalary;
                this.salary.lunchSalary=data.lunchSalary;
                this.salary.bonus=data.bonus;
                this.salary.pensionPer=data.pensionPer;
                this.salary.pensionBase=data.pensionBase;
                this.salary.medicalPer=data.medicalPer;
                this.salary.medicalBase=data.medicalBase;
                this.salary.accumulationFundPer=data.accumulationFundPer;
                this.salary.accumulationFundBase=data.accumulationFundBase;
                this.dialogTitle='编辑工资帐套';
                this.dialogVisible = true;
            },
            handleDeleteByIds() {
                this.$confirm('此操作将永久删除【' + this.multipleSelection.length + '】条工资帐套, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let deleteUrl = '/salary/sob/?';
                    this.multipleSelection.forEach(item => {
                        deleteUrl += 'ids=' + item.id + '&';
                    });
                    this.deleteRequest(deleteUrl).then(resp => {
                            if (resp) {
                                this.initSalaries();
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
            initSalaries() {
                this.getRequest('/salary/sob/').then(resp => {
                    if (resp) {
                        this.salaries = resp;
                    }
                })
            },
            handleDeleteById(data) {
                this.$confirm('此操作将永久删除工资帐套【' + data.name + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/salary/sob/" + data.id).then(resp => {
                            if (resp) {
                                this.initSalaries();
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
        },
        mounted() {
            this.initSalaries();
        }
    }

</script>
<style>
    .checkbox_style {
        text-align: center !important;
    }

</style>

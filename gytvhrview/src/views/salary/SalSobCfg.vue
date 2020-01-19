<template>
    <div style="margin-top: 10px">
        <el-table
                :data="empData"
                border
                v-loading="loading"
                style="width: 100%">
            <el-table-column
                    prop="name" align="center"
                    label="姓名"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="workID" align="center"
                    label="工号"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="email" align="center"
                    label="电子邮箱"
                    width="300">
            </el-table-column>
            <el-table-column
                    prop="phone" align="center"
                    label="电话号码"
                    width="300">
            </el-table-column>
            <el-table-column
                    prop="department.name" align="center"
                    label="所属部门"
                    width="300">
            </el-table-column>
            <el-table-column
                     align="center"
                    label="工资帐套"
                    width="300">
                <template slot-scope="scope">
                        <el-tooltip class="item" effect="dark"  placement="right" v-if="scope.row.salary">
                            <div slot="content" >
                                <table style="border-spacing:20px 6px">
                                    <tr>
                                        <td  style="text-align: right">基本工资</td>
                                        <td style="text-align: right;">{{scope.row.salary.basicSalary}}</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">交通补助</td>
                                        <td style="text-align: right">{{scope.row.salary.trafficSalary}}</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">午餐补助</td>
                                        <td style="text-align: right">{{scope.row.salary.lunchSalary}}</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">奖金</td>
                                        <td style="text-align: right">{{scope.row.salary.bonus}}</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">养老金比率</td>
                                        <td style="text-align: right">{{scope.row.salary.pensionPer}}</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">养老金基数</td>
                                        <td style="text-align: right">{{scope.row.salary.pensionBase}}</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">医疗保险比率</td>
                                        <td style="text-align: right">{{scope.row.salary.medicalPer}}</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">医疗保险基数</td>
                                        <td style="text-align: right">{{scope.row.salary.medicalBase}}</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">公积金比率</td>
                                        <td style="text-align: right">{{scope.row.salary.accumulationFundPer}}</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">公积金基数</td>
                                        <td style="text-align: right">{{scope.row.salary.accumulationFundBase}}</td>
                                    </tr>
                                </table>
                            </div>

                            <el-tag :disable-transitions="true">{{scope.row.salary.name}}</el-tag>
                        </el-tooltip>
                        <el-tag :disable-transitions="true" v-else>暂未设置</el-tag>
                </template>
            </el-table-column>

            <el-table-column
                    align="center"
                    label="操作"
                    >
                <template slot-scope="scope">
                    <el-popover
                            placement="left"
                            width="200"
                            trigger="click"
                            @show="showSalarySel(scope.row.salary)"
                            @hide="hideSalarySel(scope.row)"
                    >
                        <el-select v-model="currentSid" style="width: 200px" size="small"
                                   placeholder="请选择工资帐套">
                            <el-option
                                    v-for="item in salaries"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                        <el-button type="primary" size="small"slot="reference">修改工资帐套</el-button>
                    </el-popover>
                </template>

            </el-table-column>
        </el-table>
        <div style="display: flex;justify-content: flex-end">

            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[10, 20, 50, 100]"
                    :page-size="size"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                empData: [],
                currentPage: 1,
                size:10,
                total:-1,
                loading:true,
                salaries:[],
                currentSid:-1,

            }

        },
        mounted() {
            this.initEmps();
            this.initSalaries();
        },
        methods: {
            hideSalarySel(emp){
                if(this.currentSid){
                    this.putRequest('/salary/sobcfg/?eid='+emp.id+'&sid='+this.currentSid).then(resp=>{
                        if (resp) {
                            this.initEmps();
                        }
                    })
                }
            },
            showSalarySel(salary){
                if(salary){
                    this.currentSid=salary.id;
                }else {
                    this.currentSid=null;
                }
            },
            initSalaries(){
                this.getRequest('/salary/sobcfg/salaries').then(resp=>{
                    if (resp) {
                        this.salaries=resp;
                    }
                })
            },
            initEmps(){
                this.loading=true;
                this.getRequest('/salary/sobcfg/?page='+this.currentPage+'&size='+this.size).then(resp=>{
                    this.empData=resp.data;
                    this.total=resp.total;
                    //return data is wrong,then get the next request
                    if ((this.empData.length < 1) && this.total && this.total / this.size < this.currentPage) {
                        this.currentPage = Math.ceil(this.total / this.size);
                        this.initEmps();


                    }
                    this.loading=false;
                });

            },
            handleSizeChange(val) {
                this.size=val;
                this.initEmps();
            },
            handleCurrentChange(val) {
                this.currentPage=val;
                this.initEmps();
            }
        },


    }

</script>


<style>

</style>

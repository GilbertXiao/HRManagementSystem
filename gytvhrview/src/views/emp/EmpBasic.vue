<template>
    <div>
        <div>
            <div class="container-head">
                <div>
                    <el-input type="text" prefix-icon="el-icon-search"
                              v-model="keyword" style="width: 400px;" placeholder="通过员工名查找员工，记得回车哦"
                              @keydown.native.enter="initEmps" :disabled="advanceSearchViewVisible"/>
                    <el-button icon="el-icon-search" type="primary" style="margin-left: 10px" @click="initEmps">
                        搜索
                    </el-button>
                    <el-button type="primary" @click="doAdvanceSearchView">
                        <i class="fa fa-lg" v-bind:class="[advanceSearchViewVisible ? classUp:classDown]"
                           style="margin-right: 8px"></i>高级搜索
                    </el-button>
                    <el-button type="primary" @click="dialogVisible=true">
                        <i class="fa fa-plus-circle fa-lg" style="margin-right: 8px"></i>添加员工
                    </el-button>
                    <el-button type="danger" :disabled="multipleSelection.length==0">
                        <i class="fa fa-trash  fa-lg" style="margin-right: 8px"></i>批量删除
                    </el-button>
                </div>

                <div>
                    <el-button type="success">
                        <i class="fa fa-level-up  fa-lg" style="margin-right: 8px"></i>导入
                    </el-button>
                    <el-button type="success">
                        <i class="fa fa-level-down  fa-lg" style="margin-right: 8px"></i>导出
                    </el-button>

                </div>
            </div>

            <transition name="slide-fade">


                <div style="border: #09c0f6 1px;border-style: solid;padding: 15px 5px 0 5px;border-radius: 20px;margin-bottom: 10px"
                     v-show="advanceSearchViewVisible">
                    <el-row :gutter="20">
                        <el-col :span="4">
                            姓名：
                            <el-input type="text" v-model="searchValue.name" style="width: 210px;" size="small"/>
                        </el-col>
                        <el-col :span="4">
                            政治面貌：
                            <el-select v-model="searchValue.politicId" placeholder="请选择" size="small">
                                <el-option
                                        v-for="item in politicsStatus"
                                        :key="item.id"
                                        :label="item.name"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="4">
                            民族：
                            <el-select v-model="searchValue.nationId" placeholder="请选择" size="small">
                                <el-option
                                        v-for="item in nations"
                                        :key="item.id"
                                        :label="item.name"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="4">
                            职位：
                            <el-select v-model="searchValue.posId" placeholder="请选择" size="small">
                                <el-option
                                        v-for="item in positions"
                                        :key="item.id"
                                        :label="item.name"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="4">
                            职称：
                            <el-select v-model="searchValue.jobLevelId" placeholder="请选择" size="small">
                                <el-option
                                        v-for="item in jobLevels"
                                        :key="item.id"
                                        :label="item.name"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-col>

                    </el-row>

                    <el-row :gutter="20">
                        <el-col :span="4" style="display: flex;align-items: center; padding-left: 26px">
                            部门：
                            <el-popover
                                    placement="bottom"
                                    title="标题"
                                    width="210"
                                    trigger="click"
                                    v-model="popVisiable"
                            >
                                <el-tree :data="departments" :props="defaultProps" @node-click="handleNodeClick"
                                         :default-expand-all="true" :expand-on-click-node="false"></el-tree>
                                <el-input v-model="inputDepName" @click="this.popVisiable=!this.popVisiable"
                                          size="small" slot="reference" :readonly="true"
                                          style="width: 210px;margin-left: 6px"/>
                            </el-popover>

                        </el-col>

                        <el-col :span="8" style="display: flex;align-items: center;">
                            入职日期：
                            <el-date-picker
                                    v-model="dateRange"
                                    type="daterange"
                                    range-separator="至"
                                    start-placeholder="开始日期"
                                    value-format="yyyy-MM-dd"
                                    end-placeholder="结束日期" size="small" style="margin-left: 3px;width: 400px "
                                    unlink-panels>
                            </el-date-picker>
                        </el-col>

                        <el-col :span="4" style="display: flex;align-items: center;height: 32px;margin-left: -14px">
                            聘用形式：
                            <el-radio v-model="searchValue.engageForm" label="劳动合同"
                                      style="margin-left: 8px;margin-top: 4px">劳动合同
                            </el-radio>
                            <el-radio v-model="searchValue.engageForm" label="劳务合同" style="margin-top: 4px">劳务合同
                            </el-radio>
                        </el-col>

                    </el-row>
                    <el-row :gutter="20">
                        <el-col :span="24"
                                style="display: flex;align-items: center;margin-left: 10px; justify-content: center">
                            <el-button type="primary" size="small" @click="initEmps"> 搜索</el-button>
                            <el-button size="small" @click="doAdvanceSearchView">取消</el-button>
                        </el-col>
                    </el-row>
                </div>
            </transition>

        </div>
        <div>
            <el-form :model="emp" :rules="rules" ref="addEmpForm" style="margin: 0px;padding: 0px;">
                <div style="text-align: left">

                    <el-dialog
                        :title="dialogTitle"
                        :visible.sync="dialogVisible"
                        width="80%">
                    <el-row>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="姓名:" prop="name">
                                    <el-input prefix-icon="el-icon-edit" v-model="emp.name" size="small"
                                              style="width: 150px"
                                              placeholder="请输入员工姓名"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="性别:" prop="gender">
                                    <el-radio-group v-model="emp.gender">
                                        <el-radio label="男">男</el-radio>
                                        <el-radio style="margin-left: 15px" label="女">女</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="出生日期:" prop="birthday">
                                    <el-date-picker
                                            v-model="emp.birthday"
                                            size="small"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            style="width: 150px"
                                            type="date"
                                            placeholder="出生日期">
                                    </el-date-picker>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="政治面貌:" prop="politicId">
                                    <el-select v-model="emp.politicId" style="width: 200px" size="small"
                                               placeholder="政治面貌">
                                        <el-option
                                                v-for="item in politicsStatus"
                                                :key="item.id"
                                                :label="item.name"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="民族:" prop="nationId">
                                    <el-select v-model="emp.nationId" style="width: 150px" size="small"
                                               placeholder="请选择民族">
                                        <el-option
                                                v-for="item in nations"
                                                :key="item.id"
                                                :label="item.name"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="籍贯:" prop="nativePlace">
                                    <el-input v-model="emp.nativePlace" size="small" style="width: 120px"
                                              placeholder="员工籍贯"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="电子邮箱:" prop="email">
                                    <el-input prefix-icon="el-icon-message" v-model="emp.email" size="small"
                                              style="width: 150px"
                                              placeholder="电子邮箱地址..."></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="联系地址:" prop="address">
                                    <el-input prefix-icon="el-icon-edit" v-model="emp.address" size="small"
                                              style="width: 200px"
                                              placeholder="联系地址..."></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="职位:" prop="posId">
                                    <el-select v-model="emp.posId" style="width: 150px" size="small" placeholder="请选择职位">
                                        <el-option
                                                v-for="item in positions"
                                                :key="item.id"
                                                :label="item.name"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="职称:" prop="jobLevelId">
                                    <el-select v-model="emp.jobLevelId" style="width: 120px" size="small"
                                               placeholder="请选择职称">
                                        <el-option
                                                v-for="item in jobLevels"
                                                :key="item.id"
                                                :label="item.name"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="所属部门:" prop="departmentId">
                                    <el-popover
                                            placement="bottom"
                                            title="标题"
                                            width="210"
                                            trigger="click"
                                            v-model="popVisiable2"
                                    >
                                        <el-tree :data="departments" :props="defaultProps" @node-click="handleNodeClick2"
                                                 :default-expand-all="true" :expand-on-click-node="false"></el-tree>
                                        <el-input v-model="inputDepName" @click="this.popVisiable2=!this.popVisiable2"
                                                  size="small" slot="reference" :readonly="true"
                                                  style="width: 210px;margin-left: 6px"/>
                                    </el-popover>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="电话号码:" prop="phone">
                                    <el-input prefix-icon="el-icon-phone" v-model="emp.phone" size="small"
                                              style="width: 200px"
                                              placeholder="电话号码..."></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="工号:" prop="workID">
                                    <el-input v-model="emp.workID" disabled size="small" style="width: 150px"
                                              placeholder="员工工号..."></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="学历:" prop="tiptopDegree">
                                    <el-select v-model="emp.tiptopDegree" style="width: 120px" size="small"
                                               placeholder="最高学历">
                                        <el-option
                                                v-for="item in degrees"
                                                :key="item.id"
                                                :label="item.name"
                                                :value="item.name">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="毕业院校:" prop="school">
                                    <el-input prefix-icon="el-icon-edit" v-model="emp.school" size="small"
                                              style="width: 150px"
                                              placeholder="毕业院校名称"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="专业名称:" prop="specialty">
                                    <el-input prefix-icon="el-icon-edit" v-model="emp.specialty" size="small"
                                              style="width: 200px"
                                              placeholder="专业名称"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="入职日期:" prop="beginDate">
                                    <el-date-picker
                                            v-model="emp.beginDate"
                                            size="small"
                                            style="width: 130px"
                                            type="date"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            placeholder="入职日期">
                                    </el-date-picker>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="转正日期:" prop="conversionTime">
                                    <el-date-picker
                                            v-model="emp.conversionTime"
                                            size="small"
                                            style="width: 130px"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            type="date"
                                            placeholder="转正日期">
                                    </el-date-picker>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="合同起始日期:" prop="beginContract">
                                    <el-date-picker
                                            v-model="emp.beginContract"
                                            size="small"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            style="width: 135px"
                                            type="date"
                                            placeholder="合同起始日期">
                                    </el-date-picker>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="合同终止日期:" prop="endContract">
                                    <el-date-picker
                                            v-model="emp.endContract"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            size="small"
                                            style="width: 135px"
                                            type="date"
                                            placeholder="合同终止日期">
                                    </el-date-picker>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="身份证号码:" prop="idCard">
                                    <el-input prefix-icon="el-icon-edit" v-model="emp.idCard" size="small"
                                              style="width: 180px"
                                              placeholder="请输入员工身份证号码..."></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="聘用形式:" prop="engageForm">
                                    <el-radio-group v-model="emp.engageForm">
                                        <el-radio label="劳动合同">劳动合同</el-radio>
                                        <el-radio style="margin-left: 15px" label="劳务合同">劳务合同</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div>
                                <el-form-item label="婚姻状况:" prop="wedlock">
                                    <el-radio-group v-model="emp.wedlock">
                                        <el-radio label="已婚">已婚</el-radio>
                                        <el-radio style="margin-left: 15px" label="未婚">未婚</el-radio>
                                        <el-radio style="margin-left: 15px" label="离异">离异</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="cancelEidt">取 消</el-button>
                <el-button size="small" type="primary" @click="addEmp('addEmpForm')">确 定</el-button>
                    </span>
                </el-dialog>

                </div>
            </el-form>
            <el-table
                    :data="emps"
                    border
                    size="small"
                    stripe
                    style="width: 100%"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="55" class-name="checkbox_style">
                </el-table-column>
                <el-table-column
                        fixed
                        prop="name"
                        label="姓名"
                        align="center"
                        width="150">
                </el-table-column>
                <el-table-column
                        fixed
                        prop="workID"
                        label="工号"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="gender"
                        label="性别"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        label="出生日期"
                        align="center"
                        width="120">
                    <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template>
                </el-table-column>
                <el-table-column
                        prop="idCard"
                        label="身份证号码"
                        align="center"
                        width="300">
                </el-table-column>
                <el-table-column
                        prop="wedlock"
                        label="婚姻状况"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="nation.name"
                        label="民族"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="nativePlace"
                        label="籍贯"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="politicsStatus.name"
                        label="政治面貌"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="email"
                        label="电子邮件"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="phone"
                        label="电话号码"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="联系地址"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="department.name"
                        label="所属部门"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="position.name"
                        label="职位"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="jobLevel.name"
                        label="职称"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="engageForm"
                        label="聘用形式"
                        align="center"
                        width="120">
                </el-table-column>
                <el-table-column
                        label="入职日期"
                        align="center"
                        width="120">
                    <template slot-scope="scope">{{ scope.row.beginDate | formatDate}}</template>
                </el-table-column>
                <el-table-column
                        label="转正日期"
                        align="center"
                        width="120">
                    <template slot-scope="scope">{{ scope.row.conversionTime | formatDate}}</template>
                </el-table-column>
                <el-table-column
                        label="合同起始日期"
                        align="center"
                        width="120">
                    <template slot-scope="scope">{{ scope.row.beginContract | formatDate}}</template>
                </el-table-column>
                <el-table-column
                        label="合同截至日期"
                        align="center"
                        width="120">
                    <template slot-scope="scope">{{ scope.row.endContract | formatDate}}</template>
                </el-table-column>
                <el-table-column
                        prop="contractTerm"
                        align="center"
                        label="合同期限"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="tiptopDegree"
                        align="center"
                        label="最高学历"
                        width="120">
                </el-table-column>

                <el-table-column
                        fixed="right"
                        label="操作"
                        align="center"
                        width="300">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" size="small">编辑</el-button>
                        <el-button type="primary" size="small">查看高级资料</el-button>
                        <el-button type="danger" size="small">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <div style="display: flex;justify-content: flex-end">
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="currentPage"
                        :page-sizes="[10, 20, 30, 50]"
                        :page-size="pageSize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="totalSize">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                emps: [],
                totalSize: -1,
                currentPage: 1,
                pageSize: 10,
                keyword: '',
                advanceSearchViewVisible: false,
                classUp: 'fa-chevron-circle-up',
                classDown: 'fa fa-chevron-circle-down',
                nations: [],
                jobLevels: [],
                departments: [],
                positions: [],
                politicsStatus: [],
                degrees: [],

                searchValue: {
                    name: "",
                    nationId: "",
                    politicId: "",
                    departmentId: "",
                    jobLevelId: "",
                    posId: "",
                    engageForm: "",
                },
                emp: {
                    id: 3,
                    name: "赵琳浩",
                    gender: "男",
                    birthday: "1993-03-04T00:00:00.000+0000",
                    idCard: "610122199303041456",
                    wedlock: "未婚",
                    nationId: 1,
                    nativePlace: "陕西",
                    politicId: 3,
                    email: "zhao@qq.com",
                    phone: "15698887795",
                    address: "陕西省西安市莲湖区",
                    departmentId: 91,
                    jobLevelId: 12,
                    posId: 33,
                    engageForm: "劳动合同",
                    tiptopDegree: "博士",
                    specialty: "电子工程",
                    school: "哈尔滨理工大学",
                    beginDate: "2018-01-01T00:00:00.000+0000",
                    workState: "在职",
                    workID: "00000003",
                    contractTerm: 3.5,
                    conversionTime: "2018-04-01T00:00:00.000+0000",
                    notWorkDate: null,
                    beginContract: "2018-01-01T00:00:00.000+0000",
                    endContract: "2021-07-14T00:00:00.000+0000",
                    workAge: null
                },
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                popVisiable: false,
                popVisiable2: false,
                inputDepName: '',
                dateRange: [],
                multipleSelection: [],
                dialogVisible: false,
                dialogTitle: '',
                rules: {
                    name: [{required: true, message: '必填:姓名', trigger: 'blur'}],
                    gender: [{required: true, message: '必填:性别', trigger: 'blur'}],
                    birthday: [{required: true, message: '必填:出生日期', trigger: 'blur'}],
                    idCard: [{
                        required: true,
                        message: '必填:身份证号码',
                        trigger: 'blur'
                    }, {
                        pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
                        message: '身份证号码格式不正确',
                        trigger: 'blur'
                    }],
                    wedlock: [{required: true, message: '必填:婚姻状况', trigger: 'blur'}],
                    nationId: [{required: true, message: '必填:民族', trigger: 'change'}],
                    nativePlace: [{required: true, message: '必填:籍贯', trigger: 'blur'}],
                    politicId: [{required: true, message: '必填:政治面貌', trigger: 'blur'}],
                    email: [{required: true, message: '必填:电子邮箱', trigger: 'blur'}, {
                        type: 'email',
                        message: '邮箱格式不正确',
                        trigger: 'blur'
                    }],
                    phone: [{required: true, message: '必填:电话号码', trigger: 'blur'}],
                    address: [{required: true, message: '必填:联系地址', trigger: 'blur'}],
                    departmentId: [{required: true, message: '必填:部门', trigger: 'change'}],
                    jobLevelId: [{required: true, message: '必填:职称', trigger: 'change'}],
                    posId: [{required: true, message: '必填:职位', trigger: 'change'}],
                    engageForm: [{required: true, message: '必填:聘用形式', trigger: 'blur'}],
                    tiptopDegree: [{required: true, message: '必填:最高学历', trigger: 'change'}],
                    specialty: [{required: true, message: '必填:专业', trigger: 'blur'}],
                    workID: [{required: true, message: '必填:工号', trigger: 'blur'}],
                    school: [{required: true, message: '必填:毕业院校', trigger: 'blur'}],
                    beginDate: [{required: true, message: '必填:入职日期', trigger: 'blur'}],
                    conversionTime: [{required: true, message: '必填:转正日期', trigger: 'blur'}],
                    beginContract: [{required: true, message: '必填:合同起始日期', trigger: 'blur'}],
                    endContract: [{required: true, message: '必填:合同终止日期', trigger: 'blur'}],
                    workAge: [{required: true, message: '必填:工龄', trigger: 'blur'}]
                }
            }
        },
        mounted() {
            this.initEmps();
            this.initBaseData();
        },
        methods: {
            emptySearchValue() {
                this.searchValue = {
                    name: '',
                    nationId: '',
                    politicId: '',
                    departmentId: '',
                    jobLevelId: '',
                    posId: '',
                    engageForm: '',
                };
                this.dateRange = [];
                this.inputDepName = '';
            },
            initBaseData() {
                if (window.localStorage.getItem('jobLevels')) {
                    this.jobLevels = JSON.parse(window.localStorage.getItem('jobLevels'));
                } else {
                    this.getRequest('/employee/basic/jobLevel').then(resp => {
                        if (resp) {
                            this.jobLevels = resp;
                            window.localStorage.setItem('jobLevels', JSON.stringify(resp));
                        }
                    })
                }
                ;
                if (window.localStorage.getItem('nations')) {
                    this.nations = JSON.parse(window.localStorage.getItem('nations'));
                } else {
                    this.getRequest('/employee/basic/nation').then(resp => {
                        if (resp) {
                            this.nations = resp;
                            window.localStorage.setItem('nations', JSON.stringify(resp));
                        }
                    })
                }
                ;
                if (window.localStorage.getItem('positions')) {
                    this.positions = JSON.parse(window.localStorage.getItem('positions'));
                } else {
                    this.getRequest('/employee/basic/position').then(resp => {
                        if (resp) {
                            this.positions = resp;
                            window.localStorage.setItem('positions', JSON.stringify(resp));
                        }
                    })
                }
                ;
                if (window.localStorage.getItem('politicsStatus')) {
                    this.politicsStatus = JSON.parse(window.localStorage.getItem('politicsStatus'));
                } else {
                    this.getRequest('/employee/basic/politicsStatus').then(resp => {
                        if (resp) {
                            this.politicsStatus = resp;
                            window.localStorage.setItem('politicsStatus', JSON.stringify(resp));
                        }
                    })
                }
                ;
                if (window.localStorage.getItem('department')) {
                    this.departments = JSON.parse(window.localStorage.getItem('department'));
                } else {
                    this.getRequest('/employee/basic/department').then(resp => {
                        if (resp) {
                            this.departments = resp;
                            window.localStorage.setItem('department', JSON.stringify(resp));
                        }
                    })
                }
                ;
            },
            handleClick(row) {
                console.log(row);
            },
            initEmps() {
                let url = '/employee/basic/?page=' + this.currentPage + '&size=' + this.pageSize;
                if (this.isNotNullORBlankWithoutError(this.keyword)) {
                    url += '&name=' + this.keyword;
                }
                if (this.isNotNullORBlankWithoutError(this.searchValue.name)) {
                    url += '&name=' + this.searchValue.name;
                }
                if (this.isNotNullORBlankWithoutError(this.searchValue.nationId)) {
                    url += '&nationId=' + this.searchValue.nationId;
                }
                if (this.isNotNullORBlankWithoutError(this.searchValue.politicId)) {
                    url += '&politicId=' + this.searchValue.politicId;
                }
                if (this.isNotNullORBlankWithoutError(this.searchValue.departmentId)) {
                    url += '&departmentId=' + this.searchValue.departmentId;
                }
                if (this.isNotNullORBlankWithoutError(this.searchValue.jobLevelId)) {
                    url += '&jobLevelId=' + this.searchValue.jobLevelId;
                }
                if (this.isNotNullORBlankWithoutError(this.searchValue.posId)) {
                    url += '&posId=' + this.searchValue.posId;
                }
                if (this.isNotNullORBlankWithoutError(this.searchValue.engageForm)) {
                    url += '&engageForm=' + this.searchValue.engageForm;
                }
                if (this.isNotNullORBlankWithoutError(this.dateRange)) {
                    url += '&dateRange=' + this.dateRange;
                }
                console.log(url);
                this.getRequest(url).then(resp => {
                    this.emps = resp.data;
                    this.totalSize = resp.total;
                    console.log(this.totalSize);
                    console.log(this.totalSize / this.pageSize);
                    if ((this.emps.length < 1) && this.totalSize && this.totalSize / this.pageSize < this.currentPage) {
                        this.currentPage = Math.ceil(this.totalSize / this.pageSize);
                        this.initEmps();
                    }
                })
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.initEmps(this.currentPage, this.pageSize);
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.initEmps(this.currentPage, this.pageSize);
            },
            doAdvanceSearchView() {
                this.keyword = '';
                this.emptySearchValue();
                this.advanceSearchViewVisible = !this.advanceSearchViewVisible;

            },
            handleNodeClick(nodeData) {
                this.popVisiable = !this.popVisiable;
                this.inputDepName = nodeData.name;
                this.searchValue.departmentId = nodeData.id;
            },
            handleNodeClick2(nodeData) {
                this.popVisiable2 = !this.popVisiable2;
                this.inputDepName = nodeData.name;
                this.searchValue.departmentId = nodeData.id;
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            cancelEidt(){

            }

        },
    }
</script>

<style>
    .container-head {
        margin-top: 10px;
        margin-bottom: 10px;
        display: flex;
        justify-content: space-between;
    }

    .el-row {
        margin-bottom: 20px;

        &:last-child {
            margin-bottom: 0;
        }
    }

    .el-col {
        border-radius: 4px;
    }

    .bg-purple-dark {
        background: #99a9bf;
    }

    .bg-purple {
        background: #d3dce6;
    }

    .bg-purple-light {
        background: #e5e9f2;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }

    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
    }

    .slide-fade-enter-active {
        transition: all .3s ease;
    }

    .slide-fade-leave-active {
        transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
    }

    .slide-fade-enter, .slide-fade-leave-to
        /* .slide-fade-leave-active for below version 2.1.8 */
    {
        transform: translateX(10px);
        opacity: 0;
    }

    .checkbox_style {
        text-align: center !important;
    }
</style>

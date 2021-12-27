<template>
    <div>
        <!--多条件查询表单-->
        <el-form
        :inline="true"
        class="demo-form-inline"
        style="margin-left: 20px; margin-top: 12px;"
        >
        <el-form-item label="名称">
            <el-input
            v-model="teacherQuery.name"
            placeholder="请输入名称"
            ></el-input>
        </el-form-item>
        <el-form-item label="级别">
            <el-select v-model="teacherQuery.level" placeholder="讲师头衔">
            <el-option label="高级讲师" :value="1"></el-option>
            <el-option label="特级讲师" :value="2"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="添加时间">
            <el-time-picker
            placeholder="选择开始时间"
            v-model="teacherQuery.begin"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="00:00:00"
            type="datetime"
            ></el-time-picker>
        </el-form-item>
        <el-form-item>
            <el-time-picker
            placeholder="选择截止时间"
            v-model="teacherQuery.end"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="00:00:00"
            type="datetime"
            ></el-time-picker>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="getList()"
            >查询</el-button
            >
            <el-button type="default" @click="resetData()">清空</el-button>
        </el-form-item>
        </el-form>

        <!-- 显示数据的列表 -->
        <el-table
        :data="list"
        style="width: 100%"
        border
        fit
        highlight-current-row
        element-loading-text="数据加载中"
        v-loading="listLoading"
        >
        <el-table-column prop="date" label="序号" width="70" align="center">
            <template slot-scope="scope">
            {{ (page - 1) * limit + scope.$index + 1 }}
            </template>
        </el-table-column>
        <el-table-column prop="name" label="名称" width="80"> </el-table-column>
        <el-table-column label="头衔" width="80">
            <template slot-scope="scope">
            {{ scope.row.level === 1 ? "高级讲师" : "首席讲师" }}
            </template>
        </el-table-column>
        <el-table-column prop="intro" label="资历" />
        <el-table-column prop="gmtCreate" label="添加时间" width="160" />
        <el-table-column prop="sort" label="排序" width="60" />
        <el-table-column label="操作" width="200" align="center">
            <template slot-scope="scope">
            <router-link :to="'/teacher/edit/' + scope.row.id">
                <el-button type="primary" size="mini" icon="el-icon-edit"
                >修改</el-button
                >
            </router-link>
            <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="removeById(scope.row.id)"
                >删除</el-button
            >
            </template>
        </el-table-column>
        </el-table>

        <!--分页组件   @current-change === v-on:current-change  方法的括号省略了 -->
        <el-pagination
            background
            layout="prev, pager, next,total,jumper"
            :total="total"
            :page-size="limit"
            style="padding: 30px 0; text-align: center"
            :current-page="page"
            @current-change="getList"
            >
        </el-pagination>
  </div>
</template>

<script>
// 引入调用teacher.js文件
import teacher from '@/api/edu/teacher'
export default {
    // 写核心代码位置，不用new vue对象，前面已经封装好了
    // data: {},
    data() {
        return {
            list: null,
            page: 1,
            limit: 3,
            total: 0 , // 总记录数
            teacherQuery: {
            }
        }
    },
    created() { // 表示页面渲染之前执行，一般调用methods定义方法 
        this.getList();
    },
    methods: { // 创建具体的方法，调用teacher.js定义的方法
        getList(page = 1) {
            this.page = page
            // 使用teacher.的话不用提示出来，要自己去复制过来
            teacher.getTeacherListPage(this.page, this.limit, this.teacherQuery)
                .then(response => { // 请求成功
                    // console.log(response)
                    this.list  = response.data.rows
                    this.total = response.data.total
                    console.log(this.list)
                    console.log(this.total)
                }) 
                .catch(error => { // 请求失败
                    console.log(error)
                }) 
        },
        // 清空方法 1.清空表单输入条件数据  2.查询所有数据
        resetData() {
           // 1.清空表单输入条件数据  
            // this.teacherQuery = null  // 发生undefined错误
            this.teacherQuery = null
           // 2.查询所有数据
           this.getList()
        },
        // 删除讲师的方法
        removeById(id) {
            this.$confirm("此操作将永久删除该讲师记录, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            }).then(() => {
                //点击确定，执行then方法
                teacher.removeById(id)
                    .then((resp) => {//删除成功
                    //提示信息
                    this.$message({
                        type: "success",
                        message: "删除成功!",
                    });
                    //刷新页面
                    this.getList();
                });
            });
        }

    }
}
</script>

<style>

</style>
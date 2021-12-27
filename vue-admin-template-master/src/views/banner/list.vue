<template>
  <div>
    <!-- 显示数据的列表 -->
    <el-table
    :data="bannerList"
    style="width: 100%"
    border
    fit
    highlight-current-row
    element-loading-text="数据加载中"
    v-loading="listLoading"
    >
    <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
        {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
    </el-table-column>
    <el-table-column prop="title" label="标题" width="80"> </el-table-column>
    <el-table-column prop="imageUrl" label="图片路径" width="240" align="center"></el-table-column>

    <el-table-column
      prop="imageUrl"
      label="图片"
      sortable
      width="240"
      align="center">
      <!--插入图片链接的代码-->
      <template slot-scope="scope">
        <img  :src="scope.row.imageUrl" alt="" style="width: 200px;height: 100px">
      </template>
    </el-table-column>

    <el-table-column prop="linkUrl" label="链接地址" width="80"> </el-table-column>
    <el-table-column prop="gmtCreate" label="添加时间" width="160" />
    <el-table-column prop="sort" label="排序" width="60" />
    <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
        <router-link :to="'/banner/info/' + scope.row.id">
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
import banner from '@/api/cms/banner.js'

export default {
  data() {
    return {
      bannerList: [],
      total: 0,
      limit: 3,
      page: 1,
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList(page = 1) {
      console.log("运行了getList")
      this.page = page;
      banner.getBannerListPage(this.page, this.limit)
        .then(response => {
          this.bannerList = response.data.items;
          this.total = response.data.total;
        })
        .catch(error => {
          console.log(error)
        })
    },
    removeById(id) {
      banner.removeById(id)
        .then(response => {
           //提示信息
            this.$message({
                message: "删除幻灯片" + id + "成功",
                type: "success",
            });
            //回到讲师列表 路由跳转
            this.$router.push({path: '/banner/list'})
            this.getList();
        })
    }
  }
}
</script>

<style>

</style>
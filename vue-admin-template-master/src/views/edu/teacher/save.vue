<template>
    <div class="app-container">
        <el-form label-width="120px">
            <el-form-item label="讲师名称">
                <el-input v-model="teacher.name" />
            </el-form-item>
            <el-form-item label="讲师排序">
                <el-input-number
                v-model="teacher.sort"
                controls-position="right"
                :min="0"
                />
            </el-form-item>
            <el-form-item label="讲师头衔">
                <el-select v-model="teacher.level" clearable placeholder="选择讲师头衔">
                    <!--
                    数据类型一定要和取出的json中的一致，否则没法回填
                    因此，这里value使用动态绑定的值，保证其数据类型是number
                    -->
                    <el-option :value="1" label="高级讲师" />
                    <el-option :value="2" label="首席讲师" />
                </el-select>
            </el-form-item>
            <el-form-item label="讲师资历">
                <el-input v-model="teacher.career" />
            </el-form-item>
            <el-form-item label="讲师简介">
                <el-input v-model="teacher.intro" :rows="10" type="textarea" />
            </el-form-item>
            <!-- 讲师头像：TODO -->
            <!-- 讲师头像 -->
            <el-form-item label="讲师头像">
                <!-- 头衔缩略图 -->
                <pan-thumb :image="teacher.avatar" />
                <!-- 文件上传按钮 -->
                <el-button
                        type="primary"
                        icon="el-icon-upload"
                        @click="imagecropperShow = true"
                        >更换头像
                </el-button>
                <!--
                <input type="file"> 与 field="file"的效果是一样的
                v-show：是否显示上传组件
                :key：类似于id，如果一个页面多个图片上传控件，可以做区分
                :url：后台上传的url地址
                @close：关闭上传组件
                @crop-upload-success：上传成功后的回调 -->
                <image-cropper
                    v-show="imagecropperShow"
                    :width="300"
                    :height="300"
                    :key="imagecropperKey"
                    :url="BASE_API + '/eduoss/fileoss'"
                    field="file"
                    @close="close"
                    @crop-upload-success="cropSuccess"
                />
            </el-form-item>

            <el-form-item>
                <el-button
                        :disabled="saveBtnDisabled"
                        type="primary"
                        @click="saveOrUpdate"
                        >保存</el-button
                    >
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
//引入对应的新增api方法
import teacherApi from "@/api/edu/teacher.js";
//引入头像组件
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'

export default {
    components:{ImageCropper,PanThumb},
    data() {
        return {
            teacher: {
                name: "",
                sort: 0,
                level: 1,
                career: "",
                intro: "",
                avatar: 'https://lzy-gili-edu.oss-cn-hangzhou.aliyuncs.com/2021/12/07/19685467e6054145982af5134de418e2file.png'            
                },
            // 上传弹框组件是否显示
            imagecropperShow: false,
            imagecropperKey: 0,  // 上传组件的唯一标识
            BASE_API: process.env.BASE_API, // 获取dev.env.js里面地址
            saveBtnDisabled: false, // 保存按钮是否禁用,
        };
    },
    created() {//在页面渲染之前
        // this.init();  // 这里不用也可以实现，不影响
    },
    watch: {
        $route(to, from) { // 路由的变化方式，固定的写法。当路由一变化，就会发生执行。
            this.init()
        }
    },
    methods: {
        close(){ //关闭上传弹框的方法
            this.imagecropperShow=false;
            //上传组件初始化
            this.imagecropperKey = this.imagecropperKey+1
        },
        cropSuccess(data){ //上传成功的方法
            this.imagecropperShow=false;
            //参数resp.data被封装到了方法参数data中了
            this.teacher.avatar = data.url
            this.imagecropperKey = this.imagecropperKey+1
        },

        init() {
            //判断路径中是否有id值，有id值表示做的是修改操作
            if(this.$route.params && this.$route.params.id){
                //从路径中获取id值
                const id = this.$route.params.id
                //调用根据id查询的方法
                this.getInfoById(id)
            } else { // 表示没有id值做的是添加操作，将teacher的值进行清空
                this.teacher = {}
            }
        },
        //根据id查询，数据回显
        getInfoById(id){
            teacherApi.getTeacherInfo(id)
                .then(resp =>{
                    this.teacher = resp.data.item
            })
        },

        // 修改还是添加
        saveOrUpdate() {
            // 判断修改还是添加
            // 根据teacher是否有id值
            if(!this.teacher.id) {
                // 添加
                this.saveData();
            } else {
                this.updateTeacher();
            }
            // this.saveBtnDisabled = true;  // 保存按钮是否禁用
        },

        // 修改讲师方法
        updateTeacher() {
            teacherApi.updateTeacherInfo(this.teacher)
                .then(resp => {
                    //提示信息
                    this.$message({
                        message: "修改讲师信息成功",
                        type: "success",
                    });
                    //回到讲师列表 路由跳转
                    this.$router.push({path: '/teacher/table'})
                })
        },


        // 保存
        saveData() {
            teacherApi.addTeacher(this.teacher)
                .then((resp) => {//添加成功
                    //提示信息
                    this.$message({
                        message: "添加讲师记录成功",
                        type: "success",
                    });
                    //回到讲师列表 路由跳转
                    this.$router.push({path: '/teacher/table'})
                });
        },
    },
};
</script>

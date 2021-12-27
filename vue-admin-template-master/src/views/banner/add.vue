<template>
    <div class="app-container">
        <el-form label-width="120px">
            <el-form-item label="幻灯片标题">
                <el-input v-model="banner.title" />
            </el-form-item>
            <el-form-item label="幻灯片排序">
              <el-input-number
              v-model="banner.sort"
              controls-position="right"
              :min="0"
              />
            </el-form-item>
            <el-form-item label="幻灯片链接地址" >
                <el-input v-model="banner.linkUrl" />
            </el-form-item>
            
            <el-form-item label="幻灯片封面">
              <el-upload
                  :show-file-list="false"
                  :on-success="handleAvatarSuccess"
                  :before-upload="beforeAvatarUpload"
                  :action="BASE_API + '/eduoss/fileoss'"
                  class="avatar-uploader"
                  >
                  <img :src="banner.imageUrl" />
                </el-upload>
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
//引入头像组件
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'
import banner from '@/api/cms/banner.js'

export default {
    components:{ImageCropper,PanThumb},
    data() {
        return {
          banner: {
            title: '',
            // imageUrl: '/static/iu.png',
            imageUrl: '',
            linkUrl: '',
            sort: 0,
            gmtCreate: ''
          },
        // 上传弹框组件是否显示
        imagecropperShow: false,
        imagecropperKey: 0,  // 上传组件的唯一标识
        BASE_API: process.env.BASE_API, // 获取dev.env.js里面地址
        saveBtnDisabled: false, // 保存按钮是否禁用,
      }
    },
    created() {//在页面渲染之前
        this.init();  // 这里不用也可以实现，不影响
    },
    watch: {
        $route(to, from) { // 路由的变化方式，固定的写法。当路由一变化，就会发生执行。
            this.init()
        }
    },
    methods: {
      //上传封面成功调用的方法
      handleAvatarSuccess(resp,file) {
          this.banner.imageUrl = resp.data.url
      },
      //上传之前要调用的方法
      beforeAvatarUpload(file) {
        const isJPG = file.type === "image/jpeg";
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
            this.$message.error("上传头像图片只能是 JPG 格式!");
        }
        if (!isLt2M) {
            this.$message.error("上传头像图片大小不能超过 2MB!");
        }
        return isJPG && isLt2M;
      },

      init() {
          //判断路径中是否有id值，有id值表示做的是修改操作
          if(this.$route.params && this.$route.params.id){
              //从路径中获取id值
              const id = this.$route.params.id
              //调用根据id查询的方法
              this.getInfoById(id)
          } else { // 表示没有id值做的是添加操作，将banner的值进行清空
              this.banner = {
                sort: 0,
                imageUrl: '/static/iu3.jpg',
              }
          }
      },
      //根据id查询，数据回显
      getInfoById(id){
          banner.getById(id)
            .then(resp =>{
                this.banner = resp.data.item
          })
      },

      // 修改还是添加
      saveOrUpdate() {
          // 判断修改还是添加
          // 根据banner是否有id值
          if(!this.banner.id) {
              // 添加
              this.saveData();
          } else {
              // 修改
              this.updatebanner();
          }
          // this.saveBtnDisabled = true;  // 保存按钮是否禁用
      },

      // 修改banner方法
      updatebanner() {
          banner.updateBannerInfo(this.banner)
              .then(resp => {
                  //提示信息
                  this.$message({
                      message: "修改幻灯片信息成功",
                      type: "success",
                  });
                  //回到幻灯片列表 路由跳转
                  this.$router.push({path: '/banner/list'})
              })
      },


      // 保存也就是插入的方法
      saveData() {
          banner.saveBanner(this.banner)
              .then((resp) => {//添加成功
                  //提示信息
                  this.$message({
                      message: "添加幻灯片记录成功",
                      type: "success",
                  });
                  //回到幻灯片列表 路由跳转
                  this.$router.push({path: '/banner/list'})
              });
      },
    },
};
</script>

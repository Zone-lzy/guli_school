import request from '@/utils/request'

export default {
    //1、幻灯片列表（多条件分页查询）
    //page：当前页，limit：每页记录数，teacherQuery：条件对象
    getBannerListPage(page,limit){
      return request({
          url: `/educms/banner/pageBanner/${page}/${limit}`,
          method: 'get',
        })
    },
    //根据id删除幻灯片
    removeById(id){
        return request({
            url: `/educms/banner/remove/${id}`,
            method: 'delete',
        })
    },
    //新增幻灯片
    saveBanner(banner){
        return request({
            url: `/educms/banner/addBanner`,
            method: `post`,
            data: banner
        })
    },
    //根据id查询幻灯片
    getById(id){
        return request({
            url: `/educms/banner/get/${id}`,
            method: `get`,
        })
    },
    //修改幻灯片信息
    updateBannerInfo(crmBanner){
        return request({
            url: `/educms/banner/update`,
            method: `put`,
            data: crmBanner
        })
    },  
}
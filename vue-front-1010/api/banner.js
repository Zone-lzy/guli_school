import request from '@/utils/request' //引入已经封装好的axios 和 拦截器

export default{

    // 查询前两条banner数据
    getListBanner() {
        return request({
            url: `/educms/bannerfront/getAllBanner`,
            method: 'get'
        });
    },
}



import request from '@/utils/request'

export default {
    // 1.讲师分页查询
    // current 当前页
    // limit 每页条数 
    // teacherQuery条件对象
    getTeacherListPage(current, limit, teacherQuery) {
      return request({
        // url: '/eduservice/teacher/pageTeacherCondition/'+ 'current'+ '/' +limit,
        url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}`,
        method: 'post',
      //   teacherQuery 条件对象，后端使用RequestBody获取数据
      // data表示把对象转换为json进行传递到接口里面
        data: teacherQuery
      })
    },
     //根据id删除讲师
    removeById(id){
      return request({
          url: `/eduservice/teacher/${id}`,
          method: 'delete',
      })
    },
    // 添加讲师
    addTeacher(teacher) {
      return request({
        url: `/eduservice/teacher/addTeacher`,
        method: 'post',
        data: teacher
      })
    },
    // 获取讲师数据
    getTeacherInfo(id) {
      return request({
        url: `/eduservice/teacher/getById/${id}`,
        method: 'get'
      })
    },
    // 修改讲师数据
    updateTeacherInfo(teacher) {
      return request({
        url: `/eduservice/teacher/updateById`,
        method: 'post',
        data: teacher
      })
    }
      
}
export function getList(params) {
  return request({
    url: '/table/list',
    method: 'get',
    params
  })
}

import request from '@/utils/request'
export default {
    getPageList(page, limit, courseId) {
        return request({// /eduservice/comment/getCommentPage/{page}/{limit}
            url: `/eduservice/comment/getCommentPage/${page}/${limit}`,
            method: 'get',
            params: courseId
        })
    },
    addComment(comment) {
        return request({// /eduservice/comment/auth/addComment
            url: `/eduservice/comment/auth/addComment`,
            method: 'post',
            data: comment
        })
    }
}

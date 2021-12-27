import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/eduservice/user/login', // default: /user/login
    method: 'post',
    data: {
      username,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/eduservice/user/info', // default： /user/info
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

import $axios from 'axios'
const baseUrl = 'https://k5b206.p.ssafy.io/api'

export function getCarecenter ({}) {
  const url = baseUrl + '/carecenter'
  return $axios.get(url)
}

export function signUp ({}, user) {
  console.log(user,'액션')
  const url = baseUrl + '/user/regist'
  return $axios.post(url,user)
}
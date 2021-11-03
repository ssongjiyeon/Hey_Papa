import $axios from 'axios'
const baseUrl = 'https://k5b206.p.ssafy.io/api'
// 산후조리원 위치 받아오기
export function getCarecenter ({}) {
  const url = baseUrl + '/carecenter'
  return $axios.get(url)
}
// 회원가입
export function signUp ({}, user) {
  const url = baseUrl + '/user/regist'
  return $axios.post(url,user)
}
// 이메일 중복 확인
export function checkEmail ({}, email) {
  console.log(email,'email')
  const url = baseUrl + '/user/email'
  console.log(url,'url')
  return $axios.post(url, email)
}
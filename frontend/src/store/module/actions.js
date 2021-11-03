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
  const url = baseUrl + '/user/email'
  return $axios.post(url, email)
}
// 로그인
export function Login ({}, user) {
  const url = baseUrl + '/user/login'
  return $axios.post(url, user)
}
// 게시글쓰기
export function writeArticle ({}, article) {
  console.log(article,'article')
  const url = baseUrl + '/article'
  return $axios.post(url, article)
}
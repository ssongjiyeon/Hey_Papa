import $axios from "axios";
import { component } from "vue-control";
const baseUrl = "https://k5b206.p.ssafy.io/api";
const user_id = localStorage.getItem("userId");
// 산후조리원 위치 받아오기
export function getCarecenter({}) {
  const url = baseUrl + "/carecenter";
  return $axios.get(url);
}
// 회원가입
export function signUp({}, user) {
  const url = baseUrl + "/user/regist";
  return $axios.post(url, user);
}
// 이메일 중복 확인
export function checkEmail({}, email) {
  const url = baseUrl + "/user/email";
  return $axios.post(url, email);
}
// 로그인
export function Login({}, user) {
  const url = baseUrl + "/user/login";
  return $axios.post(url, user);
}
// 유저 정보 수정
export function setUser({}, user) {
  const url = baseUrl + "/user/" + user_id;
  return $axios.put(url, user);
}
// 유저 정보 불러오기
export function requestInfo({}, id) {
  const url = baseUrl + "/user/" + id;
  return $axios.get(url);
}
// 로그아웃
export function logout({}) {
  const url = baseUrl + "/user/logout";
  return $axios.get(url);
}
// 나의 게시글 가져오기
export function myArticle({}) {
  const url = baseUrl + "/user/article/" + user_id;
  return $axios.get(url);
}
// 나의 좋아요 게시글 가져오기
export function mylikeArticle({}) {
  const url = baseUrl + "/user/like/" + user_id;
  return $axios.get(url);
}
// 찜한 문제 가져오기
export function myZzim({}) {
  const url = baseUrl + "/user/quiz/" + user_id;
  return $axios.get(url);
}
// 게시글 수정하기
export function modifyArticle({}, object) {
  const url = baseUrl + "/article/" + object.id;
  return $axios.put(url, object.article);
}
//댓글 불러오기
export function getReply({}, articleId) {
  const url = baseUrl + "/article/" + articleId;
  return $axios.get(url);
}
// 게시글 전체 불러오기
export function allArticle({}, userId) {
  console.log(userId, "ui");
  const url = baseUrl + "/article/all/" + userId;
  console.log("불러오기");
  return $axios.get(url);
}
//댓글 달기
export function writeReply({}, replyContent) {
  const url = baseUrl + "/article/" + replyContent.articleNumber;
  return $axios.post(url, replyContent.realContent);
}
// 전체 게시글 불러오기
export function allArticle({}, id) {
  const url = baseUrl + "/article/all/" + id;
  return $axios.get(url);
}
// 게시글 삭제하기
export function deleteArticle({}, id) {
  const url = baseUrl + "/article/" + id;
  return $axios.delete(url);
}
// 게시글 쓰기
export function writeArticle({}, article) {
  const url = baseUrl + "/article";
  return $axios.post(url, article);
}
// 게시글 좋아요
export function likeArticle({}, object) {
  const send = {
    check: object.check,
    user_id: object.user_id,
  };
  const url = baseUrl + "/article/" + object.id + "/like";
  return $axios.post(url, send);
}

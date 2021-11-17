import $axios from "axios";

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
export function myArticle({}, id) {
  const url = baseUrl + "/user/article/" + id;
  return $axios.get(url);
}
// 나의 좋아요 게시글 가져오기
export function mylikeArticle({}, id) {
  const url = baseUrl + "/user/like/" + id;
  return $axios.get(url);
}
// 찜한 문제 가져오기
export function myZzim({}, id) {
  const url = baseUrl + "/user/quiz/" + id;
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
  const url = baseUrl + "/article/all/" + userId;
  return $axios.get(url);
}
//댓글 달기
export function writeReply({}, replyContent) {
  const url = baseUrl + "/article/" + replyContent.articleNumber;
  return $axios.post(url, replyContent.realContent);
}
// 게시글 삭제하기
export function deleteArticle({}, id) {
  const url = baseUrl + "/article/" + id;
  return $axios.delete(url);
}
// 게시글 쓰기
export function writeArticle({}, article) {
  const formdata = new FormData();
  formdata.append("article_thumbnail", article.img);
  formdata.append("content", article.content);
  formdata.append("hashtag", article.hashtag);
  formdata.append("user_id", article.user_id);
  const url = baseUrl + "/article";
  return $axios({
    method: "post",
    url: url,
    headers: {
      "Content-Type": "multipart/form-data",
    },
    data: formdata,
  });
}
// 프로필 수정
export function putProfile({}, user) {
  const formdata = new FormData();
  formdata.append("user_id", user.user_id);
  formdata.append("user_thumbnail", user.user_thumbnail);
  const url = baseUrl + "/user/profile/" + user.user_id;
  return $axios({
    method: "put",
    url: url,
    headers: {
      "Content-Type": "multipart/form-data",
    },
    data: formdata,
  });
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
//퀴즈 찜하기
export function saveQuiz({}, id, object) {
  const url = baseUrl + "/quiz/" + id + "/myquiz";
  return $axios.post(url, info);
}
//해시태그 검색
export function search({}, object) {
  const url = baseUrl + "/article/search/" + object.hashtag;
  return $axios.post(url, object);
}

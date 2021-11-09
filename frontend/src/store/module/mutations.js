export function setUser(state, user) {
  state.user = user;
}
export function setPwd(state, pwd) {
  state.loginPwd = pwd;
}
export function quizList(state, quizList) {
  state.quizList = quizList;
}
export function setPage(state, page) {
  state.current_page = page;
}
export function setArticle(state, article) {
  state.myArticle = article;
}
export function setlikeArticle(state, article) {
  state.myLike = article;
}
export function setMyQuiz(state, quiz) {
  state.myQuiz = quiz;
}
export function setAllarticle(state, articles) {
  const reverse = articles.reverse();
  state.allArticle = reverse;
}
export function selectArticle(state, articles) {
  state.selectArticle = articles;
}
export function likeCnt(state, like_cnt) {
  state.likecnt = like_cnt;
}
export function infoList(state, infoList) {
  state.infoList = infoList;
}

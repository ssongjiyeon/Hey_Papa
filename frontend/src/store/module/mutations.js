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
export function setMyQuiz(state, quiz) {
  state.myQuiz = quiz;
}
export function setAllarticle(state, articles) {
  console.log(articles,'뮤테이션')
  state.allArticle = articles;
}

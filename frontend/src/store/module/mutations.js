export function setUser(state, user) {
  state.user = user;
}
export function setPwd(state, pwd) {
  state.loginPwd = pwd;
}
export function quizList(state, quizList) {
  state.quizList = quizList;
  // console.log(state.quizList, "stateQL");
}

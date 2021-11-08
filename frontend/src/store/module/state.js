export default function () {
  const myArticle = [
    {
      img: "http://placehold.it/130x130",
    },
    {
      img: "http://placehold.it/130x130",
    },
    {
      img: "http://placehold.it/130x130",
    },
    {
      img: "http://placehold.it/130x130",
    },
    {
      img: "http://placehold.it/130x130",
    },
    {
      img: "http://placehold.it/130x130",
    },
    {
      img: "http://placehold.it/130x130",
    },
  ];
  const myLike = [
    {
      img: "http://placehold.it/130x130",
    },
    {
      img: "http://placehold.it/130x130",
    },
  ];
  const selectArticle = {
    comment_cnt: 0,
    content: "하이루 방가방가",
    created_at: "2021-11-08T05:08:41.000+00:00",
    hashtag: ['선주', '하이'],
    id: 18,
    img: null,
    like: false,
    like_cnt: 0,
    nickname: "돼랑이",
    user_id: 10,
    user_img: "img",
  };
  const likecnt=0;
  const allArticle = [];
  const myQuiz = [];
  const user = {};
  const loginPwd = "";
  const quizList = [];
  const feedContent = "";
  const feedImg = "";
  const current_page = 0;
  return {
    likecnt,
    allArticle,
    myArticle,
    myLike,
    user,
    loginPwd,
    quizList,
    current_page,
    myQuiz,
    selectArticle,
    feedImg,
    feedContent,
  };
}

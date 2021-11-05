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
  const selectArticle ={
    "comment_cnt": 0,
    "content": "string",
    "created_at": "2021-11-05T01:30:19.647Z",
    "hashtag": [
      "미역","애기"
    ],
    "id": "Long",
    "img": "string",
    "like_cnt": 0,
    "nickname": "string",
    "user_img": "string"
  }
  const allArticle = []
  const myQuiz = []
  const user = {};
  const loginPwd = "";
  const quizList = [];
  const current_page = 0;
  return {
    allArticle,
    myArticle,
    myLike,
    user,
    loginPwd,
    quizList,
    current_page,
    myQuiz,
    selectArticle
  };
}

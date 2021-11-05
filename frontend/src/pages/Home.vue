<template>
<head>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
</head>
  <div class="home-top-bar">
    <img src="../assets/horizon_logo.png" style="width: 10rem; heigth:3rem;">
    <div class="q-pa-md q-gutter-sm">
      <q-btn style="background-color: #F48FB1; color: white" label="글쓰기" @click="goWrite"></q-btn>
    </div>
  </div>
  <div id="articles" class="q-pa-xs row items-start card-box">
    <FeedCard v-for="para in paras" :key="para.id" :para="para"/>
  </div>
  <!-- <infinite-loading @infinite="infiniteHandler"></infinite-loading> -->

    <div class="white-space">
      끝
    </div>
</template>

<script>
import { ref } from 'vue'
import FeedCard from '../components/feed/FeedCard.vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
export default {
  components: { FeedCard },
  setup(){
    const page = ref(1)
    const paras = [
      {
        id:1,
        name: "튼튼이 아빠",
        profileUrl: "https://ifh.cc/g/0nA9O0.png",
        imgUrl: "https://ifh.cc/g/sOxIcm.png",
        content: "첫 게시글ffffffff fffffffffffffff fffffffff fffffffff ffffff fffff fffff ffffff ffff fsdfsfsd fsdfsdfsdf  sdfsdfsdfs dfsdfsdfsd asdfasdfasdfasdf asdfasdf  asdfsdfdf adsfs asdf;lkajs;dlfk s;djasdlkj;fl ;alksdj;flkas f;ldskajsl;dkfj f;lksdajf;lskdfja a;sldkfj;lk fjdakl;sdfdddd",
        like: "5",
        reply: ['안녕', '안녕1'],

      },
      {
        id:2,
        name: "씩씩이 아빠",
        profileUrl: "https://ifh.cc/g/0nA9O0.png",
        imgUrl: "https://ifh.cc/g/sOxIcm.png",
        content: "우리부장님^^",
        like: "5",
        reply: ['안녕', '안녕1'],

      },
      {
        id:2,
        name: "똑똑이 아빠",
        profileUrl: "https://ifh.cc/g/0nA9O0.png",
        imgUrl: "",
        content: "우리부장님222^^",
        like: "5",
        reply: ['안녕', '안녕1'],

      }
    ]
    const router = useRouter()
    function goWrite(){
      router.push('create')
    }
    // const api = 'https://k5b206.p.ssafy.io/heypapa/article/'

    // const infiniteHandler = ($state) => {
    //   axios.get(api, {
    //     params: {
    //       page: page,
    //     }
    //   }).then((data) => {
    //     console.log(data)
    //   })
    // }

    let count = 0
    const API_URL = 'https://k5b206.p.ssafy.io/heypapa/article'
    const {scrollTop, clientHeight, scrollHeight} = document.documentElement
    // if (scrollTop + clientHeight >= scrollHeight-2) {
    //   console.log('스크롤')
    //   axios({
    //     method: 'GET',
    //     url: API_URL + `/?page=${page}`,
    //   }).then((res) => {
    //     for(let i = 0; i < 10; i++){
    //       paras.push(res.data[i])
    //     }
    //     page.value += 1
    //   })
    // }

    // window.onscroll = function(e) {
    //   if((window.innerHeight + window.scrollY) >= (document.body.offsetHeight)-2) {
    //     count++
    //     console.log(count)
    //     console.log("스크롤 요청")
    //     let content = '<div class="block"><p>'+ count +'번째로 추가된 콘텐츠</p></div>'
    //     document.querySelector("#articles").append(content)
    //   }
    // }


    return {
      paras,
      goWrite,
      page
      // infiniteHandler
    }
  }
}
</script>

  <style scoped>
    .home-top-bar{
      display: flex;
      justify-content: space-between;
      align-content: center;
    }
    .my-card {
      width: 100%;
    }
    .card-box {
      width: 100%;
      min-width: 30px;
      overflow: scroll;
    }

    .text-box {
      display: -webkit-box;
      min-width: 300px;
      width: inherit;
      white-space: normal;
      -webkit-line-clamp: 5;
      -webkit-box-orient: vertical;
      overflow: hidden;
    }
    .heart-button {
      border: none;
      background-color: white;
      color: silver;
      font-size: 1.4rem;
    }
    .reply-box{
      display: flex;
      justify-content: center;
      margin-bottom: 0.8rem;
      margin-top: 0.5rem;
      width: 100%;
    }
    .reply-input{
      margin-right: 0.8rem;
      width: 15rem;
      height: 2.5rem;
    }
    .profile-box{
      display: flex;
      align-items: center;
    }
    .more-box{
      display: -webkit-box;
      min-width: 300px;
      width: inherit;
      white-space: normal;
      -webkit-box-orient: vertical;

    }
    .details-box{
      display: flex;
      justify-content: flex-end;
      text-decoration: underline;
    }
    .white-space{
      height: 50px !important;
    }
  </style>

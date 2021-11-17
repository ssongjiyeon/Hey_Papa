<template>
  <div style="padding-bottom: 100px">
    <head>
      <link
        rel="stylesheet"
        href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
        integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
        crossorigin="anonymous"
      />
    </head>
    <div class="my_top">
      <img src="../assets/horizon_logo.png" class="logo" style="height:70px;">
      <div style="display: flex; align-items: center; margin-left: 45px; ">
        <q-icon
          @click="goWrite"
          name="add"
          size="sm"
          style="color: rgb(235, 137, 181)"
        />
      </div>
      <q-btn flat style="color:rgb(235,137,181); margin-right:15px;" icon="menu" @click="show()" />
    </div>
    <!-- <div style="display: flex;">
      <img
        src="../assets/horizon_logo.png"
        style="height: 70px; margin: 0 auto; padding-left: 40px"
      />
      
      <q-btn flat style="color:rgb(235,137,181); margin-right:10px;" icon="menu" @click="show()" />
    </div> -->
  <div id="articles" class="q-pa-xs row items-start card-box">
    <FeedCard v-for="para in articles" :key="para" :para="para"/>
    <!-- @click="Detail(para.id)&&Backup(para.content, para.imgUrl)"  -->
  </div>
    <!-- <infinite-loading @infinite="infiniteHandler"></infinite-loading> -->
  </div>
</template>

<script>
import { onMounted, ref, computed } from 'vue'
import { useStore } from 'vuex'
import FeedCard from '../components/feed/FeedCard.vue'
import { useRouter } from 'vue-router'
import { useQuasar } from 'quasar'
export default {
  components: { FeedCard },
  setup(){
    const $q = useQuasar()
    const userId = localStorage.getItem('userId')
    onMounted(() => {
      store.dispatch('module/allArticle', userId)
      .then((res)=> {
        store.commit('module/setAllarticle', res.data)
      })
    })
    const store = useStore()
    const router = useRouter()
    const page = ref(1)
    var articles = computed(()=> store.getters['module/getallArticle'])
    function goWrite(){
      router.push('create')
    }
    const Detail = (para) => {
      router.push({ name: 'feed', params: {article_id: para }})
    }
    const Backup = (content, img) => {
      store.commit('module/feedContent', content)
      store.commit('module/feedImg', img)
    }
    function goWrite() {
      router.push("create");
    }
    function goSearch() {
      router.push('search')
    }
    function show() {
      $q.bottomSheet({
        message: '메뉴',
        actions: [
          {
            label: '회원정보수정',
            icon: 'account_circle',
            id: 'update'
          },
          {},
          {
            label: '로그아웃',
            icon: 'logout',
            id: 'logout'
          },
        ]
      }).onOk(action => {
        if (action.id == 'update'){
          router.push('set')
        }
        else if (action.id == 'logout'){
          store.dispatch('module/logout').then(()=>{
            router.push('/')
          })
        }
      }).onCancel(() => {
        // console.log('바텀시트 빠져나올때')
      }).onDismiss(() => {
        // console.log('I am triggered on both OK and Cancel')
      })
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

    // let count = 0
    // const API_URL = 'https://k5b206.p.ssafy.io/heypapa/article'
    // const {scrollTop, clientHeight, scrollHeight} = document.documentElement
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
      goWrite,
      goSearch,
      page,
      articles,
      Detail,
      Backup,
      show
      // infiniteHandler
    }
  }
}
</script>
<style scoped>
  .my_top{
    display:flex;
    justify-content: center;
    margin-left:130px;
    margin-right:20px;
    margin-top: 5px;
  }
  .my-card {
    width: 100%;
    background-color: #F2F2F2;
  }
  .card-box {
    width: 100%;
    min-width: 30px;

    /* overflow: scroll; */
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

<template>
<div >
<head>
  <link
    rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
    integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
    crossorigin="anonymous"
  />
</head>
<div style="display:flex; align-items:center;">
  <q-btn @click="backward" round dense style="background:rgb(235,137,181); color:white; margin:0px 4rem 0px 20px;" icon="arrow_back" />
  <img src="../../assets/horizon_logo.png" style="height:70px;">
  <!-- Detail -->
</div>
<div>
  <div class="profile-box">
    <img v-if="article_user_img=='NULL'" src="../../assets/default_user.png"  class="profile-img">
    <img v-else :src="'https://k5b206.p.ssafy.io/api/static/img/'+article_user_img" class="profile-img">
    {{para.nickname}}
  </div>
  <div class="text-overline text-orange-9 date">
    <!-- 날짜 양식 home 처럼 바꿔도 좋음 -->
    {{para.created_at.slice(0,4)}}년 {{para.created_at.slice(5,7)}}월 {{para.created_at.slice(8,10)}}일
    {{para.created_at.slice(12,13)}}:{{para.created_at.slice(14,16)}}
  </div>
  <div class="content-box">
    <p>{{para.content}}</p>
    <img :src="imgUrl" alt="x" style="width:100%; height:350px;">
  </div>
  <div class="">
    <div style="display:flex; align-items:center; justify-content:space-between">
      <div style="margin-left:10px;">
        <button
        class="fas fa-heart heart-button"
        @click="getHeart"
        :style="heart ? 'color: red': 'color: silver'"/>
        <span>{{para.like_cnt}}명이 좋아요를 눌렀습니다.</span>
      </div>
      <i class="far fa-comment" style="margin-right:10px;">{{para.comment_cnt}}</i>
    </div>
  </div>
  <!-- 댓글입력 단 -->
  <div class="input-box">
    <q-input bottom-slots v-model="TempReply" label="댓글쓰기" counter maxlength="120" :dense="dense">
        <template v-slot:before>
          <q-avatar>
            <img v-if="user_img=='NULL'" src="../../assets/default_user.png" >
            <img style="width:100%" v-else :src="'https://k5b206.p.ssafy.io/api/static/img/'+user_img" alt="유저이미지">
          </q-avatar>
        </template>
        <template v-slot:append>
          <q-btn round dense flat icon="send" @click="WriteReply(para)" @keyup.enter="WriteReply" />
        </template>
      </q-input>
  </div>



</div>
<!-- 댓글단 -->
<div class="reply" clickable v-ripple v-if="articleCommentList" v-for="(reply,i) in articleCommentList" :key="i">
        <div class="avatar">
          <q-avatar style="height:2rem; width:2rem; margin: 0.2rem 1rem 0 0">
            <img v-if="reply=='NULL'" src="../../assets/default_user.png" >
            <img v-else :src="`https://k5b206.p.ssafy.io/api/static/img/${reply.user_img}`">
          </q-avatar>
        </div>
        <div class="content">
          <span style="text-align: left;"><span style="font-weight: bold; margin-right:0.3rem;">{{reply.nickname}}</span>
          {{reply.content}} </span>
          <div class="reply-right">
            <span >
              {{reply.calculateTime}}
            </span>
          </div>
        </div>
      </div>
<!--  -->
</div>
</template>

<script>
import { ref, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useStore } from 'vuex'
export default {
  setup(){
    var imgUrl = 'https://k5b206.p.ssafy.io/api/static/img/'
    const store = useStore()
    const route = useRoute()
    const router = useRouter()
    const heart = ref(false)
    const dense = ref(true)
    let replylist = ref([])
    const para = computed(() => store.getters["module/getSelectArticle"])
    const user = computed(()=> store.getters['module/getUser'])
    var user_img = computed(()=>user.value.img)
    var article_user_img = computed(()=>para.value.user_img)
    const articleId = route.params.article_id
    imgUrl = imgUrl + para.value.img
    function getHeart() {
      heart.value = !heart.value
      store.dispatch('module/likeArticle',{
      id:para.value.id,check:heart.value,user_id:localStorage.getItem('userId')}).then(()=>{
        store.dispatch('module/allArticle',localStorage.getItem('userId')).then((res)=>{
          res.data.forEach(e => {
            if(e.id==para.value.id){
              store.commit('module/selectArticle', e)
            }
          });
          store.commit('module/setAllarticle', res.data)
        })
      })
    }
    if(para.value.like == true){
      heart.value = true
    }
    onMounted(() => {
      getReply()
    })
    const getReply= () => {
      store.dispatch('module/getReply', articleId)
    .then((res) => {
      store.commit('module/articleCommentList', res.data)
    })
    .catch((err) => {
      console.log(err, 'err')
    })
    }
    const articleCommentList = computed(()=>
      store.getters['module/articleCommentList']
    )
    const TempReply = ref('')
    const WriteReply = (para) => {
      const replyContent = {
        articleNumber: route.params.article_id,
        realContent : {
        content: TempReply.value,
        user_id: parseInt(localStorage.getItem('userId'))
        }
      }
      store.dispatch('module/writeReply', replyContent)
      .then(() => {
        store.dispatch('module/allArticle',localStorage.getItem('userId')).then((res)=>{
          res.data.forEach(e => {
            if(e.id==para.id){
              store.commit('module/selectArticle', e)
            }
          });
          store.commit('module/setAllarticle', res.data)
        })
        TempReply.value = ""
        getReply()
      })
    }
  const backward = () => {
    router.go(-1)
  }
  return {
    para,
    imgUrl,
    article_user_img,
    user_img,
    replylist,
    WriteReply,
    TempReply,
    getHeart,
    heart,
    dense,
    getReply,
    articleCommentList,
    backward,


  }


  }
}
</script>

<style scoped>
.heart-button {
  border: none;
  background-color: white;
  color: silver;
  font-size: 1.4rem;
}
.backward{
  padding: 1rem;
  padding-bottom: 0;
  font-size: 1.5rem;
}
.backward i {
  padding-right: 0.5rem;
}
.profile-box {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 0.3rem;
  padding: 0.7rem;
  padding-left: 0.5rem;
  /* border-top: 1px solid silver; */
  border-bottom: 1px solid silver;
  font-size: 1.1rem;

}
.date{
  margin: 0.5rem;
}
.profile-img{
  height:2rem;
  width:2rem;
  border-radius: 1rem;
  margin-right: 0.5rem;

}
.input-box{
  padding: 0.5rem;
  width: 94%;
  margin-left: 3%;
  margin-top: 3%;
}
.content-box{
  font-weight: lighter;

}
.content-box p {
  font-size: 1rem;
  margin: 0.5rem;
}
.content-box img {

}
.reply{
  display: flex;
  justify-content:flex-start;
  align-items: flex-start;
  margin: 0.8rem 0 0 1rem;

}

.avatar {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  /* width: 16rem; */
}
.content{
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  font-size:0.8rem;
  flex-direction: column;


}
.reply-right{
  display: flex;
  align-items: flex-end;
  width: inherit;
  margin-left: none;
  margin-right: auto;
  /* flex-direction: column; */
}
.reply-right span {
  font-size:0.6rem;
  text-align: left;
  margin-top: 0.2rem;

}
</style>

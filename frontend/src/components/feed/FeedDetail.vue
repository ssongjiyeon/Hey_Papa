<template>
<div style="padding:1rem;">
<head>
  <link
    rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
    integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
    crossorigin="anonymous"
  />
</head>
<div style="display: flex; justify-content: center">
  <img
    src="../../assets/horizon_logo.png"
    style="height: 70px; margin: 0 auto; padding-left: 0"
  />
</div>
<div>
  <div class="profile-box">
    <img v-if="article_user_img=='NULL'" src="../../assets/default_user.png"  class="profile-img">
    <img v-else :src="'https://k5b206.p.ssafy.io/api/static/img/'+article_user_img" class="profile-img">
    <p>{{para.nickname}}</p>
  </div>
  <div class="content-box">
    <p>{{para.content}}</p>
    <img :src="imgUrl" alt="x">
  </div>
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
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
export default {
  setup(){
    var imgUrl = 'https://k5b206.p.ssafy.io/api/static/img/'
    const store = useStore()
    const route = useRoute()
    const dense = ref(true)
    let replylist = ref([])
    const para = computed(() => store.getters["module/getSelectArticle"])
    const user = computed(()=> store.getters['module/getUser'])
    var user_img = computed(()=>user.value.img)
    var article_user_img = computed(()=>para.value.user_img)
    const articleId = route.params.article_id
    console.log(articleId, 'ai')
    imgUrl = imgUrl + para.value.img
    onMounted(() => {
      getReply()
    })


    const getReply= () => {
      store.dispatch('module/getReply', articleId)
    .then((res) => {
      console.log(res.data, 'rd')

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
      console.log(replyContent, '댓글 내용확인 1')

      store.dispatch('module/writeReply', replyContent)
      .then((res) => {
        console.log('댓글작성 완료')
        TempReply.value = ""
      })
      getReply()
      console.log(articleCommentList, 'acl')
    }

  return {
    para,
    imgUrl,
    article_user_img,
    user_img,
    replylist,
    WriteReply,
    TempReply,
    dense,
    getReply,
    articleCommentList,


  }


  }
}
</script>

<style scoped>
.profile-box {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;

}
.profile-img{
  height:2rem;
  width:2rem;
  border-radius: 1rem;
}
.input-box{
  width: 94%;
  margin-left: 3%;
  margin-top: 3%;
}
.reply{
  display: flex;
  justify-content:flex-start;
  align-items: flex-start;
  margin: 0.8rem 1rem 0 0rem;

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

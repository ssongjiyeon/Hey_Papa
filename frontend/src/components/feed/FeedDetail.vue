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
  <div v-for="(reply,i) in replylist" :key="i">
    <p>{{reply}}</p>
  </div>
  <div class="input-box">
    <q-input bottom-slots v-model="TempReply" label="댓글쓰기" counter maxlength="120" :dense="dense">
        <template v-slot:before>
          <q-avatar>
            <img v-if="user_img=='NULL'" src="../../assets/default_user.png" >
            <img v-else :src="'https://k5b206.p.ssafy.io/api/static/img/'+user_img" alt="유저이미지">
          </q-avatar>
        </template>
        <template v-slot:append>
          <q-btn round dense flat icon="send" @click="WriteReply(para)" @keyup.enter="WriteReply" />
        </template>
      </q-input>
  </div>
</div>
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
    let replylist = []
    const para = computed(() => store.getters["module/getSelectArticle"])
    const user = computed(()=> store.getters['module/getUser'])
    var user_img = computed(()=>user.value.img)
    var article_user_img = computed(()=>para.value.user_img)
    const articleId = route.params.article_id
    imgUrl = imgUrl + para.value.img
    store.dispatch('module/getReply', articleId)
    .then((res) => {
      replylist = res.data
    })
    .catch((err) => {
      console.log(err, 'err')
    })
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
    }

  return {
    para,
    imgUrl,
    article_user_img,
    user_img,
    replylist,
    WriteReply,
    TempReply,
    dense
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
  width: 85%;
  margin-left: 5%;
}

</style>

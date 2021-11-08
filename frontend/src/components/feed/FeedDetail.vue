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
    <img :src="para.user_img" alt="유저이미지">
    <p>{{para.nickname}}</p>
  </div>
  <div class="content-box">
    <p>{{para.content}}</p>
    <img :src="para.img" alt="x">
  </div>
  <div v-for="(reply,i) in replylist" key="i">
    <p>{{reply}}</p>
  </div>
  <div class="input-box">
    <q-input bottom-slots v-model="TempReply" label="댓글쓰기" counter maxlength="120" :dense="dense">
        <template v-slot:before>
          <q-avatar>
            <img src="https://cdn.quasar.dev/img/avatar5.jpg">
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
    const store = useStore()
    const route = useRoute()
    const dense = ref(true)
    let replylist = []

    const para = computed(() => store.getters["module/getSelectArticle"])
    console.log(para.value, '파라밸류')
    const articleId = route.params.article_id
    console.log('params again', articleId)
    store.dispatch('module/getReply', articleId)
    .then((res) => {
      replylist = res.data
      console.log(replylist, 'fl')
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
      })
    }

  return {
    para,
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

.input-box{
  width: 85%;
  margin-left: 5%;
}

</style>

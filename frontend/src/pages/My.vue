<template>
  <div class="my_container">
    <div class="my_top">
      <img src="../assets/horizon_logo.png" class="logo">
      <q-btn flat style="color:rgb(235,137,181); margin-right:70px;" icon="menu" @click="show()" />
    </div>
    <div class="user_info">
      <img class="profile_img" style="margin-right:20px;" src="https://isobarscience.com/wp-content/uploads/2020/09/default-profile-picture1.jpg">
      <div class="nick_name">{{user.week}}주차 {{user.nickname}} 아빠</div>
    </div>
    <q-tabs
      v-model="tab"
      class="text-black"
      style="width:100%; margin-top:50px;"
    >
      <q-tab class="text-red" @click="goArticle" name="article" label="내 게시글" />
      <q-tab class="text-green" @click="goLike" name="like" label="좋아요한 글" />
      <q-tab class="text-blue" @click="goZzim" name="zzim" label="찜 문제" />
    </q-tabs>
    <q-tab-panels v-model="tab" animated
      style="width:100%;">
      <span name="article" style="display:flex; flex-wrap:wrap;">
        <img v-for="myArticle in myArticles" :key="myArticle" :src="myArticle.img" style="width:33.3%">
      </span>
    </q-tab-panels>
    <q-tab-panels v-model="tab" animated style="width:100%;">
      <span name="like" style="display:flex; flex-wrap:wrap;">
        <img v-for="mylike in myLikes" :key="mylike" :src="mylike.img" style="width:33.3%">
      </span>
    </q-tab-panels>
    <q-tab-panels v-model="tab" animated style="width:100%;">
      <q-tab-panel name="zzim">
        <div v-for="quiz in myQuiz" :key="quiz" class="my_quiz">
          {{ quiz.Question }}
        </div>
      </q-tab-panel>
    </q-tab-panels>
  </div>
</template>

<script>
import { useQuasar } from 'quasar'
import { ref, computed, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  setup () {
    const user = computed(()=> store.getters['module/getUser'])
    const $q = useQuasar()
    const store = useStore()
    const router = useRouter()
    const img_path = '../assets/default_user.png'
    const myQuiz= [
      {
        Question:'Q. 아이가 변비일 때 먹이면 좋지 않은 과일은?'
      },
      {
        Question:'Q. 아이가 변비일 때 먹이면 좋지 않은 과일은?'
      },
      {
        Question:'Q. 아이가 변비일 때 먹이면 좋지 않은 과일은?'
      },
      {
        Question:'Q. 아이가 변비일 때 먹이면 좋지 않은 과일은?'
      }
    ]
    const myArticles = computed(()=> store.getters['module/getMyarticle'])
    const myLikes = computed(()=> store.getters['module/getMylike'])
  
    function show () {
      $q.bottomSheet({
        message: '메뉴',
        actions: [
          // {},
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

    onMounted(()=>{
      store.dispatch('module/myArticle').then((res)=>{
        console.log(res.data,'나의 게시글들')
        // store.commit('module/setArticle', res.data)
      })
    })
    function goArticle(){
      store.dispatch('module/myArticle').then((res)=>{
        console.log(res.data,'나의 게시글들')
        // store.commit('module/setArticle', res.data)
      })
    }
    function goLike(){
      console.log('좋아요')
    }
    function goZzim(){
      console.log('찜')
    }
    return {
      tab: ref('zzim'),
      myArticles,
      myLikes,
      myQuiz,
      img_path,
      user,
      goLike,
      goZzim,
      show,
      goArticle
    }
  }
}
</script>

<style scoped>
.my_top{
  display:flex;
  /* align-items: center; */
  justify-content: center;
  height:70px;
}
.logo{
  margin: 0px 50px 0px 175px;
  width: 180px;
  /* padding-left:40px; */
}
.my_container{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.user_info{
  display: flex;
}
.profile_img{
  width:100px;
  height:100px;
  border-radius: 50%;
  margin-left: -30px;
  margin-right: 40px;
}
.nick_name{
  display: flex;
  align-items: center;
  font-weight: bold;
  font-size:20px;
}
.my_quiz{
  display:flex;
  justify-content:center;
  padding:20px 0px 20px 0px;
  font-size:17px;
  font-weight: bold;
}
</style>

<template>
  <div class="my_container">
    <div class="my_top">
      <img src="../assets/horizon_logo.png" class="logo">
      <q-btn flat style="color:rgb(235,137,181);margin-left:65px; margin-right:10px;" icon="menu" @click="show()" />
    </div>
    <div class="user_info">
      <img v-if="nope || user_img=='NULL'" @click="choosepicture()" class="profile_img" style="margin-right:20px; cursor: pointer;" src="../assets/default_user.png">
      <img v-else @click="choosepicture()" class="profile_img" style="margin-right:20px; cursor: pointer;" :src="'https://k5b206.p.ssafy.io/api/static/img/'+user_img">
      <q-icon name="add_circle_outline" class="plus-icon"/>
      <input hidden ref="fileInput" type="file" @input="changeProfile"/>
      <div style="diplay:flex; flex-direction:row; padding:auto auto auto 0; margin-left: 15px;">
        <div class="nick_name1"><span style="font-weight: bold; margin: 0 0.3rem 0 0.3rem; ">{{user.week}}</span>주차</div>
        <div class="nick_name2">"{{user.nickname}}" <span style="font-weight:normal; margin-left: 0.5rem;">아빠</span> </div>
      </div>
    </div>
    <q-tabs
      v-model="tab"
      class="text-black"
      style="width:100%; margin-top:50px; font-family: 'GowunDodum-Regular';"
    >
      <q-tab class="text-red" style="font-size:1.5rem" @click="goArticle" name="article" label="내 게시글" />
      <q-tab class="text-green" style="font-size:1.5rem" @click="goLike" name="like" label="좋아요한 글" />
      <q-tab class="text-blue" style="font-size:1.5rem" @click="goZzim" name="zzim" label="찜 문제" />
    </q-tabs>
    <q-tab-panels v-model="tab" animated
      style="width:100%;">
      <span name="article" style="display:flex; flex-wrap:wrap;">
        <img @click="Detail(myArticle)" v-for="myArticle in myArticles" :key="myArticle" :src="'https://k5b206.p.ssafy.io/api/static/img/'+myArticle.img" style="width:33.3%;height:150px;">
      </span>
    </q-tab-panels>
    <q-tab-panels v-model="tab" animated style="width:100%;">
      <span name="like" style="display:flex; flex-wrap:wrap;">
        <img @click="Detail(mylike)" v-for="mylike in myLikes" :key="mylike" :src="'https://k5b206.p.ssafy.io/api/static/img/'+mylike.img" style="width:33.3%;height:150px;">
      </span>
    </q-tab-panels>
    <q-tab-panels v-model="tab" animated style="width:100%;">
      <q-tab-panel name="zzim" style="background-color: #F2F2F2;">
        <div v-for="quiz in myZzim" :key="quiz" class="my_quiz" @click="goQuiz(quiz)">
          {{ quiz.question }}
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
    var fileInput = ref(null)
    var FileImage = ref(null);
    var nope = ref(true)
    const $q = useQuasar()
    const store = useStore()
    const router = useRouter()
    const user = computed(()=> store.getters['module/getUser'])
    var user_img = computed(()=>user.value.img)
    if (user.value.img !='NULL') nope=false
    const myArticles = computed(()=> store.getters['module/getMyarticle'])
    const myLikes = computed(()=> store.getters['module/getMylike'])
    const myZzim = computed(()=> store.getters['module/getMyzzim'])
    function changeProfile(){
      var input = fileInput.value;
      var files = input.files;
      FileImage = files[0];
      if (files && FileImage) {
        const reader = new FileReader();
        reader.onload = e => {
          this.imageData = e.target.result;
        };
        reader.readAsDataURL(files[0]);
      }

      store.dispatch('module/putProfile',{user_id:localStorage.getItem('userId'),user_thumbnail:FileImage})
        .then(()=>{
          store.dispatch('module/requestInfo',localStorage.getItem('userId'))
          .then((res)=>{
            const loginUser = {
              nickname: res.data.nickname,
              img: res.data.img,
              week: res.data.week,
              dday: res.data.dday,
              region: res.data.region,
            }
            store.commit('module/setUser', loginUser)
            if (nope.value==true) nope.value=false
          })
        })
    }
    function choosepicture() {
      fileInput.value.click();
    }
    function show () {
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
    onMounted(()=>{
      const userId = localStorage.getItem('userId')
      store.dispatch('module/myArticle',userId).then((res)=>{
        store.commit('module/setArticle', res.data)
      })
      store.dispatch('module/mylikeArticle',userId).then((res)=>{
        // store.commit('module/setMyQuiz', res.data)
      })
      store.dispatch('module/myZzim',userId).then((res)=>{
        store.commit('module/setMyQuiz', res.data)
      })
    })
    const userId = localStorage.getItem('userId')
    function goArticle(){
      store.dispatch('module/myArticle',userId).then((res)=>{
        store.commit('module/setArticle', res.data)
      })
    }
    function goLike(){
      store.dispatch('module/mylikeArticle',userId).then((res)=>{
        store.commit('module/setlikeArticle', res.data)
      })
    }
    function goZzim(){
      store.dispatch('module/myZzim',userId).then((res)=>{
        store.commit('module/setMyQuiz', res.data)

      })
    }
    console.log()
    const Detail = (myArticle) => {
      store.commit('module/selectArticle', myArticle)
      router.push({ name: "feed", params: { article_id: myArticle.id } });
    };
    function goQuiz(quiz){
      // 퀴즈 디테일로 가는 부분
      console.log(quiz,'퀴즈 정보들')
      router.push({name: "zzimquiz", params: {user_id: userId, quiz: quiz.quiz_id}});
    }
    return {
      tab: ref('article'),
      nope,
      user_img,
      myArticles,
      myLikes,
      user,
      myZzim,
      fileInput,
      choosepicture,
      changeProfile,
      goLike,
      goZzim,
      show,
      goArticle,
      goQuiz,
      Detail,
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Jua&family=Noto+Sans+KR&family=Gamja+Flower&display=swap');
.my_top{
  display:flex;
  justify-content: center;
  margin-left:120px;
}
.logo{
  height:70px;
  margin-left:10px;
  width:153px;
}
/* .my_container{
  display: flex;
  flex-direction: column;
} */
.user_info{
  display: flex;
  justify-content: center;
  margin-top:10px;
}
.profile_img{
  width:100px;
  height:100px;
  border-radius: 50%;
  margin-left: -30px;
  margin-right: 40px;
}
.nick_name1{
  font-family: 'GowunDodum-Regular';
  display: flex;
  align-items: center;
  font-weight: normal;
  font-size: 18px;
  margin-top: 1.5rem;
}
.nick_name2{
  font-family: 'GowunDodum-Regular';
  display: flex;
  align-items: center;
  font-weight: bold;
  font-size:20px;
}
.my_quiz{
  display:flex;
  justify-content:center;
  padding:20px 0px 20px 0px;
  font-size:16px;
  font-family: 'Jua', sans-serif;
  background-color: #F2F2F2;
}

span {
  background-color: #F2F2F2;
}

.plus-icon {
  color: grey;
  display: flex;
  margin-left: -50px;
  margin-top: 65px;
  font-size: 25px;
  opacity: 0.3;
}
</style>

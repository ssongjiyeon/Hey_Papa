<template>
  <div>
    <div class="my_top">
      <img src="../assets/horizon_logo.png" class="logo" style="height:70px;">
      <q-btn flat style="color:rgb(235,137,181);margin-left:50px; margin-right:10px;" icon="menu" @click="show()" />
    </div>
    <!-- <div class="quiz-text">
      <img src="../assets/horizon_logo.png" class="logo">
      <h3 class="text-pink-3 text-weight-bold" style="margin: 0.5rem; font-family: 'GowunDodum-Regular';">Quiz!</h3>
    </div> -->
    <div class="card-box" q-pa-lg row item-start q-gutter-md v-if="isClicked">
      <q-card class="my-card" v-for="theme in themes" :key="theme.id" @click="QuizDetail(theme.theme)">
        <img :src="theme.imgUrl">

        <q-card-section class="card-section">
          <div class="text-h6">{{theme.theme}}</div>
        </q-card-section>
      </q-card>
    </div>
    <div v-if="!isClicked">
      <QuizDetail @OtherTheme="OtherTheme" />
    </div>
    <div class="quiz-text">
      <h3 class="text-pink-3 text-weight-bold" style="margin: 0.5rem; font-family: 'GowunDodum-Regular';">Quiz</h3>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { ref } from 'vue'
import QuizDetail from '../components/quiz/QuizDetail.vue'
import { useStore } from 'vuex'
import { useQuasar } from 'quasar'
export default {
  components: { QuizDetail },
  setup() {
    const $q = useQuasar()
    const store = useStore()
    const isClicked = ref(true)
    // quiz detail 정보
    let themes = [
      {
        id: 1,
        theme: 'baby',
        imgUrl: "https://ifh.cc/g/0nA9O0.png"
      },
      {
        id: 2,
        theme: 'food',
        imgUrl: "https://ifh.cc/g/sOxIcm.png"
      },
      {
        id: 3,
        theme: 'society',
        imgUrl: "https://ifh.cc/g/gSUEJu.png"
      },
      {
        id: 4,
        theme: 'wife',
        // imgUrl: "https://ifh.cc/g/lajTXv.png"
        imgUrl: require("../../src/assets/tempsnip.png")
      },
    ]

    // quiz detail로 가는 함수
    const QuizDetail = (theme) => {
      // api 마련되면 진행

      axios({
        method: 'GET',
        url: `https://k5b206.p.ssafy.io/api/quiz/${theme}`,
        // params : theme
      })
      .then((res) => {
        store.commit('module/quizList', res.data)
      })
      isClicked.value = !isClicked.value

      return {}
    }
    const OtherTheme = () => {
      isClicked.value = !isClicked.value
      return {}
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

    return {
      themes,
      QuizDetail,
      isClicked,
      OtherTheme,
      show

    }
  }
}
</script>

<style scoped>
.quiz-text {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin: none;
  margin-bottom: none;
  padding-top: 1rem;
  font-family: 'GowunDodum-Regular';
}

.card-box {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  margin-top:2rem;


}

.my-card {
  min-width: 8rem;
  height: 12rem;
  border-radius: 1rem;
  margin: 0.5rem;
  font-family: 'GowunDodum-Regular';
}

.my-card img {
  height: 9rem;
}

.card-section{
  height: 2rem;
  display: flex;
  align-items: center;
  justify-content: center;
}
.text-h6{
  font-size: 1.3rem;
  padding: 0;
  font-weight: bold;
}
.my_top{
  display:flex;
  justify-content: center;
  margin-left:130px;
  margin-right:10px;
}
</style>


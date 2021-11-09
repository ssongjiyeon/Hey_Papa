<template>
<div class="quiz-text">
  <img src="../assets/horizon_logo.png" class="logo">
  <h3 class="text-pink-3 text-weight-bold" style="margin: 0.5rem">Quiz!</h3>
</div>


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


</template>

<script>
import axios from 'axios'
import { ref } from 'vue'
import QuizDetail from '../components/quiz/QuizDetail.vue'
import { useStore } from 'vuex'
export default {
  components: { QuizDetail },
  setup() {
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
        imgUrl: "https://ifh.cc/g/lajTXv.png"
      },
    ]

    // quiz detail로 가는 함수
    const QuizDetail = (theme) => {
      console.log(theme)
      // api 마련되면 진행

      axios({
        method: 'GET',
        url: `https://k5b206.p.ssafy.io/api/quiz/${theme}`,
        // params : theme
      })
      .then((res) => {
        console.log('res', res.data)
        store.commit('module/quizList', res.data)
      })
      isClicked.value = !isClicked.value
      console.log(isClicked.value)

      return {}
    }
    const OtherTheme = () => {
      console.log('emit')
      isClicked.value = !isClicked.value
      console.log(isClicked.value)
      return {}
    }

    return {
      themes,
      QuizDetail,
      isClicked,
      OtherTheme,


    }
  }
}
</script>

<style scoped>
.logo{
  margin-top:10px;
  height: 70px;
}
.quiz-text {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin: 1rem;
  margin-bottom: none;
}

.card-box {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-evenly;
  margin-top: 2rem;

}

.my-card {
  max-width: 9rem;
  margin-bottom: 1rem;

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
  font-size: 0.8rem;
  padding: 0;
  font-weight: bold;
}


</style>


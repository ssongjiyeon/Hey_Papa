<template>
<head>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
</head>
  <div class="q-pa-md">
    <div class="quiz-detail-page-box">
      <div class=" q-gutter-sm other-theme-btn">
        <q-btn style="background-color: #F48FB1; color: white" label="다른 주제" @click="$emit('OtherTheme')"></q-btn>
      </div>
      <div class="pagnation-box">
        <q-pagination
        v-model="current"
        color="pink-3"
        :max="quizList.length"
        :max-pages="6"
        boundary-numbers
        @click="page(current.value)"
        />
      </div>
      <!-- <input class="quiz-number-input" type="number" v-model="slide"> / {{quizList.length}} -->

    </div>

    <q-carousel
      v-model="slide"
      transition-prev="slide-right && BeforeTransition"
      transition-next="slide-left && BeforeTransition"
      arrows
      swipeable
      animated
      control-color="primary"
      class="rounded-borders"
      @before-transition = "BeforeTransition"


    >

      <q-carousel-slide
        class="column no-wrap flex-center carousel-slide"
        v-for="(quiz, i) in quizList"
        :key="quiz.id"
        :name="i+1"
        >
        <QuizSlide :quiz="quiz" />
      </q-carousel-slide>
    </q-carousel>
  </div>
</template>

<script>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'
import axios from 'axios'
import QuizSlide from './QuizSlide.vue'
export default {
  components: { QuizSlide },
  emit: [
    'OtherTheme'
  ],
  setup (){

    const store = useStore()
    const current = ref(1)
    const slide = ref(1)
    const page = () => {
      slide.value = current.value
    }
    let quizList = computed(()=>
      store.getters['module/quizList']

    )
    const Description = ref("")
    const isAnswered = ref(false)
    const BeforeTransition = () => {
      isAnswered.value = false
      current.value = slide.value
      console.log('transition')
    }
    const isSaved = ref(false)
    const SaveQuiz = (id) => {
      console.log(isSaved.value)
      isSaved.value = !isSaved.value
      const url = "https://k5b206.p.ssafy.io/api/quiz/" + id + '/myquiz'
      const params = {
          quizcheck: isSaved.value,
          quizlike : isSaved.value,
          user_id : parseInt(localStorage.getItem('userId'))
        }
      axios.post(url, params)
      // store.dispatch('module/saveQuiz', {
      //   qc: isSaved.value,ql: isSaved.value,ui: parseInt(localStorage.getItem('userId')),qi:id
      // })
      .then((res) => {
        console.log(res.data)
      })
      .catch((err) => {
        console.log(err)
      })
    }

    const ChooseAnswer = (name, answer) => {
      console.log(name, 'name')
      isAnswered.value = !isAnswered.value
      if(name !== answer){

        Description.value = "틀렸습니다"
      // Description 내용 수정시, 위에 v-if 구문도 수정해야함
        console.log(Description.value)
        // 댓글 목록 api 호출 필요
        //  axios
      }
      else{

      Description.value = "정답입니다"
      console.log(Description.value)
      }
    }
    const Reply = ref('')
    const EnrollReply = () => {
      console.log(Reply.value)
      // axios

    }
    return {
      //default 화면? 단계를 설정하는 것(ref 안에 btn-toggle options 밸류 쓰면 됨 )
      lorem: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Itaque voluptatem totam, architecto cupiditate officia rerum, error dignissimos praesentium libero ab nemo provident incidunt ducimus iusto perferendis porro earum. Totam, numquam?',
      isAnswered,
      ChooseAnswer,
      Description,
      BeforeTransition,
      Reply,
      EnrollReply,
      quizList,
      page,
      current,
      slide,
      SaveQuiz,
      isSaved


    }
  }
}
</script>

<style scoped>
.other-theme-btn{
  display: flex;
  justify-content: center;
  padding: none;
  padding-bottom: 1rem;
}
.pagnation-box{
  display: flex;
  justify-content: center;
}
.quiz-detail-page-box{
  display: flex;
  flex-direction: column;
  justify-content: center;

}
.carousel-slide{
  flex-direction: column;
  justify-content: start;
  overflow: scroll;
  border: 3px solid pink;
}
.answer-box {
  /* float: left; */
  display: flex;
  flex-wrap: wrap;
  width: 15rem;
  height: 4rem;
  background-color: pink;
  margin: 0.5rem;
  align-items: center;
  border-radius: 0.5rem;

}

.redheart {
  color: red;
}
.greyheart {
  color: silver;
}
</style>

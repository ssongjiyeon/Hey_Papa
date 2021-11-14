<template>
<head>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
</head>
  <div class="q-pa-md" style="padding-bottom:100px;">
    <div class="quiz-detail-page-box">
      <div class=" q-gutter-sm other-theme-btn">
        <q-btn style="background-color: #F48FB1; color: white" label="다른 주제" @click="$emit('OtherTheme')"></q-btn>
      </div>
      <div class="pagnation-box">
        <q-pagination
        v-model="current"
        size="14px"
        padding="5px 25px 5px 25px"
        color="pink-3"
        :max="quizList.length"
        :max-pages="5"
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
      style="margin-top:2rem; height:32rem;"

    >

      <q-carousel-slide
        class="column no-wrap flex-center carousel-slide"
        v-for="(quiz, i) in quizList"
        :key="quiz.id"
        :name="i+1"
        style="overflow: auto;"
        >
        <QuizSlide :quiz="quiz" :i="i" :isAnswered="isAnswered" />
      </q-carousel-slide>
    </q-carousel>
  </div>
</template>

<script>
import { onMounted, computed, ref } from 'vue'
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
    const isAnswered = ref(false)
    const BeforeTransition = () => {
      isAnswered.value = false
      current.value = slide.value
      console.log('transition')
    }






    return {
      //default 화면? 단계를 설정하는 것(ref 안에 btn-toggle options 밸류 쓰면 됨 )
      BeforeTransition,
      quizList,
      page,
      current,
      slide,
      isAnswered,




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
  margin-top:10px;
}
.quiz-detail-page-box{
  display: flex;
  flex-direction: column;
  justify-content: center;

}
.carousel-slide{
  flex-direction: column;
  justify-content: start;
  border: 3px solid pink;
  -ms-overflow-style: none;
}
.carousel-slide::-webkit-scrollbar{ display:none; }
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

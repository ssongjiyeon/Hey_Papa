<template>
<head>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
</head>
  <div class="q-pa-md" style="padding-bottom:100px;">
    <div class="quiz-detail-page-box">
      <div class=" q-gutter-sm other-theme-btn">
        <i class="fas fa-arrow-left" @click="$emit('OtherTheme')"><span style="font-family: 'GowunDodum-Regular';"> 다른 주제</span> </i>
      </div>
      <div class="pagnation-box">
        <q-pagination
        v-model="current"
        padding="5px 15px 5px 15px"
        color="#5684BF"
        :max="quizList.length"
        :max-pages="5"
        control-text-color="pink-3"
        boundary-numbers
        @click="page(current.value)"
        style="font-family: 'GowunDodum-Regular';
        color:#5684BF;"
        />
      </div>
      <!-- <input class="quiz-number-input" type="number" v-model="slide"> / {{quizList.length}} -->

    </div>

    <q-carousel
      v-model="slide"
      transition-prev="slide-right && BeforeTransition"
      transition-next="slide-left && BeforeTransition"

      swipeable
      animated
      control-color="primary"
      class="rounded-borders"
      @before-transition = "BeforeTransition"
      style="margin-top:2rem; height:27rem;"

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
      <template v-slot:control>
      <q-carousel-control

          position="top-right"
          :offset="[18, 18]"
          class="q-gutter-xs row justify-between"
        >
        <q-btn
          round dense color="" text-color="pink-3" icon="arrow_left"
          @click="previous(quizList.length)"
        />
        <q-btn
          round dense color="" text-color="pink-3" icon="arrow_right"
          @click="next(quizList.length)"
        />
        </q-carousel-control>
      </template>
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
    }
    const previous = (max) => {
      if(slide.value > 1){
        slide.value -= 1
      }
    }
    const next = (max) => {
      if(slide.value < max){
        slide.value += 1
      }
    }





    return {
      //default 화면? 단계를 설정하는 것(ref 안에 btn-toggle options 밸류 쓰면 됨 )
      BeforeTransition,
      quizList,
      page,
      current,
      slide,
      isAnswered,
      previous,
      next



    }
  }
}
</script>

<style scoped>
.other-theme-btn{
  display: flex;
  justify-content: flex-start;
  padding: none;
  padding-bottom: 1rem;

}
.other-theme-btn i {
  font-size: 1.2rem;
  margin-left: 1.5rem;
  padding-bottom: 0.3rem;
  color: #5684BF;
  border-bottom: 5px solid pink;

}
.pagnation-box{
  display: flex;
  justify-content: center;
  margin-top:10px;


}
.pagnation-box button {
  background-color: #5684BF;
}
.quiz-detail-page-box{
  display: flex;
  flex-direction: column;
  justify-content: center;

}
.carousel-slide{
  flex-direction: column;
  justify-content: start;
  background-color: #F2F2F2;
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
  margin-top: none;
  align-items: center;
  border-radius: 0.5rem;

}

.redheart {
  color:pink;
}
.greyheart {
  color: silver;
}
</style>

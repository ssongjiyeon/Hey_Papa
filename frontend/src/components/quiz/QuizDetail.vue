<template>
  <div class="q-pa-md">
    <div class="row justify-center">
      <button @click="$emit('OtherTheme')">다른주제</button>
      <input class="quiz-number-input" type="number" v-model="slide"> / {{QuizList.length}}
    </div>
    <q-carousel
      v-model="slide"
      transition-prev="slide-right"
      transition-next="slide-left"
      arrows
      swipeable
      animated
      control-color="primary"
      class="rounded-borders"
    >

      <q-carousel-slide  class="column no-wrap flex-center" v-for="quiz in QuizList" :key="quiz.id" :name="quiz.id">
        <!-- <q-icon name="quiz.id" color="primary" size="56px" /> -->
        <div class="q-mt-md text-center column">
          <span>{{quiz.id}}번 문제</span>
          {{ quiz.content }}
        </div>
        <div class="row wrap justify-center">
          <div class="answer-box row no-wrap justify-center" v-for="option in quiz.options" :key="option.answer" >
            <span>{{option.answer}}</span>
          </div>
        </div>
      </q-carousel-slide>
    </q-carousel>

    <div class="row justify-center">
      <!-- <q-btn-toggle
        glossy
        v-model="slide"
        :options="[
          { label: 1, value: 1 },
          { label: 2, value: 2 },
          { label: 3, value: 3 },
          { label: 4, value: 4 },
          { label: 5, value: 5 },
          { label: 6, value: 6 },
          ]"
      /> -->
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  setup () {
    const QuizList = [
      {
        id: 1,
        content: "1번문제입니다.",
        options: [
          {
            id: "가",
            answer: "가"
          },
          {
            id: 2,
            answer: '나'
          },
          {
            id: 3,
            answer: '다'
          },
          {
            id: 4,
            answer: '라'
          },
        ]
      },
      {
        id: 2,
        content: "2번문제입니다.",
        options: [
          {
            id: 1,
            answer: "가"
          },
          {
            id: 2,
            answer: '나'
          },
          {
            id: 3,
            answer: '다'
          },
          {
            id: 4,
            answer: '라'
          },
        ]
      },
      {
        id: 3,
        content: "3번문제입니다.",
        options: [
          {
            id: 1,
            answer: "가"
          },
          {
            id: 2,
            answer: '나'
          },
          {
            id: 3,
            answer: '다'
          },
          {
            id: 4,
            answer: '라'
          },
        ]
      },
      {
        id: 4,
        content: "4번문제입니다.",
        options: [
          {
            id: 1,
            answer: "가"
          },
          {
            id: 2,
            answer: '나'
          },
          {
            id: 3,
            answer: '다'
          },
          {
            id: 4,
            answer: '라'
          },
        ]
      },
    ]


    return {
      QuizList,
      //default 화면? 단계를 설정하는 것(ref 안에 btn-toggle options 밸류 쓰면 됨 )
      slide: ref(1),
      lorem: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Itaque voluptatem totam, architecto cupiditate officia rerum, error dignissimos praesentium libero ab nemo provident incidunt ducimus iusto perferendis porro earum. Totam, numquam?',

    }
  }
}
</script>

<style scoped>
.quiz-number-input {
  width: 3rem;
  display: flex;
  align-items: center;
}


.answer-box {
  /* float: left; */
  display: flex;
  flex-wrap: wrap;
  width: 7rem;
  height: 7rem;
  background-color: pink;
  margin: 1rem;
  align-items: center;
}
</style>

<template>
  <div class="q-pa-md">
    <div class="row justify-center">
      <button @click="$emit('OtherTheme')">다른주제</button>
      <input class="quiz-number-input" type="number" v-model="slide"> / {{QuizList.length}}
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
        class="column no-wrap flex-center"
        v-for="quiz in QuizList"
        :key="quiz.id"
        :name="quiz.id"
        >
        <!-- <q-icon name="quiz.id" color="primary" size="56px" /> -->
        <!-- 문제 출제 시 -->
        <div class="q-mt-md text-center column" v-show="!isAnswered">
          <span>{{quiz.id}}번 문제</span>
          {{ quiz.content }}
        </div>

        <!-- 문제 제출시  -->
        <div class="q-mt-md text-center column" v-if="isAnswered">
          <span>{{Description}}</span>
          <span v-if="Description === '틀렸습니다'">정답은 {{quiz.answer}}</span>
          {{ quiz.answer_content }}
          <input type="text" v-model="Reply">
          <button @click="EnrollReply">댓글달기</button>
          <!-- 댓글목록 -->
          <div></div>
        </div>

        <!-- 선지 -->
        <div class="row wrap justify-center" v-show="!isAnswered">
          <div class="answer-box row no-wrap justify-center"
              v-for="option in quiz.options"
              :key="option.answer"
              @click="ChooseAnswer(option.name, quiz.answer)" >
            <span>{{option.name}}</span>
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
        answer: "가",
        answer_content: "정답내용",
        content: "1번문제입니다.",
        options: [
          {
            id: "가",
            name: "가"
          },
          {
            id: 2,
            name: '나'
          },
          {
            id: 3,
            name: '다'
          },
          {
            id: 4,
            name: '라'
          },
        ]
      },
      {
        id: 2,
        answer: "가",
        answer_content: "정답내용",
        content: "2번문제입니다.",
        options: [
          {
            id: 1,
            name: "가"
          },
          {
            id: 2,
            name: '나'
          },
          {
            id: 3,
            name: '다'
          },
          {
            id: 4,
            name: '라'
          },
        ]
      },
      {
        id: 3,
        answer: "가",
        answer_content: "정답내용",
        content: "3번문제입니다.",
        options: [
          {
            id: 1,
            name: "가"
          },
          {
            id: 2,
            name: '나'
          },
          {
            id: 3,
            name: '다'
          },
          {
            id: 4,
            name: '라'
          },
        ]
      },
      {
        id: 4,
        answer: "가",
        answer_content: "정답내용",
        content: "4번문제입니다.",
        options: [
          {
            id: 1,
            name: "가"
          },
          {
            id: 2,
            name: '나'
          },
          {
            id: 3,
            name: '다'
          },
          {
            id: 4,
            name: '라'
          },
        ]
      },
    ]
    const Description = ref("")
    const isAnswered = ref(false)
    const BeforeTransition = () => {
      isAnswered.value = false
      console.log('transition')
    }
    const ChooseAnswer = (name, answer) => {
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
      QuizList,
      //default 화면? 단계를 설정하는 것(ref 안에 btn-toggle options 밸류 쓰면 됨 )
      slide: ref(1),
      lorem: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Itaque voluptatem totam, architecto cupiditate officia rerum, error dignissimos praesentium libero ab nemo provident incidunt ducimus iusto perferendis porro earum. Totam, numquam?',
      isAnswered,
      ChooseAnswer,
      Description,
      BeforeTransition,
      Reply,
      EnrollReply
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
  width: 15rem;
  height: 4rem;
  background-color: pink;
  margin: 0.5rem;
  align-items: center;
}
</style>

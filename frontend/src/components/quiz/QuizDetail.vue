<template>
  <div class="q-pa-md">
    <div class="quiz-detail-page-box">
      <div class=" q-gutter-sm other-theme-btn">
        <q-btn style="background-color: #F48FB1; color: white" label="다른 주제" @click="$emit('OtherTheme')"></q-btn>
      </div>
      <div class="pagnation-box">
        <q-pagination
        v-model="current"
        color="purple"
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


        <!-- <q-icon name="quiz.id" color="primary" size="56px" /> -->
        <!-- 문제 출제 시 -->
        <div class="q-mt-md text-center column" v-show="!isAnswered">
          <span>{{i+1}}번 문제</span>
          {{ quiz.question }}
        </div>

        <!-- 문제 제출시  -->
        <div class="q-mt-md text-center column" v-show="isAnswered">
          <span>{{Description}}</span>
          <span v-if="Description === '틀렸습니다'">정답은 {{ quiz.answer2 }}</span>
          {{quiz.img}}
          {{ quiz.description }}
          <input type="text" v-model="Reply">
          <button @click="EnrollReply">댓글달기</button>
          <!-- 댓글목록 -->
          <div></div>
        </div>

        <!-- 선지 -->
        <div class="row wrap justify-center" v-show="!isAnswered">
          <div class="answer-box row no-wrap justify-center"
              v-for="(option, opt_idx) in quiz.candidate.split('#')"
              :key="option"
              @click="ChooseAnswer(opt_idx+1, quiz.answer)" >
            <span>{{option}}</span>
          </div>
        </div>
      </q-carousel-slide>
    </q-carousel>
  </div>
</template>

<script>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'
export default {
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
      slide


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
</style>

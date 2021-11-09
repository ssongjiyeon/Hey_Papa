<template>


        <!-- <q-icon name="quiz.id" color="primary" size="56px" /> -->
        <!-- 문제 출제 시 -->
        <div class="q-mt-md text-center column" v-show="!isAnswered">
          <span>{{i+1}}번 문제</span>
          <div>
            <i class='fas fa-heart redheart' @click="SaveQuiz(quiz.id)" :style="isSaved ? 'color: red': 'color: silver'"></i>

          </div>
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
</template>

<script>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'
import axios from 'axios'
export default {
  props: [
    "quiz"
  ],
  setup(props){
    const quiz = props.quiz
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
    const isSaved = ref(false)
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
    const Description = ref("")
    const isAnswered = ref(false)
    const Reply = ref('')
    const EnrollReply = () => {
      console.log(Reply.value)
      // axios

    }
    return {
      quiz,
      SaveQuiz,
      isSaved,
      ChooseAnswer,
      isAnswered,
      Description,
      isAnswered,
      Reply,
      EnrollReply,
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

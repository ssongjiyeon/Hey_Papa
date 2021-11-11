<template>

  <!-- 문제 출제 시 -->
  <div class="" v-show="!isAnswered">
    <div class="heart-position">
      <i class='fas fa-heart ' @click="SaveQuiz(quiz.id)" :style="isSaved ? 'color: red': 'color: silver'"></i>
    </div>
    <div>
      <span class="subject">Q{{i+1}}</span>
    </div>
  </div>
  <div class="question" v-show="!isAnswered">
    {{ quiz.question }}
  </div>

  <!-- 문제 제출시  -->
  <div class="q-mt-md text-center column" v-show="isAnswered">
    <span>{{Description}}</span>
    <span v-if="Description === '틀렸습니다'">정답은 {{ quiz.answer2 }}</span>
    {{quiz.img}}
    {{ quiz.description }}
    <div class="reply-input-box">
      <input v-model="Reply" placeholder="댓글" :dense="dense" />
      <button @click="EnrollReply(quiz.id)">등록</button>
    </div>
    <!-- 댓글목록 -->
    <div>
      <div class="reply" clickable v-ripple v-if="commentList" v-for="(comment, idx) in commentList" :key="idx">
        <div class="avatar">
          <q-avatar style="height:2.5rem; width:2.5rem; margin-right:1rem">
            <img :src="comment.user_img">
          </q-avatar>
        </div>
        <div class="content">
          <span style="text-align: left;"><span style="font-weight: bold; margin-right:0.3rem;">{{comment.nickname}}</span>
          {{comment.content}} </span>
          <div class="reply-right">
            <span style="">
              {{comment.calculateTime}}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 선지 -->
  <div class="row wrap justify-center" v-show="!isAnswered">
    <div class="answer-box row no-wrap justify-center"
        v-for="(option, opt_idx) in quiz.candidate.split('#')"
        :key="option"
        @click="ChooseAnswer(opt_idx+1, quiz.answer, quiz.id)" >
      <span>{{option}}</span>
    </div>
  </div>
</template>

<script>
import { onMounted, computed, ref } from 'vue'
import { useStore } from 'vuex'
import axios from 'axios'
export default {
  props: [
    "quiz",
    "i",
    "isAnswered"
  ],
  setup(props){
    const store = useStore()
    const quiz = props.quiz
    let comments = []
    let isAnswered = ref(false)
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
        console.log(res.data, '댓글작성완료')
      })
      .catch((err) => {
        console.log(err)
      })
    }
    const isSaved = ref(false)
    const ChooseAnswer = (name, answer, id) => {
      console.log(name, 'name')
      isAnswered.value = !isAnswered.value
      // 댓글 목록 api 호출 필요
      const url = "https://k5b206.p.ssafy.io/api/quiz/" + id
      axios.get(url)
      .then((res) => {
        store.commit('module/commentList', res.data.comments )
      })
      if(name !== answer){

        Description.value = "틀렸습니다"
      // Description 내용 수정시, 위에 v-if 구문도 수정해야함
        console.log(Description.value)

      }
      else{
      Description.value = "정답입니다"
      console.log(Description.value)
      }
    }
    const commentList = computed(()=>
      store.getters['module/commentList']
    )
    console.log(commentList)
    const Description = ref("")
    console.log(quiz.description)
    const Reply = ref('')
    const EnrollReply = (id) => {
      const userId = localStorage.getItem('userId')
      console.log(userId, 'ui')
      const url = "https://k5b206.p.ssafy.io/api/quiz/" + id
      const params = {
        content: Reply.value,
        user_id: userId,
      }
      console.log(params, 'params')
      axios.post(url, params)
      .then((res) => {
        commentList()
      })
      Reply.value = ''

    }

    return {
      quiz,
      SaveQuiz,
      isSaved,
      ChooseAnswer,
      isAnswered,
      Description,
      comments,
      Reply,
      EnrollReply,
      commentList,
      dense: ref(false)
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
.reply-input-box {
  display: flex;
  height: 2rem;
  align-items: center;
  margin: 1rem;

}
.reply-input-box input {
  margin: 0.8rem;
  height: 2.5rem;
  width: 12rem;
  border-radius: 0.5rem;
  border: 1px solid silver;

}

.reply-input-box button {
  height: 2.5rem;
  width: 4rem;
  border-radius: 0.5rem;
  border: 1px solid silver;

}

.reply{
  display: flex;
  justify-content:flex-start;
  align-items: center;
  margin-top: 0.5rem;

}

.avatar {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  /* width: 16rem; */
}
.content{
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  font-size:0.8rem;
  flex-direction: column;


}
.reply-right{
  display: flex;
  align-items: flex-end;
  width: inherit;
  margin-left: none;
  margin-right: auto;
  /* flex-direction: column; */
}
.reply-right span {
  font-size:0.6rem;
  text-align: left;
  margin-top: 0.2rem;

}
.heart-position{
  display: block;

  font-size: 1.5rem;
}

.question{
  display: flex;
  width: 15rem;
  flex-wrap: wrap;
  margin-top: 1rem;
  font: 1rem sans-serif;
}
.subject{
  font: 1.3rem black;

}

</style>

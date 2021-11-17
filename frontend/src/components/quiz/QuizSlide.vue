<template>

  <!-- 문제 출제 시 -->
  <div class="heart-subject" v-show="!isAnswered">
    <div class="heart-position">
      <i class='fas fa-heart ' @click="SaveQuiz(quiz.id)" :style="isSaved ? 'color: crimson': 'color: silver'"></i>
    </div>
    <div>
      <span class="subject">Q{{i+1}}. </span>
    </div>
  </div>
  <div class="question" v-show="!isAnswered">
    {{ quiz.question }}
  </div>

  <!-- 문제 제출시  -->
  <div class="q-mt-md text-center column" v-show="isAnswered">
    <span style="font: 1.2rem black bold; margin: 0 auto 1rem auto">{{Description}}</span>
    <span style="margin: 0 auto 0.5rem auto; border-bottom: 5px solid pink;" v-if="Description === '틀렸습니다'">정답은 "{{ quiz.answer2 }}"</span>
    <div class="description-box">
      {{quiz.img}}
      <p>
        {{ quiz.description }}
      </p>
    </div>
    <div class="reply-input-box">
      <input v-model="Reply" placeholder="    댓글" :dense="dense" @keyup.enter="EnrollReply(quiz.id)"/>
      <button  @click="EnrollReply(quiz.id)">등록</button>
    </div>
    <!-- 댓글목록 -->
    <div>
      <div class="reply" clickable v-ripple v-if="commentList" v-for="(comment, idx) in commentList" :key="idx">
        <div class="avatar">
          <q-avatar style="height:2rem; width:2rem; margin: 0.2rem 1rem 0 0">
            <img v-if="comment=='NULL'" src="../../assets/default_user.png" >
            <img v-else :src="`https://k5b206.p.ssafy.io/api/static/img/${comment.user_img}`">
          </q-avatar>
        </div>
        <div class="content">
          <span style="text-align: left;"><span style="font-weight: bold; margin-right:0.3rem;">{{comment.nickname}}</span>
          {{comment.content}} </span>
          <div class="reply-right">
            <span >
              {{comment.calculateTime}}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 선지 -->
  <div class="wrap justify-center items-center content-center answer-list" v-show="!isAnswered">
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
    const user = computed(()=> store.getters['module/getUser'])
    var user_img = computed(()=>user.value.img)
    const user_id = localStorage.getItem('userId')
    onMounted(() => {
      // 찜한 문제 확인
      axios({
        method: 'GET',
        url: "https://k5b206.p.ssafy.io/api/quiz/" + quiz.id + `/${user_id}`
      })
      .then((res) => {
        isSaved.value = res.data.like
      })
    })




    let comments = []
    let isAnswered = ref(false)
    const SaveQuiz = (id) => {

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

      })
      .catch((err) => {

      })
    }
    const isSaved = ref(false)
    const ChooseAnswer = (name, answer, id) => {
      // 댓글 목록 api 호출 필요
      const url = "https://k5b206.p.ssafy.io/api/quiz/" + id + `/${user_id}`
      axios.get(url)
      .then((res) => {
        store.commit('module/commentList', res.data.comments )
      })
      if(name == null) {
        Description.value = ""
      }
      else if(name !== answer){
        Description.value = "틀렸습니다"
        isAnswered.value = !isAnswered.value
      // Description 내용 수정시, 위에 v-if 구문도 수정해야함
      }
      else{
      Description.value = "정답입니다"
      isAnswered.value = !isAnswered.value
      }
    }
    const commentList = computed(()=>
      store.getters['module/commentList']
    )
    const Description = ref("")
    const Reply = ref('')
    const EnrollReply = (id) => {
      const userId = localStorage.getItem('userId')
      const url = "https://k5b206.p.ssafy.io/api/quiz/" + id
      const params = {
        content: Reply.value,
        user_id: userId,
      }
      axios.post(url, params)
      .then((res) => {
        ChooseAnswer(null, null, quiz.id)
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
      dense: ref(false),
      user_img
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
.answer-list{
  display: flex;
  align-items: center;
  align-content: center;
  margin: auto;

}

.answer-box {
  /* float: left; */
  display: flex;
  flex-wrap: wrap;
  width: 6.5rem; /* 14*/
  height: 6.5rem;  /* 4*/
  background-color: rgb(255, 217, 223);
  margin: 0.5rem;
  text-align: center;
  align-items: center;

  border-radius: 0.5rem;
  color: #5684BF;
  font: 1rem 'GowunDodum-Regular';
  font-weight: bold;

}
.reply-input-box {
  display: flex;
  height: 2rem;
  align-items: center;
  margin-right: 0.5rem;
  margin-top: 1rem;
  justify-content: space-between;
}
.reply-input-box input {
  margin-right: 0.5rem;
  height: 2.5rem;
  width: 14.5rem;
  border-radius: 0.5rem;
  border: 1px solid silver;

}

.reply-input-box button {
  height: 2.5rem;
  width: 4rem;
  border-radius: 0.5rem;
  border: none;
  background-color: pink;

}

.reply{
  display: flex;
  justify-content:flex-start;
  align-items: flex-start;
  margin: 0.8rem 1rem 0 0rem;

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
.description-box{
  width: 15.5rem;
  margin: 0 auto 0 auto;

}

.description-box p {
  padding: 0.3rem;
  text-align: left;

}


.heart-position{
  display: block;
  font-size: 1.3rem;

}

.question{
  display: flex;
  width: 15rem;
  flex-wrap: wrap;
  margin-top: 1rem;
  font: 1rem 'GowunDodum-Regular';
  padding: 0.5rem;
}
.subject{
  font-size: 1.3rem ;
  font-weight: bold;
}
.heart-subject {
  display: flex;
  justify-content: space-between;
  flex-direction: row-reverse;
  width: 13.8rem;
  margin-top: 3rem;

}
</style>

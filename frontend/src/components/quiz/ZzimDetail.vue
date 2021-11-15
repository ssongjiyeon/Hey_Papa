<template>
<head>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
</head>
  <div style="display:flex; align-items:center;">
    <q-btn @click="backward" round dense style="background:rgb(235,137,181); color:white; margin:0px 4rem 0px 20px;" icon="arrow_back" />
    <img src="../../assets/horizon_logo.png" style="height:70px;">
    <!-- Detail -->
  </div>
  <div class="zzim-quiz">
    <h3>찜한 퀴즈!</h3>
  </div>
  <!-- 문제 출제 시 -->

  <div class="zzim-box">
    <div class="quiz-dan" v-show="!isAnswered">
        <!-- <i class='fas fa-heart ' @click="SaveQuiz(quiz_detail.id)" :style="isSaved ? 'color: red': 'color: silver'"></i> -->
        <span class="subject">Q{{quiz_detail.id}}.</span>
    </div>
    <div class="question" v-show="!isAnswered">
      {{ quiz_detail.question }}
    </div>

    <!-- 문제 제출시  -->
    <div class="q-mt-md text-center column" v-show="isAnswered">
      <span style="font: 1.2rem black bold; margin: 0 auto 1rem auto">{{Description}}</span>
      <span style="margin: 0 auto 0.5rem auto; border-bottom: 5px solid pink;" v-if="Description === '틀렸습니다'">정답은 "{{ quiz_detail.answer2 }}"</span>
      <div class="description-box">

        <p>
          {{ quiz_detail.description }}
        </p>
      </div>
      <div class="reply-input-box" >
        <input v-model="Reply" placeholder="    댓글" :dense="dense" @keyup.enter="EnrollReply(quiz_detail.id)"/>
        <button  @click="EnrollReply(quiz_detail.id)">등록</button>
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
          v-for="(option, opt_idx) in candidateList"
          :key="option"
          @click="ChooseAnswer(opt_idx+1, quiz_detail.answer, quiz_detail.id)" >
        <span>{{option}}</span>
      </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'
import { useRoute, useRouter } from 'vue-router'
import { useStore } from 'vuex'

import { onMounted, computed, ref } from 'vue'
export default {
  setup(){
    const isSaved = ref(false)
    const store = useStore()
    const route = useRoute()
    const router = useRouter()
    const params = route.params
    const quiz = params.quiz
    const user_id = localStorage.getItem('userId')
    const quiz_detail = ref([])
    const candidateList = ref([])
    const Description = ref("")
    const Reply = ref('')
    const backward = () => {
      router.go(-1)
    }
    const commentList = computed(()=>
      store.getters['module/commentList']
    )
    let isAnswered = ref(false)
    onMounted(() => {
      axios({
        method:"GET",
        url: "https://k5b206.p.ssafy.io/api/quiz/" + quiz + `/${user_id}`

      })
      .then((res) => {
        console.log(res.data)
        quiz_detail.value = res.data
        let candidates = quiz_detail.value.candidate.split('#')
        candidateList.value = candidates
      })
    })
    console.log(quiz_detail.value, 'cadi')
    // const candidateList = quiz_detail.candidate.split('#')

    const ChooseAnswer = (name, answer, id) => {

      // 댓글 목록 api 호출 필요
      const url = "https://k5b206.p.ssafy.io/api/quiz/" + id + `/${user_id}`
      axios.get(url)
      .then((res) => {
        console.log(res.data, '댓글작성')
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

    const EnrollReply = (id) => {
      const userId = localStorage.getItem('userId')
      const url = `https://k5b206.p.ssafy.io/api/quiz/${id}`
      const params = {
        content: Reply.value,
        user_id: userId,
      }
      axios.post(url, params)
      .then((res) => {
        console.log(quiz, 'choose')
        ChooseAnswer(null, null, quiz)
      })
      Reply.value = ''
    }
    // const SaveQuiz = (id) => {
    //   isSaved.value = !isSaved.value
    //   console.log(isSaved.value)
    //   const url = "https://k5b206.p.ssafy.io/api/quiz/" + id + '/myquiz'
    //   const params = {
    //       quizcheck: isSaved.value,
    //       quizlike : isSaved.value,
    //       user_id : parseInt(localStorage.getItem('userId'))
    //     }
    //   axios.post(url, params)
    //   // store.dispatch('module/saveQuiz', {
    //   //   qc: isSaved.value,ql: isSaved.value,ui: parseInt(localStorage.getItem('userId')),qi:id
    //   // })
    //   .then((res) => {
    //     // console.log(res.data, '댓글작성완료')
    //   })
    //   .catch((err) => {
    //     console.log(err)
    //   })
    // }
    return {
      params,
      quiz_detail,
      isAnswered,
      candidateList,
      commentList,
      Description,
      Reply,
      dense: ref(false),
      isSaved,
      ChooseAnswer,
      EnrollReply,
      backward,
      // SaveQuiz
    }
  }
}
</script>

<style scoped>
.zzim-quiz{
  display: flex;
  justify-content: center;
  align-items: center;
  color: #3B5E8C;
  font-family: 'GowunDodum-Regular';


}
.zzim-box {
  margin: 1rem;
  border: 1px black solid;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  height: 32rem;
  border: 1px solid pink;
  border-radius: 10px;
  overflow: scroll;
  font-family: 'GowunDodum-Regular';
  /* padding-top: 3rem; */
}

.zzim-box::-webkit-scrollbar{ display:none; }

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
  border: 1px solid pink;

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
  background-color: pink;
  margin: 0.5rem;
  align-items: center;
  align-content: center;
  border-radius: 0.5rem;
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
  padding-top: 3rem;
  font-size: 1.5rem;

}

.question{
  display: flex;
  width: 15rem;
  flex-wrap: wrap;
  margin-top: 1rem;
  justify-content: center;
  padding: 0.5rem;
  font: 1rem 'GowunDodum-Regular';
}
.subject{
  font-size: 1.3rem ;
  font-weight: bold;

}
.quiz-dan{
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  align-items: flex-start;
  width: 13.8rem;
  margin-top: 3rem;
  font: 1.3em;
  font-family: 'GowunDodum-Regular';
}
.quiz-dan i {
  font-size: 1.3rem;
  margin: auto 0 auto 0;

}
</style>

<template>
  <q-responsive :ratio="6/13" class="col" style="max-width: 420px;">
    <div class="main_container">
      <router-view />
    </div>
  </q-responsive>
  <div class="navbar">
    <div class="nav-btn"><button @click="GoQuiz">퀴즈</button></div>
    <div class="nav-btn"><button @click="GoDiary">일기</button></div>
    <div class="nav-btn"><button @click="GoHome">홈</button></div>
    <div class="nav-btn"><button @click="GoInfo">정보</button></div>
    <div class="nav-btn"><button @click="GoMy">MY</button></div>
  </div>
    <!-- 로그인이 완료되면 ''을 받아 afterLogin의 값을 바꿔 화면에 navbar을 뿌려준다 -->



</template>

<script>
import { defineComponent, ref } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'MainLayout',

  setup () {
    const afterLogin = ref(true)
    const router = useRouter()
    const GoQuiz = () => {
      router.push('/quiz')
    }
    const GoDiary = () => {
      router.push('/diary')
    }
    const GoHome = () => {
      router.push('/home')
    }
    const GoInfo = () => {
      router.push('/info')
    }
    const GoMy = () => {
      router.push('/my')
    }




    return {
      afterLogin,
      GoDiary,
      GoHome,
      GoInfo,
      GoMy,
      GoQuiz
    }
  }
})
</script>
<style scoped>
.main_container{
  /* background-color: silver; */
  border:1px solid black;
}
.navbar{
  position: fixed;
  width: 100%;
  height: 8%;
  bottom:0px;
  display: flex;
  flex-direction: row;
  z-index: 990;
  max-width: 420px;
}
.nav-btn{
  width: 20%;

}

.nav-btn button{
  background-color: white;
  border: none;
  width: 100%;
  height: 100%;

}



</style>

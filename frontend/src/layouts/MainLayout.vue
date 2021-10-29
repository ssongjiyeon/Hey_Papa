<template>
  <div>
    <router-view />
    <div class="navbar">
      <div class="nav-btn"><button @click="GoQuiz">퀴즈</button></div>
      <div class="nav-btn"><button @click="GoMap">지도</button></div>
      <div class="nav-btn"><button @click="GoHome">홈</button></div>
      <div class="nav-btn"><button @click="GoInfo">정보</button></div>
      <div class="nav-btn"><button @click="GoMy">MY</button></div>
    </div>

  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'MainLayout',

  setup () {
    const router = useRouter()
    const GoQuiz = () => {
      router.push('/quiz')
    }
    const GoMap = () => {
      router.push('/map')
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
      GoMap,
      GoHome,
      GoInfo,
      GoMy,
      GoQuiz
    }
  }
})
</script>
<style scoped>
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

.navbar {
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

<template>
  <div>
    <router-view />
    <div class="navbar">
      <div class="nav-btn"><button @click="GoQuiz">
        <div class="btn-contain">
          <q-icon name="quiz" size="md" v-bind:class="{'main-color':page==1}"/><span>퀴즈</span></div></button></div>
      <div class="nav-btn"><button @click="GoMap">
        <div class="btn-contain">
          <q-icon name="place" size="md" v-bind:class="{'main-color':page==2}"/><span>지도</span></div></button></div>
      <div class="nav-btn"><button @click="GoHome">
        <div class="btn-contain">
          <q-icon name="home" size="md" v-bind:class="{'main-color':page==3}"/><span>홈</span></div></button></div>
      <div class="nav-btn"><button @click="GoInfo">
        <div class="btn-contain">
          <q-icon name="info" size="md" v-bind:class="{'main-color':page==4}"/><span>정보</span></div></button></div>
      <div class="nav-btn"><button @click="GoMy">
        <div class="btn-contain">
          <q-icon name="account_circle" size="md" v-bind:class="{'main-color':page==5}"/><span>마이</span></div></button></div>
    </div>

  </div>
</template>

<script>
import { defineComponent,computed } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
export default defineComponent({
  name: 'MainLayout',

  setup () {
    const store = useStore()
    const router = useRouter()
    const page = computed(()=> store.getters['module/getPage'])
    const GoQuiz = () => {
      store.commit('module/setPage', 1)
      router.push('/quiz')
    }
    const GoMap = () => {
      store.commit('module/setPage', 2)
      router.push('/map')
    }
    const GoHome = () => {
      store.commit('module/setPage', 3)
      store.dispatch('module/allArticle',localStorage.getItem('userId')).then((res)=>{
        store.commit('module/setAllarticle', res.data)
        router.push('/home')
      })
    }
    const GoInfo = () => {
      store.commit('module/setPage', 4)
      router.push('/info')
    }
    const GoMy = () => {
      store.commit('module/setPage', 5)
      router.push('/my')
    }
    return {
      page,
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
.main-color{
  color:rgb(235,137,181);
}
.navbar{
  position: fixed;
  width: 100%;
  height: 10%;
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
.btn-contain{
  display:flex;
  flex-direction:column;
  align-items:center;
}
</style>

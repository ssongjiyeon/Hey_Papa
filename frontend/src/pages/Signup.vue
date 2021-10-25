<template>
  <div>
    <q-btn class="back_btn" @click="goLogin" round style="background:rgb(86,86,239); color:white; margin:20px 0px 0px 20px;" icon="arrow_back" />
    <div class="signup_container">
      <h4 style="font-size:25px; margin-top:50px;">회원 가입</h4>
      <div style="width:290px; margin:30px 0px 40px 0px;">
        <q-input class="login_id" label="ID" type="email"/>
      </div>
      <div style="width:290px; margin-bottom:40px;">
        <q-input label="PASSWORD" type="password"/>
      </div>
      <div style="width:290px; margin-bottom:40px;">
        <q-input label="CONFIRM PASSWORD" type="password"/>
      </div>
      <q-btn unelevated rounded color="primary" label="로그인 하기" style="width:300px; margin-top:100px;"/>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
  setup(){
    const router = useRouter()
    function goLogin(){
      router.push('login')
    }
    return {
      goLogin
    }
  }
}
</script>

<style>
.signup_container{
  display: flex;
  flex-direction: column;
  align-items:center;
  /* width:300px; */
}
</style>
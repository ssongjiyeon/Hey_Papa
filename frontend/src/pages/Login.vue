<template>
  <div class="login_container">
    <h3 style="margin-top:160px;">Hey PaPa !</h3>
    <div>
      <div class="id_wrap">
        <q-input label="ID" type="email"/>
      </div>
      <div style="margin-bottom:70px;">
        <q-input label="PASSWORD" type="password"/>
      </div>
      <q-btn unelevated rounded color="primary" label="로그인 하기" style="width:300px;"/>
      <div class="plus">
        <div>
          <span>이메일로 가입하시겠어요?</span>
          <span @click="goSignup" style="font-weight:bold; margin-left:10px;" class="text-primary">회원가입</span>
        </div>
        <div style="margin-top:20px;">
          <span>비밀번호를 잊으셨나요?</span>
          <span style="font-weight:bold; margin-left:10px;" class="text-primary">PW찾기</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
export default {
  setup(){
    const router = useRouter()

    function goSignup(){
      router.push('signup')
    }
    return{
      goSignup
    } 
  }
}
</script>

<style scoped>
.login_container{
  display: flex;
  flex-direction: column;
  align-items:center;
}
.id_wrap{
  width:300px;
  margin:40px 0px 40px 0px;
}
.plus{
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top:50px;
}
</style>
<template>
  <div class="login_container">
    <h3 style="margin-top:160px;">Hey PaPa !</h3>
    <div>
      <div class="id_wrap">
        <q-input label="ID" type="email"
          v-model="form.email"
          lazy-rules
            :rules="[
            val => !!val || 'ID를 입력해주세요.',
            checkId
            ]"
        />
      </div>
      <div style="margin-top:-30px; margin-bottom:70px;">
        <q-input label="PASSWORD" type="password" v-model="form.password"
          lazy-rules
          :rules="[
            val => val && val.length > 0 || '비밀번호를 입력해주세요.',
            checkPassWord
          ]"/>
      </div>
      <q-btn @click="goMain" unelevated rounded color="primary" label="로그인 하기" style="width:300px;"/>
      <div class="plus">
        <div>
          <span>이메일로 가입하시겠어요?</span>
          <span @click="goSignup" style="font-weight:bold; margin-left:10px;" class="text-primary">회원가입</span>
        </div>
        <div style="margin-top:20px;">
          <span>비밀번호를 잊으셨나요?</span>
          <span @click="pwdMode = true" style="font-weight:bold; margin-left:10px;" class="text-primary">PW찾기</span>
        </div>
      </div>
      <q-dialog v-model="pwdMode">
        <q-card style="background: #E3E7EA">
            <q-card-section class="row items-center">
                <div class="find_form">
                  <div style="font-size:25px;">비밀번호 찾기</div>
                  <div style="font-size:18px; padding-left:10px; padding-top:10px;">이메일로 임시 비밀번호를 발급해드립니다.</div>
                  <q-input v-model="find.email" style="width:90%; margin-left:10px;" label="e-mail" />
                </div>
            </q-card-section>
            <q-card-actions>
                <div style="display:inline-block; margin:0 auto;">
                  <q-btn flat  @click="downPwd" class="find_btn" label="취소" color="black" v-close-popup />
                  <q-btn flat  @click="findPwd" class="find_btn" label="발송" color="black"/>
                </div>
            </q-card-actions>
        </q-card>
      </q-dialog>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { reactive, ref, computed } from 'vue'
import { useStore } from 'vuex'

export default {
  setup(){
    const router = useRouter()  
    const store = useStore()
    const pwdMode = ref(false)
    const form = reactive({
      email: '',
      password: '',
    })
    const find = reactive({
        email: ''
    })
    const getuser = computed(()=> store.getters['module/getUser'])
    console.log(getuser.value)
    // 아이디 유효성 검사 
    function checkId (val) {
        const reg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
        return (reg.test(val) || '이메일 형식이 잘못되었습니다.')
    }
    // 비밀번호 유효성 검사
    function checkPassWord (val) {
        const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/
        return (reg.test(val) || '올바른 형태의 8자리 이상 비밀번호를 입력해주세요.')
    }
    // 모달 false
    const downPwd = function() {
        pwdMode.value = false
    }
    // 이메일 발송
    const findPwd = function() {
        store.dispatch('module/findPwd', { email: find.email })
            .then(() => {
                Swal.fire({
                    icon: 'success',
                    title: '<span style="font-size:25px;">이메일을 발송했습니다.</span>',
                    confirmButtonColor: '#primary',
                    confirmButtonText: '<span style="font-size:18px;">확인</span>'
                })
                pwdMode.value = false
            })
    }
    function goSignup(){
      router.push('signup')
    }
    function goMain(){
      router.push('home')
    }
    return{
      form,
      pwdMode,
      find,
      goSignup,
      goMain,
      checkId,
      checkPassWord,
      downPwd,
      findPwd,
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
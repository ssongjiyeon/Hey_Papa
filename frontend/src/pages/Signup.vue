<template>
  <div>
    <q-btn class="back_btn" @click="goLogin" round style="background:rgb(86,86,239); color:white; margin:20px 0px 0px 20px;" icon="arrow_back" />
    <div class="signup_container">
      <h4 style="font-size:25px; margin-top:120px;">회원 가입</h4>
      <div style="width:290px; margin:30px 0px 40px 0px;">
        <q-input label="ID" type="email"
          v-model="form.email"
          lazy-rules
          :rules="[
          val => !!val || '필수입력항목 입니다.',
          checkEmail
          ]"
        />
        <q-btn @click="duplicateEmail" rounded class="duplicate_button" label="중복 확인"/>
      </div>
      <div style="width:290px; margin-top:-50px; margin-bottom:20px;">
        <q-input label="PASSWORD" type="password" v-model="form.password"
        lazy-rules
        :rules="[
          val => val && val.length >= 8 || '8자리 이상 입력해주세요.',
          checkPassWord]"
      />
      </div>
      <div style="width:290px; margin-bottom:40px;">
        <q-input label="CONFIRM PASSWORD" type="password" v-model="form.passwordconfirmation"
          lazy-rules
          :rules="[
            val => val && val.length > 0 || '필수입력항목 입니다.',
            checkPassWordConfirmation
          ]"/>
      </div>
      <q-btn @click="goBaby" unelevated rounded color="primary" label="계속" style="width:300px; margin-top:100px;"/>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { reactive } from 'vue'
export default {
  setup(){
    const router = useRouter()
    const Swal = require('sweetalert2')
    const form = reactive({
      email: '',
      password: '',
      passwordconfirmation: '',
    })
    let valid = {
      email: false,
      password: false,
      passwordconfirmation: false,
    }
    function checkEmail (val) {
      const reg = /^[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
      if (reg.test(val)==true) valid.email = true
      else valid.email = false
      return (reg.test(val)||'이메일 형식이 잘못되었습니다.')
    }
    function duplicateEmail() {
      if(form.email==''){
        Swal.fire({
              icon: 'error',
              title: '<span style="font-size:25px;">이메일을 입력해주세요.</span>',
              confirmButtonColor: '#ce1919',
              confirmButtonText: '<span style="font-size:18px;">확인</span>'
        })
        return 
      }
      // store.dispatch('module/checkEmail', { email: form.email })
      // .then((res) => {
      //   if (res.data.data==true){
      //     Swal.fire({
      //         icon: 'success',
      //         title: '<span style="font-size:25px;">사용 가능한 이메일 입니다.</span>',
      //         confirmButtonColor: "rgb(86,86,239)",
      //         confirmButtonText: '<span style="font-size:18px;">확인</span>'
      //     })
      //   }
      //   else{
      //     Swal.fire({
      //         icon: 'error',
      //         title: '<span style="font-size:25px;">중복된 이메일 입니다.</span>',
      //         confirmButtonColor: '#ce1919',
      //         confirmButtonText: '<span style="font-size:18px;">확인</span>'
      //     })
      //   }
      // })
    }
    function checkPassWord (val) {
      const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/
      if (reg.test(val)==true) valid.password = true
      else valid.password = false
      return (reg.test(val)||'영어, 숫자, 특수문자 포함 8자리 이상이어야 합니다.')
    }
    function checkPassWordConfirmation (val) {
      if (form.password != val){
        valid.passwordconfirmation = false
        return ('비밀번호가 일치하지 않습니다.')
      }
      else{
        valid.passwordconfirmation = true
      }
    }
    function goLogin(){
      router.push('login')
    }
    function goBaby(){
      router.push('baby')
    }
    return {
      form,
      checkEmail,
      duplicateEmail,
      checkPassWord,
      checkPassWordConfirmation,
      goLogin,
      goBaby
    }
  }
}
</script>

<style scoped>
.signup_container{
  display: flex;
  flex-direction: column;
  align-items:center;
}
.duplicate_button{
 float:right;
 top:-60px;
 font-size:12px;
 background-color: rgb(86,86,239);
 color:white;
}
</style>
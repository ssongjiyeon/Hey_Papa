<template>
  <div>
    <q-btn class="back_btn" @click="goLogin" round style="background:rgb(86,86,239); color:white; margin:20px 0px 0px 20px;" icon="arrow_back" />
    <div class="signup_container">
      <h4 style="font-size:25px; margin-top:70px;">회원 가입</h4>
      <div style="width:290px; margin:30px 0px 30px 0px;">
        <q-input label="아이디" type="email"
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
        <q-input label="비밀번호" type="password" v-model="form.password"
        lazy-rules
        :rules="[
          val => val && val.length >= 8 || '8자리 이상 입력해주세요.',
          checkPassWord]"
      />
      </div>
      <div style="width:290px; margin-bottom:20px;">
        <q-input label="비밀번호확인" type="password" v-model="form.passwordconfirmation"
          lazy-rules
          :rules="[
            val => val && val.length > 0 || '필수입력항목 입니다.',
            checkPassWordConfirmation
          ]"/>
      </div>
      <div style="width:290px; margin-bottom:40px;">
        <q-input label="지역 (예:유성구 (시/군/구))" type="text" v-model="form.region"
          lazy-rules
          :rules="[
            val => val && val.length > 0 || '필수입력항목 입니다.',
            checkRegion
          ]"/>
      </div>
      <q-btn @click="goBaby" unelevated rounded color="primary" label="계속" style="width:300px; margin-top:100px;"/>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { reactive } from 'vue'
import { useStore } from 'vuex'
export default {
  setup(){
    const router = useRouter()
    const store = useStore()
    const Swal = require('sweetalert2')
    const form = reactive({
      email: '',
      password: '',
      passwordconfirmation: '',
      region: '',
    })
    let valid = {
      email: false,
      password: false,
      passwordconfirmation: false,
      region: false,
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
      store.dispatch('module/checkEmail',  {email:form.email} )
      .then(() => {
        Swal.fire({
            icon: 'success',
            title: '<span style="font-size:25px;">사용 가능한 이메일 입니다.</span>',
            confirmButtonColor: "rgb(86,86,239)",
            confirmButtonText: '<span style="font-size:18px;">확인</span>'
        })
      })
      .catch(()=>{
        Swal.fire({
              icon: 'error',
              title: '<span style="font-size:25px;">중복된 이메일 입니다.</span>',
              confirmButtonColor: '#ce1919',
              confirmButtonText: '<span style="font-size:18px;">확인</span>'
          })
      })
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
    function checkRegion(val){
      if (val.length>=2){
        valid.region = true
      }
      else{
        valid.region = true
      }
    }
    function goLogin(){
      router.push('/')
    }
    function goBaby(){
      if (valid.email == false || valid.password == false || valid.passwordconfirmation == false || valid.region == false){
        Swal.fire({
          icon: 'error',
          title: '<span style="font-size:25px;">항목들을 모두 입력해주세요.</span>',
          confirmButtonColor: '#ce1919',
          confirmButtonText: '<span style="font-size:18px;">확인</span>'
        })
        return
      }
      const user = {
        id:form.email,
        password:form.password,
        region:form.region,
      }
      store.commit('module/setUser', user)
      router.push('baby')
    }
    return {
      form,
      checkEmail,
      duplicateEmail,
      checkPassWord,
      checkPassWordConfirmation,
      checkRegion,
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
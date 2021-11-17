<template>
  <div>
    <q-btn class="back_btn" @click="goSignup" round style="background:rgb(235,137,181); color:white; margin:30px 0px 0px 20px;" icon="arrow_back" />
    <div class="baby_container">
      <h4 style="font-size:25px; margin-top:50px;">우리 아기 태명과 생일은?</h4>
      <div style="width:290px; margin:30px 0px 20px 0px;">
        <q-input label="태명을 입력해주세요." type="email"
          v-model="form.babyname"
          color="pink-3"
          lazy-rules
          :rules="[
          val => !!val || '필수입력항목 입니다.',
          ]"
        />
      </div>
      <div style="width:290px; margin-top:-20px;">
        <q-input label="생년월일 8자리 (예: 2022-08-23)" type="email"
          v-model="form.birthday"
          color="pink-3"
          lazy-rules
          :rules="[
          val => !!val || '필수입력항목 입니다.',
          checkBirth
          ]"
        />
      </div>
      <div style="width:290px;">
        <q-input label="몇 주차 인지 숫자만 입력해주세요." type="number"
          min="0"
          max="42"
          color="pink-3"
          v-model="form.week"
          lazy-rules
          :rules="[
          val => !!val || '필수입력항목 입니다.',
          ]"
        />
      </div>
      <q-btn @click="goLogin" unelevated rounded label="완료" style="color: white; width:300px; margin-top:50px; background:rgb(235,137,181);"/>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { reactive,computed } from 'vue'
export default {
   setup(){
    const Swal = require('sweetalert2')
    const router = useRouter()
    const store = useStore()
    const form = reactive({
      babyname: '',
      birthday: '',
      week:'',
    })
    const getuser = computed(()=> store.getters['module/getUser'])
    function checkBirth (val) {
      const reg = /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/
      return (reg.test(val)|| '형식을 맞춰주세요.')
    }
    function goSignup(){
      router.push('signup')
    }
    function goLogin(){
      if (form.babyname == '' || form.birthday == '' || form.week == ''){
        Swal.fire({
          icon: 'error',
          title: '<span style="font-size:25px;">항목들을 모두 입력해주세요.</span>',
          confirmButtonColor: '#ce1919',
          confirmButtonText: '<span style="font-size:18px;">확인</span>'
        })
        return
      }
      const user = {
          dday:form.birthday,
          email:getuser.value.id,
          nickname:form.babyname,
          password:getuser.value.password,
          region:getuser.value.region,
          week:form.week,
        }
      store.dispatch('module/signUp',user)
        .then(function (res){
          if(res.data.statusCode == 200){
            Swal.fire({
              icon: 'success',
              title: '<span style="font-size:25px;">회원가입을 성공했습니다.</span>',
              confirmButtonColor: '#primary',
              confirmButtonText: '<span style="font-size:18px;">확인</span>'
            })
            store.commit('module/setUser', user)
            router.push('/')
          }
        })
    }
    return {
      form,
      checkBirth,
      goSignup,
      goLogin,
    }
  }
}
</script>

<style scoped>
.baby_container{
  display: flex;
  flex-direction: column;
  align-items:center;
}
</style>

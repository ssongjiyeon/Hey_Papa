<template>
  <div>
    <q-btn class="back_btn" @click="goMy" round style="background:rgb(86,86,239); color:white; margin:20px 0px 0px 20px;" icon="arrow_back" />
    <div class="baby_container">
      <h4 style="font-size:25px; margin-top:50px;">회원정보수정</h4>
      <div style="width:290px; margin-top:20px;">
        <q-input label="비밀번호를 입력해주세요." type="password" v-model="form.password"
        lazy-rules
        :rules="[
          val => val && val.length >= 8 || '8자리 이상 입력해주세요.',
          checkPassWord]"
      />
      </div>
      <div style="width:290px; margin-top:0px;">
        <q-input label="태명을 입력해주세요." type="email" v-model="form.babyname"
          lazy-rules
          :rules="[
          val => !!val || '필수입력항목 입니다.',
          ]"
        />
      </div>
      <div style="width:290px; margin-top:0px;">
        <q-input label="생년월일 8자리 (예: 20220823)" type="email" v-model="form.birthday"
          lazy-rules
          :rules="[
          val => !!val || '필수입력항목 입니다.',
          checkBirth
          ]"
        />
      </div>
      <div style="width:290px; margin-top:0px;">
        <q-input label="지역 (예:유성구 (시/군/구))" type="text" v-model="form.region"
        lazy-rules
        :rules="[
          val => val && val.length >= 8 || '8자리 이상 입력해주세요.',
          checkPassWord]"
      />
      </div>
      <div style="width:290px; margin-top:0px;">
        <q-input label="몇 주차 인지 숫자만 입력해주세요." type="number" v-model="form.week"
        lazy-rules
        :rules="[
          val => val && val.length >= 8 || '8자리 이상 입력해주세요.',
          checkPassWord]"
      />
      </div>
      <q-btn @click="Complete" unelevated rounded color="primary" label="완료" style="width:300px; margin-top:30px;"/>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { reactive,computed } from 'vue'
import { useStore } from 'vuex'
export default {
   setup(){
    const router = useRouter()
    const store = useStore()
    const form = reactive({
      password: '',
      babyname: '',
      birthday: '',
      region: '',
      week: '',
    })
    const user = computed(()=> store.getters['module/getUser'])
    const pwd = computed(()=> store.getters['module/getLoginpwd'])
    console.log(pwd,'pwd')
    // form.password = pwd
    // form.babyname = user.value.nickname
    // form.birthday = user.value.dday
    // form.region = user.value.region
    // form.week = user.value.week
    // console.log(form,'@@')

    function checkBirth (val) {
        const reg = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/
        return (reg.test(val)|| '숫자 8개로 입력해주세요.')
    }
    function checkPassWord (val) {
      const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/
      if (reg.test(val)==true) valid.password = true
      else valid.password = false
      return (reg.test(val)||'영어, 숫자, 특수문자 포함 8자리 이상이어야 합니다.')
    }
    function goMy(){
      router.push('my')
    }
    function Complete(){
      router.push('my')
    }
    return {
      form,
      checkPassWord,
      checkBirth,
      goMy,
      Complete,
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
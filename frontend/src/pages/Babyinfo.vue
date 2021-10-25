<template>
  <div>
    <q-btn class="back_btn" @click="goSignup" round style="background:rgb(86,86,239); color:white; margin:20px 0px 0px 20px;" icon="arrow_back" />
    <div class="baby_container">
      <h4 style="font-size:25px; margin-top:100px;">우리 아기 태명과 생일은?</h4>
      <div style="width:290px; margin:30px 0px 40px 0px;">
        <q-input label="태명을 입력해주세요." type="email"
          v-model="form.babyname"
          lazy-rules
          :rules="[
          val => !!val || '필수입력항목 입니다.',
          ]"
        />
      </div>
      <div style="width:290px; margin-top:-20px;">
        <q-input label="생년월일 8자리 (예: 20220823)" type="email"
          v-model="form.birthday"
          lazy-rules
          :rules="[
          val => !!val || '필수입력항목 입니다.',
          checkBirth
          ]"
        />
      </div>
      <q-btn @click="goBaby" unelevated rounded color="primary" label="완료" style="width:300px; margin-top:100px;"/>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { reactive } from 'vue'
export default {
   setup(){
    const router = useRouter()
    const form = reactive({
      babyname: '',
      birthday: '',
    })
    function checkBirth (val) {
        const reg = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/
        return (reg.test(val)|| '숫자 8개로 입력해주세요.')
    }
    function goSignup(){
      router.push('signup')
    }
    return {
      form,
      checkBirth,
      goSignup,
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
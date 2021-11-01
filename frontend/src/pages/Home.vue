<template>
  <div>
    <button @click="goWrite">글쓰기</button>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
export default {
  setup(){
    const router = useRouter()
    function goWrite(){
      router.push('create')
    }
    return{
      goWrite
    }
  }
}
</script>

<style>

</style>

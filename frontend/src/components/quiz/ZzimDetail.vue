<template>
{{params}}
</template>

<script>
import { useRoute } from 'vue-router'
export default {
  setup(){
    const route = useRoute()
    const params = route.params


    return {
      params
    }
  }
}
</script>

<style>

</style>

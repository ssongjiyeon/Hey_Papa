<template>
  <div class="app_container">
    <router-view />

  </div>
</template>
<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'App'
})
</script>
<style scoped>
.app_container{
  display:flex;
  flex-direction:column;
  justify-content: center;
}

</style>

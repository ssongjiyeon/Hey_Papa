<template>
  <div class="container">
    <router-view />
  </div>
</template>

<script>
// import EssentialLink from 'components/EssentialLink.vue'

import { defineComponent } from 'vue'

export default defineComponent({
  name: 'MainLayout',

  setup () {
    return {
    }
  }
})
</script>
<style scoped>
.container{
  width:390px;
  height:844px;
  border: 1px solid black;
}
</style>
<template>
  <div>
    컨테이너
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

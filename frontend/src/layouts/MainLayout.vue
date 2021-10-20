<template>
  <q-responsive :ratio="6/13" class="col" style="max-width: 390px;">
    <div class="container">
      <router-view />
    </div>
  </q-responsive>
</template>

<script>
import { defineComponent } from 'vue'
export default defineComponent({
})
</script>
<style scoped>
.container{
  background-color: silver;
}
</style>
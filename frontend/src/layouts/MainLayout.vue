<template>
  <q-responsive :ratio="6/13" class="col" style="max-width: 420px;">
    <div class="main_container">
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
.main_container{
  /* background-color: silver; */
  border:1px solid black;
}
</style>
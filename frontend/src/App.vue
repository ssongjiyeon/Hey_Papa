<template>
  <div style="display:flex; justify-content: center;">
    <q-responsive :ratio="6/13" class="col" style="max-width: 420px;">
      <div class="app_container">
        <router-view />
      </div>
    </q-responsive>
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
  border:1px solid black;
}

</style>
